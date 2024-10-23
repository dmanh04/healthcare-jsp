/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import dao.IUserDAO;
import dao.impl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserApiServlet", urlPatterns = {"/api/user"})
public class UserApiController extends HttpServlet {

    private final IUserDAO userDAO;

    public UserApiController() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String userId = request.getParameter("id");
        boolean exists;
        System.out.println(username + " " + userId);
        if (userId != null && !userId.isEmpty()) {
            int id = Integer.parseInt(userId);
            exists = userDAO.existsUpdateByUsername(username, id);
        } else {
            exists = userDAO.existsByUsername(username);
        }
        System.out.println(exists);
        response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(exists));
    }

}
