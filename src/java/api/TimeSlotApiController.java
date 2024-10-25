/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import com.google.gson.Gson;
import dao.IAppointmentDAO;
import dao.IDoctorDAO;
import dao.ITimeSlotDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.DoctorDAOImpl;
import dao.impl.TimeSlotDAOImpl;
import dto.response.TimeSlotResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import mapper.ITimeSlotMapper;
import mapper.impl.TimeSlotMapperImpl;
import models.Appointments;
import models.TimeSlot;

@WebServlet(name = "TimeSlotApiServlet", urlPatterns = {"/api/slot"})
public class TimeSlotApiController extends HttpServlet {

    private final IAppointmentDAO appointmentDAO;
    private final ITimeSlotDAO timeSlotDAO;
    private final ITimeSlotMapper timeSlotMapper;

    public TimeSlotApiController() {
        this.appointmentDAO = new AppointmentDAOImpl();
        this.timeSlotDAO = new TimeSlotDAOImpl();
        this.timeSlotMapper = new TimeSlotMapperImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dateString = request.getParameter("date");
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        java.sql.Date date = null;
        if (dateString != null && !dateString.isEmpty()) {
            date = java.sql.Date.valueOf(dateString);
        }
        List<Appointments> listAppointment = this.appointmentDAO.getListAppointmentsByDoctorIdAndDate(doctorId, date);
        List<TimeSlot> timeSlots = this.timeSlotDAO.getAllTimeSlot();
        List<TimeSlotResponse> timeSlotResponses = this.timeSlotMapper.toTimeSlotResponse(listAppointment, timeSlots);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
                String jsonResponse = new Gson().toJson(timeSlotResponses);
        out.write(jsonResponse);
        out.flush();
    }
}

