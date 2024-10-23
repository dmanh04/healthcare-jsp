/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.utils;

import common.constants.SystemConstant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Admin
 */
@WebServlet(name = "FileDownloadServlet", urlPatterns = {"/uploads/*"})
public class FileDownloadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getPathInfo().substring(1);
        File file = new File(SystemConstant.UPLOAD_DIRECTORY, fileName);

        if (file.exists() && !file.isDirectory()) {
            response.setContentType(getServletContext().getMimeType(file.getName()));
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            try (FileInputStream inStream = new FileInputStream(file); OutputStream outStream = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
        }
    }
}
