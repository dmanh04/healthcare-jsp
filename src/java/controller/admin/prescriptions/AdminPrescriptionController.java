/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.prescriptions;

import dao.IAppointmentDAO;
import dao.IMedicalRecordDAO;
import dao.IMedicineDAO;
import dao.IPrescriptionsDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.MedicalRecordDAOImpl;
import dao.impl.MedicineDAOImpl;
import dao.impl.PrescriptionsDAOImpl;
import dto.response.PrescriptionsResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import mapper.IPrescriptionsMapper;
import mapper.impl.PrescriptionsMapperImpl;
import models.Appointments;
import models.MedicalRecords;
import models.Medicines;
import models.Prescriptions;

@WebServlet(name = "AdminPrescriptionController", urlPatterns = {"/admin/prescription/*"})
public class AdminPrescriptionController extends HttpServlet {

    private final IMedicineDAO medicineDAO;
    private final IAppointmentDAO appointmentDAO;
    private final IMedicalRecordDAO medicalRecordDAO;
    private final IPrescriptionsDAO prescriptionsDAO;
    private final IPrescriptionsMapper prescriptionsMapper;

    public AdminPrescriptionController() {
        this.medicineDAO = new MedicineDAOImpl();
        this.appointmentDAO = new AppointmentDAOImpl();
        this.medicalRecordDAO = new MedicalRecordDAOImpl();
        this.prescriptionsDAO = new PrescriptionsDAOImpl();
        this.prescriptionsMapper = new PrescriptionsMapperImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Medicines> listMedicine = this.medicineDAO.getAllMedicine();
        request.setAttribute("listMedicine", listMedicine);
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String prescriptionIdStr = pathInfo.substring(1);
            try {
                int appointmentId = Integer.parseInt(prescriptionIdStr);
                Appointments appointments = this.appointmentDAO.getAppointmentsById(appointmentId);
                if (appointments == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Appointment ID not found.");
                    return;
                }
                MedicalRecords medicalRecords = this.medicalRecordDAO.getMedicalRecordsByAppointmentId(appointmentId);
                if (medicalRecords == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Medical record ID not found.");
                    return;
                }
                List<Prescriptions> listPre = this.prescriptionsDAO.getAllPrescriptionsByMedicalRecordId(medicalRecords.getId());
                List<PrescriptionsResponse> listPreResponse = this.prescriptionsMapper.toListPrescriptionsResponse(listPre);
                request.setAttribute("listPreResponse", listPreResponse);
                request.setAttribute("medicalRecords", medicalRecords);
                request.setAttribute("appointments", appointments);
                request.getRequestDispatcher("/webapp/views/admin/prescription.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid prescription ID.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Prescription ID not found.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
