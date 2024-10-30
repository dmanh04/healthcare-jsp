package dto.response;

public class MedicalRecordResponse {

    private int id;
    private String diagnosis;
    private String treatment;

    private MedicalRecordResponse(Builder builder) {
        this.id = builder.id;
        this.diagnosis = builder.diagnosis;
        this.treatment = builder.treatment;
    }

    public int getId() {
        return id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }
    public static class Builder {

        private int id;
        private String diagnosis;
        private String treatment;

        public Builder id(int id) {
            this.id = id;
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
        public MedicalRecordResponse build() {
            return new MedicalRecordResponse(this);
        }
    }
}
