package JunitTestCase;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import org.w3c.dom.Document;

import PromixtyCalc.Task;
import java_DOM_parser.ReadXML;

public class ReadXMLTest {

	static Document taskList;
	
	@BeforeClass
	public static void ReadXMLFiles(){
		taskList = ReadXML.readInput("TestXMLfiles/tasklist.xml");
	}
	
	/**
	 * Test if the task objects are created properly from
	 * the xml file tasklist.xml
	 */
	@Test
	public void taskObjectCreation() {
		Map<Integer, Task> map = ReadXML.createTaskObjects(taskList);
		Task t1 = map.get(1);
		Task t2 = map.get(2);
		
		assertEquals(1, t1.getTaskID());
		assertEquals("TaskOne", t1.getLabel());
		assertEquals("local-1", t1.getHandle());
		
		assertEquals(2, t2.getTaskID());
		assertEquals("TaskTwo", t2.getLabel());
		assertEquals("local-2", t2.getHandle());
		
	}

}
