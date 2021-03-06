package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
					 			.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			//query students: lastName='Samperi'		
			theStudents = session.createQuery("from Student s where s.lastName='Samperi'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who have last name of Samperi");
			displayStudents(theStudents);
			
			//query students: lastName='Samperi' or firstName="Alexander"		
			theStudents = session.createQuery("from Student s where"
					+ " s.lastName='Samperi' OR s.firstName='Alexander'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who have last name of Samperi or first Name of Alexander");
			displayStudents(theStudents);
			
			//query students: LIKE '%libero.it"		
			theStudents = session.createQuery("from Student s where"
					+ " s.email LIKE '%libero.it'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents whose email ends with libero.it");
			displayStudents(theStudents);
			
			//commit transaction 
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
			
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
