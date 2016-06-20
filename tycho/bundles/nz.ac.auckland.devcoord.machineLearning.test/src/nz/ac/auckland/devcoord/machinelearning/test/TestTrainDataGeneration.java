package nz.ac.auckland.devcoord.machinelearning.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import nz.ac.auckland.devcoord.machinelearning.decisionTree.InputEnum;
import nz.ac.auckland.devcoord.machinelearning.decisionTree.TrainDataGeneration;

public class TestTrainDataGeneration {
	static String trainFile;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		trainFile= InputEnum.outputToString(InputEnum.TRAIN_OUTPUT_PATH);
		deleteFile(trainFile);
		
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
	public void testTrainArffGeneration() {
		assertFalse(fileExists(trainFile));

		TrainDataGeneration.convertTrainCSVToArff();
		assertTrue(fileExists(trainFile));
	}

	
	private static boolean fileExists(String path){
		
		
		File file = new File(path);
    	
	return	file.exists();
		
	}
	
	private static void deleteFile(String path){
	
		
		File file = new File(path);
    	
		file.delete();
		
		
		
	}
	
}
