package controller.admin.appointment;


import common.constants.SystemConstant;
import dao.IAppointmentDAO;
import dao.IDoctorDAO;
import dao.ISerivceDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.DoctorDAOImpl;
import dao.impl.ServiceDAOImpl;
import dao.impl.TimeSlotDAOImpl;
import dto.criteria.AppointmentCriteria;
import dto.response.AppointmentResponse;
import dto.response.PageableResponse;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import mapper.IAppointmentMapper;
import mapper.impl.AppointmentMapperImpl;
import models.Appointments;
import models.Services;
import models.TimeSlot;
import models.User;
import dao.ITimeSlotDAO;
import dao.IUserDAO;
import dao.impl.UserDAOImpl;

@WebServlet(name = "AdminAppointmentController", urlPatterns = {"/admin/appointment"})
public class AdminAppointmentController extends HttpServlet {

    private final ITimeSlotDAO timeSlotDAO;
    private final ISerivceDAO serivceDAO;
    private final IDoctorDAO doctorDAO;
    private final IAppointmentDAO appointmentDAO;
    private final IAppointmentMapper appointmentMapper;
    private final IUserDAO userDAO;
    
    public AdminAppointmentController() {
        this.timeSlotDAO = new TimeSlotDAOImpl();
        this.serivceDAO = new ServiceDAOImpl();
        this.doctorDAO = new DoctorDAOImpl();
        this.appointmentDAO = new AppointmentDAOImpl();
        this.appointmentMapper = new AppointmentMapperImpl();
        this.userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute(SystemConstant.USER_CURRENT);
        String currentRole = (String) session.getAttribute(SystemConstant.ROLE_CURRENT);
        if (currentUser == null) {
            response.sendRedirect("/Healthcare/login");
            return;
        } else {
            if (currentRole.equals(SystemConstant.ROLE_USER)) {
                response.sendRedirect("/Healthcare/403");
                return;
            }
        }
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String serviceIdString = request.getParameter("serviceId");
        String customerName = request.getParameter("customerName");
        String status = request.getParameter("status");
        String phone = request.getParameter("phone");
        String doctorIdString = request.getParameter("doctorId");
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");
        String[] selectedTimeSlots = request.getParameterValues("listSlotId");
        java.sql.Date start = null;
        if (startDate != null && !startDate.isEmpty()) {
            start = java.sql.Date.valueOf(startDate);
        }
        java.sql.Date end = null;
        if (endDate != null && !endDate.isEmpty()) {
            end = java.sql.Date.valueOf(endDate);
        }
        Integer serviceId = (serviceIdString != null && !serviceIdString.isEmpty()) ? Integer.valueOf(serviceIdString) : null;
        Integer doctorId = (doctorIdString != null && !doctorIdString.isEmpty()) ? Integer.valueOf(doctorIdString) : null;
        int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
        int limit = (limitParam != null && !limitParam.isEmpty()) ? Integer.parseInt(limitParam) : 10;
        List<Integer> timeSlotIds = new ArrayList<>();

        if (selectedTimeSlots != null && selectedTimeSlots.length != 0) {
            for (String timeSlotId : selectedTimeSlots) {
               try{
                    timeSlotIds.add(Integer.valueOf(timeSlotId));
               }catch(Exception e){
                   
               }
            }
        }
        
        Integer doctorCurrent = null;
        if(currentRole.equals(SystemConstant.ROLE_DOCTOR)){
           User user = this.userDAO.findByUsername(currentUser);
           doctorCurrent = user.getId();
        }
        AppointmentCriteria appointmentCriteria = new AppointmentCriteria.Builder()
                .startDate(start)
                .endDate(end)
                .listTimeSlotId(timeSlotIds)
                .customerName(customerName)
                .doctorId(doctorId)
                .status(status)
                .serviceId(serviceId)
                .page(page)
                .limit(limit)
                .phone(phone)
                .doctorIdCurrent(doctorCurrent)
                .build();

        PageableResponse<Appointments> pageableAppointments = this.appointmentDAO.getListAppointmentsByFilterAdmin(appointmentCriteria);
        System.out.println(pageableAppointments.getTotalPage());
        PageableResponse<AppointmentResponse> pageable = this.appointmentMapper.toPageableResponse(pageableAppointments);
         System.out.println(pageable.getTotalPage());
        List<TimeSlot> listTimeSlot = this.timeSlotDAO.getAllTimeSlot();
        List<Services> listService = this.serivceDAO.getAllSerivce();
        List<User> listDoctor = this.doctorDAO.findAllDoctor();
        
        request.setAttribute("pageable", pageable);
        request.setAttribute("listTimeSlot", listTimeSlot);
        request.setAttribute("listService", listService);
        request.setAttribute("listDoctor", listDoctor);
        RequestDispatcher req = request.getRequestDispatcher("/webapp/views/admin/appointment.jsp");
        req.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

} 