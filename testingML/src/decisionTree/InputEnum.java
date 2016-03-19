package decisionTree;

public enum InputEnum {

	ACCURACY_CODING,PAIRS_3_2,TASKS_3_2;
	
	
	
	public static String toString(InputEnum inputEnum){
		
		switch(inputEnum){
		case ACCURACY_CODING:return "input/accuracy_coding.csv";
		case PAIRS_3_2:return "input/pairs_3_2.csv";
		case TASKS_3_2:return "input/tasks_3_2.csv";
				
		
		
		}
			
		return null;
		
		
	}
	
	
	public static String outputToString(InputEnum inputEnum){
		
		switch(inputEnum){
		case ACCURACY_CODING:return "output/accuracy_coding.arff";
		case PAIRS_3_2:return "output/pairs_3_2.arff";
		case TASKS_3_2:return "output/tasks_3_2.arff";
				
		
		
		}
			
		return null;
		
		
	}
	
}
