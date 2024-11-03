/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.medicine;

import common.constants.SystemConstant;
import dao.impl.MedicineDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import models.Medicines;

@WebServlet(name = "AdminMedicineController", urlPatterns = {"/admin/medicine"})
public class AdminMedicineController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Medicines> list = new ArrayList<>();
        MedicineDAOImpl dao = new MedicineDAOImpl();
        String currentUser = (String) session.getAttribute(SystemConstant.USER_CURRENT);
        if (currentUser == null) {
            response.sendRedirect("/Healthcare/login");
            return;
        } else {
            String currentRole = (String) session.getAttribute(SystemConstant.ROLE_CURRENT);
            if (currentRole.equals(SystemConstant.ROLE_USER) || currentRole.equals(SystemConstant.ROLE_STAFF)) {
                response.sendRedirect("/Healthcare/403");
                return;
            } else {

                String name = request.getParameter("name");
                String minPrice_raw = request.getParameter("minPrice");
                String maxPrice_raw = request.getParameter("maxPrice");
                if (name != null || minPrice_raw != null || maxPrice_raw != null) {

                    list = dao.getMedicineByPriceAndName(minPrice_raw, maxPrice_raw, name);

                } else {
                    list = dao.getAllMedicine();
                }

                request.setAttribute("listMedicine", list);
                request.getRequestDispatcher("/webapp/views/admin/medicine.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MedicineDAOImpl dao = new MedicineDAOImpl();
        String name = request.getParameter("name");
        String price_raw = request.getParameter("price");
        String description = request.getParameter("description");
        String quantity_raw = request.getParameter("quantity");

        try {
            int quantity = Integer.parseInt(quantity_raw);
            double price = Double.parseDouble(price_raw);

            Medicines newMedicine = new Medicines.Builder()
                    .medicineName(name)
                    .price(price)
                    .description(description)
                    .quantityInStock(quantity)
                    .build();
            if (dao.existsMedicineName(name)) {
                request.setAttribute("error", "Name already exist!Please enter again.");
                request.getRequestDispatcher("/webapp/views/admin/add-medicine.jsp").forward(request, response);
                return;
            } else {
                dao.addMedicine(newMedicine);
            }
            response.sendRedirect("/Healthcare/admin/medicine");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while adding medicine.");
            request.getRequestDispatcher("/webapp/views/admin/add-medicine.jsp").forward(request, response);
        }
    }

}
