/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.IUserDAO;
import models.User;
import common.utils.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class UserDAOImpl extends DBContext implements IUserDAO {

    @Override
    public boolean existsByUsername(String username) {
        String query = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public User add(User u) {
        String query = "INSERT INTO Users (username, password, first_name, last_name, email, phone, country, language,"
                + " gender, date_of_birth, "
                + " is_active, created_at, updated_at) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFirstName());
            ps.setString(4, u.getLastName());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPhone());
            ps.setString(7, u.getCountry());
            ps.setString(8, u.getLanguage());
            ps.setString(9, u.getGender());
            ps.setDate(10, new java.sql.Date(u.getDob().getTime()));
            ps.setInt(11, u.getIsActive());
            ps.setDate(12, new java.sql.Date(u.getCreatedAt().getTime()));
            ps.setDate(13, new java.sql.Date(u.getUpdatedAt().getTime()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return u; 
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }

}
