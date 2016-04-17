package JunitTestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class DataBaseTest {
	static SessionFactory sessionFactory;
	static Session session;

	@BeforeClass
	public static void createSessionFactory(){
		sessionFactory =  new Configuration().configure().buildSessionFactory();
	}

	@Before
	public void createSession(){
		session = sessionFactory.openSession();

		session.beginTransaction();
	}

	@Test
	public void testPushingDataBase(){
		// this would save the Student_Info object into the database
		FakeClass fakeTask = new FakeClass(1,"faketask");
		session.save(fakeTask);
		
		
	}

	@After
	public void commitTransaction(){
		session.getTransaction().commit();
		session.close();
	}

	@AfterClass
	public static void closeSessionFactory(){
		sessionFactory.close();
	}
}
