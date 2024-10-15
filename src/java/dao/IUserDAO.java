/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import models.User;

/**
 *
 * @author Admin
 */
public interface IUserDAO {
    
    boolean existsByUsername(String username);
    
    User add(User u);
    
}
