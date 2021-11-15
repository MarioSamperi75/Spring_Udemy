package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// Call the methods to find the Account
		List<Account> theAccounts = null;
		
		try {
			// add a boolean flag to simulate an exception
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		
		} 
		catch (Exception exc) {

			System.out.println("\nMain Program ... caugh exception: " + exc);
		}
		
		// display the accounts
		System.out.println("\n\n Main program: AfterTrowingDemoApp");
		System.out.println("-----");
		
		System.out.println(theAccounts);
		
		System.out.println("\n");
		
		
		System.out.println("\n----------- no excpetion test --------------");
		
		boolean tripWire = false;
		theAccounts= theAccountDAO.findAccounts(tripWire);
		
				
		// close the context
		context.close();
	}

}










