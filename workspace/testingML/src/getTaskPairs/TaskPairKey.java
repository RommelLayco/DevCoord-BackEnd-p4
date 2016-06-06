package getTaskPairs;

/**
 * Class is composite key to find the task pairs in a
 * map quickly
 * @author Rommel
 *
 */
public class TaskPairKey {
	
	private int task1ID;
	private int task2ID;
	
	public TaskPairKey(int t1ID, int t2ID){
		this.task1ID = t1ID;
		this.task2ID = t2ID;
	}
	
	public int getID1(){
		return this.task1ID;
	}

	public int getID2(){
		return this.task2ID;
	}
}
