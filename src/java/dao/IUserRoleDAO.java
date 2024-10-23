/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import models.Roles;

/**
 *
 * @author Admin
 */
public interface IUserRoleDAO {
    void add(int userId, int roleId);
    
    void updateByUserId(int userId, int roleId);
    
    void remove(int userId);
}
