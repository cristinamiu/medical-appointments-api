package com.medical.api.repository;

import com.medical.api.entity.employee.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Integer> {
}
