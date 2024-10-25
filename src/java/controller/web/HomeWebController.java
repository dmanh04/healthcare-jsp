/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.web;

import dao.IDoctorDAO;
import dao.impl.DoctorDAOImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.User;


@WebServlet(name = "HomeWebController", urlPatterns = {"/home"})
public class HomeWebController extends HttpServlet {
    
    private final IDoctorDAO doctorDAO;

    public HomeWebController() {
        this.doctorDAO = new DoctorDAOImpl();
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> list = doctorDAO.selectFourDoctors();
        request.setAttribute("data", list);
        RequestDispatcher req = request.getRequestDispatcher("webapp/views/web/home.jsp");
        req.forward(request, response);
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
