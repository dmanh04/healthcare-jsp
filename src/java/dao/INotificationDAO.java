/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.request.NotificationRequest;
import dto.response.NotificationResponse;
import java.util.List;


public interface INotificationDAO {
    void addNotification(NotificationRequest notificationRequest);
    
    List<NotificationResponse> getListNotificationResponseByUserId(int id);
    
    void markAllNotificationsAsRead(int id);
}
