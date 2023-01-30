package com.medical.api.service.nurse;

import com.medical.api.dto.NurseDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface NurseService {
    NurseDto addNurse(NurseDto nurseDto);
    List<NurseDto> getAllNurses();
    NurseDto getNurseById(int id);
    ResponseEntity<Object> deleteNurseById(int id);
}
