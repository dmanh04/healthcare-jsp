/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Prescriptions {

    private int id;
    private int recordId;
    private int medicineId;
    private int quantityPrescribed;
    private String notes;
    private Date createdAt;
    private Date updatedDate;

    private Prescriptions(Builder builder) {
        this.id = builder.id;
        this.recordId = builder.recordId;
        this.medicineId = builder.medicineId;
        this.quantityPrescribed = builder.quantityPrescribed;
        this.notes = builder.notes;
        this.createdAt = builder.createdAt;
        this.updatedDate = builder.updatedDate;
    }

    public static class Builder {

        private int id;
        private int recordId;
        private int medicineId;
        private int quantityPrescribed;
        private String notes;
        private Date createdAt;
        private Date updatedDate;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

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

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedDate(Date updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public Prescriptions build() {
            return new Prescriptions(this);
        }
    }

    public int getId() {
        return id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    
    @Override
    public String toString() {
        return "Prescriptions{" + "id=" + id + ", recordId=" + recordId + ", medicineId=" + medicineId + ", quantityPrescribed=" + quantityPrescribed + ", notes=" + notes + ", createdAt=" + createdAt + ", updatedDate=" + updatedDate + '}';
    }    
}
