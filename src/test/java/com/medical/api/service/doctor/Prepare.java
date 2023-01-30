package com.medical.api.service.doctor;

import com.medical.api.dto.DoctorDto;
import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.enums.Degree;
import com.medical.api.entity.enums.DepartmentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prepare {
    public static DoctorDto prepareDoctorDto(String email,
                                              String name,
                                              Address address,
                                              LocalDate dateOfBirth,
                                              DepartmentType department,
                                              Degree degree,
                                              int salary) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setEmail(email);
        doctorDto.setName(name);
        doctorDto.setAddress(address);
        doctorDto.setDateOfBirth(dateOfBirth);
        doctorDto.setDepartment(department);
        doctorDto.setDegree(degree);
        doctorDto.setSalary(salary);
        return doctorDto;
    }

    public static Doctor prepareDoctorEntity(String email,
                                              String name,
                                              Address address,
                                              LocalDate dateOfBirth,
                                              DepartmentType department,
                                              Degree degree,
                                              int salary) {
        Doctor doctor = new Doctor();
        doctor.setEmail(email);
        doctor.setName(name);
        doctor.setAddress(address);
        doctor.setDateOfBirth(dateOfBirth);
        doctor.setDepartment(department);
        doctor.setDegree(degree);
        doctor.setSalary(salary);
        return doctor;
    }

    public static List<DoctorDto> prepareDoctorDtos() {
        Address address1 = new Address("C12", "Independence", "Bucharest");
        String name1 = "Mario";
        String email1 = "mario@gmail.com";
        LocalDate dateOfBirth1 = LocalDate.of(1982,8, 27);
        DepartmentType department1 = DepartmentType.Neurology;
        Degree degree1 = Degree.MD;
        int salary1 = 9000;

        Address address2 = new Address("C14", "Independence", "Bucharest");
        String name2 = "Antonio";
        String email2 = "antonio@gmail.com";
        LocalDate dateOfBirth2 = LocalDate.of(1984,8, 3);
        DepartmentType department2 = DepartmentType.Cardiology;
        Degree degree2 = Degree.MD;
        int salary2 = 7000;

        DoctorDto doctor1 = prepareDoctorDto(email1, name1, address1, dateOfBirth1, department1, degree1, salary1);
        DoctorDto doctor2 = prepareDoctorDto(email2, name2, address2, dateOfBirth2, department2, degree2, salary2);

        return new ArrayList<>(List.of(doctor1, doctor2));
    }

    public static List<Doctor> prepareDoctorEntities() {
        Address address1 = new Address("C12", "Independence", "Bucharest");
        String name1 = "Mario";
        String email1 = "mario@gmail.com";
        LocalDate dateOfBirth1 = LocalDate.of(1982,8, 27);
        DepartmentType department1 = DepartmentType.Neurology;
        Degree degree1 = Degree.MD;
        int salary1 = 9000;

        Address address2 = new Address("C14", "Independence", "Bucharest");
        String name2 = "Antonio";
        String email2 = "antonio@gmail.com";
        LocalDate dateOfBirth2 = LocalDate.of(1984,8, 3);
        DepartmentType department2 = DepartmentType.Cardiology;
        Degree degree2 = Degree.MD;
        int salary2 = 7000;

        Doctor doctor1 = prepareDoctorEntity(email1, name1, address1, dateOfBirth1, department1, degree1, salary1);
        Doctor doctor2 = prepareDoctorEntity(email2, name2, address2, dateOfBirth2, department2, degree2, salary2);

        return new ArrayList<>(List.of(doctor1, doctor2));
    }
}
