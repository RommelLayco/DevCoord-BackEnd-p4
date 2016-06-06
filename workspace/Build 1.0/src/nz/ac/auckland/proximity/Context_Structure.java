package nz.ac.auckland.proximity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

@Embeddable
public class Context_Structure {



	/**
	 * Name of the context structure
	 * i.e. can be method name of field name
	 * 
	 * right now name is just the class name
	 */
	private String name;

	/**
	 * booleans used to calculate the proximity score in taskPair
	 * not can have a column called select
	 */

	@Column(name = "EDIT")
	@Type(type="yes_no")
	private boolean edit;

	@Column(name = "SELECTION")
	@Type(type="yes_no")
	private boolean select;

	/**
	 * Default constructor required by hibernate
	 */
	public Context_Structure(){

	}

	public Context_Structure(String name, boolean select, boolean edit){
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
