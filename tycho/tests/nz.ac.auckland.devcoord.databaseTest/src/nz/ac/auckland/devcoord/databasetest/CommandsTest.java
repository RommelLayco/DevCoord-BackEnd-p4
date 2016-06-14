package nz.ac.auckland.devcoord.databasetest;

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
	 * Test addition of task object
	 * 
	 */
	
	@Test
	public void addNewTask(){
		Task t1 = new Task(-1, "comp-key1", "testing Composite Key");
		
		service.addTask(t1);
	}
}
