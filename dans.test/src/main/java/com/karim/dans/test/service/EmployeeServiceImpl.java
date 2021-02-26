package com.karim.dans.test.service;

import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.karim.dans.test.exc.EmployeeNotFoundException;
import com.karim.dans.test.model.Employee;
import com.karim.dans.test.repository.EmployeeRepository;

@RestController
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	@PostMapping("/Employee")
	public String createEmployee(@RequestBody Employee employee) {
		// TODO Auto-generated method stub
		// used for validation
		try {
			if (employee.getFirst_name()==null) {
				throw new NullPointerException("Please fill in first name value");
			}
			if (employee.getLast_name()==null) {
				throw new NullPointerException("Please fill in last name value");
			}
			if (employee.getBirth_date()==null) {
				throw new NullPointerException("Please fill in birth date value");
			}
			if (employee.getGender()==null ) {
				throw new NullPointerException("Please fill in first name date value");
			}
		}
		catch (Exception e) {
			return e.toString();
		}
		
		// setup date format for emp_id
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMYYYY");
		String empDate = formatter.format(employee.getBirth_date());
		
		// setup random number 
		Random rand = new Random();
		String randomNumber = String.format("%04d", rand.nextInt(10000));
		
		// merge all the manipulated data
		String emp_no = employee.getDept_no() + empDate + randomNumber;
		employee.setEmp_no(emp_no);
		employeeRepository.save(employee);
		
		return employee.getFirst_name() + " " + employee.getLast_name() + " has been added to database";
		
	}


	@Override
	@PutMapping("/Employee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		// get the data from current id
		Optional<Employee> employeeCheck= employeeRepository.findById(employee.getEmp_no());
		
		// validate only filled data will be change
		if (employee.getFirst_name()==null) {
			employee.setFirst_name(employeeCheck.get().getFirst_name());
		}
		if (employee.getLast_name()==null) {
			employee.setLast_name(employeeCheck.get().getLast_name());
		}
		if (employee.getBirth_date()==null) {
			employee.setBirth_date(employeeCheck.get().getBirth_date());
		}
		if (employee.getDept_no()==null) {
			employee.setDept_no(employeeCheck.get().getDept_no());
		}
		if (employee.getGender()==null) {
			employee.setGender(employeeCheck.get().getGender());
		}
		// save the data into database
		employeeRepository.save(employee);		
		return employee;
	}
	
	@Override
	@DeleteMapping("/Employee/{empNo}")
	public List<Employee> deleteEmployee(@PathVariable String empNo) {
		// TODO Auto-generated method stub
		// get the data from current empNo
		Optional<Employee> employees = employeeRepository.findById(empNo);

		// if not found throw exception
		if (!employees.isPresent()) {
			throw new EmployeeNotFoundException("id-" + empNo);
		}
		
		// delete the data by empNo
		employeeRepository.deleteById(empNo);
		
		// get the list employee for the return
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}

	@Override
	@GetMapping("/Employee")
	public List<Employee> retrieveAllEmployee() {
		// TODO Auto-generated method stub
		// get all the employee
		return employeeRepository.findAll();
	}

	@Override
	@GetMapping("/Employee/{empNo}")
	public Employee retrieveEmployeeId(@PathVariable String empNo) {
		// TODO Auto-generated method stub
		// get the employee detail by empNo
		Optional<Employee> employees = employeeRepository.findById(empNo);

		// if not found, throw exception
		if (!employees.isPresent()) {
			throw new EmployeeNotFoundException("id-" + empNo);
		}
		
		// return the data from above
		return employees.get();
	}

	@Override
	@GetMapping("/Employee/download")
	public void downloadAllEmployee(HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		// set the content type (text : postman, csv : browser)
		response.setContentType("text/csv");
		
		// set the data for header
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        
        // set configuration csv
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        
        // get all the data from employee table
        List<Employee> employees = employeeRepository.findAll();
        
        // set up the csv body configuration
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Employee ID", "First Name", "Last Name", "Birth Date", "Dept No"};
        String[] nameMapping = {"emp_no", "first_name", "last_name", "birth_date", "dept_no"};
        
        csvWriter.writeHeader(csvHeader);
        
        // type up csv 
        for (Employee employee : employees) {
            csvWriter.write(employee, nameMapping);
        }
         
        csvWriter.close();
	}

}
