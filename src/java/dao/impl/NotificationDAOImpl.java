package dao.impl;

import common.utils.DBContext;
import dao.INotificationDAO;
import dto.request.NotificationRequest;
import java.sql.PreparedStatement;

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
}
