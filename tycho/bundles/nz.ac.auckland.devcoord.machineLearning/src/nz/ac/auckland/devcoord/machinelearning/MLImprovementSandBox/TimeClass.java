package nz.ac.auckland.devcoord.machinelearning.MLImprovementSandBox;

import nz.ac.auckland.devcoord.database.Context_Structure;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;
import nz.ac.auckland.devcoord.machinelearning.testData.CriticalityUtility;

public class TimeClass {
/**
 * Timing code Reference:http://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
 * */
	public static void main(String[] args) {

		TaskPair taskPair=buildDummyTaskPair();
		
		
		long startTime = System.nanoTime();


		CriticalityUtility.convertTestDataToArff(taskPair);
		
		
		long endTime = System.nanoTime();

		long duration = (endTime - startTime)/1000000;  // milliseconds.
		
		System.out.println(duration);

	}
	
	
	private static TaskPair buildDummyTaskPair(){
		
		Task one=new Task(1, "Dummy Handle one", "Dummy label one");
		Task two=new Task(2, "Dummy Handle two", "Dummy label two");
		Context_Structure context_StructureOne=new Context_Structure("ContextOne", false, true);
		Context_Structure context_StructureTwo=new Context_Structure("ContextOne", true, false);
		one.addContextStructure(context_StructureOne.getName(), context_StructureOne);
		two.addContextStructure(context_StructureTwo.getName(), context_StructureTwo);
		TaskPair taskPairOne=new TaskPair(one,two);
		
		return taskPairOne;
		
	}

}
