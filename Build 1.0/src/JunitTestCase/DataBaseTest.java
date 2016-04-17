package JunitTestCase;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

import PromixtyCalc.Task;
import java_DOM_parser.ReadXML;
import java_DOM_parser.WrongXML;



public class DataBaseTest {
	static SessionFactory sessionFactory;
	static Session session;

	static Document taskList;
	static Document context1;
	static Document context2;
	
	static Map<String, Task> task; 
	
	@BeforeClass
	public static void createSessionFactory(){
		sessionFactory =  new Configuration().configure().buildSessionFactory();
		
		//need to also read in the xml files
		taskList = ReadXML.readInput("TestXMLfiles/tasklist.xml");
		context1 = ReadXML.readInput("TestXMLfiles/context/local-1.xml");
		context2 = ReadXML.readInput("TestXMLfiles/context/local-2.xml");
	}

	@Before
	public void createSession(){
		//need to have task map
		try {
			task = ReadXML.createTaskObjects(taskList);
			task = ReadXML.setContextOfTaskObject(context1, task);
			task = ReadXML.setContextOfTaskObject(context2, task);
		} catch (WrongXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session = sessionFactory.openSession();

		session.beginTransaction();
	}

	@Test
	public void testPushingDataBase(){
		// this would save the Student_Info object into the database
		FakeClass fakeTask = new FakeClass(1,"faketask");
		session.save(fakeTask);
		
	}
	
	@Test
	public void testTaskObject(){
		Task t1 = new Task(0, "test-task", "Rommel's Task");
		session.save(t1);
	}
	
	@Test
	public void testPushAllTask(){
		
		for(Task t : task.values()){
			session.save(t);
		}
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
