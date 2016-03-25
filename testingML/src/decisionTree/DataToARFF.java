package decisionTree;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import getTaskPairs.ProcessData;
import getTaskPairs.TaskPair;
import getTaskPairs.TaskPairKey;
import weka.gui.ConverterFileChooser;

public class DataToARFF {

	public static void convert(ProcessData data,InputEnum inputEnum){



		try {


			switch(inputEnum){
			case PAIRS_3_2:convertPairs(data);
			case ACCURACY_CODING:convertAccuracy(data);
			case TASKS_3_2:convertTasks(data);
			}

		} catch (IOException e) {

			e.printStackTrace();

		}








		System.out.println("Arff file made");
	}


	private static void convertPairs(ProcessData data) throws IOException{
		Writer writer = null;




		writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(InputEnum.outputToString(InputEnum.PAIRS_3_2)), "utf-8"));
		writer.write("@relation pairsTask"+"\n"+"\n"+"\n");





		writer.write("@attribute Proximity numeric"+"\n");

		writer.write("@attribute SLSMS numeric"+"\n");
		writer.write("@attribute ALS numeric"+"\n");
		writer.write("@attribute Critical {true,false}"+"\n");


		writer.write("\n");

		writer.write("@data"+"\n");


		int countOfBadData=0;

		List<TaskPairKey> keys=data.getTestKeys();   //TODO
		Map<TaskPairKey, TaskPair> taskPairs=data.getTaskPairs();


		for (TaskPairKey taskPairKey : keys) {

			float proximity=taskPairs.get(taskPairKey).getPscore();
			int sLMS=taskPairs.get(taskPairKey).getSLSM();
			int aLS=taskPairs.get(taskPairKey).getAL();
			String critical=""+taskPairs.get(taskPairKey).isCritical();

			try{


				
				writer.write(proximity+","
						+ +sLMS+","+aLS+","+critical);
				writer.write("\n");

			}
			catch(NullPointerException n){

				countOfBadData+=1;




			}




		}

		writer.close();


		System.out.println("number of bad entries:"+countOfBadData);






	}


	private static void convertTasks(ProcessData data){

		/**
		 * TODO when needed
		 * */

	}


	private static void convertAccuracy(ProcessData data){

		/**
		 * TODO when needed
		 * */


	}
}