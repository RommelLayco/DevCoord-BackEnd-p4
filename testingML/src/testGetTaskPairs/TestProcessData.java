package testGetTaskPairs;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
		// one-time initialization code   
		keys = new ArrayList<TaskPairKey>();
		
		tasks = ReadFiles.readTaskFile("testInput", "tasks.csv");
		taskPairs = ReadFiles.readFile("testInput", "pairs.csv", keys);
	
		
	}

	@AfterClass
	public static void oneTimeTearDown() {
		// one-time cleanup code
		
		tasks.clear();
		taskPairs.clear();
	}
	
	@Before
	public void createProcessDataObject(){
		data = new ProcessData(taskPairs, tasks); 
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
	 */
//	@Test
//	public void test() {
//		TaskPair tp = data.getTaskPair(0);
//		
//		assertFalse(tp.isSameOS());
//		assertFalse(tp.isSameComponent());
//		assertFalse(tp.isSamePlatform());
//		
//		data.setMatching();
//		
//		
//		assertTrue(tp.isSameOS());
//		assertTrue(tp.isSameComponent());
//		assertTrue(tp.isSamePlatform());
//	}

	
	//******************* HELPER METHODS ***********************************
	public static List<TaskPair> populateList(){
		//create a list to store task pairs
		List<TaskPair> tps = new ArrayList<TaskPair>();

		//read in pairs files;
		BufferedReader br = null;

		try {

			String currentLine;
			// get file
			File pairs = new File("testInput"+System.getProperty("file.separator")
			+"pairs.csv");

			br = new BufferedReader(new FileReader(pairs));
			//read first line which is titles so we can ignore.
			currentLine = br.readLine();

			while ((currentLine = br.readLine()) != null) {
				String[] line = currentLine.split(",");
				TaskPair tp = TaskPair.create(line);
				tps.add(tp);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return tps;
	}

	public static Map<Integer, Task> populateMap(){
		Map<Integer, Task> ts = new HashMap<Integer, Task>();


		//read in pairs files;
		BufferedReader br = null;

		try {

			String currentLine;
			// get file
			File pairs = new File("testInput"+System.getProperty("file.separator")
			+"tasks.csv");

			br = new BufferedReader(new FileReader(pairs));
			//read first line which is titles so we can ignore.
			currentLine = br.readLine();

			while ((currentLine = br.readLine()) != null) {
				String[] line = currentLine.split(",");
				Task task = Task.create(line);
				ts.put(task.getTaskID(), task);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return ts;
	}
}

