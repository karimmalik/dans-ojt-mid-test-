package com.karim.dans.test.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.karim.dans.test.model.Employee;

public interface EmployeeService {
	
	public String createEmployee(@RequestBody Employee employee);
	public Employee updateEmployee(@RequestBody Employee employee);
	public List<Employee> deleteEmployee(@PathVariable String id);
	public List<Employee> retrieveAllEmployee();
	public Employee retrieveEmployeeId(@PathVariable String id);
	public void downloadAllEmployee(HttpServletResponse response) throws IOException;
	
}
