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
	
		makeTrainArff(data, true);
		makeTestArff(data, true);



	}
	
	protected static void makeTrainArff(ProcessData data,boolean withDRH) throws IOException{
		Writer writer = null;




		writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(InputEnum.outputToString(InputEnum.PAIRS_3_2_Train_Output)), "utf-8"));
	

		writer.write(getLabels(true));
		writer.write("@data"+"\n");


		int countOfBadData=0;

		List<TaskPairKey> keys=data.getTrainKeys();   //TODO
		


		for (TaskPairKey taskPairKey : keys) {
			try{
				writer.write(getStringOfTheFollowingTaskPairKey(taskPairKey, data, true));
				writer.write("\n");

			}
			catch(NullPointerException n){

				countOfBadData+=1;
			}

		}

		writer.close();


		System.out.println("number of bad entries:"+countOfBadData);
		
		
	}
	
	protected static void makeTestArff(ProcessData data,boolean withDRH) throws IOException{
		
		Writer writer = null;
	writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(InputEnum.outputToString(InputEnum.PAIRS_3_2_Test_Output)), "utf-8"));

		writer.write(getLabels(true));
		writer.write("@data"+"\n");

		int countOfBadData=0;

		List<TaskPairKey> keys=data.getTestKeys();  

		for (TaskPairKey taskPairKey : keys) {
			
			try{
			writer.write(getStringOfTheFollowingTaskPairKey(taskPairKey, data, true));
				writer.write("\n");

			}
			catch(NullPointerException n){

				countOfBadData+=1;

			}

		}

		writer.close();
		System.out.println("number of bad entries:"+countOfBadData);
	
	}


	protected static String getLabels(boolean withDRH){
		
		String toReturn="@relation pairsTask"+"\n"+"\n"+"\n"+
		"@attribute Proximity numeric"+"\n"+
				"@attribute SLSMS numeric"+"\n"+
		"@attribute ALS numeric"+"\n"+
"@attribute Critical {true,false}"+"\n"+"\n";
		return toReturn;
		
		
	}
	
	protected static String getStringOfTheFollowingTaskPairKey(TaskPairKey taskPairKey,ProcessData processData,boolean withDRH){
		Map<TaskPairKey, TaskPair> taskPairs=processData.getTaskPairs();
		float proximity=taskPairs.get(taskPairKey).getPscore();
		int sLMS=taskPairs.get(taskPairKey).getSLSM();
		int aLS=taskPairs.get(taskPairKey).getAL();
		String critical=""+taskPairs.get(taskPairKey).isCritical();
		
		return proximity+","
				+ +sLMS+","+aLS+","+critical;
		
		
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