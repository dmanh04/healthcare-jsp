/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Notification {

    private int id;
    private int recipientUserId;
    private int senderUserId;
    private int appointmentId;
    private String message;
    private int isRead;

    private Notification(Builder builder) {
        this.id = builder.id;
        this.recipientUserId = builder.recipientUserId;
        this.senderUserId = builder.senderUserId;
        this.appointmentId = builder.appointmentId;
        this.message = builder.message;
        this.isRead = builder.isRead;
    }

    public static class Builder {

        private int id;
        private int recipientUserId;
        private int senderUserId;
        private int appointmentId;
        private String message;
        private int isRead;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setRecipientUserId(int recipientUserId) {
            this.recipientUserId = recipientUserId;
            return this;
        }

        public Builder setSenderUserId(int senderUserId) {
            this.senderUserId = senderUserId;
            return this;
        }

        public Builder setAppointmentId(int appointmentId) {
            this.appointmentId = appointmentId;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setIsRead(int isRead) {
            this.isRead = isRead;
            return this;
        }

        public Notification build() {
            return new Notification(this);
        }
    }

    public int getId() {
        return id;
    }

    public int getRecipientUserId() {
        return recipientUserId;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getMessage() {
        return message;
    }

    public int getIsRead() {
        return isRead;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", recipientUserId=" + recipientUserId + ", senderUserId=" + senderUserId + ", appointmentId=" + appointmentId + ", message=" + message + ", isRead=" + isRead + '}';
    }
    
    
}
