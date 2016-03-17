package LIBSVM;



import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		//read in files
		List<TaskPair> taskPairs = ReadFiles.readPairs();
		List<Task> tasks = ReadFiles.readTask();
		
		System.out.println("Size of task pairs list: " + taskPairs.size());
		System.out.println("Size of tasks list: " + tasks.size());
		
		
		

	} //end main
}