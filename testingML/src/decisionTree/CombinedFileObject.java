package decisionTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Credit:http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * 
 * */
public class CombinedFileObject {
	
	protected ArrayList<CombinedRow> entries;
	
	
	public CombinedFileObject(){
		
entries=new ArrayList<CombinedRow>();

		String csvFile = "input/combined.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			        // use comma as separator
				String[] entry = line.split(cvsSplitBy);

				if (!entry[0].equals("id")) {
					
					CombinedRow row=new CombinedRow(entry);
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
		
		for (CombinedRow combinedRow : entries) {
			combinedRow.display();
		}
		
		
	}

}
