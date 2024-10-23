/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin.user;


import dao.IUserDAO;
import dao.impl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AdminDeleteUserController", urlPatterns = {"/admin/user/delete"})
public class AdminDeleteUserController extends HttpServlet {

    private final IUserDAO userDAO;

    public AdminDeleteUserController() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdStr = request.getParameter("id");
        if (userIdStr != null && !userIdStr.isEmpty()) {
            try {
                int userId = Integer.parseInt(userIdStr);
                userDAO.deleteUser(userId);
                response.sendRedirect(request.getContextPath() + "/admin/user?remove=true&id=" + userId);
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/admin/user?remove=false");
            } catch (Exception ex) {
                response.sendRedirect(request.getContextPath() + "/admin/user?remove=false");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/user?remove=false");
        }
    }
}
