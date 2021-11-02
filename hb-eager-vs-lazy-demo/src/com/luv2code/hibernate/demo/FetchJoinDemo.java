package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class FetchJoinDemo {

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
			
			//option 2: Hibernate query with HQL
			
			
			// get the instructor from the DB
			int theId = 1;
			
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
									+ "JOIN FETCH i.courses "
									+ "where i.id=:theInstructorId", 
							Instructor.class);
			
			//set parameters on query
			query.setParameter("theInstructorId", theId);
			
			//execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("luvToCode: Instructor: " + tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			
			// get course for the instructor efter closing session
			// how to solve lazy loadig issue? how to retrieve courses when the session is closed?
			
			
			System.out.println("\nluvToCode: The session is now closed\n");
			
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
