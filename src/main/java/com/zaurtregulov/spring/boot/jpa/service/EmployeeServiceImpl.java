package com.zaurtregulov.spring.boot.jpa.service;


import com.github.javafaker.Faker;
import com.zaurtregulov.spring.boot.jpa.dao.EmployeeRepository;
import com.zaurtregulov.spring.boot.jpa.entity.Application;
import com.zaurtregulov.spring.boot.jpa.entity.Employee;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Slf4j
@Service //этот аннотация указывает, что данный класс является сервисом - службой внедрения зависимостей и бизнес-логикой
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private List<Employee> employeeList;

    @Override
    public void loadEmployeeInDB() {
        Faker faker = new Faker();
        List<Employee> employeeList = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            employee.setName(faker.name().firstName());
            employee.setSurname(faker.name().lastName());
            employee.setDepartment(faker.job().title());
            employee.setSalary(faker.number().numberBetween(1000, 100000));
            employeeList.add(employee);
        }

        employeeRepository.saveAll(employeeList);

        log.info("Employee list loaded successfully");
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();            //findAll() - метод Spring Data JPA, который возвращает все записи из таблицы
    }
    @Override
    public void saveEmployee(Employee employee) {

        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(int id) {

        Employee employee = null;

        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isPresent()) {
            employee = optional.get();
            log.info("Employee found: " + employee);
        }else log.error("Employee not found!");

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {

        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllByName(String name) {

        List<Employee> employees = employeeRepository.findAllByName(name);
        return employees;
    }
}
