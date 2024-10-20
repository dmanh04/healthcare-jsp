/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import common.constants.SystemConstant;
import dao.ISerivceDAO;
import dao.impl.ServiceDAOImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Services;

@WebServlet(name = "AdminHomeController", urlPatterns = {"/admin"})
public class AdminHomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute(SystemConstant.USER_CURRENT);
        if (currentUser == null) {
            response.sendRedirect("/Healthcare/login");
            return;
        } else {
            String currentRole = (String) session.getAttribute(SystemConstant.ROLE_CURRENT);
            if (currentRole.equals(SystemConstant.ROLE_USER)) {
                response.sendRedirect("/Healthcare/403");
                return;
            }
        }
        RequestDispatcher req = request.getRequestDispatcher("webapp/views/admin/home.jsp");
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
