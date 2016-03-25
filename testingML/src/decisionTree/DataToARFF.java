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

	public static void convert(ProcessData data,InputEnum inputEnum,boolean withDRH){



		try {


			switch(inputEnum){
			case PAIRS_3_2:{makeArff(data, withDRH,true);
			makeArff(data, withDRH,false);};
			case ACCURACY_CODING:convertAccuracy(data);
			case TASKS_3_2:convertTasks(data);
			}

		} catch (IOException e) {

			e.printStackTrace();

		}


		System.out.println("Arff file made");
	}


	
	protected static void makeArff(ProcessData data,boolean withDRH,boolean train) throws IOException{
		Writer writer = null;

		String inputString;
		
		if (withDRH) {
			if (train) {
				inputString=InputEnum.outputToString(InputEnum.PAIRS_3_2_Train_Output_DRH);
			} else {
				inputString=InputEnum.outputToString(InputEnum.PAIRS_3_2_Test_Output_DRH);
			}
			
		} else {
			if (train) {
				inputString=InputEnum.outputToString(InputEnum.PAIRS_3_2_Train_Output_NODRH);
			} else {
				inputString=InputEnum.outputToString(InputEnum.PAIRS_3_2_Test_Output_NODRH);
			}	
		}


		writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(inputString), "utf-8"));
	

		writer.write(getLabels(withDRH));
		writer.write("@data"+"\n");


		int countOfBadData=0;

		List<TaskPairKey> keys;
		if (train) {
			keys=data.getTrainKeys();
		} else {
			keys=data.getTestKeys();
		}
		   //TODO
		


		for (TaskPairKey taskPairKey : keys) {
			try{
				writer.write(getStringOfTheFollowingTaskPairKey(taskPairKey, data, withDRH));
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
		String toReturn;
		if (withDRH) {
			toReturn="@relation pairsTask"+"\n"+"\n"+"\n"+
					"@attribute Proximity numeric"+"\n"+
							"@attribute SLSMS numeric"+"\n"+
					"@attribute ALS numeric"+"\n"+
			"@attribute Critical {true,false}"+"\n"+""
					+ "@attribute Component {true,false}"+"\n"
					+ "@attribute Platform {true,false}"+"\n"
					+ "@attribute OS {true,false}"+"\n"
					+ "\n";
		} else {

			toReturn="@relation pairsTask"+"\n"+"\n"+"\n"+
					"@attribute Proximity numeric"+"\n"+
							
			"@attribute Critical {true,false}"+"\n"+""
					+ "@attribute Component {true,false}"+"\n"
					+ "@attribute Platform {true,false}"+"\n"
					+ "@attribute OS {true,false}"+"\n"
					+ "\n";
			
		}
		

		return toReturn;
		
		
	}
	
	protected static String getStringOfTheFollowingTaskPairKey(TaskPairKey taskPairKey,ProcessData processData,boolean withDRH){
		Map<TaskPairKey, TaskPair> taskPairs=processData.getTaskPairs();
		float proximity=taskPairs.get(taskPairKey).getPscore();
		String critical=""+taskPairs.get(taskPairKey).isCritical();
		boolean component=taskPairs.get(taskPairKey).isSameComponent();
		boolean platform=taskPairs.get(taskPairKey).isSamePlatform();
		boolean oS=taskPairs.get(taskPairKey).isSameOS();
		
		if (withDRH) {
			int sLMS=taskPairs.get(taskPairKey).getSLSM();
			int aLS=taskPairs.get(taskPairKey).getAL();
			
			return proximity+","
			+ +sLMS+","+aLS+","+critical+","+component+","+platform+","+oS;
	
		} else {
			
			return proximity+","
					+ critical+","+component+","+platform+","+oS;
			

		}
		
		
		
		
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