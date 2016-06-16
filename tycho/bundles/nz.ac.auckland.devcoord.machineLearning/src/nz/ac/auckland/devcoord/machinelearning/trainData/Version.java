package nz.ac.auckland.devcoord.machinelearning.trainData;

/**
 * Enum to help split data into release version
 * 3.1 and 3.2
 * 
 * 3.1 is used to train the ML
 * 3.2 is used to test in the ML
 * 
 * Enum will be used to create to seperate key list for 3.1 and 3.2
 * @author Rommel
 *
 */
public enum Version {
		TRAIN, TEST;
	

	/**
	 * Method to get enum from string
	 * @param word a string
	 * @return a VERSION enum
	 */
	public static Version versionEnum(String word){
		if(word.equals("3.2")){
			return TEST;
		} else if(word.equals("3.1")){
			return TRAIN;
		} else{
			return null;
		}
				
	}
}
