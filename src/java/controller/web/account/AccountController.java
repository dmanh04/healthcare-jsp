/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.web.account;

import common.constants.SystemConstant;
import dao.IRoleDAO;
import dao.IUserDAO;
import dao.impl.RoleDAOImpl;
import dao.impl.UserDAOImpl;
import dto.request.UserUpdationRequest;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Roles;
import models.User;

@WebServlet(name = "AccountController", urlPatterns = {"/account"})
public class AccountController extends HttpServlet {

    private final IUserDAO userDAO;
    private final IRoleDAO roleDAO;

    public AccountController() {
        this.userDAO = new UserDAOImpl();
        this.roleDAO = new RoleDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute(SystemConstant.USER_CURRENT);
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        } else {
            User userCurrent = userDAO.findByUsername(currentUser);
            request.setAttribute("user", userCurrent);
            request.getRequestDispatcher("webapp/views/web/account.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String dob = request.getParameter("dob");
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        java.sql.Date dateOfBirth = null;
        if (dob != null && !dob.isEmpty()) {
            dateOfBirth = java.sql.Date.valueOf(dob);
        }
        Roles roleCurrent = roleDAO.findRoleByUserId(id);
        UserUpdationRequest userUpdationRequest = new UserUpdationRequest.Builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .roleId(roleCurrent.getId())
                .dob(dateOfBirth)
                .gender(gender)
                .build();
        userDAO.updateUser(userUpdationRequest);
        response.sendRedirect("account?success=true");
    }

}
