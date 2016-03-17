package decisionTree;

public class DescriptionEnum {
	
	
	
	public enum description{
		NO,NOT,SOME,SOMEWHAT,POSSIBLE,
		SIGNIFICANT,VERY,DEFINATELY
		
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
	case "Definately":
		return description.DEFINATELY;

	

	
		
	}
	return null;
	
	
	
}

}
