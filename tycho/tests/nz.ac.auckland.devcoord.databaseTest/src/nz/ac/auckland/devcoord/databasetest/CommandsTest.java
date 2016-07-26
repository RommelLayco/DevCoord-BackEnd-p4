package nz.ac.auckland.devcoord.databasetest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import nz.ac.auckland.devcoord.commands.Commands;
import nz.ac.auckland.devcoord.commands.HibernateUtil;
import nz.ac.auckland.devcoord.controller.Controller;
import nz.ac.auckland.devcoord.controller.TaskWrapper;
import nz.ac.auckland.devcoord.database.Context_Structure;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;

public class CommandsTest {

	private static Commands service;
	private static Controller controller;
	private static HibernateUtil hibernateUtil;


	@BeforeClass
	public static void setUpService(){
		hibernateUtil = HibernateUtil.getInstance();
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
	 * test method to check if task exist in database
	 */
	@Test
	public void checkIfExist(){
		assertFalse(service.taskExist(-3));

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
	@Test
	public void updateUsingController(){
		Context_Structure c1 = new Context_Structure("wrapperTest", true, true);
		TaskWrapper wrapper = TaskWrapper.
				getTestWrappper(-4, "wrapper", "test", c1);

		Task t1 = controller.updateTaskInfo(wrapper);

		//need to lazy load entire object graph
		//use a test entity manager to keep session open
		EntityManager em = hibernateUtil.getEntityManager();
		Task t2 = em.find( Task.class, -4 );
		assertNotNull(t2);

		assertEquals(t1.getTaskID(), t2.getTaskID());
		assertEquals(t1.getLabel(), t2.getLabel());
		assertEquals(t1.getHandle(), t2.getHandle());



		Context_Structure c2 = t2.getContextStructures().get("wrapperTest");
		assertEquals("wrapperTest", c2.getName());
		assertTrue(c2.isEdited());
		assertTrue(c2.isSelected());
		em.close();

	}

	@Test
	public void queryTaskTest(){
		Context_Structure c1 = new Context_Structure("queryTest", true, true);
		TaskWrapper wrapper = TaskWrapper.
				getTestWrappper(-5, "query", "test", c1);

		TaskWrapper wrapper2 = TaskWrapper.
				getTestWrappper(-6, "query2", "test2", c1);

		Task t1 = controller.updateTaskInfo(wrapper);
		controller.updateTaskInfo(wrapper2);

		List<Task> tasks = service.getTaskWithSameContext(t1);
		assertEquals(tasks.size(), 1);
	}

	/**
	 * Add task pair without persisting task first
	 */
	@Test
	public void addTaskPair(){
		Task t1 = new Task(-7,"test", "add taskPair");
		Task t2 = new Task(-8,"test", "add taskPair");

		TaskPair tp = new TaskPair(t1,t2);
		service.addTaskPair(tp);
	}

	@Test
	public void updateTaskPair(){
		Task t1 = new Task(-9,"test", "add taskPair");
		Task t2 = new Task(-10,"test", "add taskPair");

		TaskPair tp = new TaskPair(t1,t2);
		service.addTaskPair(tp);

		tp.getTask1().updateLablel("update task pair");
		tp.getTask2().updateLablel("update task pair");

		service.updateTaskPair(tp);
	}

	@Test
	public void taskPairExist(){
		assertFalse(service.taskPairExist(-3));
	}

	@Test
	public void getTaskPair(){
		Task t1 = new Task(-11,"test", "get Task pair from task id");
		Task t2 = new Task(-12,"test", "get Task pair from task id");

		TaskPair tp = new TaskPair(t1,t2);
		service.addTaskPair(tp);

		//get Task pair
		TaskPair tp2 = service.getTaskPair(-11, -12);

		assertEquals(tp.getTask1().getTaskID(), tp2.getTask1().getTaskID());
		assertEquals(tp.getTask2().getTaskID(), tp2.getTask2().getTaskID());

		assertEquals(tp.getTask1().getHandle(), tp2.getTask1().getHandle());
		assertEquals(tp.getTask2().getHandle(), tp2.getTask2().getHandle());

		assertEquals(tp.getTask1().getLabel(), tp2.getTask1().getLabel());
		assertEquals(tp.getTask2().getLabel(), tp2.getTask2().getLabel());
	}

	/**
	 * Test persist of task pairs where the tasks that make
	 * the task pair already exist in the database.
	 * 
	 * Should throw no error.
	 */
	@Test
	public void testPersistTaskPairThatUsesExistingTask(){
		Task t1 = new Task(-13,"test", "add taskPair using persisted task");
		Task t2 = new Task(-14,"test", "add taskPair using persisted task");

		service.addTask(t1);
		service.addTask(t2);

		TaskPair tp = new TaskPair(t1,t2);
		service.updateTaskPair(tp);

		TaskPair tp2 = service.getTaskPair(-13, -14);
		assertEquals(tp.getTask1().getTaskID(), tp2.getTask1().getTaskID());
		assertEquals(tp.getTask2().getTaskID(), tp2.getTask2().getTaskID());

		assertEquals(tp.getTask1().getHandle(), tp2.getTask1().getHandle());
		assertEquals(tp.getTask2().getHandle(), tp2.getTask2().getHandle());

		assertEquals(tp.getTask1().getLabel(), tp2.getTask1().getLabel());
		assertEquals(tp.getTask2().getLabel(), tp2.getTask2().getLabel());

	}

	/**
	 * Test persist of task pairs where the tasks that make
	 * the task pair already exist in the database.
	 * 
	 * This uses the controller which is what is used
	 * 
	 * Should throw no error.
	 */
	@Test
	public void testPersistTaskPairThatUsesExistingTaskWithController(){
		Task t1 = new Task(-15,"test using controller", "add taskPair using persisted task");
		Task t2 = new Task(-16,"test using controller", "add taskPair using persisted task");

		service.addTask(t1);
		service.addTask(t2);

		TaskPair tp = new TaskPair(t1,t2);

		List<TaskPair> taskpairs = new ArrayList<TaskPair>();
		taskpairs.add(tp);
		controller.saveTaskPairs(taskpairs);

		TaskPair tp2 = service.getTaskPair(-15, -16);
		assertEquals(tp.getTask1().getTaskID(), tp2.getTask1().getTaskID());
		assertEquals(tp.getTask2().getTaskID(), tp2.getTask2().getTaskID());

		assertEquals(tp.getTask1().getHandle(), tp2.getTask1().getHandle());
		assertEquals(tp.getTask2().getHandle(), tp2.getTask2().getHandle());

		assertEquals(tp.getTask1().getLabel(), tp2.getTask1().getLabel());
		assertEquals(tp.getTask2().getLabel(), tp2.getTask2().getLabel());

	}

	/**
	 * Method to test that the actual and potential scores
	 * are updated when recalculating the proximity score
	 */

	@Test
	public void updateProximityScore(){
		Task t1 = new Task(-17,"test using controller", "add taskPair using persisted task");
		Task t2 = new Task(-18,"test using controller", "add taskPair using persisted task");

		Context_Structure c1 = new Context_Structure("update proximity score test", true, true);
		t1.addContextStructure(c1.getName(), c1);
		t2.addContextStructure(c1.getName(), c1);

		TaskPair tp = new TaskPair(t1, t2);

		List<TaskPair> taskpairs = new ArrayList<TaskPair>();
		taskpairs.add(tp);
		controller.saveTaskPairs(taskpairs);

		assertEquals(1.0, tp.getPotentialValueForContext(c1.getName()),0.01);
		assertEquals(1.0, tp.getActualValueForContext(c1.getName()), 0.01);
		assertEquals(1.0, tp.getProximityScore(), 0.01);

		c1.setEdit(false);
		c1.setEdit(false);

		List<TaskPair> pairs = controller.getTaskPairs(c1, -17);
		controller.saveTaskPairs(pairs);

		TaskPair tp2 = service.getTaskPair(-17, -18);

		assertEquals(1.0, tp2.getPotentialValueForContext(c1.getName()),0.01);
		assertEquals(0.79, tp2.getActualValueForContext(c1.getName()), 0.01);
		assertEquals(0.79, tp2.getProximityScore(), 0.01);

	}
	
	/**
	 * Method to test creation of a new task pair from two task
	 * in the db using the getTaskPair method
	 * 
	 * i.e. ensuring the context_Structures are loaded into the persistence context
	 */
	@Test
	public void testLazyLoadGetTaskPair(){
		Task t1 = new Task(-19,"test creation of tp", "test of lazy loading");
		Task t2 = new Task(-20,"test creation of tp", "test of lazy loading");
		
		Context_Structure c1 = new Context_Structure("lazy loading", true, true);
		
		t1.addContextStructure(c1.getName(), c1);
		t2.addContextStructure(c1.getName(), c1);
		
		service.addTask(t1);
		service.addTask(t2);
		
		List<TaskPair> pairs = controller.getTaskPairs(c1, -19);
		TaskPair tp = pairs.get(0);
		
		controller.saveTaskPairs(pairs);
		
		assertEquals(-20, tp.getID1());
		assertEquals(-19, tp.getID2());
		assertEquals(1.0, tp.getProximityScore(), 0.01);

		
	}

}
