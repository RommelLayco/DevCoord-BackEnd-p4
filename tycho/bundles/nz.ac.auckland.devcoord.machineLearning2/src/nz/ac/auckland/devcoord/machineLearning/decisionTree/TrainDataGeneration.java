package nz.ac.auckland.devcoord.machineLearning.decisionTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nz.ac.auckland.devcoord.machineLearning.testData.CriticalityUtility;
import nz.ac.auckland.devcoord.machineLearning.trainData.ProcessData;
import nz.ac.auckland.devcoord.machineLearning.trainData.ReadFiles;
import nz.ac.auckland.devcoord.machineLearning.trainData.Task;
import nz.ac.auckland.devcoord.machineLearning.trainData.TaskAcc;
import nz.ac.auckland.devcoord.machineLearning.trainData.TaskPair;
import nz.ac.auckland.devcoord.machineLearning.trainData.TaskPairKey;
/**
 * Used for conversion of Train data csv file to a Weka Readable Arff File
 * */
public class TrainDataGeneration {
	public static void main(String[] args) {
		convertTrainCSVToArff();
	}
	public static void convertTrainCSVToArff() {
		ProcessData data= convertCSVToProcessData();
		DataToARFF.convertProcessDataToArff(data, InputEnum.TRAIN_OUTPUT_PATH);
	}
	public static ProcessData convertCSVToProcessData(){
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
		return data;
	}
}
