package com.medical.api.service.appointment;

import com.medical.api.dto.PatientDto;
import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.patient.Patient;
import com.medical.api.repository.PatientRepository;
import com.medical.api.service.patient.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static com.medical.api.service.patient.Prepare.*;
import static com.medical.api.service.patient.Prepare.preparePatientDtos;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceImplTest {

    @Test
    void addAppointment() {
    }

    @Test
    void getAllAppointments() {
    }

    @Test
    void getAppointmentById() {
    }

    @Test
    void deleteAppointmentById() {
    }
}