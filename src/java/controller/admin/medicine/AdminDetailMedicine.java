/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.medicine;

import dao.impl.MedicineDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Medicines;

@WebServlet(name = "AdminDetailMedicine", urlPatterns = {"/admin/detail/medicine"})
public class AdminDetailMedicine extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        MedicineDAOImpl dao = new MedicineDAOImpl();
        Medicines medicine = dao.getMedicineById(Integer.parseInt(id));
        request.setAttribute("medicine", medicine);
        request.getRequestDispatcher("/webapp/views/admin/detail-medicine.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
