package dao.impl;

import common.utils.DBContext;
import dao.IMedicinePurchases;
import java.math.BigDecimal;
import java.util.List;
import models.MedicinePurchases;
import java.sql.PreparedStatement;
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
}
