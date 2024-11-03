package dao.impl;

import common.utils.DBContext;
import dao.IPrescriptionsDAO;
import dto.request.PrescriptionsRequest;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Prescriptions;

public class PrescriptionsDAOImpl extends DBContext implements IPrescriptionsDAO {

    @Override
    public void addPrescriptions(PrescriptionsRequest request) {
        String sql = "INSERT INTO prescriptions (medical_record_id, medicine_id, quantity_prescribed, notes) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, request.getRecordId());
            ps.setInt(2, request.getMedicineId());
            ps.setInt(3, request.getQuantityPrescribed());
            ps.setString(4, request.getNotes());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Prescriptions> getAllPrescriptionsByMedicalRecordId(int id) {
        String sql = "SELECT prescription_id, medical_record_id, medicine_id, quantity_prescribed, notes, created_at, updated_at "
                + "FROM prescriptions WHERE medical_record_id = ?";
        List<Prescriptions> prescriptionsList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prescriptions prescription = new Prescriptions.Builder()
                            .id(rs.getInt("prescription_id"))
                            .recordId(rs.getInt("medical_record_id"))
                            .medicineId(rs.getInt("medicine_id"))
                            .quantityPrescribed(rs.getInt("quantity_prescribed"))
                            .notes(rs.getString("notes"))
                            .createdAt(rs.getTimestamp("created_at"))
                            .updatedDate(rs.getTimestamp("updated_at"))
                            .build();

                    prescriptionsList.add(prescription);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prescriptionsList;
    }

    @Override
    public void removeByPrescriptionsId(int id) {
        String sql = "DELETE FROM prescriptions WHERE prescription_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                Logger.getLogger(PrescriptionsDAOImpl.class.getName()).log(Level.WARNING, "No prescription found with ID: " + id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Prescriptions> getPrescriptionsByAppointmentId(int id) {
        String sql = "SELECT p.prescription_id, p.medical_record_id, p.medicine_id, "
                + "p.quantity_prescribed, p.notes, p.created_at, p.updated_at "
                + "FROM prescriptions p "
                + "JOIN medical_records mr ON p.medical_record_id = mr.record_id "
                + "JOIN appointments a ON mr.appointment_id = a.appointment_id "
                + "WHERE a.appointment_id = ?";

        List<Prescriptions> prescriptionsList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prescriptions prescription = new Prescriptions.Builder()
                            .id(rs.getInt("prescription_id"))
                            .recordId(rs.getInt("medical_record_id"))
                            .medicineId(rs.getInt("medicine_id"))
                            .quantityPrescribed(rs.getInt("quantity_prescribed"))
                            .notes(rs.getString("notes"))
                            .createdAt(rs.getTimestamp("created_at"))
                            .updatedDate(rs.getTimestamp("updated_at"))
                            .build();
                    prescriptionsList.add(prescription);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prescriptionsList;
    }

}
