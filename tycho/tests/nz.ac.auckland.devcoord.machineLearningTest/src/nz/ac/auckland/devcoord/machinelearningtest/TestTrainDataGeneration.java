package nz.ac.auckland.devcoord.machinelearningtest;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import nz.ac.auckland.devcoord.machinelearning.decisionTree.InputEnum;
import nz.ac.auckland.devcoord.machinelearning.decisionTree.TrainDataGeneration;

/**
 * Tests whether train data is being generated properly
 * */
public class TestTrainDataGeneration {
	static String trainFile;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		trainFile= InputEnum.outputToString(InputEnum.TRAIN_OUTPUT_PATH);
		FileUtility.deleteFile(trainFile);
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
		System.err.println(FileUtility.fileExists(trainFile));
		assertFalse(FileUtility.fileExists(trainFile));
		TrainDataGeneration.convertTrainCSVToArff();
		assertTrue(FileUtility.fileExists(trainFile));
	}
}
