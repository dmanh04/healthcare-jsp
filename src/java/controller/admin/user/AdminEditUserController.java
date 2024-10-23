package controller.admin.user;

import dao.IUserDAO;
import dao.IUserRoleDAO;
import dao.impl.UserDAOImpl;
import dao.impl.UserRoleDAOImpl;
import dto.request.UserUpdationRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminEditUserController", urlPatterns = {"/admin/user/edit"})
public class AdminEditUserController extends HttpServlet {

    private final IUserDAO userDAO;
    private final IUserRoleDAO userRoleDAO;

    public AdminEditUserController() {
        this.userDAO = new UserDAOImpl();
        this.userRoleDAO = new UserRoleDAOImpl();
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
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        java.sql.Date dateOfBirth = null;
        if (dob != null && !dob.isEmpty()) {
            dateOfBirth = java.sql.Date.valueOf(dob);
        }
        UserUpdationRequest userUpdationRequest = new UserUpdationRequest.Builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .roleId(roleId)
                .dob(dateOfBirth)
                .gender(gender)
                .build();
        userDAO.updateUser(userUpdationRequest);
        userRoleDAO.updateByUserId(id, roleId);
        response.sendRedirect(request.getContextPath() + "/admin/user?edit=true");
    }
}
