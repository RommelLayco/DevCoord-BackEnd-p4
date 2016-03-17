package LIBSVM;



import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		//read in files
		List<TaskPair> taskPairs = ReadFiles.readPairs();
		Map<Integer, Task> tasks = ReadFiles.readTask();
		
		System.out.println("Size of task pairs list: " + taskPairs.size());
		System.out.println("Size of tasks list: " + tasks.size());
		
		
		

	} //end main
}