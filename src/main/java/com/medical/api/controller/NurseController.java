package com.medical.api.controller;

import com.medical.api.dto.DoctorDto;
import com.medical.api.dto.NurseDto;
import com.medical.api.service.doctor.DoctorService;
import com.medical.api.service.nurse.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurse")
public class NurseController {
    @Autowired
    private NurseService nurseService;

    @PostMapping("/add")
    public NurseDto addNurse(@RequestBody NurseDto nurseDto) {
        return nurseService.addNurse(nurseDto);
    }

    @GetMapping
    public List<NurseDto> getAllNurses() {
        return nurseService.getAllNurses();
    }

    @GetMapping("/{id}")
    public NurseDto getNurseById(@PathVariable int id) {
        return nurseService.getNurseById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteNurseById(@PathVariable int id) {
        return nurseService.deleteNurseById(id);
    }
}
