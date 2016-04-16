package PromixtyCalc;

/**
 * Class that stores whether a java file that is 
 * associated with a task has been viewed("select") or
 * modified ("edit")
 * @author Rommel
 *
 */
public class JavaFile {
	
	/**
	 * Name of the java file
	 */
	private String name;
	
	/**
	 * booleans used to calculate the proximity score in taskPair
	 */
	private boolean select;
	private boolean edit;
	
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
