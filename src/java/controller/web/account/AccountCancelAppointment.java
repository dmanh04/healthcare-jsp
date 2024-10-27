/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.web.account;

import common.constants.SystemConstant;
import dao.IAppointmentDAO;
import dao.impl.AppointmentDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AccountCancelAppointment", urlPatterns = {"/account/cancel"})
public class AccountCancelAppointment extends HttpServlet {
    
    private final IAppointmentDAO appointmentDAO;
    
    public AccountCancelAppointment() {
        this.appointmentDAO = new AppointmentDAOImpl();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idCancel"));
        this.appointmentDAO.updateStatusById(id, SystemConstant.CANCELLED);
        response.sendRedirect("/Healthcare/manage-appointment");
    }
    
}
