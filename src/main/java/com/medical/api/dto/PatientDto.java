package com.medical.api.dto;

import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.patient.Patient;
import com.medical.api.entity.records.Appointment;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PatientDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Address address;
    private List<Appointment> appointmentList;

    public Patient toEntity() {
        Patient patientEntity = new Patient();

        patientEntity.setName(this.getName());
        patientEntity.setEmail(this.getEmail());
        patientEntity.setDateOfBirth(this.getDateOfBirth());
        patientEntity.setAddress(this.getAddress());
        patientEntity.setAppointmentList(this.getAppointmentList());

        return patientEntity;
    }
}
