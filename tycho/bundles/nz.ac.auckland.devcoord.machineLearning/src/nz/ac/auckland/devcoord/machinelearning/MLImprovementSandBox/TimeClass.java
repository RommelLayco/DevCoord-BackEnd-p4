package nz.ac.auckland.devcoord.machinelearning.MLImprovementSandBox;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import nz.ac.auckland.devcoord.database.Context_Structure;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;
import nz.ac.auckland.devcoord.machinelearning.decisionTree.InputEnum;
import nz.ac.auckland.devcoord.machinelearning.testData.CriticalityUtility;

public class TimeClass {
/**
 * Timing code Reference:http://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
 *
 *SANDBOX class to test and improve ML algorithm.
 * */
	public static void main(String[] args) {

		TaskPair taskPair=buildDummyTaskPairOne();
		
		
		long startTime = System.nanoTime();


		//CriticalityUtility.convertTestDataToArff(taskPair);
		CriticalityUtility.wasClassificationCorrect();
		
		
		long endTime = System.nanoTime();

		long duration = (endTime - startTime)/1000000;  // milliseconds.
		
		System.out.println(duration);
		
		
		buildARFFTestFileWithMultipleTaskPairs();

	}
	/**
	 * 
	 * Builds ARFF file with multiple task pairs*/
	private static void buildARFFTestFileWithMultipleTaskPairs(){
		
		
		Writer writer = null;
		String inputString;
		inputString="output/DUMMY_test.arff";
		try{
			FileOutputStream fileOutputStream=new FileOutputStream(inputString);
			OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream, "utf-8"); 
			writer = new BufferedWriter(outputStreamWriter);
			writer.write(CriticalityUtility.getLabels());
			writer.write("@data"+"\n");
			writer.write(CriticalityUtility.getStringOfTheTestTaskPair(buildDummyTaskPairOne()));
			writer.write("\n");
			writer.write(CriticalityUtility.getStringOfTheTestTaskPair(buildDummyTaskPairTwo()));
			writer.write("\n");
			
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
	
	
	
	private static TaskPair buildDummyTaskPairOne(){
		
		Task one=new Task(1, "Dummy Handle one", "Dummy label one");
		Task two=new Task(2, "Dummy Handle two", "Dummy label two");
		Context_Structure context_StructureOne=new Context_Structure("ContextOne", false, true);
		Context_Structure context_StructureTwo=new Context_Structure("ContextOne", true, false);
		one.addContextStructure(context_StructureOne.getName(), context_StructureOne);
		two.addContextStructure(context_StructureTwo.getName(), context_StructureOne);
		TaskPair taskPairOne=new TaskPair(one,two);
		taskPairOne.calcProximityScore();
		return taskPairOne;
		
	}

	private static TaskPair buildDummyTaskPairTwo(){
		
		Task one=new Task(1, "Dummy Handle Three", "Dummy label Three");
		Task two=new Task(2, "Dummy Handle four", "Dummy label four");
		Context_Structure context_StructureOne=new Context_Structure("ContextThree", false, true);
		Context_Structure context_StructureTwo=new Context_Structure("Contextfour", true, false);
		one.addContextStructure(context_StructureOne.getName(), context_StructureOne);
		two.addContextStructure(context_StructureTwo.getName(), context_StructureTwo);
		TaskPair taskPairOne=new TaskPair(one,two);
		taskPairOne.calcProximityScore();
		return taskPairOne;
		
	}
	
	
}
