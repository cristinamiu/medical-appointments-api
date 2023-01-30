package com.medical.api.service.nurse;

import com.medical.api.dto.DoctorDto;
import com.medical.api.dto.NurseDto;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.employee.Nurse;
import com.medical.api.errors.RestExceptionHandler;
import com.medical.api.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NurseServiceImpl implements NurseService{
    @Autowired
    private NurseRepository nurseRepository;

    @Override
    public NurseDto addNurse(NurseDto nurseDto) {
        Nurse nurse = nurseDto.toEntity();
        return  nurseRepository.save(nurse).toDto();
    }

    @Override
    public List<NurseDto> getAllNurses() {
        List<NurseDto> response = new ArrayList<>();
        nurseRepository.findAll().forEach(nurse -> {
            response.add(nurse.toDto());
        });

        return response;
    }

    @Override
    public NurseDto getNurseById(int id) {
        return nurseRepository.findById(id).get().toDto();
    }

    @Override
    public ResponseEntity<Object> deleteNurseById(int id) {
        Nurse recordToDelete = nurseRepository.findById(id).get();
        nurseRepository.delete(recordToDelete);

        return RestExceptionHandler.buildResponseEntity("The record has been deleted", HttpStatus.OK);

    }
}
