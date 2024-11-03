package dto.request;

public class PrescriptionsRequest {

    private int recordId;
    private int medicineId;
    private int quantityPrescribed;
    private String notes;

    private PrescriptionsRequest(Builder builder) {
        this.recordId = builder.recordId;
        this.medicineId = builder.medicineId;
        this.quantityPrescribed = builder.quantityPrescribed;
        this.notes = builder.notes;
    }

    public int getRecordId() {
        return recordId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public int getQuantityPrescribed() {
        return quantityPrescribed;
    }

    public String getNotes() {
        return notes;
    }

    public static class Builder {

        private int recordId;
        private int medicineId;
        private int quantityPrescribed;
        private String notes;

        public Builder recordId(int recordId) {
            this.recordId = recordId;
            return this;
        }

        public Builder medicineId(int medicineId) {
            this.medicineId = medicineId;
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

        public PrescriptionsRequest build() {
            return new PrescriptionsRequest(this);
        }
    }

    @Override
    public String toString() {
        return "PrescriptionsRequest{"
                + "recordId=" + recordId
                + ", medicineId=" + medicineId
                + ", quantityPrescribed=" + quantityPrescribed
                + ", notes='" + notes + '\''
                + '}';
    }
}
