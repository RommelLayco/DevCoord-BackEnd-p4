package LIBSVM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		//create a list to store task pairs
		List<TaskPair> taskpairs = new ArrayList<TaskPair>();
		
		//read in pairs files;
		BufferedReader br = null;

		try {

			String currentLine;
			// get file
			File pairs = new File("input"+System.getProperty("file.separator")
			+"pairs_3_2.csv");

			br = new BufferedReader(new FileReader(pairs));
			//read first line which is titles so we can ignore.
			currentLine = br.readLine();

			while ((currentLine = br.readLine()) != null) {
				String[] line = currentLine.split(",");
				TaskPair tp = TaskPair.create(line);
				taskpairs.add(tp);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		System.out.println("Size of task pairs list: " + taskpairs.size());

	} //end main
}