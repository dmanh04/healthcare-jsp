package mapper.impl;

import dao.IAppointmentDAO;
import dao.IMedicalRecordDAO;
import dao.IMedicineDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.MedicalRecordDAOImpl;
import dao.impl.MedicineDAOImpl;
import dto.response.PrescriptionsResponse;
import java.util.ArrayList;
import java.util.List;
import mapper.IPrescriptionsMapper;
import models.MedicalRecords;
import models.Medicines;
import models.Prescriptions;

public class PrescriptionsMapperImpl implements IPrescriptionsMapper {

    private final IMedicalRecordDAO medicalRecordDAO;
    private final IMedicineDAO medicineDAO;

    public PrescriptionsMapperImpl() {
        this.medicalRecordDAO = new MedicalRecordDAOImpl();
        this.medicineDAO = new MedicineDAOImpl();
    }

    
    
    @Override
    public List<PrescriptionsResponse> toListPrescriptionsResponse(List<Prescriptions> list) {
        List<PrescriptionsResponse> result = new ArrayList<>();
        for(Prescriptions pre : list){
            Medicines medicines = medicineDAO.getMedicineById(pre.getMedicineId());
            MedicalRecords medicalRecords = medicalRecordDAO.getMedicalRecord(pre.getRecordId());
            result.add(new PrescriptionsResponse.Builder()
                    .id(pre.getId())
                    .medicine(medicines)
                    .medicalRecord(medicalRecords)
                    .notes(pre.getNotes())
                    .quantityPrescribed(pre.getQuantityPrescribed())
                    .build());
        }
        
        return result;
    }

}
