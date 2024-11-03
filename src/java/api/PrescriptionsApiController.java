/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import com.google.gson.Gson;
import dao.IAppointmentDAO;
import dao.IMedicalRecordDAO;
import dao.INotificationDAO;
import dao.IPrescriptionsDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.MedicalRecordDAOImpl;
import dao.impl.NotificationDAOImpl;
import dao.impl.PrescriptionsDAOImpl;
import dto.request.NotificationRequest;
import dto.request.PrescriptionsRequest;
import dto.response.PrescriptionsResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import mapper.IPrescriptionsMapper;
import mapper.impl.PrescriptionsMapperImpl;
import models.Appointments;
import models.MedicalRecords;
import models.Prescriptions;

@WebServlet(name = "PrescriptionsApiServlet", urlPatterns = {"/api/prescriptions"})
public class PrescriptionsApiController extends HttpServlet {

    private final IPrescriptionsDAO prescriptionsDAO;
    private final INotificationDAO notificationDAO;
    private final IMedicalRecordDAO medicalRecordDAO;
    private final IAppointmentDAO appointmentDAO;
    private final IPrescriptionsMapper prescriptionsMapper;
    
    public PrescriptionsApiController() {
        this.prescriptionsDAO = new PrescriptionsDAOImpl();
        this.notificationDAO = new NotificationDAOImpl();
        this.medicalRecordDAO = new MedicalRecordDAOImpl();
        this.appointmentDAO = new AppointmentDAOImpl();
        this.prescriptionsMapper = new PrescriptionsMapperImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String appointmentIdParam = request.getParameter("appointmentId");
        List<Prescriptions> prescriptionsList = new ArrayList<>();

        if (appointmentIdParam != null) {
            int appointmentId = Integer.parseInt(appointmentIdParam);
            prescriptionsList = prescriptionsDAO.getPrescriptionsByAppointmentId(appointmentId);
        }
        List<PrescriptionsResponse>  prescriptionsListRes = this.prescriptionsMapper.toListPrescriptionsResponse(prescriptionsList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(prescriptionsListRes));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        PrescriptionsRequest prescriptionsRequest = gson.fromJson(reader, PrescriptionsRequest.class);
        this.prescriptionsDAO.addPrescriptions(prescriptionsRequest);
        MedicalRecords medicalRecords = this.medicalRecordDAO.getMedicalRecord(prescriptionsRequest.getRecordId());
        Appointments appointments = this.appointmentDAO.getAppointmentsById(medicalRecords.getAppointmentId());
        String message = String.format("Your appointment with ID %d has been prescriptions successfully. Please check your activity log for more details.", appointments.getId());
        this.notificationDAO.addNotification(new NotificationRequest.Builder()
                .recipientUserId(appointments.getCustomerId())
                .message(message)
                .build());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(true));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                prescriptionsDAO.removeByPrescriptionsId(id);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Successfully deleted
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid prescription ID");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Prescription ID is required");
        }
    }

}
