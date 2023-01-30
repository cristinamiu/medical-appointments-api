package com.medical.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.api.dto.DoctorDto;
import com.medical.api.dto.NurseDto;
import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.employee.Nurse;
import com.medical.api.entity.enums.DepartmentType;
import com.medical.api.errors.RestExceptionHandler;
import com.medical.api.service.nurse.NurseService;
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
import static com.medical.api.service.nurse.Prepare.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers= NurseController.class)
@ExtendWith(MockitoExtension.class)
class NurseControllerTest {
    private NurseDto nurseDto;
    private Nurse nurse;
    private List<Nurse> nurseEntities;
    private List<NurseDto> nurseDtoList;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NurseService nurseService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        Address address = new Address("C14", "Independence", "Bucharest");
        String name = "Antonio";
        String email = "antonio@gmail.com";
        LocalDate dateOfBirth = LocalDate.of(1984,8, 3);
        DepartmentType department = DepartmentType.Cardiology;
        Boolean registered = false;
        int salary = 7000;

        nurse = prepareNurseEntity(email, name, address, dateOfBirth, department, registered, salary);
        nurseDto = prepareNurseDto(email, name, address, dateOfBirth, department, registered, salary);
        nurseEntities = prepareNurseEntities();
        nurseDtoList = prepareNurseDtos();
    }

    @Test
    void addNurse() throws Exception {
        given(nurseService.addNurse(nurseDto)).willReturn(nurseDto);

        mockMvc.perform(post("/api/nurse/add")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(nurseDto)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllNurses() throws Exception {
        when(nurseService.getAllNurses()).thenReturn(nurseDtoList);

        List<NurseDto> response = nurseService.getAllNurses();

        mockMvc.perform(get("/api/nurse"))
                .andExpect(status().isOk()).andReturn();

        org.assertj.core.api.Assertions.assertThat(response).isEqualTo(nurseDtoList);
    }

    @Test
    void getNurseById() throws Exception {
        given(nurseService.getNurseById(1)).willReturn(nurseDto);

        mockMvc.perform(get("/api/nurse/1"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void deleteNurseById() throws Exception {
        when(nurseService.deleteNurseById(1)).thenReturn(RestExceptionHandler.buildResponseEntity("The record has been deleted", HttpStatus.OK));
        mockMvc.perform(delete("/api/nurse/delete/1"))
                .andExpect(status().isOk()).andReturn();
    }
}