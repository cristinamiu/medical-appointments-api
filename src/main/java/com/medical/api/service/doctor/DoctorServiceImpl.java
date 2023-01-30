package com.medical.api.service.doctor;

import com.medical.api.config.AsyncConfig;
import com.medical.api.dto.DoctorDto;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.errors.RestExceptionHandler;
import com.medical.api.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public DoctorDto addDoctor(DoctorDto doctorDto) {
        Doctor doctor = doctorDto.toEntity();
        return  doctorRepository.save(doctor).toDto();
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<DoctorDto> response = new ArrayList<>();
        doctorRepository.findAll().forEach(doctor -> {
            response.add(doctor.toDto());
        });

        return response;
    }

    @Override
    public DoctorDto getDoctorById(int id) {
        return doctorRepository.findById(id).get().toDto();

    }

    @Async(AsyncConfig.TASK_EXECUTOR)
    @Override
    public CompletableFuture<DoctorDto> getDoctorByIdAsync(int id) {
        return CompletableFuture.completedFuture(doctorRepository.findById(id).get().toDto());
    }

    @Async(AsyncConfig.TASK_EXECUTOR)
    @Override
    public CompletableFuture<DoctorDto> addDoctorAsync(DoctorDto doctorDto) {
        Doctor doctor = doctorDto.toEntity();
        return  CompletableFuture.completedFuture(doctorRepository.save(doctor).toDto());
    }

    @Async(AsyncConfig.TASK_EXECUTOR)
    @Override
    @Transactional
    public CompletableFuture<List<DoctorDto>> getAllDoctorsAsync() {

        List<DoctorDto> response = new ArrayList<>();

        CompletableFuture<List<DoctorDto>> cf = CompletableFuture.supplyAsync(() -> {
            List<Doctor> doctorEntities = doctorRepository.findAll();

            return doctorEntities;
        }).thenApply(doctorEntities -> {
            for(Doctor entity : doctorEntities) {
                response.add(entity.toDto());
            }
            return response;
        });

        return cf;
    }

    @Async(AsyncConfig.TASK_EXECUTOR)
    @Override
    public CompletableFuture<ResponseEntity<Object>> deleteDoctorByIdAsync(int id) {
        Doctor recordToDelete = doctorRepository.findById(id).get();
        doctorRepository.delete(recordToDelete);

        return CompletableFuture.completedFuture(RestExceptionHandler
                .buildResponseEntity("The record has been deleted", HttpStatus.OK));
    }

    @Override
    public ResponseEntity<Object> deleteDoctorById(int id) {
        Doctor recordToDelete = doctorRepository.findById(id).get();
        doctorRepository.delete(recordToDelete);

        return RestExceptionHandler.buildResponseEntity("The record has been deleted", HttpStatus.OK);
    }
}
