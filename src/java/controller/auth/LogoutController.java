/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.auth;

import common.constants.SystemConstant;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name="LogoutController", urlPatterns={"/logout"})
public class LogoutController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();  
        session.removeAttribute(SystemConstant.USER_CURRENT); 
        session.removeAttribute(SystemConstant.ROLE_CURRENT); 
        session.invalidate();  
        request.setAttribute("successMessage", "You are successfully logged out!");
        request.getRequestDispatcher("webapp/common/login.jsp").forward(request, response); 
    } 


}
