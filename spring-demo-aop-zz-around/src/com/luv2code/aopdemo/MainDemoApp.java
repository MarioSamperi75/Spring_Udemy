package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		
		// get membership bean from spring container
		
		MembershipDAO theMembershipDAO =  context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the business methods
		Account myAccount = new Account();
		myAccount.setName("Mario");
		myAccount.setLevel("Platinum");
		
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();
		
		// call the accountdao getter/setter methods
		theAccountDAO.setName("Prova");
		theAccountDAO.setServiceCode("Prova");
		
		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();
		
		
		// the membership business method
		theMembershipDAO.addAccount();
		theMembershipDAO.goToSleep();
				
		// close the context
		context.close();
	}

}










