/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper.impl;

import dao.ISerivceDAO;
import dao.ITimeSlotDAO;
import dao.IUserDAO;
import dao.impl.ServiceDAOImpl;
import dao.impl.TimeSlotDAOImpl;
import dao.impl.UserDAOImpl;
import dto.response.AppointmentResponse;
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

    public AppointmentMapperImpl() {
        this.userDAO = new UserDAOImpl();
        this.timeSlotDAO = new TimeSlotDAOImpl();
        this.serivceDAO = new ServiceDAOImpl();
    }

    @Override
    public List<AppointmentResponse> toAppointmentResponse(List<Appointments> listAppointment) {
        List<AppointmentResponse> res = new ArrayList<>();
        for (Appointments appointments : listAppointment) {
            User doctor = this.userDAO.findById(appointments.getDoctorId());
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
                    .build());
        }
        return res;

    }

}
