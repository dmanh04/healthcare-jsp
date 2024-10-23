package controller.admin.service;

import common.constants.SystemConstant;
import dao.ISerivceDAO;
import dao.impl.ServiceDAOImpl;
import dto.criteria.ServiceCriteria;
import dto.response.PageableResponse;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.Services;

@WebServlet(name = "AdminServiceController", urlPatterns = {"/admin/service"})
public class AdminServiceController extends HttpServlet {

    private final ISerivceDAO serviceDAO;

    public AdminServiceController() {
        this.serviceDAO = new ServiceDAOImpl();
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
            if (!currentRole.equals(SystemConstant.ROLE_ADMIN)) {
                response.sendRedirect("/Healthcare/403");
                return;
            } else {
                String searchName = request.getParameter("search");
                String pageParam = request.getParameter("page");
                String limitParam = request.getParameter("limit");
                int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
                int limit = (limitParam != null && !limitParam.isEmpty()) ? Integer.parseInt(limitParam) : 10;
                ServiceCriteria serviceCriteria = new ServiceCriteria.Builder()
                        .searchName(searchName)
                        .page(page)
                        .limit(3)
                        .build();
                System.out.println(serviceCriteria);
                PageableResponse<Services> pageableService = serviceDAO.getAllSerivceByFilter(serviceCriteria);
                request.setAttribute("pageableService", pageableService);
                request.getRequestDispatcher("/webapp/views/admin/service.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String serviceName = request.getParameter("serviceName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        String image = request.getParameter("image");
        String icon = request.getParameter("icon");
        Services newService = new Services.Builder()
                .setServiceName(serviceName)
                .setDescription(description)
                .setPrice(price)
                .setDuration(duration)
                .setImage(image)
                .setIcon(icon)
                .build();
        System.out.println(newService);
        serviceDAO.addService(newService);
        response.sendRedirect(request.getContextPath() + "/admin/service?add=true");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
