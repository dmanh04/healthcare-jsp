/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import common.constants.SystemConstant;
import common.utils.DBContext;
import dao.IAppointmentDAO;
import dto.criteria.AppointmentCriteria;
import dto.request.AppointmentRequest;
import dto.response.PageableResponse;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import models.Appointments;

public class AppointmentDAOImpl extends DBContext implements IAppointmentDAO {

    private static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());

    @Override
    public void addAppointment(AppointmentRequest appointmentRequest) {
        String query = "INSERT INTO appointments (customer_id, doctor_id, service_id, appointment_date, notes, phone, time_slot_id, customer_name) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, appointmentRequest.getCustomerId());
            ps.setInt(2, appointmentRequest.getDoctorId());
            ps.setInt(3, appointmentRequest.getServiceId());
            ps.setDate(4, (Date) appointmentRequest.getAppointmentDate());
            ps.setString(5, appointmentRequest.getNote());
            ps.setString(6, appointmentRequest.getPhone());
            ps.setInt(7, appointmentRequest.getTimeSlotId());
            ps.setString(8, appointmentRequest.getName());
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
                    appointmentsList.add(buildAppointment(rs));
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
                    appointmentsList.add(buildAppointment(rs));
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

    @Override
    public PageableResponse<Appointments> getListAppointmentsByFilterAdmin(AppointmentCriteria appointmentCriteria) {
        List<Appointments> listAppointments = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT a.* FROM appointments a ")
                .append(buildQueryFilter(appointmentCriteria))
                .append(" ORDER BY a.appointment_id DESC ")
                .append(" OFFSET ").append((appointmentCriteria.getPage() - 1) * appointmentCriteria.getLimit())
                .append(" ROWS FETCH NEXT ").append(appointmentCriteria.getLimit()).append(" ROWS ONLY");

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            setQueryParameters(ps, appointmentCriteria);
            LOGGER.info(String.format("Query: %s", query));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listAppointments.add(buildAppointment(rs));
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching appointments with filter: {0}", e.getMessage());
        }

        int totalAppointments = getTotalAppointmentCount(appointmentCriteria);
        int totalPage = (totalAppointments + appointmentCriteria.getLimit() - 1) / appointmentCriteria.getLimit();

        return new PageableResponse.Builder<Appointments>()
                .totalPage(totalPage)
                .page(appointmentCriteria.getPage())
                .size(listAppointments.size())
                .data(listAppointments)
                .build();
    }

    public String buildQueryFilter(AppointmentCriteria appointmentCriteria) {
        StringBuilder query = new StringBuilder(" ");

        if (appointmentCriteria.getServiceId() != null) {
            query.append("JOIN services s ON a.service_id = s.service_id ");
        }
        if (appointmentCriteria.getListTimeSlotId() != null && !appointmentCriteria.getListTimeSlotId().isEmpty()) {
            query.append("JOIN time_slots t ON a.time_slot_id = t.timeslot_id ");
        }
        query.append("WHERE 1=1 ");

        if (appointmentCriteria.getDoctorId() != null) {
            query.append("AND a.doctor_id = ? ");
        }

        if (appointmentCriteria.getServiceId() != null) {
            query.append("AND a.service_id = ? ");
        }
        if (appointmentCriteria.getListTimeSlotId() != null && !appointmentCriteria.getListTimeSlotId().isEmpty()) {
            query.append("AND a.time_slot_id IN (")
                    .append(appointmentCriteria.getListTimeSlotId().stream()
                            .map(id -> "?")
                            .collect(Collectors.joining(", ")))
                    .append(") ");
        }
        if (appointmentCriteria.getCustomerName() != null && !appointmentCriteria.getCustomerName().isEmpty()) {
            query.append("AND a.customer_name LIKE ? ");
        }
        if (appointmentCriteria.getPhone() != null && !appointmentCriteria.getPhone().isEmpty()) {
            query.append("AND a.phone LIKE ? ");
        }
        if (appointmentCriteria.getStatus() != null && !appointmentCriteria.getStatus().isEmpty()) {
            query.append("AND a.status = ? ");
        }
        if (appointmentCriteria.getStartDate() != null && appointmentCriteria.getEndDate() != null) {
            query.append("AND a.appointment_date BETWEEN ? AND ? ");
        } else if (appointmentCriteria.getStartDate() != null) {
            query.append("AND a.appointment_date >= ? ");
        } else if (appointmentCriteria.getEndDate() != null) {
            query.append("AND a.appointment_date <= ? ");
        }

        if (appointmentCriteria.getDoctorIdCurrent() != null) {
            query.append("AND a.doctor_id = ? ");
        }
        return query.toString();
    }

    private void setQueryParameters(PreparedStatement ps, AppointmentCriteria appointmentCriteria) throws Exception {
        int index = 1;

        if (appointmentCriteria.getDoctorId() != null) {
            ps.setInt(index++, appointmentCriteria.getDoctorId());
        }
        if (appointmentCriteria.getServiceId() != null) {
            ps.setInt(index++, appointmentCriteria.getServiceId());
        }
        if (appointmentCriteria.getListTimeSlotId() != null && !appointmentCriteria.getListTimeSlotId().isEmpty()) {
            for (Integer timeSlotId : appointmentCriteria.getListTimeSlotId()) {
                ps.setInt(index++, timeSlotId);
            }
        }
        if (appointmentCriteria.getCustomerName() != null && !appointmentCriteria.getCustomerName().isEmpty()) {
            ps.setString(index++, "%" + appointmentCriteria.getCustomerName() + "%");
        }
        if (appointmentCriteria.getPhone() != null && !appointmentCriteria.getPhone().isEmpty()) {
            ps.setString(index++, "%" + appointmentCriteria.getPhone() + "%");
        }
        if (appointmentCriteria.getStatus() != null && !appointmentCriteria.getStatus().isEmpty()) {
            ps.setString(index++, appointmentCriteria.getStatus());
        }
        if (appointmentCriteria.getStartDate() != null && appointmentCriteria.getEndDate() != null) {
            ps.setDate(index++, new java.sql.Date(appointmentCriteria.getStartDate().getTime()));
            ps.setDate(index++, new java.sql.Date(appointmentCriteria.getEndDate().getTime()));
        } else if (appointmentCriteria.getStartDate() != null) {
            ps.setDate(index++, new java.sql.Date(appointmentCriteria.getStartDate().getTime()));
        } else if (appointmentCriteria.getEndDate() != null) {
            ps.setDate(index++, new java.sql.Date(appointmentCriteria.getEndDate().getTime()));
        }

        if (appointmentCriteria.getDoctorIdCurrent() != null) {
            ps.setInt(index++, appointmentCriteria.getDoctorIdCurrent());
        }
    }

    private int getTotalAppointmentCount(AppointmentCriteria appointmentCriteria) {
        String countQuery = "SELECT COUNT(*) FROM appointments a " + buildQueryFilter(appointmentCriteria);
        try (PreparedStatement ps = connection.prepareStatement(countQuery)) {
            setQueryParameters(ps, appointmentCriteria);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error counting appointments with filter: {0}", e.getMessage());
        }
        return 0;
    }

    private Appointments buildAppointment(ResultSet rs) throws SQLException {
        return new Appointments.Builder()
                .id(rs.getInt("appointment_id"))
                .customerId(rs.getInt("customer_id"))
                .customerName(rs.getString("customer_name"))
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
    }

    @Override
    public Appointments getAppointmentsById(int id) {
        String query = "SELECT * FROM appointments WHERE appointment_id = ?";
        Appointments appointment = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    appointment = buildAppointment(rs);
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving appointment by ID: {0}", ex.getMessage());
        }
        return appointment;
    }

}
