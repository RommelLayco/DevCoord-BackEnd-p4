package nz.ac.auckland.proximity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import org.eclipse.mylyn.monitor.core.InteractionEvent.Kind;

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

	public void update(Kind kind){
		switch(kind){
		case EDIT:
			this.edit = true;
			this.select = false;
		
		case SELECTION:
			this.edit = false;
			this.select = true;
		
		default:
			System.err.println("we don't care about the other types,"
					+ "should not get here, this must be handle somewhere else");
				
		}
		
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
	
	/**
	 * Method to override hashcode  just like se325 project
	 */
	@Override
	public int hashCode( ) {
		return new HashCodeBuilder( 17, 31 ).
				append(name).
				append(edit).
				append(select).
				toHashCode( );
	}

	/**
	 * Method to override equals just like se325 project
	 */
	@Override
	public boolean equals( Object obj ) {
		if (! ( obj instanceof TaskPair ) )
			return false;
		if ( obj == this )
			return true;
		
		Context_Structure rhs = (Context_Structure) obj;
		return new EqualsBuilder( ).
				append( name, rhs.name ).
				append( edit, rhs.edit ).
				append( select, rhs.select ).
				isEquals( );
	}
}
