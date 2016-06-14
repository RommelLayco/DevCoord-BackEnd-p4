package nz.ac.auckland.devcoord.databasetest;



import org.junit.BeforeClass;
import org.junit.Test;

import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.test.TaskDataServiceImpl;

public class TaskTest {

	private static TaskDataServiceImpl service;

	@BeforeClass
	public static void createEntFactory(){
		service = new TaskDataServiceImpl();

	}

	@Test
	public void testCreateNewTask(){

		Task t1 = new Task(-1, "comp-key1", "testing Composite Key");

		service.add(t1);


	}

}
