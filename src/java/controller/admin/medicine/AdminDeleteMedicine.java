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
import java.util.List;
import models.Medicines;


@WebServlet(name = "AdminDeleteMedicine", urlPatterns = {"/admin/medicine/delete"})
public class AdminDeleteMedicine extends HttpServlet {

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    MedicineDAOImpl dao = new MedicineDAOImpl();
    String medicineId_raw = request.getParameter("id");
    try {
        int medicineId = Integer.parseInt(medicineId_raw);
        dao.deleteMedicine(medicineId);
        response.sendRedirect("/Healthcare/admin/medicine"); 
//
//        List<Medicines> list = dao.getAllMedicine();
//        request.setAttribute("listMedicine", list);
//        request.getRequestDispatcher("/webapp/views/admin/medicine.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
