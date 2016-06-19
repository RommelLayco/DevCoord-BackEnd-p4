package nz.ac.auckland.devcoord.databasetest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import nz.ac.auckland.devcoord.commands.Commands;
import nz.ac.auckland.devcoord.database.Task;

public class CommandsTest {

	private static Commands service;

	@BeforeClass
	public static void setUpService(){
		service = new Commands();
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
	public void updateTaskInfo(){
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
	
}
