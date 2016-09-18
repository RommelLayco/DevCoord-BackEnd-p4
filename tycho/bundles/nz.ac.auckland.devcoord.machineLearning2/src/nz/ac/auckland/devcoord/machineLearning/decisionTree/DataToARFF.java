package nz.ac.auckland.devcoord.machineLearning.decisionTree;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import nz.ac.auckland.devcoord.machineLearning.trainData.ProcessData;
import nz.ac.auckland.devcoord.machineLearning.trainData.TaskPair;
import nz.ac.auckland.devcoord.machineLearning.trainData.TaskPairKey;
/**
 * Used for conversion of ProcessData Object to ARFF files(Weka readable)
 * 
 * */
public class DataToARFF {
	/**
	 * Used for making the arff file for the train data
	 * to an arff file.
	 * */
	public static void convertProcessDataToArff(ProcessData data,InputEnum inputEnum){
		Writer writer = null;
		String inputString;
		inputString=InputEnum.outputToString(inputEnum);
		FileOutputStream fileOutputStream = null;
		try {
			 fileOutputStream=new FileOutputStream(inputString);
		}
		catch (FileNotFoundException e) {
			
			try {
				fileOutputStream=new FileOutputStream(InputEnum.toString(InputEnum.WORKSPACE)+System.getProperty("file.separator")+inputString);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream, "utf-8"); 
			writer = new BufferedWriter(outputStreamWriter);
			writer.write(getLabels());
			writer.write("@data"+"\n");
			int countOfBadData=0;
			List<TaskPairKey> keys;
			if (true) {
				keys=data.getTestKeys();//USING TEST KEYS HERE FOR TRAINING
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
			fileOutputStream.close();
			outputStreamWriter.close();
			//System.out.println("number of bad entries:"+countOfBadData);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e1) {
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
				"@attribute SameComponent {true,false}"+"\n"+
				"@attribute SamePlatform {true,false}"+"\n"+
				"@attribute SameOS {true,false}"+"\n"+

				"@attribute Critical {true,false}"+"\n"+""
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
		String component=""+taskPairs.get(taskPairKey).isSameComponent();
		String platform=""+taskPairs.get(taskPairKey).isSamePlatform();
		String os=""+taskPairs.get(taskPairKey).isSameOS();
		
		return proximity+","
		+component+","
		+platform+","
		+os+","
		+ critical;
	}
}