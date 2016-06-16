package nz.ac.auckland.devcoord.machinelearning.decisionTree;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import nz.ac.auckland.devcoord.machinelearning.getTaskPairs.ProcessData;
import nz.ac.auckland.devcoord.machinelearning.getTaskPairs.TaskPair;
import nz.ac.auckland.devcoord.machinelearning.getTaskPairs.TaskPairKey;
/**
 * Used for conversion of CSV files provided to ARFF files(Weka readable)
 * 
 * */
public class DataToARFF {
	
	
	/**
	 * Used for making the arff file for the train data
	 * to an arff file.
	 * */
	public static void convertTrainData(ProcessData data,InputEnum inputEnum){
	
		


		Writer writer = null;

		String inputString;



		inputString=InputEnum.outputToString(InputEnum.PAIRS_3_2_Train_Output_NODRH);



		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(inputString), "utf-8"));
			

			writer.write(getLabels());
			writer.write("@data"+"\n");


			int countOfBadData=0;

			List<TaskPairKey> keys;
			if (true) {
				keys=data.getTrainKeys();
			}
			for (TaskPairKey taskPairKey : keys) {
				try{
					writer.write(getStringOfTheFollowingTaskPairKey(taskPairKey, data));
					writer.write("\n");

				}
				catch(NullPointerException n){

					countOfBadData+=1;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			writer.close();
			System.out.println("number of bad entries:"+countOfBadData);

			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		
		
		
	} 


	public static void convertTestData(TaskPair taskPair){
		

		Writer writer = null;

		String inputString;



		inputString=InputEnum.outputToString(InputEnum.PAIRS_3_2_Test_Output_NODRH);

		
		try{
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(inputString), "utf-8"));
			

			writer.write(getLabels());
			writer.write("@data"+"\n");
			writer.write(getStringOfTheTestTaskPair(taskPair));
			writer.write("\n");
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}




	/**
	 * Helper method- creates a label which is written in the arff file,depending on the arguments,
	 * it includes or excludes DRH attributes from the label.
	 * **/
	protected static String getLabels(){
		String toReturn;


		toReturn="@relation pairsTask"+"\n"+"\n"+"\n"+
				"@attribute Proximity numeric"+"\n"+

			"@attribute Critical {true,false}"+"\n"+""
			+ "@attribute Component {true,false}"+"\n"
			+ "@attribute Platform {true,false}"+"\n"
			+ "@attribute OS {true,false}"+"\n"
			+ "\n";




		return toReturn;


	}

	/**
	 * Helper method-creates a string that contains information about a pair of task.
	 * this string is returned,which is then written directly into the arff file(not be this method). 
	 * 
	 * */
	protected static String getStringOfTheFollowingTaskPairKey(TaskPairKey taskPairKey,ProcessData processData){
		Map<TaskPairKey, TaskPair> taskPairs=processData.getTaskPairs();
		float proximity=taskPairs.get(taskPairKey).getPscore();
		String critical=""+taskPairs.get(taskPairKey).isCritical();
		boolean component=taskPairs.get(taskPairKey).isSameComponent();
		boolean platform=taskPairs.get(taskPairKey).isSamePlatform();
		boolean oS=taskPairs.get(taskPairKey).isSameOS();



		return proximity+","
		+ critical+","+component+","+platform+","+oS;







	}

	protected static String getStringOfTheTestTaskPair(TaskPair taskPair){
		//Map<TaskPairKey, TaskPair> taskPairs=processData.getTaskPairs();
		float proximity=taskPair.getPscore();
		String critical=""+taskPair.isCritical();
		boolean component=taskPair.isSameComponent();
		boolean platform=taskPair.isSamePlatform();
		boolean oS=taskPair.isSameOS();



		return proximity+","
		+ critical+","+component+","+platform+","+oS;







	}
	
	

}