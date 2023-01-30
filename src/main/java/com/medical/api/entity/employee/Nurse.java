package com.medical.api.entity.employee;

import com.medical.api.dto.DoctorDto;
import com.medical.api.dto.NurseDto;
import com.medical.api.entity.records.Appointment;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nurse extends Employee{
    private Boolean registered;

    @OneToMany(mappedBy = "nurse")
    private List<Appointment> appointmentList;

    public NurseDto toDto() {
        NurseDto nurseDto = new NurseDto();

        nurseDto.setId(this.getId());
        nurseDto.setName(this.getName());
        nurseDto.setEmail(this.getEmail());
        nurseDto.setDateOfBirth(this.getDateOfBirth());
        nurseDto.setAddress(this.getAddress());
        nurseDto.setDepartment(this.getDepartment());
        nurseDto.setRegistered(this.getRegistered());
        nurseDto.setSalary(this.getSalary());
        nurseDto.setAppointmentList(this.getAppointmentList());

        return nurseDto;
    }
}
