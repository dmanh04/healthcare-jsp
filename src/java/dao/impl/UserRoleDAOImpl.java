/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.IUserRoleDAO;
import common.utils.DBContext;
import common.utils.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class UserRoleDAOImpl extends DBContext implements IUserRoleDAO {

    @Override
    public void add(int userId, int roleId) {
        String query = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, roleId);
            ps.executeUpdate();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int userId) {
        String query = "DELETE FROM user_roles WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateByUserId(int userId, int roleId) {
        String query = "UPDATE user_roles SET role_id = ? WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, roleId);
            ps.setInt(2, userId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                java.util.logging.Logger.getLogger(DBContext.class.getName())
                        .log(java.util.logging.Level.INFO, "User role updated successfully for userId: {0}", userId);
            } else {
                java.util.logging.Logger.getLogger(DBContext.class.getName())
                        .log(java.util.logging.Level.WARNING, "No user found with userId: {0}", userId);
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
