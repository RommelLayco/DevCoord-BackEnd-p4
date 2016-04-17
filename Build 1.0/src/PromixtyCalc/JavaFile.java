package PromixtyCalc;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Class that stores whether a java file that is 
 * associated with a task has been viewed("select") or
 * modified ("edit")
 * 
 * This class is embeddable as each java file is particular context
 * associted with a task.
 * @author Rommel
 *
 */
@Embeddable
public class JavaFile {
	
	/**
	 * Name of the java file
	 */
	@Column(name="name", nullable = false)
	private String name;
	
	/**
	 * booleans used to calculate the proximity score in taskPair
	 */
	
	@Column(name="SELECT", nullable = false)
	private boolean select;
	
	@Column(name="EDIT", nullable = false)
	private boolean edit;
	
	/**
	 * Default constructor needed by jpa
	 */
	public JavaFile(){
		
	}
	
	public JavaFile(String name, boolean select, boolean edit){
		this.name = name;
		this.select = select;
		this.edit = edit;
	}
	
	public String getName(){
		return this.name;
	
	}

	public boolean isSelected(){
		return this.select;
	}
	
	public void setSelected(boolean var){
		this.select = var;
	}
	
	public boolean isEdited(){
		return this.edit;
	}
	
	public void setEdit(boolean var){
		this.edit = var;
	}
}
