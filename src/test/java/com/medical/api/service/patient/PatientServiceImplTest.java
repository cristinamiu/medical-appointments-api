package com.medical.api.service.patient;

import com.medical.api.dto.DoctorDto;
import com.medical.api.dto.PatientDto;
import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.patient.Patient;
import com.medical.api.errors.RestExceptionHandler;
import com.medical.api.repository.PatientRepository;
import com.medical.api.service.doctor.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.medical.api.service.patient.Prepare.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {
    private PatientDto patientDto;
    private Patient patient;
    private List<Patient> patientEntities;
    private List<PatientDto> patientDtoList;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void init() {
        Address address = new Address("C14", "Independence", "Bucharest");
        String name = "Antonio";
        String email = "antonio@gmail.com";
        LocalDate dateOfBirth = LocalDate.of(1984,8, 3);


        patient = preparePatientEntity(email, name, address, dateOfBirth);
        patientDto = preparePatientDto(email, name, address, dateOfBirth);

        patientEntities = preparePatientEntities();
        patientDtoList = preparePatientDtos();
    }

    @Test
    void addPatient() {
        when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);

        PatientDto savedPatient = patientService.addPatient(patientDto);

        org.assertj.core.api.Assertions.assertThat(savedPatient).isEqualTo(patient.toDto());
    }

    @Test
    void getAllPatients() {
        when(patientRepository.findAll()).thenReturn(patientEntities);

        List<PatientDto> response = patientService.getAllPatients();

        org.assertj.core.api.Assertions.assertThat(response).isEqualTo(patientDtoList);
    }

    @Test
    void getPatientById() {
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));

        PatientDto response = patientService.getPatientById(1);

        org.assertj.core.api.Assertions.assertThat(response).isEqualTo(patient.toDto());
    }

    @Test
    void deletePatientById() {
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));

        ResponseEntity<Object> response = patientService.deletePatientById(1);

        org.assertj.core.api.Assertions.assertThat(response)
                .isEqualTo(RestExceptionHandler.buildResponseEntity("The record has been deleted", HttpStatus.OK));
    }
}