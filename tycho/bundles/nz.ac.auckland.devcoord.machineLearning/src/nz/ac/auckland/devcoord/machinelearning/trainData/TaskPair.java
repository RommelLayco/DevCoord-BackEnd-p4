package nz.ac.auckland.devcoord.machinelearning.trainData;

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
	private boolean sameComponent;
	private boolean samePlatform;
	private boolean sameOS;
	private boolean critical;
	
	
	public TaskPair(int task1, int task2, float pscore, int SLSM, int AL){
		this.task1 = task1;
		this.task2 = task2;
		this.proxmityScore = pscore;
		this.SLSM = SLSM;
		this.AL = AL;
		this.sameComponent = false;
		this.sameOS = false;
		this.samePlatform = false;
		this.critical = false;
	}
	/**
	 * for coding/testing ML stuff in plugin
	 * */
	public TaskPair(int task1, int task2, float pscore,boolean isCritical){
		this.task1 = task1;
		this.task2 = task2;
		this.proxmityScore = pscore;
		this.SLSM = -1;
		this.AL = -1;
		this.sameComponent = false;
		this.sameOS = false;
		this.samePlatform = false;
		this.critical = isCritical;
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
	
	public static TaskPair createAll(String[] line){
		int t1 = Integer.parseInt(line[0]);
		int t2 = Integer.parseInt(line[1]);
		float pscore = Float.parseFloat(line[5]);
		int SLSM = Integer.parseInt(line[6]);
		int AL = Integer.parseInt(line[7]);
		
		TaskPair tp = new TaskPair(t1, t2, pscore, SLSM, AL);
			
		tp.sameComponent = getBool(line[2]);
		tp.samePlatform = getBool(line[3]);
		tp.sameOS = getBool(line[4]);
		
		
		return tp;
	}
	
	/**
	 * Method to mark task pair as critical
	 * if taskAcc says it is
	 * @param taskAcc
	 */
	public void setCritical(TaskAcc taskAcc){
		if(taskAcc.isCritical()){
			this.critical = true;
		}
	}
	
	/**
	 * Method to set the rest of the fields for the object
	 * sameComponent, sameOS same platform
	 * 
	 * @param t1 a task object
	 * @param t2 a task object
	 */
	public void setRest(Task t1, Task t2){
		this.setComponent(t1, t2);
		this.setOS(t1, t2);
		this.setPlatform(t1, t2);
	}
	
	/**
	 * Check if task pair has the same component
	 * if true set sameComponent to true
	 * 
	 * To be used in setrest() method
	 * 
	 * @param t1 task objects
	 * @param t2 task objects
	 */
	private void setComponent(Task t1, Task t2){
		if(t1.getComponent() == t2.getComponent()){
			this.sameComponent = true;
		}
	}
	
	/**
	 * Check if task pair has the same Platform
	 * if true set samePlatform to true
	 * 
	 * To be used in setRest() method
	 * 
	 * @param t1 task objects
	 * @param t2 task objects
	 */
	private void setPlatform(Task t1, Task t2){
		if(t1.getPlatform() == t2.getPlatform()){
			this.samePlatform = true;
		}
	}
	
	/**
	 * Check if task pair has the same OS
	 * if true set sameOSto true
	 * 
	 * To be used in setRest() method
	 * 
	 * @param t1 task objects
	 * @param t2 task objects
	 */
	private void setOS(Task t1, Task t2){
		if(t1.getOS() == t2.getOS()){
			this.sameOS = true;
		}
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

	public boolean isSameComponent(){
		return this.sameComponent;
	}

	public boolean isSamePlatform(){
		return this.samePlatform;
	}
	
	public boolean isSameOS(){
		return this.sameOS;
	}
	
	public boolean isCritical(){
		return this.critical;
	}
	
	public static boolean getBool(String word){
		if(word.equals("TRUE")){
			return true;
		} else{
			return false;
		}
	}
}
