/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.response;

import java.util.Date;
import models.Services;
import models.TimeSlot;
import models.User;

public class AppointmentResponse {

    private int id;
    private User customer;
    private User doctor;
    private Services services;
    private Date date;
    private TimeSlot timeSlot;
    private String status;
    private String notes;
    private String phone;
    private String customerName;

    private AppointmentResponse(Builder builder) {
        this.id = builder.id;
        this.doctor = builder.doctor;
        this.services = builder.services;
        this.date = builder.date;
        this.timeSlot = builder.timeSlot;
        this.status = builder.status;
        this.notes = builder.notes;
        this.phone = builder.phone;
        this.customerName = builder.customerName;
        this.customer = builder.customer;
    }

    public User getCustomer() {
        return customer;
    }

    public int getId() {
        return id;
    }

    public User getDoctor() {
        return doctor;
    }

    public Services getServices() {
        return services;
    }

    public Date getDate() {
        return date;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public String getPhone() {
        return phone;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "AppointmentResponse{" + "id=" + id + ", customer=" + customer + ", doctor=" + doctor + ", services=" + services + ", date=" + date + ", timeSlot=" + timeSlot + ", status=" + status + ", notes=" + notes + ", phone=" + phone + ", customerName=" + customerName + '}';
    }

    public static class Builder {

        private int id;
        private User doctor;
        private Services services;
        private Date date;
        private TimeSlot timeSlot;
        private String status;
        private String notes;
        private String phone;
        private String customerName;
        private User customer;

        public Builder customer(User customer) {
            this.customer = customer;
            return this;
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder doctor(User doctor) {
            this.doctor = doctor;
            return this;
        }

        public Builder services(Services services) {
            this.services = services;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder timeSlot(TimeSlot timeSlot) {
            this.timeSlot = timeSlot;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public AppointmentResponse build() {
            return new AppointmentResponse(this);
        }
    }
}
