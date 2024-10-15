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
public class UserRoleDAOImpl extends DBContext implements IUserRoleDAO{

    @Override
    public void add(int userId, int roleId) {
        String query = "INSERT INTO UserRoles (user_id, role_id) VALUES (?, ?)";
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
        String query = "DELETE FROM UserRoles WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
}
