package dao.impl;

import common.utils.DBContext;
import dao.IMedicinePurchases;
import java.math.BigDecimal;
import java.util.List;
import models.MedicinePurchases;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicinePurchasesImpl extends DBContext implements IMedicinePurchases {

    @Override
    public void addAll(List<MedicinePurchases> list) {
        String sql = "INSERT INTO medicine_purchases (prescription_id, medicine_id, quantity_purchased, total_price) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (MedicinePurchases purchase : list) {
                statement.setInt(1, purchase.getPrescriptionId());
                statement.setInt(2, purchase.getMedicineId());
                statement.setInt(3, purchase.getQuantityPurchased());
                double doubleValue = purchase.getTotalPrice();
                BigDecimal bigDecimalValue = BigDecimal.valueOf(doubleValue);
                statement.setBigDecimal(4, bigDecimalValue);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (Exception e) {
            Logger.getLogger(MedicinePurchasesImpl.class.getName()).log(Level.SEVERE, "Error inserting medicine purchases", e);
        }
    }

    @Override
    public List<String> getPurchasedMedicines(int recordId) {
        List<String> purchasedMedicines = new ArrayList<>();
        String sql = "SELECT m.medicine_name, mp.quantity_purchased "
                + "FROM healthcare_final.dbo.medicine_purchases mp "
                + "JOIN healthcare_final.dbo.prescriptions p ON mp.prescription_id = p.prescription_id "
                + "JOIN healthcare_final.dbo.medicines m ON mp.medicine_id = m.medicine_id "
                + "WHERE p.medical_record_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, recordId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String medicineName = rs.getString("medicine_name");
                    int quantity = rs.getInt("quantity_purchased");
                    purchasedMedicines.add(medicineName + " " + quantity);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PrescriptionsDAOImpl.class.getName()).log(Level.SEVERE, "Error retrieving purchased medicines", ex);
        }
        return purchasedMedicines;
    }
}
