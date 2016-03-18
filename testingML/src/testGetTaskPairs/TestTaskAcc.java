package testGetTaskPairs;

import static org.junit.Assert.*;

import org.junit.Test;

import getTaskPairs.TaskAcc;
import getTaskPairs.Version;



public class TestTaskAcc {

	/**
	 * Test to see if task object is initialized correctly
	 */
	@Test
	public void testCreate() {
		String[] line = {"1", "3.2", "276383", "280540", "Not", "Not", "Not",
				"Not", "Not", "Not", "No", "No"};
	
		TaskAcc taskAcc = TaskAcc.create(line);
		
		assertEquals(276383, taskAcc.getT1());
		assertEquals(280540, taskAcc.getT2());
		assertEquals(Version.TEST, taskAcc.getVersion());
		assertFalse(taskAcc.isD1());
		assertFalse(taskAcc.isD2());
		assertFalse(taskAcc.isC1());
		assertFalse(taskAcc.isC2());
		assertFalse(taskAcc.isCritical());
		
	}

}
