package com.medical.api.service.nurse;

import com.medical.api.dto.DoctorDto;
import com.medical.api.dto.NurseDto;
import com.medical.api.entity.embeddable.Address;
import com.medical.api.entity.employee.Doctor;
import com.medical.api.entity.employee.Nurse;
import com.medical.api.entity.enums.Degree;
import com.medical.api.entity.enums.DepartmentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prepare {
    public static NurseDto prepareNurseDto(String email,
                                            String name,
                                            Address address,
                                            LocalDate dateOfBirth,
                                            DepartmentType department,
                                            Boolean registered,
                                            int salary) {
        NurseDto nurseDto = new NurseDto();
        nurseDto.setEmail(email);
        nurseDto.setName(name);
        nurseDto.setAddress(address);
        nurseDto.setDateOfBirth(dateOfBirth);
        nurseDto.setDepartment(department);
        nurseDto.setRegistered(registered);
        nurseDto.setSalary(salary);
        return nurseDto;
    }

    public static Nurse prepareNurseEntity(String email,
                                              String name,
                                              Address address,
                                              LocalDate dateOfBirth,
                                              DepartmentType department,
                                              Boolean registered,
                                              int salary) {
        Nurse nurse = new Nurse();
        nurse.setEmail(email);
        nurse.setName(name);
        nurse.setAddress(address);
        nurse.setDateOfBirth(dateOfBirth);
        nurse.setDepartment(department);
        nurse.setRegistered(registered);
        nurse.setSalary(salary);
        return nurse;
    }

    public static List<NurseDto> prepareNurseDtos() {
        Address address1 = new Address("C12", "Independence", "Bucharest");
        String name1 = "Mario";
        String email1 = "mario@gmail.com";
        LocalDate dateOfBirth1 = LocalDate.of(1982,8, 27);
        DepartmentType department1 = DepartmentType.Neurology;
        Boolean registered1 = true;
        int salary1 = 9000;

        Address address2 = new Address("C14", "Independence", "Bucharest");
        String name2 = "Antonio";
        String email2 = "antonio@gmail.com";
        LocalDate dateOfBirth2 = LocalDate.of(1984,8, 3);
        DepartmentType department2 = DepartmentType.Cardiology;
        Boolean registered2 = true;
        int salary2 = 7000;

        NurseDto nurse1 = prepareNurseDto(email1, name1, address1, dateOfBirth1, department1, registered1, salary1);
        NurseDto nurse2 = prepareNurseDto(email2, name2, address2, dateOfBirth2, department2, registered2, salary2);

        return new ArrayList<>(List.of(nurse1, nurse2));
    }

    public static List<Nurse> prepareNurseEntities() {
        Address address1 = new Address("C12", "Independence", "Bucharest");
        String name1 = "Mario";
        String email1 = "mario@gmail.com";
        LocalDate dateOfBirth1 = LocalDate.of(1982,8, 27);
        DepartmentType department1 = DepartmentType.Neurology;
        Degree degree1 = Degree.MD;
        Boolean registered1 = true;
        int salary1 = 9000;

        Address address2 = new Address("C14", "Independence", "Bucharest");
        String name2 = "Antonio";
        String email2 = "antonio@gmail.com";
        LocalDate dateOfBirth2 = LocalDate.of(1984,8, 3);
        DepartmentType department2 = DepartmentType.Cardiology;
        Degree degree2 = Degree.MD;
        Boolean registered2 = true;
        int salary2 = 7000;

        Nurse nurse1 = prepareNurseEntity(email1, name1, address1, dateOfBirth1, department1, registered1, salary1);
        Nurse nurse2 = prepareNurseEntity(email2, name2, address2, dateOfBirth2, department2, registered2, salary2);

        return new ArrayList<>(List.of(nurse1, nurse2));
    }
}
