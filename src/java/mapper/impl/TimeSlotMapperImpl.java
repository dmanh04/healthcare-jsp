/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper.impl;

import java.util.List;
import mapper.ITimeSlotMapper;
import models.Appointments;
import models.TimeSlot;


import dto.response.TimeSlotResponse;
import java.util.ArrayList;

public class TimeSlotMapperImpl implements ITimeSlotMapper {

    @Override
    public List<TimeSlotResponse> toTimeSlotResponse(List<Appointments> listAppointment, List<TimeSlot> timeSlots) {
        List<TimeSlotResponse> timeSlotResponses = new ArrayList<>();
        for (TimeSlot timeSlot : timeSlots) {
            boolean isBooked = false;
            for (Appointments appointment : listAppointment) {
                if (appointment.getTimeSlotId() == timeSlot.getId()) {
                    isBooked = true;
                    break;
                }
            }
            TimeSlotResponse timeSlotResponse = new TimeSlotResponse.Builder()
                .id(timeSlot.getId())
                .time(timeSlot.getTime())
                .isBooked(isBooked)
                .build();
            timeSlotResponses.add(timeSlotResponse);
        }
        return timeSlotResponses;
    }
}

