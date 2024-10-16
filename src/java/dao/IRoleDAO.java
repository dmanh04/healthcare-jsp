/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.util.Map;
import models.Roles;


public interface IRoleDAO {
    
    Roles findUserByRoleName(String name);
    
    Roles findRoleByID(int roleId);
    
    List<Roles> getAllRole();
    
    Roles findRoleByUserId(int userId);
    
    Map<Integer, String> getRoleIdAndName();
}
