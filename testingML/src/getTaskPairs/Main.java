package getTaskPairs;



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
		
		List<String[]> lines3 = ReadFiles.readFile("input", "acc.csv");
		Map<TaskPairKey, TaskAcc> taskAcc = ReadFiles.makeTaskAccMap(lines3, accKeys);
		
		
		
		System.out.println("Size of task pairs list: " + taskPairs.size());
		System.out.println("Size of task pairs key: " + keys.size());
		System.out.println("Size of tasks list: " + tasks.size());
		System.out.println("Size of task acc key: " + accKeys.size());
		System.out.println("Size of task acc list: " + taskAcc.size());
		
		//ProcessData data = new ProcessData(taskPairs, tasks);
		//data.setMatching();
		

	} //end main
}