package com.medical.api.entity.employee;

import com.medical.api.entity.Person;
import com.medical.api.entity.enums.DepartmentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Person {

    private int salary;

    @Column(name = "department")
    @Enumerated(EnumType.STRING)
    private DepartmentType department;

}
