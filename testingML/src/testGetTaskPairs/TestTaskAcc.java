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
	
	/**
	 * Test to see if it sets critical to correct value
	 */
	@Test
	public void testSetCritical(){
		
		TaskAcc t1 = new TaskAcc(1,2,Version.TEST,true,true, true, false);
		TaskAcc t2 = new TaskAcc(1,2,Version.TEST,true,false, false, false);
		TaskAcc t3 = new TaskAcc(1,2,Version.TEST,true,false, true, true);
		TaskAcc t4 = new TaskAcc(1,2,Version.TEST,false,false, false, true);
		
		t1.setCritical();
		t2.setCritical();
		t3.setCritical();
		t4.setCritical();
		
		assertTrue(t1.isCritical());
		assertFalse(t2.isCritical());
		assertTrue(t3.isCritical());
		assertFalse(t4.isCritical());
		
		
	}

}
