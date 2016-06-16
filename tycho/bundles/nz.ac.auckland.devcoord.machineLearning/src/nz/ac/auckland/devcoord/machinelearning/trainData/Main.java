package nz.ac.auckland.devcoord.machinelearning.trainData;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		
		
		List<String[]> lines4 =  ReadFiles.readFile("input", "tasks_3_1.csv");
		taskPairs = ReadFiles.addTasksPairs(lines4, keys, taskPairs);
		
		
		System.out.println("Size of task pairs list: " + taskPairs.size());
		System.out.println("Size of task pairs key: " + keys.size());
		System.out.println("Size of tasks list: " + tasks.size());
		System.out.println("Size of task acc key: " + accKeys.size());
		System.out.println("Size of task acc list: " + taskAcc.size());
		
		ProcessData data = new ProcessData(taskPairs, tasks, keys, taskAcc, accKeys);
		data.setMatching();
		data.setCritical();
		
		System.out.println("Number of data to train: " + data.getTrainKeys().size());
		System.out.println("Number of data to test: " + data.getTestKeys().size());
		

	} //end main
}