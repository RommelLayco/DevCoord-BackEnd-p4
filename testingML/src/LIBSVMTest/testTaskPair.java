package LIBSVMTest;

import static org.junit.Assert.*;

import org.junit.Test;

import LIBSVM.Component;
import LIBSVM.OS;
import LIBSVM.Platform;
import LIBSVM.Task;
import LIBSVM.TaskPair;

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
		assertFalse(tp.getSameComponent());
		assertFalse(tp.getSamePlatform());
		assertFalse(tp.getSameOS());
				
	}

	/**
	 * Test to see if other fields change
	 */
	@Test
	public void testSetRest(){
		TaskPair tp = new TaskPair(1,2,3,4,5);
		
		Task t1 = new Task(1, Platform.ALL, OS.ALL, Component.BUGZILLA);
		Task t2 = new Task(2, Platform.ALL, OS.ALL, Component.BUGZILLA);
		
		assertFalse(tp.getSameComponent());
		assertFalse(tp.getSamePlatform());
		assertFalse(tp.getSameOS());
		
		tp.setRest(t1, t2);
		
		assertTrue(tp.getSameComponent());
		assertTrue(tp.getSameOS());
		assertTrue(tp.getSamePlatform());
		
		
	}
}
