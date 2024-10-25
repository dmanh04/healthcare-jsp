
package dto.response;




public class TimeSlotResponse {
    private int id;
    private String time;
    private boolean isBooked;

    private TimeSlotResponse(Builder builder) {
        this.id = builder.id;
        this.time = builder.time;
        this.isBooked = builder.isBooked;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public boolean isIsBooked() {
        return isBooked;
    }
    
    
    
    

    public static class Builder {
        private int id;
        private String time;
        private boolean isBooked;

        public Builder isBooked(boolean isBooked) {
            this.isBooked = isBooked;
            return this;
        }
        
        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder time(String time) {
            this.time = time;
            return this;
        }

        public TimeSlotResponse build() {
            return new TimeSlotResponse(this);
        }
    }

    @Override
    public String toString() {
        return "TimeSlotResponse{" + "id=" + id + ", time=" + time + ", isBooked=" + isBooked + '}';
    }

    
}
