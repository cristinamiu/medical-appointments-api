package com.medical.api.service.doctor;

import com.medical.api.dto.DoctorDto;
import com.medical.api.entity.Person;
import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.enums.Degree;
import com.medical.api.entity.enums.DepartmentType;
import com.medical.api.errors.RestExceptionHandler;
import com.medical.api.repository.DoctorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static com.medical.api.service.doctor.Prepare.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceImplTest {
    private DoctorDto doctorDto;
    private Doctor doctor;
    private List<Doctor> doctorEntities;
    private List<DoctorDto> doctorDtoList;

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @BeforeEach
    void init() {
        Address address = new Address("C14", "Independence", "Bucharest");
        String name = "Antonio";
        String email = "antonio@gmail.com";
        LocalDate dateOfBirth = LocalDate.of(1984,8, 3);
        DepartmentType department = DepartmentType.Cardiology;
        Degree degree = Degree.MD;
        int salary = 7000;


        doctor = prepareDoctorEntity(email, name, address, dateOfBirth, department, degree, salary);
        doctorDto = prepareDoctorDto(email, name, address, dateOfBirth, department, degree, salary);

        doctorEntities = prepareDoctorEntities();
        doctorDtoList = prepareDoctorDtos();

    }

    @Test
    void addDoctor() {

        when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);

        DoctorDto savedDoctor = doctorService.addDoctor(doctorDto);

        org.assertj.core.api.Assertions.assertThat(savedDoctor).isEqualTo(doctor.toDto());
    }

    @Test()
    void addDoctorThrowExceptionOnDuplicateEmail() {
        assertThrows(NoSuchElementException.class, () -> {
            when(doctorRepository.save(Mockito.any(Doctor.class))).thenThrow(new NoSuchElementException());

            DoctorDto savedDoctor = doctorService.addDoctor(doctorDto);
        });
    }

    @Test
    void getAllDoctors() {
        when(doctorRepository.findAll()).thenReturn(doctorEntities);

        List<DoctorDto> response = doctorService.getAllDoctors();

        org.assertj.core.api.Assertions.assertThat(response).isEqualTo(doctorDtoList);
    }



    @Test
    void getDoctorById() {

        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));

        DoctorDto response = doctorService.getDoctorById(1);

        org.assertj.core.api.Assertions.assertThat(response).isEqualTo(doctor.toDto());

    }

    @Test
    void deleteDoctorById() {

        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));

        ResponseEntity<Object> response = doctorService.deleteDoctorById(1);

        org.assertj.core.api.Assertions.assertThat(response)
                .isEqualTo(RestExceptionHandler.buildResponseEntity("The record has been deleted", HttpStatus.OK));

    }
}