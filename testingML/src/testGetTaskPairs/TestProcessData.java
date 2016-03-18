package testGetTaskPairs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import getTaskPairs.ProcessData;
import getTaskPairs.Task;
import getTaskPairs.TaskPair;
import getTaskPairs.TaskPairKey;
import getTaskPairs.ReadFiles;

public class TestProcessData {

	private static Map<Integer, Task> tasks;
	private static Map<TaskPairKey, TaskPair> taskPairs;
	private static List<TaskPairKey> keys;
	
	private ProcessData data;

	@BeforeClass
	public static void oneTimeSetUp() {
		List<String[]> taskLines = ReadFiles.readFile("testInput", "tasks.csv");
		List<String[]> taskPairLines = ReadFiles.readFile("testInput", "pairs.csv");
		
		// one-time initialization code   
		keys = new ArrayList<TaskPairKey>();
		
		tasks = ReadFiles.makeTaskMap(taskLines);
		taskPairs = ReadFiles.makeTaskPairMap(taskPairLines, keys);
	
		
	}

	@AfterClass
	public static void oneTimeTearDown() {
		// one-time cleanup code
		
		tasks.clear();
		taskPairs.clear();
	}
	
	@Before
	public void createProcessDataObject(){
		data = new ProcessData(taskPairs, tasks, keys); 
	}
	
	/**
	 * Test to see if correct task pair is pulled from map correctly
	 */
	@Test
	public void testTaskPairKey(){
		TaskPairKey key = keys.get(0);
		int t1 = key.getID1();
		int t2 = key.getID2();
		
		
		TaskPair tp = data.getTaskPair(key);
		assertEquals(t1, tp.getT1());
		assertEquals(t2, tp.getT2());
	}
	
	/**
	 * Test to see if correct task pair key is in wrong order will obtain null
	 */
	@Test
	public void testTaskPairKeyOrder(){
		TaskPairKey key = keys.get(0);
		int t1 = key.getID1();
		int t2 = key.getID2();
		
		TaskPairKey key2 = new TaskPairKey(t2, t1); 
		
		TaskPair tp = data.getTaskPair(key);
		TaskPair tp2 = data.getTaskPair(key2);
		
		assertNotNull(tp);
		assertNull(tp2);
		
	}

	/**
	 * Test the set matching method.
	 * Checks to see if it can find the task in the map
	 * and change the taskpair matching fields to true
	 * Set matching method comes from the TaskPair class
	 */
	@Test
	public void testSetMatching() {
		TaskPairKey key = keys.get(0);
		TaskPair tp = data.getTaskPair(key);
		
		assertFalse(tp.isSameOS());
		assertFalse(tp.isSameComponent());
		assertFalse(tp.isSamePlatform());
		
		data.setMatching();
		
		assertTrue(tp.isSameOS());
		assertTrue(tp.isSameComponent());
		assertTrue(tp.isSamePlatform());
	}

}

