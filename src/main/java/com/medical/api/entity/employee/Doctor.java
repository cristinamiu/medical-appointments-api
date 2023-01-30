package com.medical.api.entity.employee;

import com.medical.api.dto.DoctorDto;
import com.medical.api.entity.Person;
import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.enums.Degree;
import com.medical.api.entity.records.Appointment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends Employee{
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @OneToMany(mappedBy = "doctor",fetch= FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentList;

    public DoctorDto toDto() {
        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setId(this.getId());
        doctorDto.setName(this.getName());
        doctorDto.setEmail(this.getEmail());
        doctorDto.setDateOfBirth(this.getDateOfBirth());
        doctorDto.setAddress(this.getAddress());
        doctorDto.setDepartment(this.getDepartment());
        doctorDto.setDegree(this.getDegree());
        doctorDto.setSalary(this.getSalary());
        doctorDto.setAppointmentList(this.getAppointmentList());

        return doctorDto;
    }

    public static List<DoctorDto> entityListToDtoList(List<Doctor> doctorList) {
        List<DoctorDto> result = new ArrayList<>();

        doctorList.forEach(entity -> {result.add(entity.toDto());});

        return result;
    }
}
