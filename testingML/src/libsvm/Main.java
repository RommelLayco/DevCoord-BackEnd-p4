package LIBSVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import getTaskPairs.ProcessData;
import getTaskPairs.ReadFiles;
import getTaskPairs.Task;
import getTaskPairs.TaskAcc;
import getTaskPairs.TaskPair;
import getTaskPairs.TaskPairKey;

public class Main {

	public static void main(String[] args) {
		//List of taskPair keys
		List<TaskPairKey> keys = new ArrayList<TaskPairKey>();
		
		//List of taskAcc keys
		List<TaskPairKey> accKeys = new ArrayList<TaskPairKey>();
		
		List<String[]> lines = ReadFiles.readFile("input", "tasks_3_2.csv");
		Map<Integer, Task> tasks = ReadFiles.makeTaskMap(lines);
		
		List<String[]> lines2 = ReadFiles.readFile("input", "pairs_3_2.csv");
		Map<TaskPairKey, TaskPair> taskPairs = ReadFiles.makeTaskPairMap(lines2, keys);
		
		List<String[]> lines3 = ReadFiles.readFile("input", "accuracy_coding.csv");
		Map<TaskPairKey, TaskAcc> taskAcc = ReadFiles.makeTaskAccMap(lines3, accKeys);
		
		
		//process data
		ProcessData data = new ProcessData(taskPairs, tasks, keys, taskAcc, accKeys);
				
		data.setMatching();
		data.setCritical();
		
		WritetoFile.writeToFile(data, "output", "test.txt");
		
		
		System.out.println("done");

	}

}
