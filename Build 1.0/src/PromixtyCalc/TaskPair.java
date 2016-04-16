package PromixtyCalc;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Class used to calculate two task proximity score
 * @author Rommel
 *
 */
public class TaskPair {

	private int taskID1;
	private int taskID2;
	private String handle1;
	private String handle2;
	private double potentialScore;
	private double actualScore;
	private double proximityScore;

	public TaskPair(int taskID1, int taskID2, String handle1, String handle2){
		this.taskID1 = taskID1;
		this.taskID2 = taskID2;
		this.handle1 = handle1;
		this.handle2 = handle2;
		this.potentialScore = 0;
		this.actualScore = 0;
		this.proximityScore = 0;
	}
	

	/**
	 * Method to calculate the proximity score 
	 * @param files1
	 * @param files2
	 */
	public void calcProximityScore(Map<String, JavaFile> files1,
			Map<String, JavaFile> files2){
		
		//calculate the actual and potential scores
		this.calcPotentialScore(files1, files2);
		this.calcActualScore(files1, files2);
		
		
		this.proximityScore = this.actualScore / this.potentialScore;
	}
	
	/**
	 * Calculate the potential score of the two task
	 * i.e. union of the java files map
	 * @param files1
	 * @param files2
	 */
	private void calcPotentialScore(Map<String, JavaFile> files1, 
			Map<String, JavaFile> files2){

		double potentialScore = 0;
		Iterator<String> keys = combineKeys(files1, files2);

		while(keys.hasNext()){
			String key = keys.next(); //get the two java files
			JavaFile file1 = files1.get(key);
			JavaFile file2 = files2.get(key);
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
	private void calcActualScore(Map<String, JavaFile> files1, 
			Map<String, JavaFile> files2){

		double actualScore = 0;
		Iterator<String> keys = combineKeys(files1, files2);

		while(keys.hasNext()){
			String key = keys.next(); //get the two java files
			JavaFile file1 = files1.get(key);
			JavaFile file2 = files2.get(key);
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
	private static double potentialScore(JavaFile file1, JavaFile file2){

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
	private static double actualScore(JavaFile file1, JavaFile file2){
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
	private static Iterator<String> combineKeys(Map<String, JavaFile> files1, 
			Map<String, JavaFile> files2){

		Set<String> set1 = files1.keySet();
		Set<String> set2 = files2.keySet();
		Set<String> combinedSet = new HashSet<String>();
		
		combinedSet.addAll(set1);
		combinedSet.addAll(set2);

		Iterator<String> it = combinedSet.iterator();

		return it; 

	}

	public double getProximityScore(){
		return this.proximityScore;
	}
	
	public int getID1(){
		return this.taskID1;
	}
	
	public int getID2(){
		return this.taskID2;
	}
	
	public String getHandle1(){
		return this.handle1;
	}
	
	public String getHandle2(){
		return this.handle2;
	}




}
