/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ISerivceDAO;
import dao.impl.ServiceDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AdminDeleteSerivce", urlPatterns = {"/admin/delete/service"})
public class AdminDeleteSerivce extends HttpServlet {

    private ISerivceDAO serviceDAO;

    @Override
    public void init() throws ServletException {
        serviceDAO = new ServiceDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String serviceIdParam = request.getParameter("id");
        int serviceId = 0;
        if (serviceIdParam != null && !serviceIdParam.isEmpty()) {
            try {
                serviceId = Integer.parseInt(serviceIdParam);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid service ID");
                return;
            }
        }
        if (serviceId > 0) {
            serviceDAO.deleteSerivceById(serviceId);
            response.sendRedirect(request.getContextPath() + "/admin/service?remove=true&serviceId=" + serviceId);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Service ID is required");
        }
    }

}
