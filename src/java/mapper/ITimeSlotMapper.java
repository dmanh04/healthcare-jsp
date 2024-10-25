package mapper;

import dto.response.TimeSlotResponse;
import java.util.List;
import models.Appointments;
import models.TimeSlot;

public interface ITimeSlotMapper {

    List<TimeSlotResponse> toTimeSlotResponse(List<Appointments> listAppointment,
            List<TimeSlot> timeSlots);
}
