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

public class TestProcessData {

	private static Map<Integer, Task> tasks;
	private static List<TaskPair> taskPairs;
	
	private ProcessData data;

	@BeforeClass
	public static void oneTimeSetUp() {
		// one-time initialization code   
				
		tasks = populateMap();
		taskPairs = populateList();
		
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
	 * Test the set matching method.
	 * Checks to see if it can find the task in the map
	 * and change the taskpair matching fields to true
	 */
	@Test
	public void test() {
		TaskPair tp = data.getTaskPair(0);
		
		assertFalse(tp.isSameOS());
		assertFalse(tp.isSameComponent());
		assertFalse(tp.isSamePlatform());
		
		data.setMatching();
		
		
		assertTrue(tp.isSameOS());
		assertTrue(tp.isSameComponent());
		assertTrue(tp.isSamePlatform());
	}

	
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

