package controller.admin.user;

import common.constants.SystemConstant;
import dao.IUserDAO;
import dao.impl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "AdminUploadUserController", urlPatterns = {"/admin/user/upload"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)
public class AdminUploadUserController extends HttpServlet {

    private final IUserDAO userDAO;

    public AdminUploadUserController() {
        this.userDAO = new UserDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        for (Part part : request.getParts()) {
            String fileName = UUID.randomUUID() + extractFileName(part);
            if (fileName != null && !fileName.isEmpty()) {
                File uploadDir = getFolderUpload();
                File file = new File(uploadDir, fileName);
                part.write(file.getAbsolutePath());
            }
            userDAO.updatePhotos(userId, fileName);
        }
        response.sendRedirect(request.getContextPath() + "/admin/user?edit=true");
    }

    private File getFolderUpload() {
        File folderUpload = new File(SystemConstant.UPLOAD_DIRECTORY);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return null;
    }
}
