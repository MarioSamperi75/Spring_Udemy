package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.catalina.valves.LoadBalancerDrainingValve;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	List<Student> theStudents = new ArrayList<>();
	
	@PostConstruct
	public void LoadData() {
		
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Mario", "Samperi"));
		theStudents.add(new Student("Ellen", "Bredefeldt"));
		theStudents.add(new Student("Alexander", "Samperi-Bredefeldt"));
		
	}
	
	// define endpoint for /student -returns list of students
	@GetMapping("/students")
	public List<Student> getStudents() {
		return theStudents;
	}

}
