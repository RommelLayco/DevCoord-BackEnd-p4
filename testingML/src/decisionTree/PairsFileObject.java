package decisionTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Used to make an object out of the pairs file.
 * (not currently used)
 * */
public class PairsFileObject {
protected ArrayList<PairsRow> entries;
	
	
	public PairsFileObject(){
		
entries=new ArrayList<PairsRow>();

		String csvFile = "input/pairs_3_2.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			        // use comma as separator
				String[] entry = line.split(cvsSplitBy);

				if (!entry[0].equals("Task1")) {
					
					PairsRow row=new PairsRow(entry);
					entries.add(row);
				}

				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		

		
			
		
	}
	
	public void display(){
		
		for (PairsRow pairsRow: entries) {
			pairsRow.display();
		}
		
		
	}
}
