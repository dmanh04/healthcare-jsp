/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.web.account;

import common.constants.SystemConstant;
import dao.IAppointmentDAO;
import dao.IUserDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.UserDAOImpl;
import dto.response.AppointmentResponse;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import mapper.IAppointmentMapper;
import mapper.impl.AppointmentMapperImpl;
import models.Appointments;
import models.User;


@WebServlet(name = "AccountManageAppointmentController", urlPatterns = {"/manage-appointment"})
public class AccountManageAppointmentController extends HttpServlet {

    private final IUserDAO userDAO;
    private final IAppointmentDAO appointmentDAO;
    private final IAppointmentMapper appointmentMapper;

    public AccountManageAppointmentController() {
        this.userDAO = new UserDAOImpl();
        this.appointmentDAO = new AppointmentDAOImpl();
        this.appointmentMapper = new AppointmentMapperImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute(SystemConstant.USER_CURRENT);
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        } else {
            User userCurrent = userDAO.findByUsername(currentUser);
            List<Appointments> listAppointmentRes = this.appointmentDAO.getListAppointmentsByCustomerId(userCurrent.getId());
            List<AppointmentResponse> listAppointment = this.appointmentMapper.toAppointmentResponse(listAppointmentRes);
            request.setAttribute("user", userCurrent);
            request.setAttribute("listAppointment", listAppointment);
            request.getRequestDispatcher("webapp/views/web/manage-appointment.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
