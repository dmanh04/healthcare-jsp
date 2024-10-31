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

/**
 *
 * @author luong
 */
@WebServlet(name = "AdminEditMedicine", urlPatterns = {"/admin/edit/medicine"})
public class AdminEditMedicine extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        MedicineDAOImpl dao = new MedicineDAOImpl();
        Medicines medicine = dao.getMedicineById(Integer.parseInt(id));
        request.setAttribute("medicine", medicine);
        request.getRequestDispatcher("/webapp/views/admin/edit-medicine.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String quantity = request.getParameter("quantity");
        MedicineDAOImpl dao = new MedicineDAOImpl();
        Medicines medicineUpdate = new Medicines.Builder()
                    .id(Integer.parseInt(id))
                    .medicineName(name)
                    .price(Double.parseDouble(price))
                    .description(description)
                    .quantityInStock(Integer.parseInt(quantity))
                    .build();
        if (dao.existsUpdateByMedicineName(name, Integer.parseInt(id))) {
            request.setAttribute("error", "Name already exists !Please enter again.");
            request.setAttribute("medicine", medicineUpdate);
            request.getRequestDispatcher("/webapp/views/admin/edit-medicine.jsp").forward(request, response);
        } else {
            dao.updateMedicine(medicineUpdate);
            response.sendRedirect("/Healthcare/admin/medicine"); 
        }

    }

}
