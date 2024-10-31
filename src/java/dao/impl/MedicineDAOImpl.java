package dao.impl;

import common.utils.DBContext;
import dao.IMedicineDAO;
import java.util.List;
import models.Medicines;
import java.sql.*;
import java.util.ArrayList;


public class MedicineDAOImpl extends DBContext implements IMedicineDAO {

    @Override
    public List<Medicines> getAllMedicine() {
        List<Medicines> list = new ArrayList<>();
        String sql = "select * from medicines ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Medicines me = new Medicines.Builder()
                        .id(rs.getInt("medicine_id"))
                        .medicineName(rs.getString("medicine_name"))
                        .description(rs.getString("description"))
                        .quantityInStock(rs.getInt("quantity_in_stock"))
                        .price(rs.getDouble("price"))
                        .createdAt(rs.getDate("created_at"))
                        .updatedAt(rs.getDate("updated_at"))
                        .build();
                list.add(me);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    @Override
    public List<Medicines> getMedicineByPriceAndName(String minPrice_raw, String maxPrice_raw, String name) {
        List<Medicines> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from medicines where 1=1");
        double minPrice = 0;
        double maxPrice = 0;
        try {
            minPrice = Double.parseDouble(minPrice_raw);
            maxPrice = Double.parseDouble(maxPrice_raw);
        } catch (Exception e) {
        }
        if (minPrice_raw != null && !minPrice_raw.isEmpty() && minPrice != 0) {
            sql.append(" and price >= ?");
        }
        if (maxPrice_raw != null && !maxPrice_raw.isEmpty() && maxPrice != 0) {
            sql.append(" and price <= ?");
        }
        if (name != null && !name.isEmpty()) {
            sql.append(" and medicine_name like ?");
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            int paramIndex = 1;
            if (minPrice_raw != null && !minPrice_raw.isEmpty() && minPrice != 0) {
                st.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice_raw != null && !maxPrice_raw.isEmpty() && maxPrice != 0) {
                st.setDouble(paramIndex++, maxPrice);;
            }
            if (name != null && !name.isEmpty()) {
                st.setString(paramIndex, "%" + name + "%");
            }
            System.out.println(sql.toString());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Medicines me = new Medicines.Builder()
                        .id(rs.getInt("medicine_id"))
                        .medicineName(rs.getString("medicine_name"))
                        .description(rs.getString("description"))
                        .quantityInStock(rs.getInt("quantity_in_stock"))
                        .price(rs.getDouble("price"))
                        .createdAt(rs.getDate("created_at"))
                        .updatedAt(rs.getDate("updated_at"))
                        .build();
                list.add(me);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addMedicine(Medicines medicine) {
        String sql = "INSERT INTO medicines (medicine_name, description, quantity_in_stock, price) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, medicine.getMedicineName());
            st.setString(2, medicine.getDescription());
            st.setInt(3, medicine.getQuantityInStock());
            st.setDouble(4, medicine.getPrice());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMedicine(Medicines medicine) {
        String sql = "update medicines set medicine_name = ?, description = ?,quantity_in_stock = ?, price = ?, created_at = ?, updated_at = ? where medicine_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, medicine.getMedicineName());
            st.setString(2, medicine.getDescription());
            st.setInt(3, medicine.getQuantityInStock());
            st.setDouble(4, medicine.getPrice());
            st.setDate(5, (Date) medicine.getCreatedAt());
            st.setDate(6, (Date) medicine.getUpdatedAt());
            st.setInt(7, medicine.getId());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    @Override
    public void deleteMedicine(int medicineId) {
        String sql = "delete from medicines where medicine_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, medicineId);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    @Override
    public Medicines getMedicineById(int id) {
        String sql = "select * from medicines where medicine_id = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Medicines me = new Medicines.Builder()
                        .id(rs.getInt("medicine_id"))
                        .medicineName(rs.getString("medicine_name"))
                        .description(rs.getString("description"))
                        .quantityInStock(rs.getInt("quantity_in_stock"))
                        .price(rs.getDouble("price"))
                        .createdAt(rs.getDate("created_at"))
                        .updatedAt(rs.getDate("updated_at"))
                        .build();
                return me;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean existsUpdateByMedicineName(String name, int id) {
        String sql = "select count(*) from medicines where medicine_id != ? and medicine_name = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int cnt = rs.getInt(1);
                return cnt > 0;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean existsMedicineName(String name) {
        String sql = "select count(*) from medicines where medicine_name = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int cnt = rs.getInt(1);
                return cnt > 0;
            }
        } catch (Exception e) {
        }
        return false;
    }

}
