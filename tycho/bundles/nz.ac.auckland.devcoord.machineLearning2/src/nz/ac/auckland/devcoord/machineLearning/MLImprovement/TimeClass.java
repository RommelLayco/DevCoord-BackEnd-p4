package nz.ac.auckland.devcoord.machineLearning.MLImprovement;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import nz.ac.auckland.devcoord.database.Context_Structure;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;
import nz.ac.auckland.devcoord.machineLearning.decisionTree.InputEnum;
import nz.ac.auckland.devcoord.machineLearning.testData.CriticalityUtility;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
/**
 * Used for comparison of the effeciency of the Machine learning implementation.
 * */
public class TimeClass {
	/**
	 * Timing code Reference:http://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
	 *
	 *SANDBOX class to test and improve ML algorithm.
	 * */
	public static void main(String[] args) {
				TaskPair taskPair=buildDummyTaskPairTwo();
				long startTime = System.nanoTime();
				CriticalityUtility.convertTestDataToArff(taskPair);
				boolean correct=CriticalityUtility.wasClassificationCorrect();
				System.out.println("Correct old?:"+correct);
				long endTime = System.nanoTime();
				long duration = (endTime - startTime)/1000000;  // milliseconds.
				System.out.println( "Time taken for old method:"+duration);
				startTime = System.nanoTime();
				correct=classifyWithoutFile(taskPair);
				endTime = System.nanoTime();
				duration = (endTime - startTime)/1000000;  // milliseconds.
				System.out.println("Correct NEW?:"+correct);
				System.out.println( "Time taken for NEW method:"+duration);
	}
	public static boolean classifyWithoutFile(TaskPair taskPair)
	{
		String trainString;
		trainString=InputEnum.toString(InputEnum.TRAIN_OUTPUT_PATH);
		// train classifier
		J48 cls = new J48();
		Instances train;
		Instances test;
		try {
			FileReader trainFileReader=new FileReader(trainString);
			BufferedReader trainBufferedReader=new BufferedReader(trainFileReader);
			train = new Instances(trainBufferedReader);
			train.setClassIndex(train.numAttributes() - 1);
			String data=CriticalityUtility.getLabels()+CriticalityUtility.getStringOfTheTestTaskPair(taskPair);
			InputStream inputStream=new ByteArrayInputStream(data.getBytes());
			BufferedReader testBufferedReader=new BufferedReader(new InputStreamReader(inputStream));
			test = new Instances(testBufferedReader);				
			test.setClassIndex(test.numAttributes() - 1);
			cls.setUnpruned(false);
			cls.buildClassifier(train);
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(cls, test);
			trainBufferedReader.close();
			testBufferedReader.close();
			return eval.correct()>0;
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	private static TaskPair buildDummyTaskPairOne(){
		Task one=new Task(1, "Dummy Handle one", "Dummy label one","OSOne", "Platform", "ComponentTwo","Owner","DescriptionOne");
		Task two=new Task(2, "Dummy Handle two", "Dummy label two","OS", "Platform", "Component","Owner","DescriptionOne");
		Context_Structure context_StructureOne=new Context_Structure("ContextOne", false, true);
		Context_Structure context_StructureTwo=new Context_Structure("ContextOne", true, false);
		one.addContextStructure(context_StructureOne.getName(), context_StructureOne);
		two.addContextStructure(context_StructureTwo.getName(), context_StructureOne);
		TaskPair taskPairOne=new TaskPair(one,two);
		return taskPairOne;
	}
	private static TaskPair buildDummyTaskPairTwo(){
		Task one=new Task(1, "Dummy Handle Three", "Dummy label Three","OS", "Platform", "Component","Owner","DescriptionOne");
		Task two=new Task(2, "Dummy Handle four", "Dummy label four","OS", "Platform", "Component","Owner","DescriptionOne");
		Context_Structure context_StructureOne=new Context_Structure("ContextThree", false, true);
		Context_Structure context_StructureTwo=new Context_Structure("Contextfour", true, false);
		one.addContextStructure(context_StructureOne.getName(), context_StructureOne);
		two.addContextStructure(context_StructureTwo.getName(), context_StructureTwo);
		TaskPair taskPairOne=new TaskPair(one,two);
		return taskPairOne;
	}
}
