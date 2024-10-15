
package models;

import java.util.Date;


public class Appointments {
    private int id;
    private int customerId;
    private int doctorId;
    private int serviceId;
    private Date appointmentDate;
    private String status;
    private String note;
    private Date createdAt;
    private Date updateddAt;

    public Appointments() {
    }

    public Appointments(int id, int customerId, int doctorId, int serviceId, Date appointmentDate, String status, String note, Date createdAt, Date updateddAt) {
        this.id = id;
        this.customerId = customerId;
        this.doctorId = doctorId;
        this.serviceId = serviceId;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.note = note;
        this.createdAt = createdAt;
        this.updateddAt = updateddAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateddAt() {
        return updateddAt;
    }

    public void setUpdateddAt(Date updateddAt) {
        this.updateddAt = updateddAt;
    }
    
    
}
