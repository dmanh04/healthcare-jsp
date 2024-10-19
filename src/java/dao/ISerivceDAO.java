/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import models.Services;

/**
 *
 * @author Admin
 */
public interface ISerivceDAO {
    
    List<Services> getAllSerivce();
    
    Services findServiceById(int id);
    
    void addService(Services service);
    
    boolean existsByServiceName(String name);
    
    boolean existsUpdateByServiceName(String name, int currentServiceId);
    
    void updateService(Services services);
    
    void deleteSerivceById(int id);
}
