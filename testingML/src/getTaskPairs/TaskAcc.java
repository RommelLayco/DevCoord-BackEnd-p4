package getTaskPairs;

/**
 * Class to store information from the accuracy coding file.
 * @author Rommel
 *
 */
public class TaskAcc {

	private int task1;
	private int task2;
	private Version version;
	private boolean discussion1;
	private boolean discussion2;
	private boolean conflict1;
	private boolean conflict2;
	private boolean critical;

	public TaskAcc(int task1, int task2, Version version,
			boolean discussion1, boolean discussion2, boolean conflict1, boolean conflict2){

		this.task1 = task1;
		this.task2 = task2;
		this.version = version;
		this.discussion1 = discussion1;
		this.discussion2 = discussion2;
		this.conflict1 = conflict1;
		this.conflict2 = conflict2;
		this.critical = false;
	}

	/**
	 * Set critical field to true or false
	 * by looking at the booleans 
	 */
	public void setCritical(){
		if((this.discussion1 && this.discussion2) || (this.conflict1 && this.conflict2)){
			this.critical = true;
		}
	}


	/**
	 * Helper method to create taskAcc object from a line in the file
	 * @param line a string array of words
	 * @return Taskpair object to be used for processing in the future
	 */
	public static TaskAcc create(String[] line){
		//convert into right type
		int t1 = Integer.parseInt(line[2]);
		int t2 = Integer.parseInt(line[3]);
		Version version = Version.versionEnum(line[1]);
		boolean d1 = readWord(line[6]);
		boolean d2 = readWord(line[7]);
		boolean c1 = readWord(line[8]);
		boolean c2 = readWord(line[9]);

		//Create task pair object
		TaskAcc taskAcc = new TaskAcc(t1, t2, version, d1, d2, c1, c2);
		taskAcc.setCritical();

		return taskAcc;
	}


	//******************************** HELPER METHOD TO CONVERT INPUT INTO BOOLEAN ************************
	private static boolean readWord(String word){
		if(word.equals("Not")){
			return false;
		} else {
			return true;
		}
	}

	//********************* HELPER METHOD to test input *******************88
	public int getT1(){
		return this.task1;
	}

	public int getT2(){
		return this.task2;
	}

	public Version getVersion(){
		return this.version;
	}

	public boolean isD1(){
		return this.discussion1;
	}

	public boolean isD2(){
		return this.discussion2;
	}

	public boolean isC1(){
		return this.conflict1;
	}

	public boolean isC2(){
		return this.conflict2;
	}

	public boolean isCritical(){
		return this.critical;
	}

}
