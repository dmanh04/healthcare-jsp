/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.criteria.UserCriteria;
import dto.request.UserCreationRequest;
import dto.request.UserUpdationRequest;
import dto.response.PageableResponse;
import models.User;

/**
 *
 * @author Admin
 */
public interface IUserDAO {
    
    PageableResponse<User> getAllUserByFilter(UserCriteria userCriteria);
    
    boolean existsByUsername(String username);
    
    boolean existsUpdateByUsername(String username, int id);
    
    boolean createUser(UserCreationRequest userCreationRequest);
    
    User add(User u);
    
    void updateUser(UserUpdationRequest userUpdationRequest);
    
    User findByUsername(String username);
    
    void updatePassword(String newPassword, int id);
    
    void deleteUser(int id);
    
    void updatePhotos(int id, String photos);
    
}
