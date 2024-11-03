package dto.response;

import models.MedicalRecords;
import models.Medicines;

public class PrescriptionsResponse {

    private final int id;
    private final MedicalRecords medicalRecord;
    private final Medicines medicine;
    private final int quantityPrescribed;
    private final String notes;

    private PrescriptionsResponse(Builder builder) {
        this.id = builder.id;
        this.medicalRecord = builder.medicalRecordId;
        this.medicine = builder.medicine;
        this.quantityPrescribed = builder.quantityPrescribed;
        this.notes = builder.notes;
    }

    public int getId() {
        return id;
    }

    public MedicalRecords getMedicalRecordId() {
        return medicalRecord;
    }

    public Medicines getMedicine() {
        return medicine;
    }

    public int getQuantityPrescribed() {
        return quantityPrescribed;
    }

    public String getNotes() {
        return notes;
    }

    public static class Builder {

        private int id;
        private MedicalRecords medicalRecordId;
        private Medicines medicine;
        private int quantityPrescribed;
        private String notes;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder medicalRecord(MedicalRecords medicalRecordId) {
            this.medicalRecordId = medicalRecordId;
            return this;
        }

        public Builder medicine(Medicines medicine) {
            this.medicine = medicine;
            return this;
        }

        public Builder quantityPrescribed(int quantityPrescribed) {
            this.quantityPrescribed = quantityPrescribed;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public PrescriptionsResponse build() {
            return new PrescriptionsResponse(this);
        }
    }
}
