package com.medical.api.entity.patient;

import com.medical.api.dto.DoctorDto;
import com.medical.api.dto.PatientDto;
import com.medical.api.entity.Person;
import com.medical.api.entity.records.Appointment;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Patient extends Person {

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointmentList;

    public PatientDto toDto() {
        PatientDto patientDto = new PatientDto();

        patientDto.setId(this.getId());
        patientDto.setName(this.getName());
        patientDto.setEmail(this.getEmail());
        patientDto.setDateOfBirth(this.getDateOfBirth());
        patientDto.setAddress(this.getAddress());
        patientDto.setAppointmentList(this.getAppointmentList());

        return patientDto;
    }
}
