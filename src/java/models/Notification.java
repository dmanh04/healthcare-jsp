/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Notification {

    private int id;
    private int recipientUserId;
    private String message;
    private int isRead;

    private Notification(Builder builder) {
        this.id = builder.id;
        this.recipientUserId = builder.recipientUserId;
        this.message = builder.message;
        this.isRead = builder.isRead;
    }

    public static class Builder {
        private int id;
        private int recipientUserId;
        private String message;
        private int isRead;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder userId(int recipientUserId) {
            this.recipientUserId = recipientUserId;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder isRead(int isRead) {
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

    public String getMessage() {
        return message;
    }

    public int getIsRead() {
        return isRead;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", recipientUserId=" + recipientUserId + ", message=" + message + ", isRead=" + isRead + '}';
    }

}
