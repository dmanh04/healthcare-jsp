/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.medical_purchase;

import common.constants.SystemConstant;
import dao.IMedicalRecordDAO;
import dao.impl.MedicalRecordDAOImpl;
import dao.impl.MedicineDAOImpl;
import dto.criteria.MedicalRecordCriteria;
import dto.response.MedicalRecordHistoryResponse;
import dto.response.PageableResponse;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "AdminMedicalPurchaseController", urlPatterns = {"/admin/medical-purchase"})
public class AdminMedicalPurchaseController extends HttpServlet {

    private final IMedicalRecordDAO medicalRecordDAO;

    public AdminMedicalPurchaseController() {
        this.medicalRecordDAO = new MedicalRecordDAOImpl();
    }

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
            if (currentRole.equals(SystemConstant.ROLE_CURRENT)) {
                response.sendRedirect("/Healthcare/403");
                return;
            } else {
                String customerName = request.getParameter("customerName");
                String pageParam = request.getParameter("page");
                String limitParam = request.getParameter("limit");
                int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
                int limit = (limitParam != null && !limitParam.isEmpty()) ? Integer.parseInt(limitParam) : 10;
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                java.sql.Date start = null;
                if (startDate != null && !startDate.isEmpty()) {
                    start = java.sql.Date.valueOf(startDate);
                }
                java.sql.Date end = null;
                if (endDate != null && !endDate.isEmpty()) {
                    end = java.sql.Date.valueOf(endDate);
                }
                MedicalRecordCriteria medicalRecordCriteria = new MedicalRecordCriteria.Builder()
                        .customerName(customerName)
                        .page(page)
                        .limit(limit)
                        .startDate(start)
                        .endDate(end)
                        .build();
                PageableResponse<MedicalRecordHistoryResponse> pageable = this.medicalRecordDAO.getHistoryResponse(medicalRecordCriteria);
                request.setAttribute("pageable", pageable);
                request.getRequestDispatcher("/webapp/views/admin/medical-purchase.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
