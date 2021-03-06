package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			// create a student object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Mario", "Samperi", "mariosss@libero.it");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			session.save(tempStudent);
			System.out.println("Saving the student...");
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
			
		}

	}

}
