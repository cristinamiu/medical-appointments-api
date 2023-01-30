package com.medical.api.dto;

import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.employee.Nurse;
import com.medical.api.entity.patient.Patient;
import com.medical.api.entity.records.Appointment;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDto {
    private Long id;
    private String roomNo;
    private LocalDate date;

    private Long patientId;
    private Long doctorId;
    private Long nurseId;

    public Appointment toEntity(Patient patient, Doctor doctor, Nurse nurse) {
        Appointment appointmentEntity = new Appointment();

        appointmentEntity.setDate(this.date);
        appointmentEntity.setRoomNo(this.roomNo);
        appointmentEntity.setPatient(patient);
        appointmentEntity.setDoctor(doctor);
        appointmentEntity.setNurse(nurse);

        return appointmentEntity;
    }
}
