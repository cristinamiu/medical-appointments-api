package com.medical.api.service.appointment;

import com.medical.api.dto.AppointmentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {
    AppointmentDto addAppointment(AppointmentDto appointmentDto);
    List<AppointmentDto> getAllAppointments();
    public AppointmentDto getAppointmentById(int id);
    ResponseEntity<Object> deleteAppointmentById(int id);
}
