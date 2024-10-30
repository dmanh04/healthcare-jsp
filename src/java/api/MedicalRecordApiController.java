package api;

import com.google.gson.Gson;
import common.constants.SystemConstant;
import dao.IAppointmentDAO;
import dao.IMedicalRecordDAO;
import dao.INotificationDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.MedicalRecordDAOImpl;
import dao.impl.NotificationDAOImpl;
import dto.request.MedicalRecordRequest;
import dto.request.NotificationRequest;
import dto.response.MedicalRecordResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MedicalRecordtApiServlet", urlPatterns = {"/api/record/action"})
public class MedicalRecordApiController extends HttpServlet {

    private final IAppointmentDAO appointmentDAO;
    private final IMedicalRecordDAO medicalRecordDAO;
    private final INotificationDAO notificationDAO;

    public MedicalRecordApiController() {
        this.appointmentDAO = new AppointmentDAOImpl();
        this.medicalRecordDAO = new MedicalRecordDAOImpl();
        this.notificationDAO = new NotificationDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String appointmentIdParam = request.getParameter("appointmentId");
        int appointmentId = Integer.parseInt(appointmentIdParam);
        MedicalRecordResponse medicalRecord = medicalRecordDAO.getMedicalRecordResponse(appointmentId);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        if (medicalRecord != null) {
            out.write(gson.toJson(medicalRecord));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.write(gson.toJson("Medical record not found"));
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        MedicalRecordRequest medicalRecordRequest = gson.fromJson(reader, MedicalRecordRequest.class);
        this.medicalRecordDAO.addMedicalRecord(medicalRecordRequest);
        this.appointmentDAO.updateStatusById(medicalRecordRequest.getAppointmentId(), SystemConstant.COMPLETED);
        String message = String.format("Your appointment with ID %d has been completed successfully. Please check your activity log for more details.", medicalRecordRequest.getAppointmentId());
        this.notificationDAO.addNotification(new NotificationRequest.Builder()
                .recipientUserId(medicalRecordRequest.getRecipientUserId())
                .message(message)
                .build());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(true));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int recordId = Integer.parseInt(request.getParameter("recordId"));
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String diagnosis = request.getParameter("diagnosis");
        String treatment = request.getParameter("treatment");
        MedicalRecordRequest medicalRecordRequest = new MedicalRecordRequest.Builder()
                .appointmentId(appointmentId)
                .diagnosis(diagnosis)
                .treatment(treatment)
                .recordId(recordId)
                .build();
        this.medicalRecordDAO.updateMedicalRecord(medicalRecordRequest);
        this.appointmentDAO.updateStatusById(appointmentId, SystemConstant.COMPLETED);
        String message = String.format("Your appointment with ID %d has been updated completed successfully. Please check your activity log for more details.", appointmentId);
        this.notificationDAO.addNotification(new NotificationRequest.Builder()
                .recipientUserId(userId)
                .message(message)
                .build());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(true));
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
