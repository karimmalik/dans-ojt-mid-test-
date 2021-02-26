create database dans_mid_test;
use dans_mid_test;

CREATE TABLE department (
    dept_no varchar(3) NOT NULL,
    dept_name varchar(45),
    primary key (dept_no )
);

create table employee(
	emp_no VARCHAR (15),
    first_name VARCHAR (45),
    last_name VARCHAR(45),
    gender ENUM ('MALE', 'FEMALE'),
    birth_date DATE,
    dept_no varchar(3),
    foreign key (dept_no) references department(dept_no)
);