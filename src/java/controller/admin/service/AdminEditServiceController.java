/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.service;

import dao.ISerivceDAO;
import dao.impl.ServiceDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Services;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AdminEditServiceController", urlPatterns = {"/admin/edit/service"})
public class AdminEditServiceController extends HttpServlet {

    private final ISerivceDAO serviceDAO;

    public AdminEditServiceController() {
        this.serviceDAO = new ServiceDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int serviceId = Integer.parseInt(request.getParameter("id"));
        String serviceName = request.getParameter("serviceName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        String image = request.getParameter("image");
        String icon = request.getParameter("icon");

        if (serviceDAO.existsUpdateByServiceName(serviceName, serviceId)) {
            request.setAttribute("errorMessage", "Service name already exists. Please choose a different name.");
            request.getRequestDispatcher("/webapp/views/admin/service.jsp").forward(request, response);
        } else {
            Services updatedService = new Services.Builder()
                    .setId(serviceId)
                    .setServiceName(serviceName)
                    .setDescription(description)
                    .setPrice(price)
                    .setDuration(duration)
                    .setImage(image)
                    .setIcon(icon)
                    .build();

            serviceDAO.updateService(updatedService);
            response.sendRedirect(request.getContextPath() + "/admin/service?edit=true");
        }
    }
}
