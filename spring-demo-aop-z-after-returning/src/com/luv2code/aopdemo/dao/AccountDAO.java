package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;
	
	
	// add a new method: findAccounts()
	
	public List<Account> findAccounts() {
		
		List<Account> myAccounts = new ArrayList<>();
		
		// create sample accounts
		Account temp1 = new Account("John", "Silver");
		Account temp2 = new Account("Mario", "Platinum");
		Account temp3 = new Account("Luca", "Gold");
		
		// add those accounts to our list
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
						
		
		return myAccounts;
	}

	public void addAccount(Account thAccount, boolean vipFlag) {
		
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
		
	}
	
	public boolean doWork(){
		System.out.println(getClass() + ": doWork(");
		
		return true;
	}

	public String getName() {
		System.out.println(getClass() + ": in getName(");
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println(getClass() + ": in setName(");
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": in getServiceCode(");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
		System.out.println(getClass() + ": in setServiceCode(");
	}
	
	
}
