/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import common.constants.SystemConstant;
import common.utils.DBContext;
import dao.IAppointmentDAO;
import dto.request.AppointmentRequest;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Appointments;

public class AppointmentDAOImpl extends DBContext implements IAppointmentDAO {

    private static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());

    @Override
    public void addAppointment(AppointmentRequest appointmentRequest) {
        String query = "INSERT INTO appointments (customer_id, doctor_id, service_id, appointment_date, notes, phone, time_slot_id ) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, appointmentRequest.getCustomerId());
            ps.setInt(2, appointmentRequest.getDoctorId());
            ps.setInt(3, appointmentRequest.getServiceId());
            ps.setDate(4, (Date) appointmentRequest.getAppointmentDate());
            ps.setString(5, appointmentRequest.getNote());
            ps.setString(6, appointmentRequest.getPhone());
            ps.setInt(7, appointmentRequest.getTimeSlotId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                LOGGER.log(Level.INFO, "Appointment added successfully.");
            } else {
                LOGGER.log(Level.WARNING, "No appointment was added.");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error adding appointment: {0}", ex.getMessage());
        }
    }

    @Override
    public List<Appointments> getListAppointmentsByDoctorIdAndDate(int doctorId, java.util.Date date) {
        List<Appointments> appointmentsList = new ArrayList<>();
        String query = "SELECT * FROM appointments WHERE doctor_id = ? AND appointment_date = ? AND status != ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, doctorId);
            ps.setDate(2, new java.sql.Date(date.getTime()));
            ps.setString(3, SystemConstant.CANCELLED); 
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointments appointment = new Appointments.Builder()
                            .id(rs.getInt("appointment_id"))
                            .customerId(rs.getInt("customer_id"))
                            .doctorId(rs.getInt("doctor_id"))
                            .serviceId(rs.getInt("service_id"))
                            .appointmentDate(rs.getDate("appointment_date"))
                            .status(rs.getString("status"))
                            .note(rs.getString("notes"))
                            .phone(rs.getString("phone"))
                            .timeSlotId(rs.getInt("time_slot_id"))
                            .createdAt(rs.getTimestamp("created_at"))
                            .updatedAt(rs.getTimestamp("updated_at"))
                            .build();
                    appointmentsList.add(appointment);
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "SQL Error retrieving appointments: {0}", ex);
        }
        return appointmentsList;
    }

    @Override
    public List<Appointments> getListAppointmentsByCustomerId(int id) {
        List<Appointments> appointmentsList = new ArrayList<>();
        String query = "SELECT * FROM appointments WHERE customer_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointments appointment = new Appointments.Builder()
                            .id(rs.getInt("appointment_id"))
                            .customerId(rs.getInt("customer_id"))
                            .doctorId(rs.getInt("doctor_id"))
                            .serviceId(rs.getInt("service_id"))
                            .appointmentDate(rs.getDate("appointment_date"))
                            .status(rs.getString("status"))
                            .note(rs.getString("notes"))
                            .phone(rs.getString("phone"))
                            .timeSlotId(rs.getInt("time_slot_id"))
                            .createdAt(rs.getTimestamp("created_at"))
                            .updatedAt(rs.getTimestamp("updated_at"))
                            .build();
                    appointmentsList.add(appointment);
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "SQL Error retrieving appointments by customer ID: {0}", ex.getMessage());
        }
        return appointmentsList;
    }

    @Override
    public void updateStatusById(int id, String status) {
        String query = "UPDATE appointments SET status = ? WHERE appointment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                LOGGER.log(Level.INFO, "Appointment status updated successfully.");
            } else {
                LOGGER.log(Level.WARNING, "No appointment was updated.");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error updating appointment status: {0}", ex.getMessage());
        }
    }
}
