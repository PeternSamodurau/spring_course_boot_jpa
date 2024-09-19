package com.zaurtregulov.spring.boot.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //этот аннотация указывает, что данный класс является сущностью
@Table(name = "employees")
@Data //аннотация, которая создает геттеры,сеттеры, toString, hashCode и equals
@NoArgsConstructor //аннотация, которая создает конструктор без параметров

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //аннотация, которая указывает, что стратегия генерации идентификаторов является IDENTITY
    @Column(name = "id")
    private int id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "surname",unique = true)
    private String surname;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private int salary;



    public Employee(String name, String surname, String department, int salary) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }
}
