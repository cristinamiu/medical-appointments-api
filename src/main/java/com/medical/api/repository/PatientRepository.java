package com.medical.api.repository;

import com.medical.api.entity.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
