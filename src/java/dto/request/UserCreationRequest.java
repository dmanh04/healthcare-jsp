/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.request;

/**
 *
 * @author Admin
 */
public class UserCreationRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private int roleId;


    private UserCreationRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.roleId = builder.roleId;
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

    public String getPhone() {
        return phone;
    }

    public int getRoleId() {
        return roleId;
    }

    public static class Builder {

        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String phone;
        private int roleId;

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

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder roleId(int roleId) {
            this.roleId = roleId;
            return this;
        }

        public UserCreationRequest build() {
            return new UserCreationRequest(this);
        }
    }
}
