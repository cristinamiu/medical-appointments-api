package com.medical.api.entity.records;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medical.api.dto.AppointmentDto;
import com.medical.api.dto.DoctorDto;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.employee.Nurse;
import com.medical.api.entity.patient.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String roomNo;
    private LocalDate date;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="nurse_id", referencedColumnName = "id")
    private Nurse nurse;

    public AppointmentDto toDto() {
        AppointmentDto appointmentDto = new AppointmentDto();

        appointmentDto.setId(this.getId());
        appointmentDto.setRoomNo(this.getRoomNo());
        appointmentDto.setDate(this.getDate());
        appointmentDto.setPatientId(this.getPatient().getId());
        appointmentDto.setDoctorId(this.getDoctor().getId());
        appointmentDto.setNurseId(this.getNurse().getId());

        return appointmentDto;
    }

}
