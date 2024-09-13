package com.zaurtregulov.spring.boot.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //этот аннотация указывает, что данный класс является сущностью
@Table(name = "employees")
@Getter
@Setter
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //аннотация, которая указывает, что стратегия генерации идентификаторов является IDENTITY
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private int salary;

    public Employee() {
    }

    public Employee(String name, String surname, String department, int salary) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }
}
