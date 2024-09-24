package com.zaurtregulov.spring.boot.jpa.controller;


import com.zaurtregulov.spring.boot.jpa.entity.Employee;
import com.zaurtregulov.spring.boot.jpa.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //это контроллер приложения который управляет Rest запросами и возвращает ответы в формате JSON
@RequestMapping("/api") //аннотация, которая указывает, что URL-адрес начинается с "/api"
@Slf4j
@AllArgsConstructor
public class EmployeeController {

    @Autowired //аннотация @Autowired используется для автоматического создания бинов
    private EmployeeService employeeService;

    //  @Secured("ADMIN")
    @GetMapping("/employees")
    //аннотация, которая указывает, что URL-адрес "/employees" будет обрабатываться этим контроллером методом GET
    public List<Employee> showAllEmployees() {

        List<Employee> allEmployees = employeeService.getAllEmployees();//получаем список всех сотрудников
        log.info("Show all employees success");

        return allEmployees;
    }

    @GetMapping("/employee/id/{id}") //аннотация, которая указывает, что URL-адрес "/employees/{id}" будет обрабатываться этим контроллером методом GET
    public Employee getEmployee(@PathVariable("id") int id) {

        Employee employee = employeeService.getEmployee(id);//получаем сотрудника по id
        if (id < 0 || employee == null) {
            log.error("Employee not found!" + id);
            throw new RuntimeException("Employee with ID = " + id + " not found");

        } else log.info("Get employee by ID success: " + employee);
        return employee;
    }

    // только через postman
    @PostMapping("/employee/post")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        log.info("Employee: " + employee);
        return employee;
    }

    @PutMapping("/employee/put")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        log.info("Employee: " + employee);
        return employee;
    }

    @DeleteMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {

        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            log.error("Employee not found!");

            return "Employee with ID = " + id + " not found";
        } else
            employeeService.deleteEmployee(id);

        log.info("Employee deleted: " + employee);
        return "Employee with ID = " + id + " was deleted";
    }

    @PutMapping("/employees/load")
    public ResponseEntity<String> loadEmployees() {
        try {
            employeeService.loadEmployeeInDB();

            log.info("Employees loaded successfully");
            return ResponseEntity.ok("Employees loaded successfully");
        } catch (Exception e) {
            log.error("Error loading employees", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading employees");
        }
    }
    @GetMapping("/get-employee/name/{name}")
    public List<Employee> showAllEmployeesByName(@PathVariable("name") String name){

        return employeeService.findAllByName(name);
    }
}
