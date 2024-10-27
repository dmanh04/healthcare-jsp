/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.web.account;

import common.constants.SystemConstant;
import dao.IAppointmentDAO;
import dao.INotificationDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.NotificationDAOImpl;
import dto.request.NotificationRequest;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AccountCancelAppointment", urlPatterns = {"/account/cancel"})
public class AccountCancelAppointment extends HttpServlet {

    private final IAppointmentDAO appointmentDAO;
    private final INotificationDAO notificationDAO;

    public AccountCancelAppointment() {
        this.appointmentDAO = new AppointmentDAOImpl();
        this.notificationDAO = new NotificationDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idCancel"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        this.appointmentDAO.updateStatusById(id, SystemConstant.CANCELLED);
        String notificationMessage = "Dear Doctor, your patient has cancelled their appointment."
                + " Please check your schedule for any updates.";
        NotificationRequest notiDoctor = new NotificationRequest.Builder()
                .recipientUserId(doctorId)
                .message(notificationMessage)
                .build();
        this.notificationDAO.addNotification(notiDoctor);
        response.sendRedirect("/Healthcare/manage-appointment");
    }

}
