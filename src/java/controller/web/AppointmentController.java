package controller.web;

import common.constants.SystemConstant;
import dao.IAppointmentDAO;
import dao.IDoctorDAO;
import dao.ISerivceDAO;
import dao.ITimeSlotDAO;
import dao.IUserDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.DoctorDAOImpl;
import dao.impl.ServiceDAOImpl;
import dao.impl.TimeSlotDAOImpl;
import dao.impl.UserDAOImpl;
import dto.request.AppointmentRequest;
import dto.response.TimeSlotResponse;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import mapper.ITimeSlotMapper;
import mapper.impl.TimeSlotMapperImpl;
import models.Appointments;
import models.Services;
import models.TimeSlot;
import models.User;

@WebServlet(name = "AppoinmentController", urlPatterns = {"/appointment"})
public class AppointmentController extends HttpServlet {

    private final IAppointmentDAO appointmentDAO;
    private final IUserDAO userDAO;
    private final ISerivceDAO serivceDAO;
    private final ITimeSlotDAO timeSlotDAO;
    private final IDoctorDAO doctorDAO;
    private final ITimeSlotMapper timeSlotMapper;

    public AppointmentController() {
        this.appointmentDAO = new AppointmentDAOImpl();
        this.userDAO = new UserDAOImpl();
        this.serivceDAO = new ServiceDAOImpl();
        this.timeSlotDAO = new TimeSlotDAOImpl();
        this.doctorDAO = new DoctorDAOImpl();
        this.timeSlotMapper = new TimeSlotMapperImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dateString = request.getParameter("date");
        String doctorIdString = request.getParameter("doctorId");
        Map<Integer, Services> mapService = this.serivceDAO.getIdAndService();
        List<User> listDoctor = this.doctorDAO.findAllDoctor();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        if (dateString != null && !dateString.isEmpty()) {
            sqlDate = java.sql.Date.valueOf(dateString);
        }
        int doctorId = listDoctor.get(0).getId();
        if (doctorIdString != null && !doctorIdString.isEmpty()) {
            doctorId = Integer.parseInt(doctorIdString);
        }
        System.out.println(sqlDate + " " + doctorId);
        List<Appointments> listAppointment = this.appointmentDAO.getListAppointmentsByDoctorIdAndDate(doctorId, sqlDate);
        List<TimeSlot> timeSlots = this.timeSlotDAO.getAllTimeSlot();
        List<TimeSlotResponse> timeSlotResponses = this.timeSlotMapper.toTimeSlotResponse(listAppointment, timeSlots);
        request.setAttribute("mapService", mapService);
        request.setAttribute("timeSlots", timeSlotResponses);
        request.setAttribute("listDoctor", listDoctor);
        request.getRequestDispatcher("webapp/views/web/appoinment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(SystemConstant.USER_CURRENT);
        if (username == null) {
            response.sendRedirect("login");
            return;
        }
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String date = request.getParameter("date");
        Integer doctorId = Integer.valueOf(request.getParameter("doctorId"));
        Integer timeSlotId = Integer.valueOf(request.getParameter("timeSlotId"));
        Integer serviceId = Integer.valueOf(request.getParameter("serviceId"));
        String note = request.getParameter("note");
        java.sql.Date appointmentDate = null;
        if (date != null && !date.isEmpty()) {
            appointmentDate = java.sql.Date.valueOf(date);
        }
        User userCurrent = this.userDAO.findByUsername(username);
        System.out.println(userCurrent);
        AppointmentRequest appointmentRequest = new AppointmentRequest.Builder()
                .name(name)
                .phone(phone)
                .appointmentDate(appointmentDate)
                .doctorId(doctorId)
                .serviceId(serviceId)
                .note(note)
                .timeSlotId(timeSlotId)
                .customerId(userCurrent.getId())
                .build();
        System.out.println(appointmentRequest);
        this.appointmentDAO.addAppointment(appointmentRequest);
        response.sendRedirect("manage-appointment?success=true");
    }

}
