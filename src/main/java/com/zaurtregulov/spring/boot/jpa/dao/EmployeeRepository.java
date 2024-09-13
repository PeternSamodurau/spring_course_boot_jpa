package com.zaurtregulov.spring.boot.jpa.dao;

import com.zaurtregulov.spring.boot.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {   //Integer - тип первичного ключа

}
