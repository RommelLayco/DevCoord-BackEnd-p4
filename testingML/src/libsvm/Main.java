package libsvm;

import java.io.IOException;
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

	public static void main(String[] args) throws IOException {
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


		//process data
		ProcessData data = new ProcessData(taskPairs, tasks, keys, taskAcc, accKeys);

		data.setMatching();
		data.setCritical();


		System.out.println("");
		System.out.println("----------------------------------------------------------");
		System.out.println("                         SVM with DRH properties");
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Formating data");


		WriteToFile.writeToFile(data, "output", "train.train", true);
		WriteToFile.writeToFile(data, "output", "test.test", false);


		System.out.println("");
		System.out.println("done formatting data");
		System.out.println("----------------------------------");
		System.out.println("begin training of data");


		System.out.println("");
		String[] param = {"output\\train.train"};
		svm_train.runTrain(param);

		System.out.println("");
		System.out.println("----------------------------------");
		System.out.println("test model");

		System.out.println("");
		String[] param2 = {"output\\test.test", "output\\train.train.model",
		"output\\testOutput.out"};
		svm_predict.runPredict(param2);

		System.out.println("");
		System.out.println("----------------------------------------------------------");
		System.out.println("                        SVM with no DRH properties");
		System.out.println("----------------------------------------------------------");

		System.out.println("Start Formating data");


		WriteToFile.writeNoDRH(data, "output", "trainNoDRH.train", true);
		WriteToFile.writeNoDRH(data, "output", "testNoDRH.test", false);
		
		System.out.println("");
		System.out.println("done formatting data");
		System.out.println("----------------------------------");
		System.out.println("begin training of data");


		System.out.println("");
		String[] param3 = {"output\\trainNoDRH.train"};
		svm_train.runTrain(param3);

		System.out.println("");
		System.out.println("----------------------------------");
		System.out.println("test model");

		System.out.println("");
		String[] param4 = {"output\\testNoDRH.test", "output\\trainNoDRH.train.model",
		"output\\testOutputNoDRH.out"};
		svm_predict.runPredict(param4);


		System.out.println("");
		System.out.println("----------------------------------------------------------");
		System.out.println("                         DONE");
		System.out.println("----------------------------------------------------------");

	}

}