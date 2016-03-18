package getTaskPairs;

import java.util.List;
import java.util.Map;

/**
 * Class contains the list of task pairs 
 * and a map of task, to help process the information
 * @author Rommel
 *
 */
public class ProcessData {
	
	private Map<TaskPairKey, TaskPair> taskPairs;
	private Map<Integer, Task> tasks;
	private List<TaskPairKey> keys;
	
	public ProcessData(Map<TaskPairKey, TaskPair> taskPairs, Map<Integer, Task> tasks,
			List<TaskPairKey> keys){
		this.taskPairs = taskPairs;
		this.tasks = tasks;
		this.keys = keys;
	}
	
	/**
	 * Method to set sameComponent, sameOS and samePlatform
	 * fields of taskPair object
	 * 
	 * iterates through taskpair list and finds the task objects in
	 * the task map
	 */
	public void setMatching(){
		for(TaskPairKey key : this.keys){
			
			//get task from map using the taskPairKey
			Task t1 = this.tasks.get(key.getID1());
			Task t2 = this.tasks.get(key.getID2());
			
			//check if there task are not null
			if(t1 == null || t2 == null){
				continue;
			}
			
			//get Task Pair
			TaskPair pair = this.taskPairs.get(key);
			
			//set matching fields to true
			pair.setRest(t1, t2);
		}
	}
	
	// ***************** getter methods for junit testing ***************************
	public TaskPair getTaskPair(TaskPairKey key){
		return this.taskPairs.get(key);
	}
	
}
