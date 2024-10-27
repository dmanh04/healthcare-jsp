/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import common.utils.DBContext;
import dao.ITimeSlotDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.TimeSlot;

/**
 *
 * @author Admin
 */
public class TimeSlotDAOImpl extends DBContext implements ITimeSlotDAO {

    @Override
    public List<TimeSlot> getAllTimeSlot() {
        String query = "SELECT * FROM time_slots";
        List<TimeSlot> listTimeSlot = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listTimeSlot.add(new TimeSlot.Builder()
                        .id(rs.getInt("timeslot_id"))
                        .time(rs.getString("time"))
                        .build());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTimeSlot;
    }

    @Override
    public TimeSlot getTimeSlotById(int id) {
        String query = "SELECT * FROM time_slots WHERE timeslot_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TimeSlot.Builder()
                            .id(rs.getInt("timeslot_id"))
                            .time(rs.getString("time"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
}
