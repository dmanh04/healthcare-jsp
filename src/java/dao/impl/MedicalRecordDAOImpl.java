package dao.impl;

import common.utils.DBContext;
import dao.IMedicalRecordDAO;
import dto.request.MedicalRecordRequest;
import dto.response.MedicalRecordResponse;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicalRecordDAOImpl extends DBContext implements IMedicalRecordDAO {

    @Override
    public void addMedicalRecord(MedicalRecordRequest medicalRecordRequest) {
        String sql = "INSERT INTO medical_records (appointment_id, diagnosis, treatment) "
                + "VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, medicalRecordRequest.getAppointmentId());
            ps.setString(2, medicalRecordRequest.getDiagnosis());
            ps.setString(3, medicalRecordRequest.getTreatment());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(MedicalRecordDAOImpl.class.getName()).log(Level.SEVERE, "Error adding medical record", e);
        }
    }

    @Override
    public void updateMedicalRecord(MedicalRecordRequest medicalRecordRequest) {
        String sql = "UPDATE medical_records "
                + "SET appointment_id = ?, diagnosis = ?, treatment = ? "
                + "WHERE record_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, medicalRecordRequest.getAppointmentId());
            ps.setString(2, medicalRecordRequest.getDiagnosis());
            ps.setString(3, medicalRecordRequest.getTreatment());
            ps.setInt(4, medicalRecordRequest.getRecordId());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(MedicalRecordDAOImpl.class.getName()).log(Level.SEVERE, "Error updating medical record", e);
        }
    }

    @Override
    public MedicalRecordResponse getMedicalRecordResponse(int id) {
        String sql = "SELECT record_id, diagnosis, treatment FROM medical_records WHERE appointment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new MedicalRecordResponse.Builder()
                            .id(rs.getInt("record_id"))
                            .diagnosis(rs.getString("diagnosis"))
                            .treatment(rs.getString("treatment"))
                            .build();
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MedicalRecordDAOImpl.class.getName()).log(Level.SEVERE, "Error retrieving medical record", e);
        }
        return null;
    }

}
