package LIBSVM;

public enum Platform {
	PC, ALL, OTHER, MACINTOSH, SUN;

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
