package com.zaurtregulov.spring.boot.jpa.dao;

import com.zaurtregulov.spring.boot.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// jpa - java persistence api - стандартная спецификация для работы с базами данных
// hibernate - самая популярная реализация спецификации jpa
//jpa описывает правила работы с базами данных, а hibernate - реализует эти правила
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {   //Integer - тип первичного ключа
  public List<Employee> findAllByName(String name);
}
