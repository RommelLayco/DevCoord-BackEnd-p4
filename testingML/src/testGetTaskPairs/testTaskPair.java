package testGetTaskPairs;

import static org.junit.Assert.*;

import org.junit.Test;

import getTaskPairs.Component;
import getTaskPairs.OS;
import getTaskPairs.Platform;
import getTaskPairs.Task;
import getTaskPairs.TaskPair;

public class testTaskPair {

	/**
	 * test to see if task pair initialized correctly
	 */
	@Test
	public void testCreate() {
		String[] line = {"228910","279316",	"14.8614046", "0","8", "2"};

		
		TaskPair tp = TaskPair.create(line);
		
		
		assertEquals(228910, tp.getT1());
		assertEquals(279316, tp.getT2());
		assertEquals(14.8614046f, tp.getPscore(), 0.00000002);
		assertEquals(8, tp.getSLSM());
		assertEquals(2, tp.getAL());
		assertFalse(tp.isSameComponent());
		assertFalse(tp.isSamePlatform());
		assertFalse(tp.isSameOS());
		assertFalse(tp.isCritical());
				
	}

	/**
	 * Test to see if other fields change
	 */
	@Test
	public void testSetRest(){
		TaskPair tp = new TaskPair(1,2,3,4,5);
		
		Task t1 = new Task(1, Platform.ALL, OS.ALL, Component.BUGZILLA);
		Task t2 = new Task(2, Platform.ALL, OS.ALL, Component.BUGZILLA);
		
		assertFalse(tp.isSameComponent());
		assertFalse(tp.isSamePlatform());
		assertFalse(tp.isSameOS());
		
		tp.setRest(t1, t2);
		
		assertTrue(tp.isSameComponent());
		assertTrue(tp.isSameOS());
		assertTrue(tp.isSamePlatform());
		
		
	}
}
