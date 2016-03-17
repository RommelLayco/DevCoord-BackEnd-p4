package getTaskPairs;

public enum Platform {
	PC, ALL, OTHER, MACINTOSH, SUN;

	/**
	 * Method to get enum from string
	 * @param word a string
	 * @return  platform enum
	 */
	public static Platform platformEnum(String word){
		if(word.equals("PC")){
			return PC;
		} else if(word.equals("All")){
			return ALL;
		} else if(word.equals("Other")){
			return OTHER;
		} else if(word.equals("Macintosh")){
			return MACINTOSH;
		} else if(word.equals("Sun")){
			return SUN;
		} else{
			return null;
		}
				
	}
}
