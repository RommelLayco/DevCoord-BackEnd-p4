package decisionTree;

import decisionTree.DescriptionEnum.description;

public class MainClass {

	public static void main(String[] args) {


		PairsFileObject fo=new PairsFileObject();
		
	//	fo.display();

		
		PairsCSVtoARFFconverter.convert();
		//CombinedCsvToArffConverter.convert("input/combined.csv");
		
		
	//	MakeTree.make("input/pairs.arff");
		
		
		
		System.out.println("Done");
		
		
	}
}
