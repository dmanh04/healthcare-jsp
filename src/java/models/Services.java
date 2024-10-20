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
public class Services {

    private int id;
    private String serviceName;
    private String description;
    private double price;
    private int duration;
    private String image;
    private String icon;
    private Date createdAt;
    private Date updatedAt;

    private Services(Builder builder) {
        this.id = builder.id;
        this.serviceName = builder.serviceName;
        this.description = builder.description;
        this.price = builder.price;
        this.duration = builder.duration;
        this.image = builder.image;
        this.icon = builder.icon;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public String getImage() {
        return image;
    }

    public String getIcon() {
        return icon;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public static class Builder {

        private int id;
        private String serviceName;
        private String description;
        private double price;
        private int duration;
        private String image;
        private String icon;
        private Date createdAt;
        private Date updatedAt;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setServiceName(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public Builder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Services build() {
            return new Services(this);
        }
    }

    @Override
    public String toString() {
        return "Services{" + "id=" + id + ", serviceName=" + serviceName + ", description=" + description + ", price=" + price + ", duration=" + duration + ", image=" + image + ", icon=" + icon + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
