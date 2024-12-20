/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.response;



import java.util.Date;

public class NotificationResponse {

    private final int id;
    private final String message;
    private final boolean isRead;
    private final Date createdAt;

    private NotificationResponse(Builder builder) {
        this.id = builder.id;
        this.message = builder.message;
        this.isRead = builder.isRead;
        this.createdAt = builder.createdAt;
    }

    public int getId() {
        return id;
    }


    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public static class Builder {
        private int id;
        private String message;
        private boolean isRead;
        private Date createdAt;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder isRead(boolean isRead) {
            this.isRead = isRead;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public NotificationResponse build() {
            return new NotificationResponse(this);
        }
    }

    @Override
    public String toString() {
        return "NotificationResponse{" + "id=" + id + ", message=" + message + ", isRead=" + isRead + ", createdAt=" + createdAt + '}';
    }
}
