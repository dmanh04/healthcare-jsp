/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.web;

import dao.ISerivceDAO;
import dao.impl.ServiceDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Services;

@WebServlet(name = "ServiceController", urlPatterns = {"/services/*"})
public class ServiceController extends HttpServlet {
    
    private final ISerivceDAO serivceDAO;

    public ServiceController() {
        this.serivceDAO = new ServiceDAOImpl();
    }
    
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String serviceId = pathInfo.substring(1); 
            try {
                int Id = Integer.parseInt(serviceId); 
                Services service = serivceDAO.findServiceById(Id);
                if (service != null) {
                    request.setAttribute("service", service);
                    request.getRequestDispatcher("/webapp/views/web/service.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Doctor not found");
                }

            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Doctor ID format");
            }
        } else {
            List<Services> list = serivceDAO.getAllSerivce();
            request.setAttribute("data", list);
            request.getRequestDispatcher("webapp/views/web/list-services.jsp").forward(request, response);
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
