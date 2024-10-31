/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import models.Medicines;

/**
 *
 * @author Admin
 */
public interface IMedicineDAO {
    
    List<Medicines> getAllMedicine();
    List<Medicines> getMedicineByPriceAndName(String minPrice, String maxPrice, String name) ;
    void addMedicine(Medicines medicine);
    void updateMedicine(Medicines medicine);
    void deleteMedicine(int medicineId);
    Medicines getMedicineById(int id);
    boolean existsUpdateByMedicineName(String name, int id);
    boolean existsMedicineName(String name);
}
