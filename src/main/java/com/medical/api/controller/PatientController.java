package com.medical.api.controller;

import com.medical.api.dto.PatientDto;
import com.medical.api.service.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public PatientDto addPatient(@RequestBody PatientDto patientDto) {
        return patientService.addPatient(patientDto);
    }

    @GetMapping
    public List<PatientDto> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientDto getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePatientById(@PathVariable int id) {
        return patientService.deletePatientById(id);
    }
}
