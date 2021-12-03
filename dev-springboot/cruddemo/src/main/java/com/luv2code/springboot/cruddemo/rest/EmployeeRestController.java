package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	// add mapping for GET /employees/{employeeId} 
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		if (theEmployee==null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		return theEmployee;
	}

	// add mapping for POST /employees - add new employee 
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
	
		// also just in case they pass an Id in JSON ... set id to 0
		// this is to force a save of a new item ... instead of update
		theEmployee.setId(0);
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	
	}
	
}






