package com.karim.dans.test.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	private String emp_no;
	private String first_name;
	private String last_name;
	private String gender;
	private Date birth_date;
	private String dept_no;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String emp_no, String first_name, String last_name, String gender, Date birth_date, String dept_no) {
		super();
		this.emp_no = emp_no;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.birth_date = birth_date;
		this.dept_no = dept_no;
	}
	
	public String getEmp_no() {
		return emp_no;
	}
	
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Date getBirth_date() {
		return birth_date;
	}
	
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	
	public String getDept_no() {
		return dept_no;
	}
	
	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}
	
}
