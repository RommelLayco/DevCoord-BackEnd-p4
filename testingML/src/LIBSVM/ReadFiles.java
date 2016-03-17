package LIBSVM;

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
	 * A method to read in the pair file and return a list
	 * of taskpair objects.
	 * 
	 * Makes the main method more readable.
	 * @return list of taskPair objects
	 */
	public static List<TaskPair> readPairs(){
		//create a list to store task pairs
		List<TaskPair> taskPairs = new ArrayList<TaskPair>();

		//read in pairs files;
		BufferedReader br = null;

		try {

			String currentLine;
			// get file
			File pairs = new File("input"+System.getProperty("file.separator")
			+"pairs_3_2.csv");

			br = new BufferedReader(new FileReader(pairs));
			//read first line which is titles so we can ignore.
			currentLine = br.readLine();

			while ((currentLine = br.readLine()) != null) {
				String[] line = currentLine.split(",");
				TaskPair tp = TaskPair.create(line);
				taskPairs.add(tp);

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

		return taskPairs;

	}// end of method readPairs

	/**
	 * A method to read in the pair file and return a list
	 * of task objects
	 * 
	 * Makes main method more readable
	 * @return list of task objects
	 */
	public static Map<Integer, Task> readTask(){
		//create a hash map to store task objects.
		//Access time is constant
		//Key will be the taskID
		Map<Integer, Task> tasks = new HashMap<Integer, Task>();


		//read in pairs files;
		BufferedReader br = null;

		try {

			String currentLine;
			// get file
			File pairs = new File("input"+System.getProperty("file.separator")
			+"tasks_3_2.csv");

			br = new BufferedReader(new FileReader(pairs));
			//read first line which is titles so we can ignore.
			currentLine = br.readLine();

			while ((currentLine = br.readLine()) != null) {
				String[] line = currentLine.split(",");
				Task task = Task.create(line);
				tasks.put(task.getTaskID(), task);

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

		return tasks;
	}

}
