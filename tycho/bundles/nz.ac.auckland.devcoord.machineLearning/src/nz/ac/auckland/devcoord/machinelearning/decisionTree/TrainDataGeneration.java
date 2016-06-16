package nz.ac.auckland.devcoord.machinelearning.decisionTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nz.ac.auckland.devcoord.machinelearning.testData.CriticalityUtility;
import nz.ac.auckland.devcoord.machinelearning.trainData.ProcessData;
import nz.ac.auckland.devcoord.machinelearning.trainData.ReadFiles;
import nz.ac.auckland.devcoord.machinelearning.trainData.Task;
import nz.ac.auckland.devcoord.machinelearning.trainData.TaskAcc;
import nz.ac.auckland.devcoord.machinelearning.trainData.TaskPair;
import nz.ac.auckland.devcoord.machinelearning.trainData.TaskPairKey;
/**
 * To run the algorithm
 * */
public class TrainDataGeneration {

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



//		/**With Without*/
//		/**UNPRUNED*/
//		makeARFFAndTree(data,true);

		/**PRUNED*/
		DataToARFF.convertTrainData(data, InputEnum.PAIRS_3_2);




	}


}
