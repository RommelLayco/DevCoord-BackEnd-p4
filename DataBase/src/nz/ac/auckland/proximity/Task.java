package nz.ac.auckland.proximity;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
/**
 * Class that stores information about a
 * task needed to calculate promixty score
 * @author Rommel
 *
 */
@Entity
@Table(name="TASKS")
public class Task {
	
	/**
	 * Task id created by mylyn.
	 */
	@Id
	private Integer taskID;
	
	/**
	 * Handle is the identifier used in the context xml
	 * for a task
	 */
	private String handle;
	
	/**
	 * Label is the name of the task
	 * Named when creating a task
	 */
	private String label;
	
	/**
	 * The java files that are associated with the task
	 * Key is the name of the java file
	 * 
	 * Java file is a value type as each file is context asscoaited with
	 * a specific task
	 */
	@ElementCollection
	@CollectionTable(name="CONTEXT_STRUCTURES")
	@MapKeyColumn(name="STRUCTURE_NAME")
	private Map<String, Context_Structure> contextStructure;
	
	/**
	 * Default constructor required by hibernate
	 */
	public Task(){
		
	}
	
	public Task(int taskID, String handle, String label){
		this.taskID = taskID;
		this.handle = handle;
		this.label = label;
		this.contextStructure = new HashMap<String, Context_Structure>();
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
	
	public void addContextStructure(String filepath, Context_Structure structure){
		this.contextStructure.put(filepath, structure);
	}
	
	public Map<String, Context_Structure> getContextStructures(){
		return this.contextStructure;
	}

}
