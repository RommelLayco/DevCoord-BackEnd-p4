package nz.ac.auckland.devcoord.database;



import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;




@Entity
@Table(name = "TASK_PAIRS",
uniqueConstraints = {@UniqueConstraint(columnNames = {"task_pair_1", "task_pair_2"})}) 
public class TaskPair {

	@Id @GeneratedValue
	Integer taskPairID;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name = "task_pair_1")
	private Task task1;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "task_pair_2")
	private Task task2;

	private double proximityScore;

	private double actualScore;

	private double potentialScore;

	@ElementCollection
	@CollectionTable(name="POTENTIAL_SCORES")
	@MapKeyColumn(name="POTENTIAL_SCORES_KEYS")
	private Map<String, Double> potentialScores;

	@ElementCollection
	@CollectionTable(name="ACTUAL_SCORES")
	@MapKeyColumn(name="ACTUAL_SCORES_KEY")
	private Map<String, Double> actualScores;


	@Column(name = "SAME_OS")
	@Type(type="yes_no")
	private boolean sameOS;

	@Column(name = "SAME_PLATFORM")
	@Type(type="yes_no")
	private boolean samePlatform;

	@Column(name = "SAME_COMPONENT")
	@Type(type="yes_no")
	private boolean sameComponent;


	private boolean isCritical;

	/**
	 * To prevent duplication of the same two task, the task id
	 * that is smaller will always be task1.
	 * 
	 * if if task id are 10 and 15
	 * task1 = will be the task with id 10 and task2 the one with 15
	 * @param task1
	 * @param task2
	 */
	public TaskPair(Task task1, Task task2){

		this.actualScores = new HashMap<String,Double>();
		this.potentialScores = new HashMap<String,Double>();

		if(task1.getTaskID() < task2.getTaskID()){
			this.task1 = task1;
			this.task2 = task2;
		} else {
			this.task1 = task2;
			this.task2 = task1;
		}

		//only do this if the two task actually have 
		//context_Structures
		if(this.task1.getContextStructures().size() > 0 && this.task2.getContextStructures().size() > 0){
			this.calcProximityScore();
		} else {
			this.proximityScore = 0;
		}

		setTaskProperties();
	}

	/**
	 * Default constuctor required
	 */
	public TaskPair(){}

	/**
	 * Method to initalize the value of a proximity score
	 * when creating a new task pair
	 * @param files1
	 * @param files2
	 */
	private void calcProximityScore(){

		//calculate the actual and potential scores
		this.calcPotentialScore();
		this.calcActualScore();


		this.proximityScore = this.actualScore / this.potentialScore;
	}

	/**
	 * Calculates the proximity score by changing the weighting appied to the
	 * file that generated the event.
	 * 
	 * Minus the score stored for the file
	 * Calculates the new score
	 * Updates the  proximity score with the new score
	 * 
	 * @param file1
	 * @param file2
	 */
	public void updateProximityScore(Context_Structure file1, Context_Structure file2){
		//minus the old values of the file from the potential and 
		//actual scores.

		String name = file1.getName();

		if(this.potentialScores.containsKey(name)){ //i.e. if context already exist in task
			
			this.potentialScore -= this.potentialScores.get(name);
			this.actualScore -= this.actualScores.get(name);
		}

		//set the update value for the file in the map
		//or add the new context structure if it does not already exist in 
		//the task
		setPotentialValueForContext(name, potentialScore(file1, file2));
		setActualValueForContext(name, actualScore(file1, file2));

		//update the potentail and actual scores
		this.potentialScore += getPotentialValueForContext(name);
		this.actualScore += getActualValueForContext(name);

		this.proximityScore = this.actualScore / this.potentialScore;

	}

	/**
	 * Calculate the potential score of the two task
	 * i.e. union of the java files map
	 * @param files1
	 * @param files2
	 */
	private void calcPotentialScore(){

		double potentialScore = 0;
		Iterator<String> keys = combineKeys(this.task1.getContextStructures(), this.task2.getContextStructures());

		while(keys.hasNext()){
			String key = keys.next(); //get the two java files
			Context_Structure file1 = this.task1.getContextStructures().get(key);
			Context_Structure file2 = this.task2.getContextStructures().get(key);
			potentialScore += potentialScore(file1, file2);
		}

		this.potentialScore = potentialScore;

	}

	/**
	 * Calcualte the actualScore of the two tasks
	 * i.e. the intersection of the java files map
	 * @param files1
	 * @param files2
	 */
	private void calcActualScore(){

		double actualScore = 0;
		Iterator<String> keys = combineKeys(this.task1.getContextStructures(), this.task2.getContextStructures());

		while(keys.hasNext()){
			String key = keys.next(); //get the two java files
			Context_Structure file1 = this.task1.getContextStructures().get(key);
			Context_Structure file2 = this.task2.getContextStructures().get(key);
			actualScore += actualScore(file1, file2);
		}

		this.actualScore = actualScore;


	}


	/**
	 * Helper method to give the potential score between two files
	 * @param file1
	 * @param file2
	 * @return either 1, or 0.59 or 0
	 */
	private double potentialScore(Context_Structure file1, Context_Structure file2){

		//only do work if one of the files is not null
		if(file1 != null && file2 != null){ 

			if(file1.isEdited() || file2.isEdited()){
				this.potentialScores.put(file1.getName(), 1.0);
				return 1;
			} else if(file1.isSelected() || file1.isSelected()){
				this.potentialScores.put(file1.getName(), 0.59);
				return 0.59;
			} else {
				this.potentialScores.put(file1.getName(), 0.0);
				return 0;
			}

		} else if(file2 != null){ //file 1 is null

			if(file2.isEdited()){
				this.potentialScores.put(file2.getName(), 1.0);
				return 1;
			} else if (file2.isSelected()){
				this.potentialScores.put(file2.getName(), 0.59);
				return 0.59;
			} else{
				this.potentialScores.put(file2.getName(), 0.0);
				return 0;
			}

		} else if(file1 != null){ //file 2 is null

			if(file1.isEdited()){
				this.potentialScores.put(file1.getName(), 1.0);
				return 1;
			} else if (file1.isSelected()){
				this.potentialScores.put(file1.getName(), 0.59);
				return 0.59;
			} else{
				this.potentialScores.put(file1.getName(), 0.0);
				return 0;
			}

		} else {
			throw new IllegalArgumentException("files for calculating potential values were both null");
		}
	}

	/**
	 * Helper method to calculate the actual score of two files
	 * @param file1
	 * @param file2
	 * @return
	 */
	private double actualScore(Context_Structure file1, Context_Structure file2){
		if(file1 != null && file2 != null){

			if(file1.isEdited() && file2.isEdited()){
				this.actualScores.put(file1.getName(), 1.0);
				return 1;
			} else if (file1.isEdited() || file2.isEdited()){ //only 1 is edited
				this.actualScores.put(file1.getName(), 0.79);
				return 0.79;
			} else if(file1.isSelected() || file2.isSelected()){ //one of them is selected but not edited
				this.actualScores.put(file1.getName(), 0.59);
				return 0.59;
			} else {
				this.actualScores.put(file1.getName(), 0.0);
				return 0;
			}

		} else {
			//files does not exist in both task working set
			//only exist in one of the working sets

			if(file1 != null){
				this.actualScores.put(file1.getName(), 0.0);
			} else { //that is file 1 is null and we are looking at file 2
				this.actualScores.put(file2.getName(), 0.0);
			}
			return 0;
		}
	}

	/**
	 * Helper method to return a single iterator of the set of keys of
	 * both maps
	 * @param files1
	 * @param files2
	 * @return a single set of keys for both maps
	 */
	private static Iterator<String> combineKeys(Map<String, Context_Structure> files1, 
			Map<String, Context_Structure> files2){


		Set<String> set1 = files1.keySet();
		Set<String> set2 = files2.keySet();
		Set<String> combinedSet = new HashSet<String>();

		combinedSet.addAll(set1);
		combinedSet.addAll(set2);

		Iterator<String> it = combinedSet.iterator();

		return it; 

	}

	public Integer getID(){
		return this.taskPairID;
	}

	public double getProximityScore(){
		return this.proximityScore;
	}

	public int getID1(){
		return this.task1.getTaskID();
	}

	public int getID2(){
		return this.task2.getTaskID();
	}

	public Task getTask1(){
		return this.task1;
	}

	public Task getTask2(){
		return this.task2;
	}

	public boolean isSameOS(){
		return this.sameOS;
	}
	public boolean isSamePlatform(){
		return this.samePlatform;
	}

	public boolean isSameComponent(){
		return this.sameComponent;
	}
	/**
	 * Resets the potential value for the
	 * associated context_Struture
	 * @param value
	 */
	public void setPotentialValueForContext(String name, double value){
		this.potentialScores.put(name,value);
	}

	/**
	 * Gets the potential value score for the
	 * associated context_Structure
	 * @param name
	 */
	public double getPotentialValueForContext(String name){
		return this.potentialScores.get(name);
	}

	/**
	 * Resets the actual value for the
	 * associated context_Struture
	 * @param value
	 */
	public void setActualValueForContext(String name, double value){
		this.actualScores.put(name,value);
	}

	/**
	 * Gets the actual value score for the
	 * associated context_Structure
	 * @param name
	 */
	public double getActualValueForContext(String name){
		if(this.actualScores.get(name) != null){
			return this.actualScores.get(name);
		} else{
			return -1;
		}
	}

	//need to override equals and hash for maps and sets
	/**
	 * Method to override hashcode  just like se325 project
	 */
	@Override
	public int hashCode( ) {
		return new HashCodeBuilder( 17, 31 ).
				append(taskPairID).
				append(task1).
				append(task2).
				append(proximityScore).
				append(potentialScore).
				append(actualScore).
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

		TaskPair rhs = (TaskPair) obj;
		return new EqualsBuilder( ).
				append( taskPairID, rhs.taskPairID ).
				append( task1, rhs.task1 ).
				append( task2, rhs.task2 ).
				append( proximityScore, rhs.proximityScore ).
				append( potentialScore, rhs.potentialScore ).
				append( actualScore, rhs.actualScore ).
				isEquals( );
	}

	public boolean isCritical() {
		return isCritical;
	}

	public void setCritical(boolean isCritical) {
		this.isCritical = isCritical;
	}

	/**
	 * Method to set the other task properties
	 * 
	 * i.e. if the the two task have the same OS, Component and Platform
	 */
	public void setTaskProperties(){

		if(task1.getOS().equals(task2.getOS())){
			this.sameOS = true;
		} else {
			this.sameOS = false;
		}

		if(task1.getPlatform().equals(task2.getPlatform())){
			this.samePlatform = true;
		} else {
			this.samePlatform = false;
		}

		if(task1.getComponent().equals(task2.getComponent())){
			this.sameComponent = true;
		} else {
			this.sameComponent = false;
		}
	}
}


