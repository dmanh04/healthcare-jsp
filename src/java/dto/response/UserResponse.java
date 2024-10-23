/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.response;

import java.util.Date;
import models.Roles;

public class UserResponse {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Roles role;
    private String photos;
    private String gender;
    private Date dob;

    private UserResponse(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.role = builder.role;
        this.photos = builder.photos; 
        this.dob = builder.dob;
        this.gender = builder.gender;
    }

    public static class Builder {

        private int id;
        private String username;
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
        private Roles role;
        private String photos;
        private String gender;
        private Date dob;

        public Builder gender(String gender) {
            this.gender = gender;  
            return this;
        }
        
        public Builder dob(Date dob) {
            this.dob = dob;  
            return this;
        }
        
        public Builder photos(String photos) {
            this.photos = photos;  
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder role(Roles role) {
            this.role = role;
            return this;
        }

        public UserResponse build() {
            return new UserResponse(this);
        }
    }

    public String getGender() {
        return gender;
    }

    public Date getDob() {
        return dob;
    }
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Roles getRole() {
        return role;
    }

    public String getPhotos() {
        return photos;
    }
}
