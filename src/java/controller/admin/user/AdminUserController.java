package controller.admin.user;

import common.constants.SystemConstant;
import common.utils.PasswordUtils;
import dao.IRoleDAO;
import dao.IUserDAO;
import dao.IUserRoleDAO;
import dao.impl.RoleDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.UserRoleDAOImpl;
import dto.criteria.UserCriteria;
import dto.request.UserCreationRequest;
import dto.response.PageableResponse;
import dto.response.UserResponse;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import mapper.IUserMapper;
import mapper.impl.UserMapperImpl;
import models.User;

@WebServlet(name = "AdminUserController", urlPatterns = {"/admin/user"})
public class AdminUserController extends HttpServlet {

    private final IUserDAO userDAO;

    private final IRoleDAO roleDAO;

    private final IUserRoleDAO userRoleDAO;

    private final IUserMapper userMapper;

    public AdminUserController() {
        this.userDAO = new UserDAOImpl();
        this.roleDAO = new RoleDAOImpl();
        this.userRoleDAO = new UserRoleDAOImpl();
        this.userMapper = new UserMapperImpl();
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
                String username = request.getParameter("usernameSearch");
                String fullname = request.getParameter("fullnameSearch");
                String role = request.getParameter("roleIdSearch");
                String pageParam = request.getParameter("page");
                String limitParam = request.getParameter("limit");
                int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
                int limit = (limitParam != null && !limitParam.isEmpty()) ? Integer.parseInt(limitParam) : 10;
                Integer roleId = (role != null && !role.isEmpty()) ? Integer.parseInt(role) : null;
                UserCriteria userCriteria = new UserCriteria.Builder()
                        .fullname(fullname)
                        .roleId(roleId)
                        .username(username)
                        .limit(limit)
                        .page(page)
                        .build();
                System.out.println(userCriteria);
                PageableResponse<User> pageable = userDAO.getAllUserByFilter(userCriteria);
                Map<Integer, String> roleMap = roleDAO.getRoleIdAndName();
                PageableResponse<UserResponse> pageableUser = userMapper.toPageableResponse(pageable);
                request.setAttribute("pageableUser", pageableUser);
                request.setAttribute("roleMap", roleMap);
                request.getRequestDispatcher("/webapp/views/admin/user.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String phone = request.getParameter("phone");
                String dob = request.getParameter("dob");
                String gender = request.getParameter("gender");
                int roleId = Integer.parseInt(request.getParameter("roleId"));
                if (username == null || password == null || firstName == null || lastName == null || phone == null) {
                    request.setAttribute("errorMessage", "All fields are required.");
                    request.getRequestDispatcher("/webapp/views/admin/user.jsp").forward(request, response);
                    return;
                }
                try {
                    java.sql.Date dateOfBirth = null;
                    if (dob != null && !dob.isEmpty()) {
                        dateOfBirth = java.sql.Date.valueOf(dob);
                    }
                    String hashedPassword = PasswordUtils.hashPassword(password);
                    UserCreationRequest userRequest = new UserCreationRequest.Builder()
                            .username(username)
                            .password(hashedPassword)
                            .firstName(firstName)
                            .lastName(lastName)
                            .phone(phone)
                            .roleId(roleId)
                            .dob(dateOfBirth)
                            .gender(gender)
                            .build();
                    userDAO.createUser(userRequest);
                    User userAdd = userDAO.findByUsername(userRequest.getUsername());
                    userRoleDAO.add(userAdd.getId(), userRequest.getRoleId());
                    response.sendRedirect(request.getContextPath() + "/admin/user?add=true");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
                    request.getRequestDispatcher("/webapp/views/admin/user.jsp").forward(request, response);
                }
            }
        }
    }

}
