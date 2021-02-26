package com.karim.dans.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karim.dans.test.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
}
