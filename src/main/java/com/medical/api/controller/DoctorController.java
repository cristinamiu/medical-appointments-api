package com.medical.api.controller;

import com.medical.api.dto.DoctorDto;
import com.medical.api.service.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/add")
    public DoctorDto addDoctor(@RequestBody DoctorDto doctorDto) {
        return doctorService.addDoctor(doctorDto);
    }

    @GetMapping
    public List<DoctorDto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorDto getDoctorById(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/{id}/async")
    public DoctorDto getDoctorByIdAsync(@PathVariable int id) throws ExecutionException, InterruptedException {
        CompletableFuture<DoctorDto> result = doctorService.getDoctorByIdAsync(id);

        return result.get();
    }

    @PostMapping("/add/async")
    public DoctorDto addDoctorAsync(@RequestBody DoctorDto doctorDto) throws ExecutionException, InterruptedException {
        CompletableFuture<DoctorDto> result = doctorService.addDoctorAsync(doctorDto);
        return result.get();
    }

    @GetMapping("/async")
    public List<DoctorDto> getAllDoctorsAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<List<DoctorDto>> result = doctorService.getAllDoctorsAsync();
        return doctorService.getAllDoctorsAsync().get();
    }

    @DeleteMapping("/delete/{id}/async")
    public ResponseEntity<Object> deleteDoctorByIdAsync(@PathVariable int id) {
        return doctorService.deleteDoctorById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDoctorById(@PathVariable int id) {
        return doctorService.deleteDoctorById(id);
    }
}
