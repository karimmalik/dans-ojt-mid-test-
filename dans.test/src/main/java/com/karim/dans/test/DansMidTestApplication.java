package com.karim.dans.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.karim.dans.test.model.Department;
import com.karim.dans.test.repository.DepartmentRepository;

@SpringBootApplication
public class DansMidTestApplication {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DansMidTestApplication.class, args);
	}

	@Bean
    CommandLineRunner init (){
        return args -> {
            Department department1 = new Department();
            department1.setDept_no("ENG");
            department1.setDept_name("Engineering");
            departmentRepository.save(department1);
            
            Department department2 = new Department();
            department2.setDept_no("FIN");
            department2.setDept_name("Finance");
            departmentRepository.save(department2);
            
            Department department3 = new Department();
            department3.setDept_no("HRD");
            department3.setDept_name(" Human Resource Development");
            departmentRepository.save(department3);

        };
    }
}
