package com.medical.api.service.patient;

import com.medical.api.dto.PatientDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface PatientService {
    PatientDto addPatient(PatientDto patient);
    List<PatientDto> getAllPatients();
    PatientDto getPatientById(int id);
    ResponseEntity<Object> deletePatientById(int id);
}
