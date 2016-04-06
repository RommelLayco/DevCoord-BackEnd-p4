package PromixtyCalc;

import java.util.HashMap;
import java.util.Map;

public class Task {
	
	private int taskID;
	private String handle;
	private String label;
	private Map<String, JavaFile> javaFiles;
	
	public Task(int taskID, String handle, String label){
		this.taskID = taskID;
		this.handle = handle;
		this.label = label;
		this.javaFiles = new HashMap<String, JavaFile>();
	}
	
	public int getTaskID(){
		return this.taskID;
	}
	
	public String getHandle(){
		return this.handle;
	}
	
	public String getLabel(){
		return this.label;
	}
	
	public void addJavaFile(String filepath, JavaFile file){
		this.javaFiles.put(filepath, file);
	}
	
	public Map<String, JavaFile> getJavaFiles(){
		return this.javaFiles;
	}

}
