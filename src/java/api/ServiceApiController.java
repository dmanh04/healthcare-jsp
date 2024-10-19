/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import dao.ISerivceDAO;
import dao.impl.ServiceDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServiceApiServlet", urlPatterns = {"/api/service"})
public class ServiceApiController extends HttpServlet {

    private final ISerivceDAO serviceDAO;

    public ServiceApiController() {
        this.serviceDAO = new ServiceDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String serviceName = request.getParameter("serviceName");
        String serviceIdParam = request.getParameter("id");

        boolean exists;
        System.out.println(serviceName + " " + serviceIdParam);
        if (serviceIdParam != null && !serviceIdParam.isEmpty()) {
            int serviceId = Integer.parseInt(serviceIdParam);
            exists = serviceDAO.existsUpdateByServiceName(serviceName, serviceId);
        } else {
            exists = serviceDAO.existsByServiceName(serviceName);
        }
        System.out.println(exists);
        response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(exists));
    }

}
