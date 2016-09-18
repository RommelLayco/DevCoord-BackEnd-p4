package nz.ac.auckland.devcoord.machineLearning.testData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import nz.ac.auckland.devcoord.database.TaskPair;
import nz.ac.auckland.devcoord.machineLearning.decisionTree.InputEnum;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
/**
 * Utility class which will be used to fill in the criticality of the task pairs
 * */
public class CriticalityUtility {
	public static List<TaskPair> fillInCriticality(List<TaskPair> taskPairsListArg){
		for (TaskPair taskPair : taskPairsListArg) {
			taskPair.setCritical(false);
			if (!wasClassificationCorrectNoFile(taskPair)) {
				taskPair.setCritical(!taskPair.isCritical());
			}
		}
		return taskPairsListArg;
	}
	/**
	 * No new files created when classifying whether critical or not,ie,the
	 * more efficient method.
	 * */
	public static boolean wasClassificationCorrectNoFile(TaskPair taskPair)
	{
		String trainString;
		// train classifier
		J48 cls = new J48();
		Instances train;
		Instances test;
		FileReader trainFileReader=null;
		try {
			trainString=InputEnum.toString(InputEnum.TRAIN_OUTPUT_PATH);
			trainFileReader = new FileReader(trainString);
		} catch (FileNotFoundException e) {
			trainString=InputEnum.toString(InputEnum.WORKSPACE)+System.getProperty("file.separator")+InputEnum.toString(InputEnum.TRAIN_OUTPUT_PATH);
			try {
				trainFileReader = new FileReader(trainString);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
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
	/**
	 * Creates an arff file out of a TaskPair object
	 * */
	public static void convertTestDataToArff(TaskPair taskPair ){
		Writer writer = null;
		String inputString;
		inputString=InputEnum.outputToString(InputEnum.TEST_OUTPUT_PATH);
		try{
			FileOutputStream fileOutputStream=new FileOutputStream(inputString);
			OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream, "utf-8"); 
			writer = new BufferedWriter(outputStreamWriter);
			writer.write(getLabels());
			writer.write(getStringOfTheTestTaskPair(taskPair));
			writer.close();
			outputStreamWriter.close();
			fileOutputStream.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * Return true if the value of isCritical already present is correct.
	 * 
	 * */
	public static boolean wasClassificationCorrect()
	{
		String trainString;
		String testString;
			trainString=InputEnum.toString(InputEnum.TRAIN_OUTPUT_PATH);
			testString=InputEnum.toString(InputEnum.TEST_OUTPUT_PATH);
			// train classifier
		     J48 cls = new J48();
		     Instances train;
		     Instances test;
			try {
				FileReader trainFileReader=new FileReader(trainString);
				BufferedReader trainBufferedReader=new BufferedReader(trainFileReader);
				train = new Instances(trainBufferedReader);
				train.setClassIndex(train.numAttributes() - 1);
				FileReader testFileReader=new FileReader(testString);
				BufferedReader testBufferedReader=new BufferedReader(testFileReader);
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
	/**
	 * Helper method- creates a label which is written in the arff file,depending on the arguments,
	 * it includes or excludes DRH attributes from the label.
	 * **/
	public static String getLabels(){
		String toReturn;
		toReturn="@relation pairsTask"+"\n"+"\n"+"\n"+
				"@attribute Proximity numeric"+"\n"+
				"@attribute SameComponent {true,false}"+"\n"+
				"@attribute SamePlatform {true,false}"+"\n"+
				"@attribute SameOS {true,false}"+"\n"+
				"@attribute Critical {true,false}"+"\n"+""
				+ "\n"+"@data"+"\n";
		return toReturn;
	}
	/**
	 * Helper method-creates a string that contains information about a pair TEST of task.
	 * this string is returned,which is then written directly into the arff file(not be this method). 
	 * 
	 * */
	public static String getStringOfTheTestTaskPair(TaskPair taskPair){
		double proximity=taskPair.getProximityScore();
		String critical=""+taskPair.isCritical();
		String component=""+taskPair.isSameComponent();
		String platform=""+taskPair.isSamePlatform();
		String os=""+taskPair.isSameOS();
		return proximity+","
		+component+","
		+platform+","
		+os+","
		+ critical;
	}
}
