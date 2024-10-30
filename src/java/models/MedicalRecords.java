/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

import java.util.Date;

public class MedicalRecords {

    private int id;
    private int appointmentId;
    private String diagnosis;
    private String treatment;
    private Date createdAt;
    private Date updatedAt;

    private MedicalRecords(Builder builder) {
        this.id = builder.id;
        this.appointmentId = builder.appointmentId;
        this.diagnosis = builder.diagnosis;
        this.treatment = builder.treatment;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {
        private int id;
        private int appointmentId;
        private String diagnosis;
        private String treatment;
        private Date createdAt;
        private Date updatedAt;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder appointmentId(int appointmentId) {
            this.appointmentId = appointmentId;
            return this;
        }

        public Builder diagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
            return this;
        }

        public Builder treatment(String treatment) {
            this.treatment = treatment;
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

        public MedicalRecords build() {
            return new MedicalRecords(this);
        }
    }

    public int getId() {
        return id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "MedicalRecords{" + "id=" + id + ", appointmentId=" + appointmentId + ", diagnosis=" + diagnosis + ", treatment=" + treatment + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    
}
