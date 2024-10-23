package dto.request;

import java.util.Date;

public class UserUpdationRequest {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private int roleId;
    private Date dob;
    private String gender;

    private UserUpdationRequest(Builder builder) {
        this.username = builder.username;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.roleId = builder.roleId;
        this.id = builder.id;
        this.dob = builder.dob;
        this.gender = builder.gender;
    }

    public String getGender() {
        return gender;
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

    public int getRoleId() {
        return roleId;
    }

    public Date getDob() {
        return dob;
    }

    public static class Builder {

        private int id;
        private String username;
        private String firstName;
        private String lastName;
        private String phone;
        private int roleId;
        private Date dob;
        private String gender;

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }
        
        public Builder dob(Date dob) {
            this.dob = dob;
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

        public Builder roleId(int roleId) {
            this.roleId = roleId;
            return this;
        }

        public UserUpdationRequest build() {
            return new UserUpdationRequest(this);
        }
    }
}
