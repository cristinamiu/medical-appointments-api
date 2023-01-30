package com.medical.api.service.appointment;

import com.medical.api.dto.AppointmentDto;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.employee.Nurse;
import com.medical.api.entity.patient.Patient;
import com.medical.api.entity.records.Appointment;
import com.medical.api.errors.RestExceptionHandler;
import com.medical.api.repository.AppointmentRepository;
import com.medical.api.repository.DoctorRepository;
import com.medical.api.repository.NurseRepository;
import com.medical.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        Patient patient = patientRepository.findById(appointmentDto.getPatientId().intValue()).get();
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId().intValue()).get();
        Nurse nurse = nurseRepository.findById(appointmentDto.getNurseId().intValue()).get();

        Appointment appointmentEntity = appointmentDto.toEntity(patient, doctor, nurse);

        return appointmentRepository.save(appointmentEntity).toDto();
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        List<AppointmentDto> response = new ArrayList<>();

        appointmentRepository.findAll().forEach(appointment -> {
            response.add(appointment.toDto());
        });

        return response;
    }

    @Override
    public AppointmentDto getAppointmentById(int id) {
        return appointmentRepository.findById(id).get().toDto();
    }

    @Override
    public ResponseEntity<Object> deleteAppointmentById(int id) {
        Appointment recordToDelete = appointmentRepository.findById(id).get();
        appointmentRepository.delete(recordToDelete);

        return RestExceptionHandler.buildResponseEntity("The record has been deleted", HttpStatus.OK);

    }
}
