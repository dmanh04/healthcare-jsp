/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.web.account;

import common.constants.SystemConstant;
import common.utils.PasswordUtils;
import dao.IUserDAO;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AccountPasswordController", urlPatterns = {"/change-password"})
public class AccountPasswordController extends HttpServlet {

    private final IUserDAO userDAO;

    public AccountPasswordController() {
        this.userDAO = new UserDAOImpl();
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
            request.getRequestDispatcher("/webapp/views/web/password.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute(SystemConstant.USER_CURRENT);
        User userCurrent = userDAO.findByUsername(currentUser);
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String hashOldPassword = PasswordUtils.hashPassword(oldPassword);
        if (!hashOldPassword.equals(userCurrent.getPassword())) {
            request.setAttribute("errorMessage", "Mật khẩu cũ chưa chính xác.");
            request.getRequestDispatcher("/webapp/views/web/password.jsp").forward(request, response);
            return;
        } else {
            String hashNewPassword = PasswordUtils.hashPassword(newPassword);
            this.userDAO.updatePassword(hashNewPassword, userCurrent.getId());
            response.sendRedirect("/Healthcare/account?success=true");
        }

    }

}
