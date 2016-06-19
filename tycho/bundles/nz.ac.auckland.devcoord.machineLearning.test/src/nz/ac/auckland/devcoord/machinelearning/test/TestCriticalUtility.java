package nz.ac.auckland.devcoord.machinelearning.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import nz.ac.auckland.devcoord.database.Context_Structure;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;
import nz.ac.auckland.devcoord.machinelearning.decisionTree.*;
import nz.ac.auckland.devcoord.machinelearning.testData.CriticalityUtility;

public class TestCriticalUtility {


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TrainDataGeneration.convertTrainCSVToArff();

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
	public void testFillInCriticality() {


		Task one=new Task(1, "Dummy Handle one", "Dummy label one");

		Context_Structure context_StructureOne=new Context_Structure("ContextOne", false, true);
		one.addContextStructure(context_StructureOne.getName(), context_StructureOne);

		Task two=new Task(2, "Dummy Handle two", "Dummy label two");

		Context_Structure context_StructureTwo=new Context_Structure("ContextOne", true, false);
		two.addContextStructure(context_StructureTwo.getName(), context_StructureTwo);

		Task three=new Task(3, "Dummy Handle one", "Dummy label three");

		Context_Structure context_StructureThree=new Context_Structure("ContextThree", false, true);
		three.addContextStructure(context_StructureThree.getName(), context_StructureThree);

		TaskPair taskPairOne=new TaskPair(one,two);
		taskPairOne.setCritical(false);
		TaskPair taskPairTwo=new TaskPair(one,three);
		taskPairTwo.setCritical(false);
		TaskPair taskPairThree=new TaskPair(three,two);
		taskPairThree.setCritical(false);

		taskPairOne.calcProximityScore();taskPairTwo.calcProximityScore();taskPairThree.calcProximityScore();

		//Initially,criticality is set to false for all TaskPair objects
		assertFalse(taskPairOne.isCritical());
		assertFalse(taskPairTwo.isCritical());
		assertFalse(taskPairThree.isCritical());



		List<TaskPair> taskPairs=new ArrayList<TaskPair>();
		taskPairs.add(taskPairOne);taskPairs.add(taskPairTwo);taskPairs.add(taskPairThree);

		taskPairs=CriticalityUtility.fillInCriticality(taskPairs);

		//After correcting the criticality,only the first TaskPair should have be critical.
		assertTrue(taskPairOne.isCritical());
		assertFalse(taskPairTwo.isCritical());
		assertFalse(taskPairThree.isCritical());



	}

}
