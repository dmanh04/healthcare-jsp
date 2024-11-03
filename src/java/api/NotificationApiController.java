package api;

import com.google.gson.Gson;
import common.constants.SystemConstant;
import dao.INotificationDAO;
import dao.IUserDAO;
import dao.impl.NotificationDAOImpl;
import dao.impl.UserDAOImpl;
import dto.response.NotificationResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import models.User;

@WebServlet(name = "NotificationApiServlet", urlPatterns = {"/api/notification"})
public class NotificationApiController extends HttpServlet {

    private final INotificationDAO notificationDAO;
    private final IUserDAO userDAO;

    public NotificationApiController() {
        this.notificationDAO = new NotificationDAOImpl();
        this.userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<NotificationResponse> res = new ArrayList<>();
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute(SystemConstant.USER_CURRENT);
        if (currentUser != null && !currentUser.isEmpty()) {
            User userCurrent = userDAO.findByUsername(currentUser);
            res = this.notificationDAO.getListNotificationResponseByUserId(userCurrent.getId());
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.write(gson.toJson(res));
        response.setStatus(HttpServletResponse.SC_OK);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute(SystemConstant.USER_CURRENT);

        if (currentUser != null && !currentUser.isEmpty()) {
            User userCurrent = userDAO.findByUsername(currentUser);
            notificationDAO.markAllNotificationsAsRead(userCurrent.getId());
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
