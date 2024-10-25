/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import common.utils.DBContext;
import dao.IDoctorDAO;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import models.User;

public class DoctorDAOImpl extends DBContext implements IDoctorDAO {

    @Override
    public List<User> findAllDoctor() {
        List<User> doctors = new ArrayList<>();
        String query = "SELECT u.user_id, u.username, u.first_name, u.last_name, u.email, u.phone, "
                + "u.country, u.language, u.gender, u.date_of_birth, u.is_active, "
                + "u.created_at, u.updated_at, u.photos "
                + "FROM users u "
                + "INNER JOIN user_roles ur ON u.user_id = ur.user_id "
                + "INNER JOIN roles r ON ur.role_id = r.role_id "
                + "WHERE r.role_name like '%DOCTOR%' AND u.is_active = 1";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                doctors.add(new User.Builder()
                        .id(rs.getInt("user_id"))
                        .username(rs.getString("username"))
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name"))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .country(rs.getString("country"))
                        .language(rs.getString("language"))
                        .gender(rs.getString("gender"))
                        .dob(rs.getDate("date_of_birth"))
                        .isActive(rs.getInt("is_active"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .updatedAt(rs.getTimestamp("updated_at"))
                        .photos(rs.getString("photos"))
                        .build());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doctors;
    }

}
