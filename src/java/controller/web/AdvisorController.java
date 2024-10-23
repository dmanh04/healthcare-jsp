/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 *
 * @author HUYEN DIEU
 */

@WebServlet(name = "AdvisorController", urlPatterns = {"/advisor"})
public class AdvisorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        if (query != null && !query.trim().isEmpty()) {
            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            response.sendRedirect("https://nhakhoathuyduc.com.vn/?s=" + query);
        } else {
               request.getRequestDispatcher("webapp/views/web/advisor.jsp").forward(request, response);
        }

 
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
