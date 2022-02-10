package com.management.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan({ "com.management.student" })
public class StudentApplication{


	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

}
