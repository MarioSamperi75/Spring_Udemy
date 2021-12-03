package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.From;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	EntityManager entityManager;
	
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {

		// create a query
		Query theQuery = 
				entityManager.createQuery("from Employee");
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		// get employee
		Employee thEmployee = entityManager.find(Employee.class, theId);		
		
		//return employee
		return thEmployee;
		
	}

	@Override
	public void save(Employee theEmployee) {
		// save or update the employee
		Employee dbEmployee = entityManager.merge(theEmployee);	
		
		// the id is generaded automatically in the db
		// but it's nice if we have it in our entity too!
		theEmployee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteById(int theId) {
		
		// delete object with primary key
		Query theQuery = entityManager.createQuery(
				"delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
	}

}
