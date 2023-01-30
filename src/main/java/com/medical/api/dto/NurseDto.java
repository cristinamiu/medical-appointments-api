package com.medical.api.dto;

import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.employee.Nurse;
import com.medical.api.entity.enums.DepartmentType;
import com.medical.api.entity.records.Appointment;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class NurseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Address address;
    private int salary;
    private DepartmentType department;
    private Boolean registered;
    private List<Appointment> appointmentList;

    public Nurse toEntity() {
        Nurse nurseEntity = new Nurse();

        nurseEntity.setId(this.id);
        nurseEntity.setName(this.name);
        nurseEntity.setEmail(this.email);
        nurseEntity.setDateOfBirth(this.dateOfBirth);
        nurseEntity.setAddress(this.address);
        nurseEntity.setDepartment(this.department);
        nurseEntity.setRegistered(this.registered);
        nurseEntity.setSalary(this.salary);
        nurseEntity.setAppointmentList(this.appointmentList);

        return nurseEntity;
    }
}
