package nz.ac.auckland.devcoord.databasetest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import nz.ac.auckland.devcoord.commands.Commands;
import nz.ac.auckland.devcoord.controller.Controller;
import nz.ac.auckland.devcoord.controller.TaskWrapper;
import nz.ac.auckland.devcoord.database.Context_Structure;
import nz.ac.auckland.devcoord.database.Task;

public class CommandsTest {

	private static Commands service;
	private static Controller controller;

	@BeforeClass
	public static void setUpService(){
		service = new Commands();
		controller = new Controller();
	}


	/**
	 * 
	 * Test addition of task object using commands from the 
	 * database plugin
	 * 
	 */

	@Test
	public void addNewTask(){
		Task t1 = new Task(-1, "comp-key1", "testing Composite Key");

		service.addTask(t1);

		Task t2 = service.getTask(-1);
		assertEquals(t1.getTaskID(), t2.getTaskID());
		assertEquals(t1.getLabel(), t2.getLabel());
		assertEquals(t1.getHandle(), t2.getHandle());
	}
	
	/**
	 * Test update of task using commands from database plugin
	 */

	@Test
	public void updateTask(){
		Task t1 = new Task(-2, "update value", "testing update existing");

		service.addTask(t1);
		
		t1.updateHandle("newer handle name");
		service.updateTask(t1);
		
		Task t2 = service.getTask(-2);
		assertEquals(t1.getTaskID(), t2.getTaskID());
		assertEquals(t1.getLabel(), t2.getLabel());
		assertEquals(t1.getHandle(), t2.getHandle());
	}
	
	/**
	 * Test update/ add task info with the method from 
	 * controller method in the plugin plugin
	 * 
	 * Uses the two add and update commands tested above,
	 * 
	 * Uses a task wrapper to update the information
	 * 
	 */
	public void updateUsingController(){
		Context_Structure c1 = new Context_Structure("wrapperTest", true, true);
		TaskWrapper wrapper = TaskWrapper.
				getTestWrappper(-3, "wrapper", "test", c1);
		
		controller.updateTaskInfo(wrapper);
		Task t1 = controller.getTask(-3);
		
		assertEquals(t1.getTaskID(), -3);
		assertEquals(t1.getLabel(), "wrapper");
		assertEquals(t1.getHandle(), "test");
		
		//get context Structure
		Context_Structure c2 = t1.getContextStructures().get("wrapperTest");
		assertEquals("wrapperTest", c2.getName());
		assertTrue(c2.isEdited());
		assertTrue(c2.isSelected());
		
	}
	
}
