/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.web;

import dao.IDoctorDAO;
import dao.impl.DoctorDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.User;

/**
 *
 * @author luong
 */
@WebServlet(name = "DoctorController", urlPatterns = {"/doctors/*"})
public class DoctorController extends HttpServlet {
    
    private final IDoctorDAO doctorDAO;

    public DoctorController() {
        this.doctorDAO = new DoctorDAOImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo(); 
        if (pathInfo != null && pathInfo.length() > 1) {
            String doctorIdStr = pathInfo.substring(1); 
            try {
                int doctorId = Integer.parseInt(doctorIdStr); 
                User u = this.doctorDAO.findDoctorById(doctorId);  
                if (u != null) {
                    request.setAttribute("doctor", u);
                    request.getRequestDispatcher("/webapp/views/web/doctor.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Doctor not found");
                }

            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Doctor ID format");
            }
        } else {
            List<User> list = this.doctorDAO.findAllDoctorByIsActive(1);
            request.setAttribute("data", list);
            request.getRequestDispatcher("/webapp/views/web/list-doctors.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
