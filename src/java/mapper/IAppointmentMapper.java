/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import dto.response.AppointmentResponse;
import dto.response.PageableResponse;
import java.util.List;
import models.Appointments;

/**
 *
 * @author Admin
 */
public interface IAppointmentMapper {

    List<AppointmentResponse> toAppointmentResponse(List<Appointments> listAppointment);

    PageableResponse<AppointmentResponse> toPageableResponse(PageableResponse<Appointments> pageableResponse);

}
