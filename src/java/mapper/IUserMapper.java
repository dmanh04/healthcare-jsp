/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import dto.response.PageableResponse;
import dto.response.UserResponse;
import models.User;


public interface IUserMapper {
    
    UserResponse toUserReponse(User user);
    
    PageableResponse<UserResponse> toPageableResponse(PageableResponse<User> pageableResponse);
    
    
}
