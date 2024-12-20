/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.criteria.ServiceCriteria;
import dto.response.PageableResponse;
import java.util.List;
import java.util.Map;
import models.Services;

/**
 *
 * @author Admin
 */
public interface ISerivceDAO {
    
    PageableResponse<Services> getAllSerivceByFilter(ServiceCriteria serviceCriteria);
    
    int getTotalServicesCount(ServiceCriteria serviceCriteria);
    
    Services findServiceById(int id);
    
    void addService(Services service);
    
    boolean existsByServiceName(String name);
    
    boolean existsUpdateByServiceName(String name, int currentServiceId);
    
    void updateService(Services services);
    
    void deleteSerivceById(int id);
    
    Map<Integer, Services> getIdAndService();
    
    List<Services> getAllSerivce();
}
