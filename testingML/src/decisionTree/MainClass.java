package decisionTree;

import decisionTree.DescriptionEnum.description;

public class MainClass {

	public static void main(String[] args) {


		CombinedFileObject fo=new CombinedFileObject();

		CombinedCsvToArffConverter.convert("input/combined.csv");
		
		
		MakeTree.make();
		
		
		
		System.out.println("Done");
		
		
	}
}
