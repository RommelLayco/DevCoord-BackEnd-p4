package decisionTree;

public class DescriptionEnum {
	
	
	
	public enum description{
		NO,NOT,SOME,SOMEWHAT,POSSIBLE,
		SIGNIFICANT,VERY,DEFINITELY
	}
	public static String getValueOf(description desc){
		
		switch(desc){
		
		case NO:
			return "No";
		case NOT:
			return "Not";
		case SOME:
			return "Some";
		case SOMEWHAT:
			return "Somewhat";
		case POSSIBLE:
			return "Possible";
		case SIGNIFICANT:
			return "Significant";
		case VERY:
			return "Very";
		case DEFINITELY:
			return "Definitely";

		

		
			
		}
		return null;
		
		
		
	}
	
public static description parse(String input){
	
	switch(input){
	
	case "No":
		return description.NO;
	case "Not":
		return description.NOT;
	case "Some":
		return description.SOME;
	case "Somewhat":
		return description.SOMEWHAT;
	case "Possible":
		return description.POSSIBLE;
	case "Significant":
		return description.SIGNIFICANT;
	case "Very":
		return description.VERY;
	case "Definitely":
		return description.DEFINITELY;

	

	
		
	}
	return null;
	
	
	
}

}
