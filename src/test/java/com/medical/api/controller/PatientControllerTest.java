package com.medical.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.api.dto.DoctorDto;
import com.medical.api.dto.PatientDto;
import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.enums.Degree;
import com.medical.api.entity.enums.DepartmentType;
import com.medical.api.entity.patient.Patient;
import com.medical.api.errors.RestExceptionHandler;
import com.medical.api.service.patient.PatientService;
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
import static com.medical.api.service.doctor.Prepare.prepareDoctorDtos;
import static com.medical.api.service.patient.Prepare.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers= PatientController.class)
@ExtendWith(MockitoExtension.class)
class PatientControllerTest {
    private PatientDto patientDto;
    private Patient patient;
    private List<Patient> patientEntities;
    private List<PatientDto> patientDtoList;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    ObjectMapper objectMapper;

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
    void addPatient() throws Exception {
        given(patientService.addPatient(patientDto)).willReturn(patientDto);

        mockMvc.perform(post("/api/patient/add")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(patientDto)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPatients() throws Exception {
        when(patientService.getAllPatients()).thenReturn(patientDtoList);

        List<PatientDto> response = patientService.getAllPatients();

        mockMvc.perform(get("/api/patient"))
                .andExpect(status().isOk()).andReturn();

        org.assertj.core.api.Assertions.assertThat(response).isEqualTo(patientDtoList);
    }

    @Test
    void getPatientById() throws Exception {
        given(patientService.getPatientById(1)).willReturn(patientDto);

        mockMvc.perform(get("/api/patient/1"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void deletePatientById() throws Exception {
        when(patientService.deletePatientById(1)).thenReturn(RestExceptionHandler.buildResponseEntity("The record has been deleted", HttpStatus.OK));
        mockMvc.perform(delete("/api/patient/delete/1"))
                .andExpect(status().isOk()).andReturn();
    }
}