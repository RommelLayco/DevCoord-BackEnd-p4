package LIBSVM;

import java.util.List;
import java.util.Map;

/**
 * Class contains the list of task pairs 
 * and a map of task, to help process the information
 * @author Rommel
 *
 */
public class ProcessData {
	
	private List<TaskPair> taskPairs;
	private Map<Integer, Task> tasks;
	
	public ProcessData(List<TaskPair> taskPairs, Map<Integer, Task> tasks ){
		this.taskPairs = taskPairs;
		this.tasks = tasks;
	}
	
	/**
	 * Method to set sameComponent, sameOS and samePlatform
	 * fields of taskPair object
	 * 
	 * iterates through taskpair list and finds the task objects in
	 * the task map
	 */
	public void setMatching(){
		for(TaskPair pair : this.taskPairs){
			//get task from map
			Task t1 = this.tasks.get(pair.getT1());
			Task t2 = this.tasks.get(pair.getT2());
			
			//check if there task are not null
			if(t1 == null || t2 == null){
				continue;
			}
			//set matching fields to true
			pair.setRest(t1, t2);
		}
	}
	
	// ***************** getter methods for junit testing ***************************
	public TaskPair getTaskPair(int index){
		return this.taskPairs.get(index);
	}
	
}
