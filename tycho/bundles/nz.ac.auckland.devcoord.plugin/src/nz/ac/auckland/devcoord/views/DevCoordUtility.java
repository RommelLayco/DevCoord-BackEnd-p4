package nz.ac.auckland.devcoord.views;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.devcoord.controller.TaskWrapper;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;

/**
 * Helper class for Devcoord
 * */
public class DevCoordUtility {

	
	
	static ArrayList<Task> returnTasksThatAreCritical(List<TaskPair> pairs,TaskWrapper taskWrapper){


		ArrayList<Task> toReturn=new ArrayList<Task>();

		for (TaskPair taskPair : pairs) {
			if(taskPair.isCritical()){
				if(taskPair.getTask1().getTaskID()==taskWrapper.getTaskID()){

					toReturn.add(taskPair.getTask2());

				}
				else{

					toReturn.add(taskPair.getTask1());

				}



			}

		}


		return toReturn;

	}
	
	static int getOtherTaskID(int ID,TaskPair pair){		

		if (pair.getID1()==ID) {		
			return pair.getID2();		
		}		
		else if (pair.getID2()==ID) {		
			return pair.getID1();		
		}		
		else{		

			return -1;		
		}		

	}
	
	static String getOverlappingTaskPairs(List<TaskPair> pairs,TaskWrapper taskWrapper){

		String separator=System.getProperty("line.separator");
		String toReturn="";
		for (TaskPair  pair: pairs) {
			toReturn+=" Task: "+getOtherTaskID(taskWrapper.getTaskID(), pair)+separator;


		}

		return toReturn;

	}
	static String criticalString(List<TaskPair> pairs,TaskWrapper taskWrapper){
		String separator=System.getProperty("line.separator");
		String toReturn="";
		ArrayList<Task> criticalTasks=returnTasksThatAreCritical(pairs,taskWrapper);



		for (Task task : criticalTasks) {
			toReturn+="Task: "+task.getTaskID()+"   Owner: "+task.getOwner()+separator;
			toReturn+="	Description: "+task.getDescription()+separator;


		}

		return toReturn;
	}
	
	
}
