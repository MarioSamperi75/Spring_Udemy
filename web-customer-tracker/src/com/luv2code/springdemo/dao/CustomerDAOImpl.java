package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	
	// need to inject the session factory
	 @Autowired
	 private SessionFactory SessionFactory;
	

	 // we removed @transactional, because the DAO is involved in the service transaction. 
	 // When we use both service and DAO, write @Transactional just in the service!! 
	@Override
	public List<Customer> getCustomers() {
	
		// get the current hibernate session
		Session currentSession = SessionFactory.getCurrentSession();
		
		// create a query ... and sort by lastName
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", 
															   Customer.class);
		 
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		
		// return the result
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		// get the current hibernate session
		Session currentSession = SessionFactory.getCurrentSession();
		
		//save the customer
		currentSession.save(theCustomer);
		
	}

}
