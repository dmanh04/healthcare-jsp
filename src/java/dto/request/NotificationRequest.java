/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.request;

public class NotificationRequest {

    private final int recipientUserId;
    private final String message;

    private NotificationRequest(Builder builder) {
        this.recipientUserId = builder.recipientUserId;
        this.message = builder.message;
    }

    public int getRecipientUserId() {
        return recipientUserId;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {

        private int recipientUserId;
        private String message;

        public Builder recipientUserId(int recipientUserId) {
            this.recipientUserId = recipientUserId;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public NotificationRequest build() {
            return new NotificationRequest(this);
        }
    }

    @Override
    public String toString() {
        return "NotificationRequest{" + "recipientUserId=" + recipientUserId + ", message=" + message + '}';
    }

}
