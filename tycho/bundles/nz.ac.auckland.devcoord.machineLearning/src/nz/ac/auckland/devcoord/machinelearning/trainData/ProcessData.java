package nz.ac.auckland.devcoord.machinelearning.trainData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class contains the list of task pairs 
 * and a map of task, to help process the information
 * @author Rommel
 *
 */
public class ProcessData {

	//info
	private Map<TaskPairKey, TaskPair> taskPairs;
	private Map<Integer, Task> tasks;
	private Map<TaskPairKey, TaskAcc> taskAccs;

	private List<TaskPairKey> pairKeys;
	private List<TaskPairKey> accKeys;

	private List<TaskPairKey> keysTrain;
	private List<TaskPairKey> keysTest;

	public ProcessData(Map<TaskPairKey, TaskPair> taskPairs, Map<Integer, Task> tasks,
			List<TaskPairKey> keys, Map<TaskPairKey, TaskAcc> taskAccs, List<TaskPairKey> accKeys){

		this.taskPairs = taskPairs;
		this.tasks = tasks;
		this.pairKeys = keys;
		this.taskAccs = taskAccs;
		this.accKeys = accKeys;

		keysTrain = new ArrayList<TaskPairKey>();
		keysTest = new ArrayList<TaskPairKey>();
	}

	/**
	 * Method to set sameComponent, sameOS and samePlatform
	 * fields of taskPair object
	 * 
	 * iterates through taskpair list and finds the task objects in
	 * the task map
	 */
	public void setMatching(){
		for(TaskPairKey key : this.pairKeys){

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

	/**
	 * Method to set right critical value and generate two key list.
	 */
	public void setCritical(){
		for(TaskPairKey key : this.accKeys){


			//get TaskAcc
			TaskAcc taskAcc = this.taskAccs.get(key);

			//get correct taskPairkey
			TaskPairKey key2 = getRightKey(key);
			if(key2 == null){
				continue;
			}

			//get Task pair
			TaskPair taskPair = this.taskPairs.get(key2);


			//set correct critical and store in test or train key list

			taskPair.setCritical(taskAcc);

			if(taskAcc.getVersion() == Version.TRAIN){
				this.keysTrain.add(key2);
			} else {
				this.keysTest.add(key2);
			}
		}	


	}


	/**
	 * Helper method to find task pair key
	 * @param key from taskacc
	 * @return taskPairKey
	 */

	private TaskPairKey getRightKey(TaskPairKey key){
		for(TaskPairKey tpKey : this.pairKeys){
			if((tpKey.getID1() == key.getID1() && tpKey.getID2() == key.getID2())
					|| tpKey.getID2() == key.getID1() && tpKey.getID1() == key.getID2()){
				return tpKey;
			}
		}
		return null;
	}


	// ***************** getter methods for junit testing ***************************
	public TaskPair getTaskPair(TaskPairKey key){
		return this.taskPairs.get(key);
	}

	public List<TaskPairKey> getTrainKeys(){
		return this.keysTrain;
	}

	public List<TaskPairKey> getTestKeys(){
		return this.keysTest;
	}

	public Map<TaskPairKey, TaskPair> getTaskPairs(){
		return this.taskPairs;
	}
}
