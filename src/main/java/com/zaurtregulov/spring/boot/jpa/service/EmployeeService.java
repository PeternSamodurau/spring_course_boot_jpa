package com.zaurtregulov.spring.boot.jpa.service;

import com.zaurtregulov.spring.boot.jpa.entity.Employee;

import java.util.List;

// интерфейс DAO отвечает за доступ к данным, а Service отвечает за бизнес-логику.
// DAO- позволяет абстрагировать доступ к данным от бизнес-логики,
// что упрощает изменение и поддержку приложения.
public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployee(int id);

    public void deleteEmployee(int id);
}
