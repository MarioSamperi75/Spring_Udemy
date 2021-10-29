package com.luv2code.jdbc;


import java.sql.DriverManager;

import org.hibernate.boot.model.relational.AuxiliaryDatabaseObject.Expandable;

import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;

public class Test_Jdbc {

	public static void main(String[] args) {
		String jdbcUrl= "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";
		try {
			System.out.println("Connecting to the database: " + jdbcUrl);
			
			Connection myConn = 
					DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successful !!");
					
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
