package com.medical.api.service.nurse;

import com.medical.api.dto.DoctorDto;
import com.medical.api.dto.NurseDto;
import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.employee.Nurse;
import com.medical.api.entity.enums.Degree;
import com.medical.api.entity.enums.DepartmentType;
import com.medical.api.errors.RestExceptionHandler;
import com.medical.api.repository.DoctorRepository;
import com.medical.api.repository.NurseRepository;
import com.medical.api.service.doctor.DoctorServiceImpl;
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

import static com.medical.api.service.doctor.Prepare.*;
import static com.medical.api.service.doctor.Prepare.prepareDoctorDtos;
import static com.medical.api.service.nurse.Prepare.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NurseServiceImplTest {

    @Mock
    private NurseRepository nurseRepository;

    @InjectMocks
    private NurseServiceImpl nurseService;

    @Test
    void addNurse() {
        Address address = new Address("C14", "Independence", "Bucharest");
        String name = "Antonio";
        String email = "antonio@gmail.com";
        LocalDate dateOfBirth = LocalDate.of(1984,8, 3);
        DepartmentType department = DepartmentType.Cardiology;
        Boolean registered = true;
        int salary = 7000;


        Nurse nurse = prepareNurseEntity(email, name, address, dateOfBirth, department, registered, salary);
        NurseDto nurseDto = prepareNurseDto(email, name, address, dateOfBirth, department, registered, salary);

        when(nurseRepository.save(Mockito.any(Nurse.class))).thenReturn(nurse);

        NurseDto savedNurse = nurseService.addNurse(nurseDto);

        org.assertj.core.api.Assertions.assertThat(savedNurse).isEqualTo(nurse.toDto());
    }

    @Test
    void getAllNurses() {
        List<Nurse> nurseEntities = prepareNurseEntities();
        List<NurseDto> nurseDtoList = prepareNurseDtos();

        when(nurseRepository.findAll()).thenReturn(nurseEntities);

        List<NurseDto> response = nurseService.getAllNurses();

        org.assertj.core.api.Assertions.assertThat(response).isEqualTo(nurseDtoList);
    }

    @Test
    void getNurseById() {
        Address address = new Address("C14", "Independence", "Bucharest");
        String name = "Maria";
        String email = "maria@gmail.com";
        LocalDate dateOfBirth = LocalDate.of(1984,8, 3);
        DepartmentType department = DepartmentType.Cardiology;
        Boolean registered = true;
        int salary = 7000;


        Nurse nurseEntity = prepareNurseEntity(email, name, address, dateOfBirth, department, registered, salary);

        when(nurseRepository.findById(1)).thenReturn(Optional.of(nurseEntity));

        NurseDto response = nurseService.getNurseById(1);

        org.assertj.core.api.Assertions.assertThat(response).isEqualTo(nurseEntity.toDto());
    }

    @Test
    void deleteNurseById() {
        Address address = new Address("C14", "Independence", "Bucharest");
        String name = "Maria";
        String email = "maria@gmail.com";
        LocalDate dateOfBirth = LocalDate.of(1984,8, 3);
        DepartmentType department = DepartmentType.Cardiology;
        Boolean registered = false;
        int salary = 7000;


        Nurse nurseEntity = prepareNurseEntity(email, name, address, dateOfBirth, department, registered, salary);

        when(nurseRepository.findById(1)).thenReturn(Optional.of(nurseEntity));

        ResponseEntity<Object> response = nurseService.deleteNurseById(1);

        org.assertj.core.api.Assertions.assertThat(response)
                .isEqualTo(RestExceptionHandler.buildResponseEntity("The record has been deleted", HttpStatus.OK));
    }
}