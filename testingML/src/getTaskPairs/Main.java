package getTaskPairs;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		//List of taskPair keys
		List<TaskPairKey> keys = new ArrayList<TaskPairKey>();
		
		//read in files
		Map<TaskPairKey, TaskPair> taskPairs = ReadFiles.readFile("input", "pairs_3_2.csv", keys);
		Map<Integer, Task> tasks = ReadFiles.readTaskFile("input", "tasks_3_2.csv");
		
		System.out.println("Size of task pairs list: " + taskPairs.size());
		System.out.println("Size of task pairs key: " + keys.size());
		System.out.println("Size of tasks list: " + tasks.size());
		
		//ProcessData data = new ProcessData(taskPairs, tasks);
		//data.setMatching();
		

	} //end main
}