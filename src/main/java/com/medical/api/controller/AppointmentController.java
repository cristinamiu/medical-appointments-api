package com.medical.api.controller;

import com.medical.api.dto.AppointmentDto;
import com.medical.api.service.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/add")
    public AppointmentDto addAppointment(@RequestBody AppointmentDto appointmentDto) {
        return appointmentService.addAppointment(appointmentDto);
    }

    @GetMapping
    public List<AppointmentDto> getAllAppointment() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentDto getAppointmentById(@PathVariable int id) {
        return appointmentService.getAppointmentById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAppointmentById(@PathVariable int id) {
        return appointmentService.deleteAppointmentById(id);
    }
}
