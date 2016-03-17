package LIBSVM;

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
	
}
