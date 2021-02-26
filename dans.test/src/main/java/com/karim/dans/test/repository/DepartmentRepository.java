package com.karim.dans.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.karim.dans.test.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, String>{

}
