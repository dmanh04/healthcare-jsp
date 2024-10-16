/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import common.constants.SystemConstant;
import dao.IRoleDAO;
import dao.IUserDAO;
import dao.IUserRoleDAO;
import dao.impl.RoleDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.UserRoleDAOImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.System.Logger;
import java.time.LocalDate;
import java.util.Date;
import models.Roles;
import models.User;



@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    
    private final IUserDAO userDAO;
    private final IUserRoleDAO userRoleDAO;
    private final IRoleDAO roleDAO;
    public RegisterController() {
        this.userDAO = new UserDAOImpl();
        this.userRoleDAO = new UserRoleDAOImpl();
        this.roleDAO = new RoleDAOImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher req = request.getRequestDispatcher("webapp/views/web/register.jsp");
        req.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("retypePassword");
        String name = request.getParameter("name");
        String[] arr = name.split("\\s+");
        if(userDAO.existsByUsername(username)){
            request.setAttribute("errorMessage", "Username already exists.");
            request.getRequestDispatcher("webapp/views/web/register.jsp").forward(request, response);
            return;
        }
        User user = new User.Builder()
                .username(username)
                .password(password)
                .firstName(arr[0])
                .lastName(arr[arr.length - 1])
                .isActive(1)
                .email(username)
                .dob(java.sql.Date.valueOf(LocalDate.now()))
                .build(); 
        Roles roleUserDefault = roleDAO.findUserByRoleName(SystemConstant.ROLE_USER);
        if (password.equals(confirmPassword)) {
            userDAO.add(user);
            User userAdd = userDAO.findByUsername(user.getUsername());
            userRoleDAO.add(userAdd.getId(), roleUserDefault.getId());
            request.setAttribute("successMessage", "Registration complete!");
            request.getRequestDispatcher("webapp/common/login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Passwords did not match. <br/> Please enter the same password in both fields.");
            request.getRequestDispatcher("webapp/views/web/register.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
