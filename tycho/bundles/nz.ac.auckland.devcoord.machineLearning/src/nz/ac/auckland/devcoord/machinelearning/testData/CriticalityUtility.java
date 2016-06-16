package nz.ac.auckland.devcoord.machinelearning.testData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;
import nz.ac.auckland.devcoord.machinelearning.decisionTree.InputEnum;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
/**
 * Utility class which will be used to fill in the criticality of the task pairs
 * */
public class CriticalityUtility {

	public static void main(String[] args) {
		Task one=new Task(1, "Dummy Handle one", "Dummy label one");
		Task two=new Task(2, "Dummy Handle two", "Dummy label two");
		Task three=new Task(3, "Dummy Handle one", "Dummy label three");

		
		
		TaskPair taskPairOne=new TaskPair(one,two);
		taskPairOne.setCritical(true);
		TaskPair taskPairTwo=new TaskPair(one,three);
		taskPairTwo.setCritical(false);
		TaskPair taskPairThree=new TaskPair(three,two);
		taskPairThree.setCritical(false);
		
		
		List<TaskPair> taskPairs=new ArrayList<TaskPair>();
		taskPairs.add(taskPairOne);taskPairs.add(taskPairTwo);taskPairs.add(taskPairThree);
		
		System.out.println("BEFORE CLASSIFYING,Criticality is:");
		for (TaskPair taskPair : taskPairs) {
			System.out.println("	====================");
			System.out.println("	First Task ID:"+taskPair.getID1());
			System.out.println("	Second Task ID:"+taskPair.getID2());
			System.out.println("	IsCritical?:"+taskPair.isCritical());
			
			
		}
		
		taskPairs=fillInCriticality(taskPairs);
		
		System.out.println("AFTER CLASSIFYING,Criticality is:");
		for (TaskPair taskPair : taskPairs) {
			System.out.println("	====================");
			System.out.println("	First Task ID:"+taskPair.getID1());
			System.out.println("	Second Task ID:"+taskPair.getID2());
			System.out.println("	IsCritical?:"+taskPair.isCritical());
			
			
		}
		
		
		
	}
	
	
	public static List<TaskPair> fillInCriticality(List<TaskPair> taskPairsListArg){

		for (TaskPair taskPair : taskPairsListArg) {
			//this loop will contain the machine learning code

			taskPair.setCritical(false);
			
			convertTestDataToArff(taskPair);
			

			if (!wasClassificationCorrect()) {
				taskPair.setCritical(!taskPair.isCritical());
				
			}
			

		}


		return taskPairsListArg;


	}

	
	/**
	 * Creates an arff file out of a TaskPair object
	 * */
	private static void convertTestDataToArff(TaskPair taskPair ){
		
		Writer writer = null;

		String inputString;



		inputString=InputEnum.outputToString(InputEnum.PAIRS_3_2_Test_Output_NODRH);


		try{
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(inputString), "utf-8"));


			writer.write(getLabels());
			writer.write("@data"+"\n");
			writer.write(getStringOfTheTestTaskPair(taskPair));
			writer.write("\n");
			writer.close();
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
	private static boolean wasClassificationCorrect()
	{
		
		String trainString;
		String testString;
	
			trainString=InputEnum.toString(InputEnum.PAIRS_3_2_Train_Output_NODRH);
			testString=InputEnum.toString(InputEnum.PAIRS_3_2_Test_Output_NODRH);
	
			// train classifier
		     J48 cls = new J48();
		     Instances train;
		     Instances test;
		     
			try {
				train = new Instances(new BufferedReader(new FileReader(trainString)));
				train.setClassIndex(train.numAttributes() - 1);
				
				
				test = new Instances(new BufferedReader(new FileReader(testString)));
				test.setClassIndex(test.numAttributes() - 1);
				


			
				cls.setUnpruned(false);
				
				
			     cls.buildClassifier(train);
			     
			     Evaluation eval = new Evaluation(train);
			     eval.evaluateModel(cls, test);
			     
			    
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
	protected static String getLabels(){
		String toReturn;


		toReturn="@relation pairsTask"+"\n"+"\n"+"\n"+
				"@attribute Proximity numeric"+"\n"+

				"@attribute Critical {true,false}"+"\n"+""
				+ "\n";




		return toReturn;


	}
	/**
	 * Helper method-creates a string that contains information about a pair TEST of task.
	 * this string is returned,which is then written directly into the arff file(not be this method). 
	 * 
	 * */
	protected static String getStringOfTheTestTaskPair(TaskPair taskPair){

		double proximity=taskPair.getProximityScore();
		String critical=""+taskPair.isCritical();




		return proximity+","
		+ critical;







	}


}
