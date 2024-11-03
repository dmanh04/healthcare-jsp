package models;

public class MedicinePurchases {

    private int id;
    private int customerId;
    private int prescriptionId;
    private int medicineId;
    private int quantityPurchased;
    private int totalPrice;

    private MedicinePurchases(Builder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.prescriptionId = builder.prescriptionId;
        this.medicineId = builder.medicineId;
        this.quantityPurchased = builder.quantityPurchased;
        this.totalPrice = builder.totalPrice;
    }

    public static class Builder {

        private int id;
        private int customerId;
        private int prescriptionId;
        private int medicineId;
        private int quantityPurchased;
        private int totalPrice;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder prescriptionId(int prescriptionId) {
            this.prescriptionId = prescriptionId;
            return this;
        }

        public Builder medicineId(int medicineId) {
            this.medicineId = medicineId;
            return this;
        }

        public Builder quantityPurchased(int quantityPurchased) {
            this.quantityPurchased = quantityPurchased;
            return this;
        }

        public Builder totalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public MedicinePurchases build() {
            return new MedicinePurchases(this);
        }
    }
}
