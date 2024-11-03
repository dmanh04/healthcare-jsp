/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.criteria.AppointmentCriteria;
import dto.request.AppointmentRequest;
import dto.response.PageableResponse;
import java.util.Date;
import java.util.List;
import models.Appointments;

public interface IAppointmentDAO {
    
    PageableResponse<Appointments> getListAppointmentsByFilterAdmin(AppointmentCriteria appointmentCriteria);
    
    void addAppointment(AppointmentRequest appointmentRequest);
    
    Appointments getAppointmentsById(int id);
    
    List<Appointments> getListAppointmentsByDoctorIdAndDate(int doctorId, Date date);
    
    List<Appointments> getListAppointmentsByCustomerId(int id);
    
    void updateStatusById(int id, String status);
}
