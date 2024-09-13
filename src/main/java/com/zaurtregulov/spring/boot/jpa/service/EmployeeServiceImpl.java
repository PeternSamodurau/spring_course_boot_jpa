package com.zaurtregulov.spring.boot.jpa.service;


import com.zaurtregulov.spring.boot.jpa.dao.EmployeeRepository;
import com.zaurtregulov.spring.boot.jpa.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service //этот аннотация указывает, что данный класс является сервисом - службой внедрения зависимостей и бизнес-логикой
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
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
}
