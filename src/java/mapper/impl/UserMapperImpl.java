package mapper.impl;

import dao.IRoleDAO;
import dao.impl.RoleDAOImpl;
import dto.response.PageableResponse;
import dto.response.UserResponse;
import java.util.List;
import mapper.IUserMapper;

import models.Roles;
import models.User;

public class UserMapperImpl implements IUserMapper {
    
    private final IRoleDAO roleDAO;
    
    public UserMapperImpl() {
        this.roleDAO = new RoleDAOImpl();
    }
    
    @Override
    public UserResponse toUserReponse(User user) {
        Roles role = roleDAO.findRoleByUserId(user.getId());
        return new UserResponse.Builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .role(role)
                .photos(user.getPhotos())
                .gender(user.getGender())
                .dob(user.getDob())
                .build();
    }
    
    @Override
    public PageableResponse<UserResponse> toPageableResponse(PageableResponse<User> pageableResponse) {
        List<UserResponse> listUserResponses = pageableResponse.getData().stream()
                .map(it -> toUserReponse(it))
                .toList();
        return new PageableResponse.Builder<UserResponse>()
                .data(listUserResponses)
                .page(pageableResponse.getPage())
                .size(pageableResponse.getSize())
                .totalPage(pageableResponse.getTotalPage())
                .build();
    }
    
}
