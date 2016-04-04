package decisionTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import getTaskPairs.ProcessData;
import getTaskPairs.ReadFiles;
import getTaskPairs.Task;
import getTaskPairs.TaskAcc;
import getTaskPairs.TaskPair;
import getTaskPairs.TaskPairKey;
/**
 * To run the algorithm
 * */
public class MainClass {

	public static void main(String[] args) {

		/**Rommel's*/
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


		ProcessData data = new ProcessData(taskPairs, tasks, keys, taskAcc, accKeys);
		data.setMatching();
		data.setCritical();

		/**Rommel's*/	


		/**With DRH*/
			/**UNPRUNED*/
				makeARFFAndTree(true,data,true);
			/**PRUNED*/
				makeARFFAndTree(true,data,false);

		
		/**With Without*/
				/**UNPRUNED*/
				makeARFFAndTree(false,data,true);

				/**PRUNED*/
				makeARFFAndTree(false,data,false);



		System.out.println("Done");


	}
	/**
	 * Calls convert method to make arff files from csvs,
	 * and Make method to create a tree and make a report for the test set classification.
	 * */
	private static void makeARFFAndTree(boolean DRH,ProcessData data,boolean unpruned){


		DataToARFF.convert(data, InputEnum.PAIRS_3_2,DRH);
		MakeTree.make(DRH,unpruned);




	}

}
