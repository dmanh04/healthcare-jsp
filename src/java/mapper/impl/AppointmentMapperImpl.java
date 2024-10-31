/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper.impl;

import dao.IDoctorDAO;
import dao.ISerivceDAO;
import dao.ITimeSlotDAO;
import dao.IUserDAO;
import dao.impl.DoctorDAOImpl;
import dao.impl.ServiceDAOImpl;
import dao.impl.TimeSlotDAOImpl;
import dao.impl.UserDAOImpl;
import dto.response.AppointmentResponse;
import dto.response.PageableResponse;
import java.util.ArrayList;
import java.util.List;
import mapper.IAppointmentMapper;
import models.Appointments;
import models.Services;
import models.TimeSlot;
import models.User;

/**
 *
 * @author Admin
 */
public class AppointmentMapperImpl implements IAppointmentMapper {

    private final IUserDAO userDAO;
    private final ITimeSlotDAO timeSlotDAO;
    private final ISerivceDAO serivceDAO;
    private final IDoctorDAO doctorDAO;

    public AppointmentMapperImpl() {
        this.userDAO = new UserDAOImpl();
        this.timeSlotDAO = new TimeSlotDAOImpl();
        this.serivceDAO = new ServiceDAOImpl();
        this.doctorDAO = new DoctorDAOImpl();
    }

    @Override
    public List<AppointmentResponse> toAppointmentResponse(List<Appointments> listAppointment) {
        List<AppointmentResponse> res = new ArrayList<>();
        for (Appointments appointments : listAppointment) {
            User cusUser = this.userDAO.findById(appointments.getCustomerId());
            User doctor = this.doctorDAO.findDoctorByIdNotNeedActive(appointments.getDoctorId());
            Services services = this.serivceDAO.findServiceById(appointments.getServiceId());
            TimeSlot timeSlot = this.timeSlotDAO.getTimeSlotById(appointments.getTimeSlotId());
            res.add(new AppointmentResponse.Builder()
                    .id(appointments.getId())
                    .doctor(doctor)
                    .services(services)
                    .date(appointments.getAppointmentDate())
                    .timeSlot(timeSlot)
                    .status(appointments.getStatus())
                    .notes(appointments.getNote())
                    .phone(appointments.getPhone())
                    .customerName(appointments.getCustomerName())
                    .customer(cusUser)
                    .build());
        }
        return res;

    }

    @Override
    public PageableResponse<AppointmentResponse> toPageableResponse(PageableResponse<Appointments> pageableResponse) {
        List<AppointmentResponse> list = toAppointmentResponse(pageableResponse.getData());
        return new PageableResponse.Builder<AppointmentResponse>()
                .totalPage(pageableResponse.getTotalPage())
                .page(pageableResponse.getPage())
                .size(list.size())
                .data(list)
                .build();
    }

}
