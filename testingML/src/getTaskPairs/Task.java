package getTaskPairs;

/**
 * Class to store tasks from the tasks file for further processing
 * @author Rommel
 *
 */
public class Task {

	private int taskID;
	private Platform platform;
	private OS os;
	private Component component;

	public Task(int taskID, Platform platform, OS os, Component component){
		this.taskID = taskID;
		this.platform = platform;
		this.os = os;
		this.component = component;
	}

	/**
	 * Method to initial 
	 * @param line
	 * @return
	 */
	public static Task create(String[] line){
		//convert into right type
		int taskID = Integer.parseInt(line[0]);
		Platform platform = Platform.platformEnum(line[9]);
		OS os = OS.osEnum(line[10]);
		Component component = Component.componentEnum(line[12]);
		
		//Create task pair object
		Task task = new Task(taskID, platform, os, component);
		return task;
		
	}
	

	// ***************** getter methods for junit testing ***************************
	public int getTaskID(){
		return this.taskID;
	}
	public Platform getPlatform(){
		return this.platform;
	}

	public OS getOS(){
		return this.os;
	}

	public Component getComponent(){
		return this.component;
	}
	
}
