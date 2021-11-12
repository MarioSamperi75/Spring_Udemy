package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;
	

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
