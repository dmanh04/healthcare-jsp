/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import common.constants.SystemConstant;
import common.utils.PasswordUtils;
import dao.IRoleDAO;
import dao.IUserDAO;
import dao.impl.RoleDAOImpl;
import dao.impl.UserDAOImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Roles;
import models.User;

@WebServlet(name = "AuthenticateController", urlPatterns = {"/login"})
public class AuthenticateController extends HttpServlet {

    private final IUserDAO userDAO;
    private final IRoleDAO roleDAO;

    public AuthenticateController() {
        this.userDAO = new UserDAOImpl();
        this.roleDAO = new RoleDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute(SystemConstant.USER_CURRENT);
        if (currentUser != null) {
            String currentRole = (String) session.getAttribute(SystemConstant.ROLE_CURRENT);
            if (!currentRole.equals(SystemConstant.ROLE_USER)) {
                response.sendRedirect("admin");
            } else {
                response.sendRedirect("home");
            }
            return;
        }
        RequestDispatcher req = request.getRequestDispatcher("webapp/common/login.jsp");
        req.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        if (!userDAO.existsByUsername(username)) {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("webapp/common/login.jsp").forward(request, response);
            return;
        }
        User userCurrent = userDAO.findByUsername(username);
        System.out.println("User found: " + userCurrent);
        String hashPassword = PasswordUtils.hashPassword(password);
        if (!hashPassword.equals(userCurrent.getPassword())) {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("webapp/common/login.jsp").forward(request, response);
            return;
        }
        Roles roleCurrent = roleDAO.findRoleByUserId(userCurrent.getId());
        session.setAttribute(SystemConstant.USER_CURRENT, userCurrent.getUsername());
        session.setAttribute(SystemConstant.ROLE_CURRENT, roleCurrent.getRoleName());

        System.out.println("User role: " + roleCurrent);

        if (!roleCurrent.getRoleName().equals(SystemConstant.ROLE_USER)) {
            response.sendRedirect("admin");
        } else {
            response.sendRedirect("home");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
