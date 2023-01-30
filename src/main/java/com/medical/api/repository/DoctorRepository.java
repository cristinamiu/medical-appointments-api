package com.medical.api.repository;

import com.medical.api.entity.employee.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    boolean existsByEmail(String email);
}
