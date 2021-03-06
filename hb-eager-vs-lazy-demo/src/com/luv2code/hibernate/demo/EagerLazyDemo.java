package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			
			// get the instructor from the DB
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("luvToCode: Instructor: " + tempInstructor);
			
			// get course for the instructor
			System.out.println("luvToCode: Courses: " + tempInstructor.getCourses()); 
			
			//commit transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			
			// get course for the instructor efter closing session
			// how to solve lazy loadig issue? how to retrieve courses when the session is closed?
			//option1: retrieve when the session is open. Course will be in the memory and you will get again and again.
			
			System.out.println("\nluvToCode: The session is now closed\n" + tempInstructor);
			
			System.out.println("luvToCode: Courses: " + tempInstructor.getCourses()); 
			
			
			System.out.println("luvToCode:  Done!!");
			
		}
		finally {
			// add clean up code
			
			session.close();
			factory.close();
			
		}

	}

}
