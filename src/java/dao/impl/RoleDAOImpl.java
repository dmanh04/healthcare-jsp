/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.IRoleDAO;
import models.Roles;
import common.utils.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class RoleDAOImpl extends DBContext implements IRoleDAO {

    @Override
    public Roles findRoleByID(int roleId) {
        String query = "SELECT * FROM roles WHERE role_id = ?";
        Roles role = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, roleId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    role = new Roles();
                    role.setId(rs.getInt("role_id"));
                    role.setRoleName(rs.getString("role_name"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }

    @Override
    public List<Roles> getAllRole() {
        String query = "SELECT * FROM roles";
        List<Roles> roles = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Roles role = new Roles();
                role.setId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
                roles.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }

    @Override
    public Map<Integer, String> getRoleIdAndName() {
        String query = "SELECT role_id, role_name FROM roles";
        Map<Integer, String> roleMap = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roleMap.put(rs.getInt("role_id"), rs.getString("role_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roleMap;
    }

    @Override
    public Roles findUserByRoleName(String name) {
        String query = "SELECT * FROM Roles WHERE role_name = ?";
        Roles role = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    role = new Roles();
                    role.setId(rs.getInt("role_id"));
                    role.setRoleName(rs.getString("role_name"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }

    @Override
    public Roles findRoleByUserId(int userId) {
        String query = "SELECT r.role_id, r.role_name "
                + "FROM roles r "
                + "JOIN user_roles ur ON r.role_id = ur.role_id "
                + "WHERE ur.user_id = ?";
        System.out.println(query);
        Roles role = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    role = new Roles();
                    role.setId(rs.getInt("role_id"));
                    role.setRoleName(rs.getString("role_name"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }
}
