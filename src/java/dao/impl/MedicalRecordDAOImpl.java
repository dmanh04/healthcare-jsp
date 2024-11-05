package dao.impl;

import common.utils.DBContext;
import dao.IMedicalRecordDAO;
import dao.IMedicinePurchases;
import dao.IPrescriptionsDAO;
import dto.criteria.MedicalRecordCriteria;
import dto.request.MedicalRecordRequest;
import dto.response.MedicalRecordHistoryResponse;
import dto.response.MedicalRecordResponse;
import dto.response.PageableResponse;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.MedicalRecords;

public class MedicalRecordDAOImpl extends DBContext implements IMedicalRecordDAO {

    private final IPrescriptionsDAO prescriptionsDAO;
    private final IMedicinePurchases medicinePurchases;

    public MedicalRecordDAOImpl() {
        this.prescriptionsDAO = new PrescriptionsDAOImpl();
        this.medicinePurchases = new MedicinePurchasesImpl();
    }

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

    @Override
    public MedicalRecords getMedicalRecord(int id) {
        String sql = "SELECT record_id, appointment_id, diagnosis, treatment FROM medical_records WHERE record_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MedicalRecords medicalRecord = new MedicalRecords.Builder()
                            .id(rs.getInt("record_id"))
                            .appointmentId(rs.getInt("appointment_id"))
                            .diagnosis("diagnosis")
                            .treatment("treatment")
                            .build();
                    return medicalRecord;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MedicalRecordDAOImpl.class.getName()).log(Level.SEVERE, "Error retrieving medical record", e);
        }
        return null;
    }

    @Override
    public MedicalRecords getMedicalRecordsByAppointmentId(int id) {
        String sql = "SELECT record_id, appointment_id, diagnosis, treatment FROM medical_records WHERE appointment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new MedicalRecords.Builder()
                            .id(rs.getInt("record_id"))
                            .appointmentId(rs.getInt("appointment_id"))
                            .diagnosis(rs.getString("diagnosis"))
                            .treatment(rs.getString("treatment"))
                            .build();
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MedicalRecordDAOImpl.class.getName()).log(Level.SEVERE, "Error retrieving medical record by appointment ID", e);
        }
        return null;
    }

    @Override
    public PageableResponse<MedicalRecordHistoryResponse> getHistoryResponse(MedicalRecordCriteria medicalRecordCriteria) {
        List<MedicalRecordHistoryResponse> responses = new ArrayList<>();
        String sql = "SELECT mr.record_id, a.customer_name, mr.diagnosis, mr.treatment, "
                + "a.appointment_date "
                + "FROM medical_records mr "
                + "JOIN appointments a ON mr.appointment_id = a.appointment_id "
                + "WHERE 1=1";

        if (medicalRecordCriteria.getCustomerName() != null) {
            sql += " AND a.customer_name LIKE ?";
        }
        if (medicalRecordCriteria.getStartDate() != null) {
            sql += " AND mr.created_at >= ?";
        }
        if (medicalRecordCriteria.getEndDate() != null) {
            sql += " AND mr.created_at <= ?";
        }
        sql += " ORDER BY mr.record_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int index = 1;

            if (medicalRecordCriteria.getCustomerName() != null) {
                ps.setString(index++, "%" + medicalRecordCriteria.getCustomerName() + "%");
            }
            if (medicalRecordCriteria.getStartDate() != null) {
                ps.setDate(index++, new java.sql.Date(medicalRecordCriteria.getStartDate().getTime()));
            }
            if (medicalRecordCriteria.getEndDate() != null) {
                ps.setDate(index++, new java.sql.Date(medicalRecordCriteria.getEndDate().getTime()));
            }

            ps.setInt(index++, (medicalRecordCriteria.getPage() - 1) * medicalRecordCriteria.getLimit());
            ps.setInt(index++, medicalRecordCriteria.getLimit());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MedicalRecordHistoryResponse response = new MedicalRecordHistoryResponse.Builder()
                            .customerName(rs.getString("customer_name"))
                            .medicalRecordId(rs.getInt("record_id"))
                            .diagnosis(rs.getString("diagnosis"))
                            .treatment(rs.getString("treatment"))
                            .prescribedMedicines(this.prescriptionsDAO.getPrescribedMedicines(rs.getInt("record_id")))
                            .purchasedMedicines(this.medicinePurchases.getPurchasedMedicines(rs.getInt("record_id")))
                            .appointmentDate(rs.getDate("appointment_date"))
                            .build();
                    responses.add(response);
                }
            }

            int totalCount = getTotalCount(medicalRecordCriteria);
            int totalPage = (int) Math.ceil((double) totalCount / medicalRecordCriteria.getLimit());

            return new PageableResponse.Builder<MedicalRecordHistoryResponse>()
                    .page(medicalRecordCriteria.getPage())
                    .size(responses.size())
                    .data(responses)
                    .totalPage(totalPage)
                    .build();

        } catch (SQLException e) {
            Logger.getLogger(MedicalRecordDAOImpl.class.getName()).log(Level.SEVERE, "Error retrieving medical record history", e);
        }

        return null;
    }

    private int getTotalCount(MedicalRecordCriteria medicalRecordCriteria) {
        String sql = "SELECT COUNT(*) FROM medical_records mr "
                + "JOIN appointments a ON mr.appointment_id = a.appointment_id "
                + "WHERE 1=1";

        if (medicalRecordCriteria.getCustomerName() != null) {
            sql += " AND a.customer_name LIKE ?";
        }
        if (medicalRecordCriteria.getStartDate() != null) {
            sql += " AND mr.created_at >= ?";
        }
        if (medicalRecordCriteria.getEndDate() != null) {
            sql += " AND mr.created_at <= ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int index = 1;

            if (medicalRecordCriteria.getCustomerName() != null) {
                ps.setString(index++, "%" + medicalRecordCriteria.getCustomerName() + "%");
            }
            if (medicalRecordCriteria.getStartDate() != null) {
                ps.setDate(index++, new java.sql.Date(medicalRecordCriteria.getStartDate().getTime()));
            }
            if (medicalRecordCriteria.getEndDate() != null) {
                ps.setDate(index++, new java.sql.Date(medicalRecordCriteria.getEndDate().getTime()));
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MedicalRecordDAOImpl.class.getName()).log(Level.SEVERE, "Error counting medical records", e);
        }
        return 0;
    }

}
