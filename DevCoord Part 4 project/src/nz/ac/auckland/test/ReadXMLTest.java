package nz.ac.auckland.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import org.w3c.dom.Document;

import java_DOM_parser.Kind;
import java_DOM_parser.ReadXML;
import java_DOM_parser.WrongXML;
import nz.ac.auckland.proximity.Context_Structure;
import nz.ac.auckland.proximity.Task;

public class ReadXMLTest {

	static Document taskList;
	static Document context1;
	
	@BeforeClass
	public static void ReadXMLFiles(){
		taskList = ReadXML.readInput("TestXMLfiles/tasklist.xml");
		context1 = ReadXML.readInput("TestXMLfiles/context/local-1.xml");
	}
	
	/**
	 * Test if the task objects are created properly from
	 * the xml file tasklist.xml
	 */
	@Test
	public void taskObjectCreation() {
		Map<String, Task> map = null;
		try {
			map = ReadXML.createTaskObjects(taskList);
		} catch (WrongXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Task t1 = map.get("local-1");
		Task t2 = map.get("local-2");
				
		assertEquals(1, t1.getTaskID());
		assertEquals("TaskOne", t1.getLabel());
		assertEquals("local-1", t1.getHandle());
		
		assertEquals(2, t2.getTaskID());
		assertEquals("TaskTwo", t2.getLabel());
		assertEquals("local-2", t2.getHandle());
		
	}

	@Test
	public void taskContextSetting(){
		Map<String, Task> map = null;
		try {
			map = ReadXML.createTaskObjects(taskList);
			Task t1 = map.get("local-1");
			assertEquals(0, t1.getContextStructures().size());
			
			map = ReadXML.setContextOfTaskObject(context1, map);
			
			
			assertEquals(2, t1.getContextStructures().size());
			
			Map<String,Context_Structure> fileMap = t1.getContextStructures();
			Context_Structure f1 = fileMap.get("=Build 1.0/src&lt;TestClass{Class1.java");
			Context_Structure f2 = fileMap.get("=Build 1.0/src&lt;TestClass{Class2.java");
			
			assertTrue(f1.isSelected());
			assertTrue(f1.isEdited());
			assertTrue(f2.isSelected());
			assertFalse(f2.isEdited());
			
			
			
		} catch (WrongXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test that String is converted to the
	 * corret enum
	 */
	@Test
	public void kindEnumTest(){
		String selection = "selection";
		String Selection = "SELection";
		String edit = "edit";
		String Edit = "EdIT";
		
		assertEquals(Kind.SELECTION, Kind.fromString(selection));
		assertEquals(Kind.SELECTION, Kind.fromString(Selection));
		assertEquals(Kind.EDIT, Kind.fromString(edit));
		assertEquals(Kind.EDIT, Kind.fromString(Edit));

	}
}
