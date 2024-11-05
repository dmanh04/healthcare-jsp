package dto.response;

import java.util.Date;
import java.util.List;

public class MedicalRecordHistoryResponse {

    private String customerName;
    private int medicalRecordId;
    private String diagnosis;
    private String treatment;
    private List<String> prescribedMedicines;
    private List<String> purchasedMedicines;
    private Date appointmentDate;

    private MedicalRecordHistoryResponse(Builder builder) {
        this.customerName = builder.customerName;
        this.medicalRecordId = builder.medicalRecordId;
        this.diagnosis = builder.diagnosis;
        this.treatment = builder.treatment;
        this.prescribedMedicines = builder.prescribedMedicines;
        this.purchasedMedicines = builder.purchasedMedicines;
        this.appointmentDate = builder.appointmentDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getMedicalRecordId() {
        return medicalRecordId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public List<String> getPrescribedMedicines() {
        return prescribedMedicines;
    }

    public List<String> getPurchasedMedicines() {
        return purchasedMedicines;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    
    public static class Builder {

        private String customerName;
        private int medicalRecordId;
        private String diagnosis;
        private String treatment;
        private List<String> prescribedMedicines;
        private List<String> purchasedMedicines;
        private Date appointmentDate;

        public Builder() {
        }

        
        public Builder appointmentDate(Date appointmentDate) {
            this.appointmentDate = appointmentDate;
            return this;
        }
        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder medicalRecordId(int medicalRecordId) {
            this.medicalRecordId = medicalRecordId;
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

        public Builder prescribedMedicines(List<String> prescribedMedicines) {
            this.prescribedMedicines = prescribedMedicines;
            return this;
        }

        public Builder purchasedMedicines(List<String> purchasedMedicines) {
            this.purchasedMedicines = purchasedMedicines;
            return this;
        }

        public MedicalRecordHistoryResponse build() {
            return new MedicalRecordHistoryResponse(this);
        }
    }
}
