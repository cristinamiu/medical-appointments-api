package com.medical.api.service.patient;

import com.medical.api.dto.NurseDto;
import com.medical.api.dto.PatientDto;
import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.employee.Nurse;
import com.medical.api.entity.enums.Degree;
import com.medical.api.entity.enums.DepartmentType;
import com.medical.api.entity.patient.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prepare {
    public static PatientDto preparePatientDto(String email,
                                               String name,
                                               Address address,
                                               LocalDate dateOfBirth) {
        PatientDto patientDto = new PatientDto();
        patientDto.setEmail(email);
        patientDto.setName(name);
        patientDto.setAddress(address);
        patientDto.setDateOfBirth(dateOfBirth);
        return patientDto;
    }

    public static Patient preparePatientEntity(String email,
                                               String name,
                                               Address address,
                                               LocalDate dateOfBirth) {
        Patient patient = new Patient();
        patient.setEmail(email);
        patient.setName(name);
        patient.setAddress(address);
        patient.setDateOfBirth(dateOfBirth);
        return patient;
    }

    public static List<PatientDto> preparePatientDtos() {
        Address address1 = new Address("C12", "Independence", "Bucharest");
        String name1 = "Mario";
        String email1 = "mario@gmail.com";
        LocalDate dateOfBirth1 = LocalDate.of(1982,8, 27);

        Address address2 = new Address("C14", "Independence", "Bucharest");
        String name2 = "Antonio";
        String email2 = "antonio@gmail.com";
        LocalDate dateOfBirth2 = LocalDate.of(1984,8, 3);

        PatientDto patient1 = preparePatientDto(email1, name1, address1, dateOfBirth1);
        PatientDto patient2 = preparePatientDto(email2, name2, address2, dateOfBirth2);

        return new ArrayList<>(List.of(patient1, patient2));
    }

    public static List<Patient> preparePatientEntities() {
        Address address1 = new Address("C12", "Independence", "Bucharest");
        String name1 = "Mario";
        String email1 = "mario@gmail.com";
        LocalDate dateOfBirth1 = LocalDate.of(1982,8, 27);

        Address address2 = new Address("C14", "Independence", "Bucharest");
        String name2 = "Antonio";
        String email2 = "antonio@gmail.com";
        LocalDate dateOfBirth2 = LocalDate.of(1984,8, 3);

        Patient patient1 = preparePatientEntity(email1, name1, address1, dateOfBirth1);
        Patient patient2 = preparePatientEntity(email2, name2, address2, dateOfBirth2);

        return new ArrayList<>(List.of(patient1, patient2));
    }
}
