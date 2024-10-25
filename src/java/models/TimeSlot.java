/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


public class TimeSlot {

    private int id;
    private String time;

    private TimeSlot(Builder builder) {
        this.id = builder.id;
        this.time = builder.time;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public static class Builder {
        private int id;
        private String time;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder time(String time) {
            this.time = time;
            return this;
        }

        public TimeSlot build() {
            return new TimeSlot(this);
        }
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "id=" + id +
                ", time='" + time + '\'' +
                '}';
    }
}
