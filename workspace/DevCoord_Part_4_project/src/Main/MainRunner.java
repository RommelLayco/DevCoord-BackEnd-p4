package Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nz.ac.auckland.proximity.Task;

public class MainRunner {

	public static void main(String[] args) {
		SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();

		// this would save the task
		Task t1 = new Task(1,"local-Rommel", "randomTask");
		session.save(t1);
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

	}

}
