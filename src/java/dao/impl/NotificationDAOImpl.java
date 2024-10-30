package dao.impl;

import common.utils.DBContext;
import dao.INotificationDAO;
import dto.request.NotificationRequest;
import dto.response.NotificationResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAOImpl extends DBContext implements INotificationDAO {

    @Override
    public void addNotification(NotificationRequest notificationRequest) {
        String query = "INSERT INTO notifications (recipient_user_id, message) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, notificationRequest.getRecipientUserId());
            ps.setString(2, notificationRequest.getMessage());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.err.println("SQL Error adding notification: " + ex.getMessage());
        }
    }

    @Override
    public List<NotificationResponse> getListNotificationResponseByUserId(int id) {
        List<NotificationResponse> notifications = new ArrayList<>();
        String query = "SELECT notification_id, message, is_read, created_at FROM notifications WHERE recipient_user_id = ? "
                + " and is_read = 0";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NotificationResponse notification = new NotificationResponse.Builder()
                        .id(rs.getInt("notification_id"))
                        .message(rs.getString("message"))
                        .isRead(rs.getBoolean("is_read"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .build();
                notifications.add(notification);
            }
        } catch (Exception ex) {
            System.err.println("SQL Error retrieving notifications: " + ex.getMessage());
        }
        return notifications;
    }

    @Override
    public void markAllNotificationsAsRead(int userId) {
        String query = "UPDATE notifications SET is_read = 1 WHERE recipient_user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.err.println("SQL Error marking notifications as read: " + ex.getMessage());
        }
    }
}
