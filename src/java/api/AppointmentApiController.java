/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import com.google.gson.Gson;
import common.constants.SystemConstant;
import dao.IAppointmentDAO;
import dao.INotificationDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.NotificationDAOImpl;
import dto.request.NotificationRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AppointmentApiServlet", urlPatterns = {"/api/appointment/action"})
public class AppointmentApiController extends HttpServlet {

    private final IAppointmentDAO appointmentDAO;
    private final INotificationDAO notificationDAO;

    public AppointmentApiController() {
        this.appointmentDAO = new AppointmentDAOImpl();
        this.notificationDAO = new NotificationDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        appointmentDAO.updateStatusById(appointmentId, SystemConstant.CONFIRMED);
        String message = String.format("Your appointment with ID %d has been confirmed.", appointmentId);
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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        appointmentDAO.updateStatusById(appointmentId, SystemConstant.CANCELLED);
        String message = String.format("Your appointment with ID %d has been canceled.", appointmentId);
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
