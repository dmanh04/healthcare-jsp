package dao;

import dto.request.PrescriptionsRequest;
import java.util.List;
import models.Prescriptions;


public interface IPrescriptionsDAO {
    
    void addPrescriptions(PrescriptionsRequest request);
    
    List<Prescriptions> getAllPrescriptionsByMedicalRecordId(int id);
    
    List<Prescriptions> getPrescriptionsByAppointmentId(int id);
    
    void removeByPrescriptionsId(int id);
    
}
