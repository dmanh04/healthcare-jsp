package dao.impl;

import common.constants.SystemConstant;
import dao.IUserDAO;
import models.User;
import common.utils.DBContext;
import common.utils.IntegerUtils;
import common.utils.StringUtils;
import dto.criteria.UserCriteria;
import dto.request.UserCreationRequest;
import dto.request.UserUpdationRequest;
import dto.response.PageableResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOImpl extends DBContext implements IUserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());

    @Override
    public boolean existsByUsername(String username) {
        String query = "SELECT COUNT(*) FROM Users WHERE is_active = 1 AND username = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error checking if username exists: {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public User add(User u) {
        String query = "INSERT INTO Users (username, password, first_name, last_name, email, phone, country, language, "
                + "gender, is_active, photos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFirstName());
            ps.setString(4, u.getLastName());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPhone());
            ps.setString(7, u.getCountry());
            ps.setString(8, u.getLanguage());
            ps.setString(9, u.getGender());
            ps.setInt(10, u.getIsActive());
            ps.setString(11, u.getPhotos());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return u;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error adding user: {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM Users WHERE username = ? AND is_active = 1";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User.Builder()
                            .id(rs.getInt("user_id"))
                            .username(rs.getString("username"))
                            .password(rs.getString("password"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .email(rs.getString("email"))
                            .phone(rs.getString("phone"))
                            .country(rs.getString("country"))
                            .language(rs.getString("language"))
                            .gender(rs.getString("gender"))
                            .isActive(rs.getInt("is_active"))
                            .photos(rs.getString("photos"))
                            .build();
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error finding user by username: {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public PageableResponse<User> getAllUserByFilter(UserCriteria userCriteria) {
        List<User> userList = new ArrayList<>();
        StringBuilder query = new StringBuilder(buildQueryFilter(userCriteria))
                .append(" ORDER BY u.user_id ")
                .append(" OFFSET ").append((userCriteria.getPage() - 1) * userCriteria.getLimit())
                .append(" ROWS FETCH NEXT ").append(userCriteria.getLimit()).append(" ROWS ONLY");
        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            setQueryParameters(ps, userCriteria);
            LOGGER.info(String.format("Query: %s", query));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    userList.add(buildUser(rs));
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching users with filter: {0}", e.getMessage());
        }

        int totalUsers = getTotalUserCount(userCriteria);
        int totalPage = (int) Math.ceil((double) totalUsers / userCriteria.getLimit());

        return new PageableResponse.Builder<User>()
                .totalPage(totalPage)
                .page(userCriteria.getPage())
                .size(userList.size())
                .data(userList)
                .build();
    }

    private int getTotalUserCount(UserCriteria userCriteria) {
        int totalCount = 0;
        String query = buildTotalCountQuery(userCriteria);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            setQueryParameters(ps, userCriteria);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalCount = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving user count: {0}", e.getMessage());
        }
        return totalCount;
    }

    private String buildQueryFilter(UserCriteria userCriteria) {
        StringBuilder query = new StringBuilder("SELECT * FROM users u ");
        if (IntegerUtils.isPositive(userCriteria.getRoleId())) {
            query.append("JOIN user_roles ur ON u.user_id = ur.user_id ");
        }
        query.append("WHERE is_active = 1 ");
        if (IntegerUtils.isPositive(userCriteria.getRoleId())) {
            query.append("AND ur.role_id = ? ");
        }
        if (StringUtils.checkString(userCriteria.getUsername())) {
            query.append("AND u.username LIKE ? ");
        }
        if (StringUtils.checkString(userCriteria.getFullname())) {
            query.append("AND CONCAT(u.first_name, ' ', u.last_name) LIKE ? ");
        }
        return query.toString();
    }

    private String buildTotalCountQuery(UserCriteria userCriteria) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM users u ");
        if (IntegerUtils.isPositive(userCriteria.getRoleId())) {
            query.append("JOIN user_roles ur ON u.user_id = ur.user_id ");
        }
        query.append("WHERE is_active = 1 ");
        if (IntegerUtils.isPositive(userCriteria.getRoleId())) {
            query.append("AND ur.role_id = ? ");
        }
        if (StringUtils.checkString(userCriteria.getUsername())) {
            query.append("AND u.username LIKE ? ");
        }
        if (StringUtils.checkString(userCriteria.getFullname())) {
            query.append("AND CONCAT(u.first_name, ' ', u.last_name) LIKE ? ");
        }
        return query.toString();
    }

    private void setQueryParameters(PreparedStatement ps, UserCriteria userCriteria) throws Exception {
        int index = 1;
        if (IntegerUtils.isPositive(userCriteria.getRoleId())) {
            ps.setInt(index++, userCriteria.getRoleId());
        }
        if (StringUtils.checkString(userCriteria.getUsername())) {
            ps.setString(index++, "%" + userCriteria.getUsername() + "%");
        }
        if (StringUtils.checkString(userCriteria.getFullname())) {
            ps.setString(index++, "%" + userCriteria.getFullname() + "%");
        }
    }

    private User buildUser(ResultSet rs) throws Exception {
        return new User.Builder()
                .id(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .country(rs.getString("country"))
                .language(rs.getString("language"))
                .gender(rs.getString("gender"))
                .dob(rs.getDate("date_of_birth"))
                .isActive(rs.getInt("is_active"))
                .createdAt(rs.getTimestamp("created_at"))
                .updatedAt(rs.getTimestamp("updated_at"))
                .photos(rs.getString("photos"))
                .build();
    }

    @Override
    public boolean existsUpdateByUsername(String username, int id) {
        String query = "SELECT COUNT(*) FROM Users WHERE is_active = 1 AND username = ? AND user_id != ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setInt(2, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error checking if username exists for update: {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean createUser(UserCreationRequest userCreationRequest) {
        String query = "INSERT INTO users (username, password, first_name, last_name, phone, email ,is_active, photos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userCreationRequest.getUsername());
            ps.setString(2, userCreationRequest.getPassword());
            ps.setString(3, userCreationRequest.getFirstName());
            ps.setString(4, userCreationRequest.getLastName());
            ps.setString(5, userCreationRequest.getPhone());
            ps.setString(6, userCreationRequest.getUsername());
            ps.setInt(7, 1);
            ps.setString(8, SystemConstant.PHOTOS_DEFAULT);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error creating user: {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public void deleteUser(int id) {
        String query = "UPDATE users SET is_active = 0 WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                LOGGER.log(Level.INFO, "User with ID {0} marked as inactive successfully.", id);
            } else {
                LOGGER.log(Level.WARNING, "No user found with ID {0}.", id);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error marking user as inactive: {0}", ex.getMessage());
        }
    }

    @Override
    public void updateUser(UserUpdationRequest userUpdationRequest) {
        String query = "UPDATE users SET username = ?, first_name = ?, last_name = ?, phone = ? "
                + "WHERE user_id = ? AND is_active = 1";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userUpdationRequest.getUsername());
            ps.setString(2, userUpdationRequest.getFirstName());
            ps.setString(3, userUpdationRequest.getLastName());
            ps.setString(4, userUpdationRequest.getPhone());
            ps.setInt(5, userUpdationRequest.getId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                LOGGER.log(Level.INFO, "User with ID {0} updated successfully.", userUpdationRequest.getId());
            } else {
                LOGGER.log(Level.WARNING, "No active user found with ID {0}.", userUpdationRequest.getId());
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error updating user with ID {0}: {1}", new Object[]{userUpdationRequest.getId(), ex.getMessage()});
        }
    }

    @Override
    public void updatePhotos(int id, String photos) {
        String query = "UPDATE users SET photos = ? WHERE user_id = ? AND is_active = 1";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, photos);
            ps.setInt(2, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                LOGGER.log(Level.INFO, "Photos updated successfully for user with ID {0}.", id);
            } else {
                LOGGER.log(Level.WARNING, "No active user found with ID {0} for photo update.", id);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error updating photos for user with ID {0}: {1}", new Object[]{id, ex.getMessage()});
        }
    }
}
