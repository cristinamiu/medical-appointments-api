package com.medical.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.api.dto.DoctorDto;
import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.enums.Degree;
import com.medical.api.entity.enums.DepartmentType;
import com.medical.api.errors.RestExceptionHandler;
import com.medical.api.service.doctor.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static com.medical.api.service.doctor.Prepare.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers= DoctorController.class)
@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {
    private DoctorDto doctorDto;
    private Doctor doctor;
    private List<Doctor> doctorEntities;
    private List<DoctorDto> doctorDtoList;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Autowired
    ObjectMapper objectMapper;

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
    void addDoctor() throws Exception {

        given(doctorService.addDoctor(doctorDto)).willReturn(doctorDto);

        mockMvc.perform(post("/api/doctor/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(doctorDto)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllDoctors() throws Exception {

        when(doctorService.getAllDoctors()).thenReturn(doctorDtoList);

        List<DoctorDto> response = doctorService.getAllDoctors();

        mockMvc.perform(get("/api/doctor"))
                        .andExpect(status().isOk()).andReturn();

        org.assertj.core.api.Assertions.assertThat(response).isEqualTo(doctorDtoList);
    }

    @Test
    void getDoctorById() throws Exception {
        given(doctorService.getDoctorById(1)).willReturn(doctorDto);

        mockMvc.perform(get("/api/doctor/1"))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    void deleteDoctorById() throws Exception {
        when(doctorService.deleteDoctorById(1)).thenReturn(RestExceptionHandler.buildResponseEntity("The record has been deleted", HttpStatus.OK));
        mockMvc.perform(delete("/api/doctor/delete/1"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void getDoctorByIdAsync() throws Exception {
        given(doctorService.getDoctorById(1)).willReturn(doctorDto);

        mockMvc.perform(get("/api/doctor/1"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void addDoctorAsync() throws Exception {
        given(doctorService.addDoctor(doctorDto)).willReturn(doctorDto);

        mockMvc.perform(post("/api/doctor/add")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(doctorDto)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllDoctorsAsync() throws Exception {
        when(doctorService.getAllDoctors()).thenReturn(doctorDtoList);

        List<DoctorDto> response = doctorService.getAllDoctors();

        mockMvc.perform(get("/api/doctor"))
                .andExpect(status().isOk()).andReturn();

        org.assertj.core.api.Assertions.assertThat(response).isEqualTo(doctorDtoList);
    }

    @Test
    void deleteDoctorByIdAsync() throws Exception {
        when(doctorService.deleteDoctorById(1)).thenReturn(RestExceptionHandler.buildResponseEntity("The record has been deleted", HttpStatus.OK));
        mockMvc.perform(delete("/api/doctor/delete/1"))
                .andExpect(status().isOk()).andReturn();
    }
}