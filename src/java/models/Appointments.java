package models;

import java.util.Date;

public class Appointments {

    private final int id;
    private final int customerId;
    private final int doctorId;
    private final int serviceId;
    private final String customerName;
    private final Date appointmentDate;
    private final String status;
    private final String note;
    private final String phone;
    private final int timeSlotId;
    private final Date createdAt;
    private final Date updatedAt;

    private Appointments(Builder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.doctorId = builder.doctorId;
        this.serviceId = builder.serviceId;
        this.customerName = builder.customerName;
        this.appointmentDate = builder.appointmentDate;
        this.status = builder.status;
        this.note = builder.note;
        this.phone = builder.phone;
        this.timeSlotId = builder.timeSlotId;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {
        private int id;
        private int customerId;
        private int doctorId;
        private int serviceId;
        private  String customerName;
        private Date appointmentDate;
        private String status;
        private String note;
        private String phone;
        private int timeSlotId;
        private Date createdAt;
        private Date updatedAt;

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }
        
        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder doctorId(int doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public Builder serviceId(int serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder appointmentDate(Date appointmentDate) {
            this.appointmentDate = appointmentDate;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder timeSlotId(int timeSlotId) {
            this.timeSlotId = timeSlotId;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Appointments build() {
            return new Appointments(this);
        }
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public String getPhone() {
        return phone;
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Appointments{" + "id=" + id + ", customerId=" + customerId + ", doctorId=" + doctorId + ", serviceId=" + serviceId + ", customerName=" + customerName + ", appointmentDate=" + appointmentDate + ", status=" + status + ", note=" + note + ", phone=" + phone + ", timeSlotId=" + timeSlotId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

   
    
    
}
