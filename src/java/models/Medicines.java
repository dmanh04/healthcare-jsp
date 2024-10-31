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
public class Medicines {

    private int id;
    private String medicineName;
    private String description;
    private int quantityInStock;
    private double price;
    private Date createdAt;
    private Date updatedAt;

    private Medicines(Builder builder) {
        this.id = builder.id;
        this.medicineName = builder.medicineName;
        this.description = builder.description;
        this.quantityInStock = builder.quantityInStock;
        this.price = builder.price;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public double getPrice() {
        return price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public static class Builder {

        private int id;
        private String medicineName;
        private String description;
        private int quantityInStock;
        private double price;
        private Date createdAt;
        private Date updatedAt;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder medicineName(String medicineName) {
            this.medicineName = medicineName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder quantityInStock(int quantityInStock) {
            this.quantityInStock = quantityInStock;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
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

        public Medicines build() {
            return new Medicines(this);
        }
    }

}
