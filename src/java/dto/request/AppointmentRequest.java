/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.request;

import java.util.Date;

public class AppointmentRequest {

    private String name;
    private String phone;
    private Date appointmentDate;
    private int doctorId;
    private int timeSlotId;
    private int serviceId;
    private String note;
    private int customerId;

    private AppointmentRequest(Builder builder) {
        this.name = builder.name;
        this.phone = builder.phone;
        this.appointmentDate = builder.appointmentDate;
        this.doctorId = builder.doctorId;
        this.timeSlotId = builder.timeSlotId;
        this.serviceId = builder.serviceId;
        this.note = builder.note;
        this.customerId = builder.customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getNote() {
        return note;
    }

    public int getCustomerId() {
        return customerId;
    }

    public static class Builder {

        private String name;
        private String phone;
        private Date appointmentDate;
        private int doctorId;
        private int timeSlotId;
        private int serviceId;
        private String note;
        private int customerId;

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder appointmentDate(Date appointmentDate) {
            this.appointmentDate = appointmentDate;
            return this;
        }

        public Builder doctorId(int doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public Builder timeSlotId(int timeSlotId) {
            this.timeSlotId = timeSlotId;
            return this;
        }

        public Builder serviceId(int serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public AppointmentRequest build() {
            return new AppointmentRequest(this);
        }
    }

}
