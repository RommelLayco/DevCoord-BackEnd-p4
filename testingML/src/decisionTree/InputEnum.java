package decisionTree;

public enum InputEnum {

	ACCURACY_CODING,PAIRS_3_2,PAIRS_3_2_Train_Output_DRH,PAIRS_3_2_Train_Output_NODRH,
	PAIRS_3_2_Test_Output_DRH,PAIRS_3_2_Test_Output_NODRH,
	TASKS_3_2,WEKA_DECISION_TREE_REPORT_DRH,WEKA_DECISION_TREE_REPORT_NODRH;
	
	
	
	public static String toString(InputEnum inputEnum){
		
		switch(inputEnum){
		case ACCURACY_CODING:return "input/accuracy_coding.csv";
		case PAIRS_3_2:return "input/pairs_3_2.csv";
		case TASKS_3_2:return "input/tasks_3_2.csv";
		case WEKA_DECISION_TREE_REPORT_DRH:return "output/decision_tree_report_DRH.txt";
		case WEKA_DECISION_TREE_REPORT_NODRH:return "output/decision_tree_report_NODRH.txt";
		
		case PAIRS_3_2_Train_Output_NODRH:return "output/train_NODRH.arff";
		case PAIRS_3_2_Test_Output_NODRH:return "output/test_NODRH.arff";
		case PAIRS_3_2_Train_Output_DRH:return "output/train_DRH.arff";
		case PAIRS_3_2_Test_Output_DRH:return "output/test_DRH.arff";
		
		}
			
		return null;
		
		
	}
	
	
	public static String outputToString(InputEnum inputEnum){
		
		switch(inputEnum){
		case ACCURACY_CODING:return "output/accuracy_coding.arff";
		case PAIRS_3_2:return "output/pairs_3_2.arff";
		case TASKS_3_2:return "output/tasks_3_2.arff";
		case WEKA_DECISION_TREE_REPORT_DRH:return "output/decision_tree_report_DRH.txt";
		case WEKA_DECISION_TREE_REPORT_NODRH:return "output/decision_tree_report_NODRH.txt";
		case PAIRS_3_2_Train_Output_NODRH:return "output/train_NODRH.arff";
		case PAIRS_3_2_Test_Output_NODRH:return "output/test_NODRH.arff";
		case PAIRS_3_2_Train_Output_DRH:return "output/train_DRH.arff";
		case PAIRS_3_2_Test_Output_DRH:return "output/test_DRH.arff";		
		
		
		}
			
		return null;
		
		
	}
	
}
