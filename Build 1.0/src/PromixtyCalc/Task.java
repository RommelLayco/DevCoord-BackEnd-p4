package PromixtyCalc;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * Class that stores information about a
 * task needed to calculate promixty score
 * @author Rommel
 *
 */
@Entity
@Table(name = "TASKS")
public class Task {
	
	/**
	 * Task id created by mylyn.
	 */
	@Id
	@Column(name = "ID")
	private int taskID;
	
	/**
	 * Handle is the identifier used in the context xml
	 * for a task
	 */
	@Column(name = "HANDLE",nullable = false)
	private String handle;
	
	/**
	 * Label is the name of the task
	 * Named when creating a task
	 */
	@Column(name = "TASK_NAME", nullable = false)
	private String label;
	
	/**
	 * The java files that are associated with the task
	 * Key is the name of the java file
	 */
	@ElementCollection
	@CollectionTable(name="STRUCTURE_CONTEXTS")
	@MapKeyColumn(name="STRUCTURE_NAME")
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
