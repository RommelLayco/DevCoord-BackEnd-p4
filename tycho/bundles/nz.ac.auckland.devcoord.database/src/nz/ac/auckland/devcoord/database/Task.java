package nz.ac.auckland.devcoord.database;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
	@Column(name = "ID")
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
	@ElementCollection//(fetch = FetchType.EAGER)
	@CollectionTable(name="CONTEXT_STRUCTURES")
	@MapKeyColumn(name="HASHMAP_KEY")
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
	
	public void updateTaskID(int ID){
		this.taskID = ID;
	}

	public String getHandle(){
		return this.handle;
	}

	public void updateHandle(String handle){
		this.handle = handle;
	}
	
	public String getLabel(){
		return this.label;
	}
	
	public void updateLablel(String label){
		this.label = label;
	}

	public void addContextStructure(String filepath, Context_Structure structure){
		this.contextStructure.put(filepath, structure);
	}

	public Map<String, Context_Structure> getContextStructures(){
		return this.contextStructure;
	}

	//need to override equals and hash for maps and sets
	/**
	 * Method to override hashcode  just like se325 project
	 */
	@Override
	public int hashCode( ) {
		return new HashCodeBuilder( 17, 31 ).
				append(taskID).
				append(handle).
				append(label).
				append(contextStructure).
				toHashCode( );
	}

	/**
	 * Method to override equals just like se325 project
	 */
	@Override
	public boolean equals( Object obj ) {
		if (! ( obj instanceof Task ) )
			return false;
		if ( obj == this )
			return true;

		Task rhs = (Task) obj;
		return new EqualsBuilder( ).
				append( taskID, rhs.taskID ).
				append( handle, rhs.handle ).
				append( label, rhs.label ).
				append( contextStructure, rhs.contextStructure ).
				isEquals( );
	}



	
}