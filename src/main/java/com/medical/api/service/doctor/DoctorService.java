package com.medical.api.service.doctor;

import com.medical.api.dto.DoctorDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DoctorService {
    DoctorDto addDoctor(DoctorDto doctor);
    List<DoctorDto> getAllDoctors();
    DoctorDto getDoctorById(int id);
    ResponseEntity<Object> deleteDoctorById(int id);

    CompletableFuture<DoctorDto> getDoctorByIdAsync(int id);
    CompletableFuture<DoctorDto> addDoctorAsync(DoctorDto doctorDto);
    CompletableFuture<List<DoctorDto>> getAllDoctorsAsync();
    CompletableFuture<ResponseEntity<Object>> deleteDoctorByIdAsync(int id);
}
