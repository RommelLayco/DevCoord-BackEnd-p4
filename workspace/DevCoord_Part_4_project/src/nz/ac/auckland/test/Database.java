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

import java_DOM_parser.ReadXML;
import java_DOM_parser.WrongXML;
import nz.ac.auckland.proximity.Context_Structure;
import nz.ac.auckland.proximity.Task;
import nz.ac.auckland.proximity.TaskPair;


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

	/**
	 * Test if task pair has correct annotations
	 * Test to make sure if input to object creation is reversed will
	 * throw an exeception as the task already exisit
	 */
	@Test
	public void TestCompositeKeyOfTaskPair(){
		Task t1 = new Task(-1, "comp-key1", "testing Composite Key");
		Task t2 = new Task(-2, "comp-key2", "testing Composite Key");
		Task t3 = new Task(-3, "comp-key3", "allow t1 and t3 when t1 and t2 already exisit");
		
		
		TaskPair tp = new TaskPair(t1, t2);
		TaskPair tp2 = new TaskPair(t3,t1);
		session.save(tp);
		try{
			session.save(tp2);
		} catch(Exception ex){
			System.err.println(ex.getMessage());
		}
			
	}
	
	/**
	 * Need to create fake task and calc score
	 */
	@Test
	public void TestAcutalTaskPair(){
		Task t1 = task.get("local-1");
		Task t2 = task.get("local-2");
		
		
		TaskPair tp = new TaskPair(t1, t2);
		tp.calcProximityScore();
		session.save(tp);
		
		//make sure the two task are pushed to database as well
		Task taskFromDatabase1 = session.get(Task.class, t1.getTaskID());
		//Task taskFromDatabase2 = session.get(Task.class, t2.getTaskID());
		
		assertEquals(t1.getTaskID(), taskFromDatabase1.getTaskID());
		assertEquals(t1.getLabel(), taskFromDatabase1.getLabel());
		assertEquals(t1.getHandle(), taskFromDatabase1.getHandle());
		
			
	}

	@Test
	public void testTaskObject(){
		Task t1 = new Task(0, "test-task", "Rommel's Task");
		t1.addContextStructure("s1", new Context_Structure("s1", true, false));
		t1.addContextStructure("structure2", new Context_Structure("structure2",false, false));
		session.save(t1);
		
	}
	
	/**
	 * Test to check if all task objects stored in local
	 * map are pushed to database
	 */
	@Test
	public void testPushTaskMap(){
		
		for(Task t1 : task.values()){
			//check if it exisit in the database
			Task t = session.get(Task.class, t1.getTaskID());
			
			if(t == null){
			session.persist(t1);
			}
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
