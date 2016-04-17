package nz.ac.auckland.test;

import static org.junit.Assert.*;

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

import nz.ac.auckland.proximity.Context_Structure;
import nz.ac.auckland.proximity.Task;


public class Database {
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
//		taskList = ReadXML.readInput("TestXMLfiles/tasklist.xml");
//		context1 = ReadXML.readInput("TestXMLfiles/context/local-1.xml");
//		context2 = ReadXML.readInput("TestXMLfiles/context/local-2.xml");
	}

	@Before
	public void createSession(){
		//need to have task map
//		try {
//			task = ReadXML.createTaskObjects(taskList);
//			task = ReadXML.setContextOfTaskObject(context1, task);
//			task = ReadXML.setContextOfTaskObject(context2, task);
//		} catch (WrongXML e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		session = sessionFactory.openSession();

		session.beginTransaction();
	}

	

	@Test
	public void testTaskObject(){
		Task t1 = new Task(1, "test-task", "Rommel's Task");
		t1.addContextStructure("s1", new Context_Structure("s1", true, false));
		t1.addContextStructure("structure2", new Context_Structure("structure2",false, false));
		session.save(t1);
		
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
