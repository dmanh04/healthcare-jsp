package models;

public class MedicinePurchases {

    private int id;
    private int prescriptionId;
    private int medicineId;
    private int quantityPurchased;
    private double totalPrice;

    private MedicinePurchases(Builder builder) {
        this.id = builder.id;
        this.prescriptionId = builder.prescriptionId;
        this.medicineId = builder.medicineId;
        this.quantityPurchased = builder.quantityPurchased;
        this.totalPrice = builder.totalPrice;
    }

    public static class Builder {

        private int id;
        private int prescriptionId;
        private int medicineId;
        private int quantityPurchased;
        private double totalPrice; 

        public Builder id(int id) {
            this.id = id;
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

        public Builder totalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public MedicinePurchases build() {
            return new MedicinePurchases(this);
        }
    }

    public int getId() {
        return id;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "MedicinePurchases{" + "id=" + id + ", prescriptionId=" + prescriptionId + ", medicineId=" + medicineId + ", quantityPurchased=" + quantityPurchased + ", totalPrice=" + totalPrice + '}';
    }
}
