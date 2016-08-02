package nz.ac.auckland.devcoord.machinelearning.trainData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Helper Class to read in the pairs and task files
 * 
 * @author Rommel
 *
 */
public class ReadFiles {



	/**
	 * Read files and stores contents into string array to be process further
	 * @param folder name of folder must be in the project directory
	 * @param fileName must be a file in the given folder name
	 * @return String[] to be processed further.
	 */
	public static List<String[]> readFile(String folder, String fileName){
		//create a hash map to store task objects.
		//Access time is constant
		//Key will be the taskID of both tasks
		List<String[]> lines = new ArrayList<String[]>();

		//read in pairs files;
		BufferedReader br = null;

		try {
			 System.out.println("--"+new File(".").getCanonicalPath());
			String currentLine;
			// get file
			File pairs = new File(folder +System.getProperty("file.separator")
			+ fileName);

			br = new BufferedReader(new FileReader(pairs));
			//read first line which is titles so we can ignore.
			currentLine = br.readLine();

			while ((currentLine = br.readLine()) != null) {
				String[] line = currentLine.split(",");
				lines.add(line);
			}
br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return lines;
	}

	/**
	 * Method to process string[] and convert them in to task objects 
	 * and store in map for further processing
	 * @param lines a list of String []
	 * @return map 
	 */
	public static Map<Integer, Task> makeTaskMap(List<String[]> lines){
		//create a hash map to store task objects.
		//Access time is constant
		//Key will be the taskID
		Map<Integer, Task> tasks = new HashMap<Integer, Task>();

		for(String[] line : lines){
			Task task = Task.create(line);
			tasks.put(task.getTaskID(), task);
		}

		return tasks;
	}

	/**
	 * Method to process string[] and convert them in to taskPair objects 
	 * and store in map for further processing
	 * @param lines a list of String [] and a list of keys
	 * @return map 
	 */
	public static Map<TaskPairKey, TaskPair> makeTaskPairMap(List<String[]> lines, List<TaskPairKey> keys){
		//create a hash map to store task objects.
		//Access time is constant
		//Key will be the taskID of both tasks
		Map<TaskPairKey, TaskPair> taskPairMap = new HashMap<TaskPairKey, TaskPair>();

		for(String[] line : lines){
			TaskPair taskPair = TaskPair.create(line);

			//create key to store task pair
			TaskPairKey key = new TaskPairKey(taskPair.getT1(), taskPair.getT2());
			keys.add(key);
			taskPairMap.put(key, taskPair);
		}

		return taskPairMap;
	}
	
	public static Map<TaskPairKey, TaskPair> addTasksPairs(List<String[]> lines, List<TaskPairKey> keys,
			Map<TaskPairKey, TaskPair> taskPairs){
		
		for(String[] line : lines){
			TaskPair taskPair = TaskPair.createAll(line);

			//create key to store task pair
			TaskPairKey key = new TaskPairKey(taskPair.getT1(), taskPair.getT2());
			keys.add(key);
			taskPairs.put(key, taskPair);
		}
		
		return taskPairs;
	}

	/**
	 * Method to process string[] and convert them in to taskAcc objects 
	 * and store in map for further processing
	 * 
	 * @param lines a list of String [] and a list of keys
	 * @return map 
	 */
	public static Map<TaskPairKey, TaskAcc> makeTaskAccMap(List<String[]> lines, List<TaskPairKey> keys){
		//create a hash map to store task objects.
		//Access time is constant
		//Key will be the taskID of both tasks
		Map<TaskPairKey, TaskAcc> taskAccMap = new HashMap<TaskPairKey, TaskAcc>();

		for(String[] line : lines){
			TaskAcc taskAcc = TaskAcc.create(line);

			//create key to store task pair
			TaskPairKey key = new TaskPairKey(taskAcc.getT1(), taskAcc.getT2());
			keys.add(key);
			taskAccMap.put(key, taskAcc);
		}

		return taskAccMap;
	}
}
