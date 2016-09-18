package nz.ac.auckland.devcoord.machinelearningtest;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import nz.ac.auckland.devcoord.machinelearning.decisionTree.DataToARFF;
import nz.ac.auckland.devcoord.machinelearning.decisionTree.InputEnum;
import nz.ac.auckland.devcoord.machinelearning.decisionTree.TrainDataGeneration;
import nz.ac.auckland.devcoord.machinelearning.trainData.ProcessData;
import weka.core.Instances;
/**
 * Tests conversion of train data to arff file
 * */
public class TestDataToArff {
	static ProcessData processData;
	static String filePath;
	static InputEnum inputEnum;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		inputEnum=InputEnum.UNIT_TEST_TRAIN_PATH;
		filePath=InputEnum.outputToString(inputEnum);
		FileUtility.deleteFile(filePath);
		processData=TrainDataGeneration.convertCSVToProcessData();
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
	}
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testProcessDataToArffConversion() {
		assertFalse(FileUtility.fileExists(filePath));	
		DataToARFF.convertProcessDataToArff(processData, inputEnum);	
		Instances arffInstances=readARFFAndGetAllInstances();
		int numberOfArffInstances=arffInstances.numInstances();
		int numberOfProcessDataInstances=processData.getTestKeys().size();
		boolean areEqual=numberOfArffInstances==numberOfProcessDataInstances;
		assertTrue(areEqual);//Number of instances in processdata and arff file are equal
		assertTrue(FileUtility.fileExists(filePath));
	}
	
	/*Helper method*/
	private static Instances readARFFAndGetAllInstances(){
		Instances toReturn;
		try {
			FileReader fileReader=new FileReader(filePath);
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			toReturn = new Instances(bufferedReader);
			toReturn.setClassIndex(toReturn.numAttributes() - 1);
			bufferedReader.close();
			return toReturn;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
