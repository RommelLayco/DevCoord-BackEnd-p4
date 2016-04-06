package PromixtyCalc;

public class JavaFile {
	
	private String name;
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
