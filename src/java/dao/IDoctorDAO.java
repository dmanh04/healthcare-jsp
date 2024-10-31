/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import models.User;

/**
 *
 * @author Admin
 */
public interface IDoctorDAO {
    
    List<User> findAllDoctorByIsActive(int isActive);
    
    User findDoctorById(int id);
    
    List<User> selectFourDoctors();
    
    List<User> findAllDoctor();
    
     User findDoctorByIdNotNeedActive(int id);
    
}
