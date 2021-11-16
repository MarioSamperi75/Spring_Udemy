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
	 private SessionFactory sessionFactory;
	

	 // we removed @transactional, because the DAO is involved in the service transaction. 
	 // When we use both service and DAO, write @Transactional just in the service!! 
	@Override
	public List<Customer> getCustomers() {
	
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
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
		Session currentSession = sessionFactory.getCurrentSession();
		 
		//save or update the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}


	@Override
	public Customer getCustomer(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve/read from the database using the primary key
		Customer theCustomer= currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object using the primary key
		//option1 :delete by query
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
			
 		// option2 :retrieve the customer and delete from the session. Without query.
		// Customer theCustomer= currentSession.get(Customer.class, theId);
		// currentSession.delete(theCustomer);
	
	}

}
