/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.request.MedicalRecordRequest;
import dto.response.MedicalRecordResponse;
import models.MedicalRecords;


public interface IMedicalRecordDAO {
    
    MedicalRecordResponse getMedicalRecordResponse(int id);
    
    MedicalRecords getMedicalRecord(int id);
    
    MedicalRecords getMedicalRecordsByAppointmentId(int id);
    
    void addMedicalRecord(MedicalRecordRequest medicalRecordRequest);
    
    void updateMedicalRecord(MedicalRecordRequest medicalRecordRequest);
}
