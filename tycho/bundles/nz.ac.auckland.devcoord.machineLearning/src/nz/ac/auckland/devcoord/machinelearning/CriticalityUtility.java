package nz.ac.auckland.devcoord.machinelearning;

import java.util.List;

import nz.ac.auckland.devcoord.database.TaskPair;
/**
 * Utility class which will be used to fill in the criticality of the task pairs
 * */
public class CriticalityUtility {

	public static List<TaskPair> fillInCriticality(List<TaskPair> taskPairsListArg){
		
		for (TaskPair taskPair : taskPairsListArg) {
			//this loop will contain the machine learning code
			
			taskPair.setCritical(true);//Dummy code
		
		}
		
		
		return taskPairsListArg;
		
				
	}
	
	
}
