package LIBSVM;

/**
 * Class to store task pairs into objects for further processing
 * @author Rommel
 *
 */

public class TaskPair {
	
	private int task1;
	private int task2;
	private float proxmityScore;
	private int SLSM;
	private int AL;
	
	
	public TaskPair(int task1, int task2, float pscore, int SLSM, int AL){
		this.task1 = task1;
		this.task2 = task2;
		this.proxmityScore = pscore;
		this.SLSM = SLSM;
		this.AL = AL;
	}
	
	/**
	 * Helper method to create task pair object from a line in the file
	 * @param line a string array of words
	 * @return Taskpair object to be used for processing in the future
	 */
	public static TaskPair create(String[] line){
		//convert into right type
		int t1 = Integer.parseInt(line[0]);
		int t2 = Integer.parseInt(line[1]);
		float pscore = Float.parseFloat(line[2]);
		int SLSM = Integer.parseInt(line[4]);
		int AL = Integer.parseInt(line[5]);
		
		//Create task pair object
		TaskPair tp = new TaskPair(t1, t2, pscore, SLSM, AL);
		
		return tp;
	}
	
	// ***************** getter methods for junit testing ***************************
	public int getT1(){
		return this.task1;
	}
	public int getT2(){
		return this.task2;
	}

	public float getPscore(){
		return this.proxmityScore;
	}

	public int getSLSM(){
		return this.SLSM;
	}

	public int getAL(){
		return this.AL;
	}


}
