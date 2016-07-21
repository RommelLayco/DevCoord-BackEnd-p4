package nz.ac.auckland.devcoord.machinelearningtest;

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
		
		System.err.println("======================================");
		
		  
		
		FileUtility.deleteFile(trainFile);
		
		System.err.println("========================================");
		System.err.println("Actual file deletion location: " + trainFile);

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

		System.err.println("======================================");
		
		System.err.println("Working Directory of where the test is executed = " +
				System.getProperty("user.dir"));

		assertFalse(FileUtility.fileExists(trainFile));

		TrainDataGeneration.convertTrainCSVToArff();
		assertTrue(FileUtility.fileExists(trainFile));
	}




}
