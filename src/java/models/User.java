package models;

import java.util.Date;

public class User {

    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String country;
    private String language;
    private String gender;
    private Date dob;
    private int isActive;
    private Date createdAt;
    private Date updatedAt;

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.country = builder.country;
        this.language = builder.language;
        this.gender = builder.gender;
        this.dob = builder.dob;
        this.isActive = builder.isActive;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getGender() {
        return gender;
    }

    public Date getDob() {
        return dob;
    }

    public int getIsActive() {
        return isActive;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public static class Builder {

        private int id;
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String country;
        private String language;
        private String gender;
        private Date dob;
        private int isActive;
        private Date createdAt;
        private Date updatedAt;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
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

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder dob(Date dob) {
            this.dob = dob;
            return this;
        }

        public Builder isActive(int isActive) {
            this.isActive = isActive;
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

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", country=" + country + ", language=" + language + ", gender=" + gender + ", dob=" + dob + ", isActive=" + isActive + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    
}
