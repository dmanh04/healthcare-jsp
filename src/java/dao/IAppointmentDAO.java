/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.request.AppointmentRequest;
import java.util.Date;
import java.util.List;
import models.Appointments;

public interface IAppointmentDAO {
    void addAppointment(AppointmentRequest appointmentRequest);
    
    List<Appointments> getListAppointmentsByDoctorIdAndDate(int doctorId, Date date);
}
