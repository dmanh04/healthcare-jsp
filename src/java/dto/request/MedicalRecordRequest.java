/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.request;

/**
 *
 * @author Admin
 */
public class MedicalRecordRequest {

    private int recordId;
    private int appointmentId;
    private String diagnosis;
    private String treatment;
    private int recipientUserId;

    private MedicalRecordRequest(Builder builder) {
        this.appointmentId = builder.appointmentId;
        this.diagnosis = builder.diagnosis;
        this.treatment = builder.treatment;
        this.recordId = builder.recordId;
        this.recipientUserId = builder.recipientUserId;
    }

    public static class Builder {

        private int appointmentId;
        private String diagnosis;
        private String treatment;
        private int recordId;
        private int recipientUserId;

        public Builder recipientUserId(int recipientUserId) {
            this.recipientUserId = recipientUserId;
            return this;
        }

        public Builder recordId(int recordId) {
            this.recordId = recordId;
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

        public MedicalRecordRequest build() {
            return new MedicalRecordRequest(this);
        }
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public int getRecipientUserId() {
        return recipientUserId;
    }

    public void setRecipientUserId(int recipientUserId) {
        this.recipientUserId = recipientUserId;
    }

    @Override
    public String toString() {
        return "MedicalRecordRequest{" + "recordId=" + recordId + ", appointmentId=" + appointmentId + ", diagnosis=" + diagnosis + ", treatment=" + treatment + ", recipientUserId=" + recipientUserId + '}';
    }
}
