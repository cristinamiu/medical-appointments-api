package com.medical.api.dto;

import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.enums.Degree;
import com.medical.api.entity.enums.DepartmentType;
import com.medical.api.entity.records.Appointment;
import jakarta.persistence.Embedded;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DoctorDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Address address;

    private int salary;
    private Degree degree;
    private DepartmentType department;
    private List<Appointment> appointmentList;

    public Doctor toEntity() {
        Doctor doctorEntity = new Doctor();

        doctorEntity.setId(this.id);
        doctorEntity.setName(this.name);
        doctorEntity.setEmail(this.email);
        doctorEntity.setDateOfBirth(this.dateOfBirth);
        doctorEntity.setAddress(this.address);
        doctorEntity.setDepartment(this.department);
        doctorEntity.setDegree(this.degree);
        doctorEntity.setSalary(this.salary);
        doctorEntity.setAppointmentList(this.appointmentList);

        return doctorEntity;
    }
}
