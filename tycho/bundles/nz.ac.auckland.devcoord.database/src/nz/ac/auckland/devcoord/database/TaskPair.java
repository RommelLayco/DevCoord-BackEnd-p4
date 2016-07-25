package nz.ac.auckland.devcoord.database;



import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;



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

	
	private double potentialScore;

	
	private double actualScore;
	
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
		if(task1.getTaskID() < task2.getTaskID()){
			this.task1 = task1;
			this.task2 = task2;
		} else {
			this.task1 = task2;
			this.task2 = task1;
		}

		proximityScore = 0;
	}
	
	/**
	 * Default constuctor required
	 */
	public TaskPair(){}

	/**
	 * Method to calculate the proximity score 
	 * @param files1
	 * @param files2
	 */
	public void calcProximityScore(){

		//calculate the actual and potential scores
		this.calcPotentialScore();
		this.calcActualScore();


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
	private static double potentialScore(Context_Structure file1, Context_Structure file2){

		if(file1 != null && file2 != null){

			if(file1.isEdited() || file2.isEdited()){
				return 1;
			} else if (file1.isSelected() || file2.isSelected()){
				return 0.59;
			} else {
				return 0;
			}

		} else if(file1 != null){ //therefore file 2 is null

			if(file1.isEdited()){
				return 1;
			} else if(file1.isSelected()){
				return 0.59;
			} else {
				return 0;
			}

		} else{ //file2 is not null and file 1 is null

			if(file2.isEdited()){
				return 1;
			} else if (file2.isSelected()){
				return 0.59;
			} else{
				return 0;
			}
		}
	}

	/**
	 * Helper method to calculate the actual score of two files
	 * @param file1
	 * @param file2
	 * @return
	 */
	private static double actualScore(Context_Structure file1, Context_Structure file2){
		if(file1 != null && file2 != null){

			if(file1.isEdited() && file2.isEdited()){
				return 1;
			} else if (file1.isEdited() || file2.isEdited()){ //only 1 is edited
				return 0.79;
			} else if(file1.isSelected() || file2.isSelected()){ //one of them is selected but not edited
				return 0.59;
			} else {
				return 0;
			}

		} else {
			return 0; //files does not exist in both task working set
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
}


