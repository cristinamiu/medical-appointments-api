package com.medical.api.service.patient;

import com.medical.api.dto.PatientDto;
import com.medical.api.entity.patient.Patient;
import com.medical.api.errors.RestExceptionHandler;
import com.medical.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientDto addPatient(PatientDto patientDto) {
        Patient patient = patientDto.toEntity();
        return  patientRepository.save(patient).toDto();
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<PatientDto> response = new ArrayList<>();
        patientRepository.findAll().forEach(patient -> {
            response.add(patient.toDto());
        });

        return response;
    }

    @Override
    public PatientDto getPatientById(int id) {
        return patientRepository.findById(id).get().toDto();    }

    @Override
    public ResponseEntity<Object> deletePatientById(int id) {
        Patient recordToDelete = patientRepository.findById(id).get();
        patientRepository.delete(recordToDelete);

        return RestExceptionHandler.buildResponseEntity("The record has been deleted", HttpStatus.OK);
    }
}
