package LIBSVM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import getTaskPairs.ProcessData;
import getTaskPairs.TaskPair;
import getTaskPairs.TaskPairKey;

/**
 * Class to write things to a file in the output folder.
 * @author Rommel
 *
 */
public class WritetoFile {



	/**
	 * If file already exist it will be replaced
	 * 
	 * @param data 
	 * @param folderName
	 * @param fileName name file
	 */
	public static void writeToFile(ProcessData data, String folderName, String fileName, boolean train){



		// get file
		File f = new File(folderName +System.getProperty("file.separator")
		+ fileName);


		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(f), "utf-8"))) {


			if(train){
				for(TaskPairKey key : data.getTrainKeys()){

					TaskPair tp = data.getTaskPairs().get(key);

					String line = createLine(tp);
					writer.write(line);
				}
			} else {
				for(TaskPairKey key : data.getTestKeys()){

					TaskPair tp = data.getTaskPairs().get(key);

					String line = createLine(tp);
					writer.write(line);
				}
			}


		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/**
	 * Helper method to format line;
	 * @param TaskPair tp
	 * @return
	 */
	private static String createLine(TaskPair tp){
		String line = "";

		int critical = convertBoolToInt(tp.isCritical());
		float pscore = tp.getPscore();
		int SLSM = tp.getSLSM();
		int AL = tp.getAL();
		int component = convertBoolToInt(tp.isSameComponent());
		int platform = convertBoolToInt(tp.isSamePlatform());
		int os = convertBoolToInt(tp.isSameOS());

		//create line
		line = line + critical + " 1:" + pscore;
		line = line + " 2:" + SLSM;
		line = line + " 3:" + AL;
		line = line + " 4:" + component;
		line = line + " 5:" + platform;
		line = line + " 6:" + os +"\n";

		return line;
	}

	/**
	 * Helper method to convert boolean to int
	 * @param b
	 * @return 1 if true or 0 if false
	 */
	private static int convertBoolToInt(Boolean b){
		if(b){
			return 1;
		} else {
			return 0;
		}
	}

}
