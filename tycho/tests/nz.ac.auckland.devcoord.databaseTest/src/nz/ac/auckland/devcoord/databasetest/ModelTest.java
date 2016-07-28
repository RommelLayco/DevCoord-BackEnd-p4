package nz.ac.auckland.devcoord.databasetest;

import static org.junit.Assert.*;

import org.junit.Test;

import nz.ac.auckland.devcoord.database.Context_Structure;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;

public class ModelTest {
	
	/**
	 * Test if the scores are stored in the map
	 * when a task pair is created
	 */
	
	@Test
	public void TestHashMapsofTaskPairs(){
		Task t1 = new Task(1,"test", "potential and actual score map", "OS", "Platform", "Component");
		Task t2 = new Task(2, "test", "potential and actual score map", "OS", "Platform", "Component");
		
		Context_Structure c1 = new Context_Structure("1", true, true);
		Context_Structure c2 = new Context_Structure("2", true, false);
		Context_Structure c3 = new Context_Structure("3", true, true);
		
		t1.addContextStructure("1", c1);
		t1.addContextStructure("2", c2);
		t1.addContextStructure("3", c3);
		
		t2.addContextStructure("1", c1);
		t2.addContextStructure("2", c2);
		
		TaskPair tp = new TaskPair(t1, t2);
		
		assertEquals(1.0, tp.getPotentialValueForContext("1"), 0.001);
		assertEquals(0.59, tp.getPotentialValueForContext("2"), 0.001);
		assertEquals(1.0, tp.getPotentialValueForContext("3"), 0.001);
		
		assertEquals(1.0, tp.getActualValueForContext("1"), 0.001);
		assertEquals(0.59, tp.getActualValueForContext("2"), 0.001);
		assertEquals(0.0, tp.getActualValueForContext("3"), 0.001);
		
	}

}
