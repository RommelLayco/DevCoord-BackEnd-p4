package JunitTestCase;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

import java_DOM_parser.ReadXML;
import java_DOM_parser.WrongXML;
import nz.ac.auckland.proximity.Context_Structure;
import nz.ac.auckland.proximity.Task;
import nz.ac.auckland.proximity.TaskPair;

public class TaskPairTest {

	static Map<String, Task> task; 
	static Set<String> keys;
	
	
	static Document taskList;
	static Document context1;
	static Document context2;
	
	@BeforeClass
	public static void ReadXMLFiles(){
		taskList = ReadXML.readInput("TestXMLfiles/tasklist.xml");
		context1 = ReadXML.readInput("TestXMLfiles/context/local-1.xml");
		context2 = ReadXML.readInput("TestXMLfiles/context/local-2.xml");
	}
	
	@Before
	public void initializeMaps(){
		try {
			task = ReadXML.createTaskObjects(taskList);
			task = ReadXML.setContextOfTaskObject(context1, task);
			task = ReadXML.setContextOfTaskObject(context2, task);
		} catch (WrongXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//also 
		keys = task.keySet();
	}
	
	@After
	public void clearMap(){
		task.clear();
		keys.clear();
	}
	
	@Test
	public void testCreateTaskPair() {
		Iterator<String> it1 = keys.iterator();
		
		String key1 = it1.next();
		String key2 = it1.next();
		
		Task t1 = task.get(key1);
		Task t2 = task.get(key2);
		
		//create taskpair object
		TaskPair tp = new TaskPair(t1.getTaskID(), t2.getTaskID(),
				t1.getHandle(), t2.getHandle());
		
		
		assertEquals(t1.getTaskID(), tp.getID1());
		assertEquals(t2.getTaskID(), tp.getID2());
		assertEquals(t1.getHandle(), tp.getHandle1());
		assertEquals(t2.getHandle(), tp.getHandle2());
		assertEquals(0, tp.getProximityScore(), 0.0000000001);
		
		
	}
	
	/**
	 * keys of the two java file:
	 * 		=Build 1.0/src&lt;TestClass{Class2.java
	 *		=Build 1.0/src&lt;TestClass{Class1.java
	 * 
	 * Task 1 has both class1 and class 2
	 * Task 2 has only class1
	 * 
	 * In task1 
	 * 		class 1 is edit
	 * 		class 2 is select
	 * 
	 * In task2
	 * 		class 1 is select
	 */
	@Test
	public void testProximityCalc() {
		Iterator<String> it1 = keys.iterator();
		
		String key1 = it1.next();
		String key2 = it1.next();
		
		Task t1 = task.get(key1);
		Task t2 = task.get(key2);
		
		//create taskpair object
		TaskPair tp = new TaskPair(t1.getTaskID(), t2.getTaskID(),
				t1.getHandle(), t2.getHandle());
		
		Context_Structure t1f1 = t1.getContextStructures().get("=Build 1.0/src&lt;TestClass{Class1.java");
		Context_Structure t1f2 = t1.getContextStructures().get("=Build 1.0/src&lt;TestClass{Class2.java");
		Context_Structure t2f1 = t2.getContextStructures().get("=Build 1.0/src&lt;TestClass{Class1.java");
		
		assertTrue(t1f1.isEdited());
		
		assertTrue(t1f2.isSelected());
		assertFalse(t1f2.isEdited());
		
		assertTrue(t2f1.isSelected());
		assertFalse(t2f1.isEdited());
		
		
		tp.calcProximityScore(t1.getContextStructures(), t2.getContextStructures());
		assertEquals(0.49685534591, tp.getProximityScore() , 0.00001);
		
		
		
		
		
	}

}
