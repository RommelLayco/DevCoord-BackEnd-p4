package nz.ac.auckland.devcoord.machinelearningtest;

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
		Task two=new Task(2, "Dummy Handle two", "Dummy label two");
		Task three=new Task(3, "Dummy Handle one", "Dummy label three");

		//context_StructureOne and context_StructureTwo have the same handle
		Context_Structure context_StructureOne=new Context_Structure("ContextOne", false, true);
		Context_Structure context_StructureTwo=new Context_Structure("ContextOne", true, false);
		Context_Structure context_StructureThree=new Context_Structure("ContextThree", false, true);
		
		one.addContextStructure(context_StructureOne.getName(), context_StructureOne);
		two.addContextStructure(context_StructureTwo.getName(), context_StructureTwo);
		three.addContextStructure(context_StructureThree.getName(), context_StructureThree);

		//Since Task one and two both have the same context stucture,
		//the classifier shoud classify them as critical
		TaskPair taskPairOne=new TaskPair(one,two);
		TaskPair taskPairTwo=new TaskPair(one,three);
		TaskPair taskPairThree=new TaskPair(three,two);
		
		//Initially,criticality is set to false for all TaskPair objects
		taskPairOne.setCritical(false);
		taskPairTwo.setCritical(false);
		taskPairThree.setCritical(false);
		
		//Proximity scores are calculated for each pair
		//taskPairOne.calcProximityScore();taskPairTwo.calcProximityScore();taskPairThree.calcProximityScore();

		//Task pairs should not be critical prior to the classification
		assertFalse(taskPairOne.isCritical());
		assertFalse(taskPairTwo.isCritical());
		assertFalse(taskPairThree.isCritical());


		//Task pairs have their correct criticality filled in
		List<TaskPair> taskPairs=new ArrayList<TaskPair>();
		taskPairs.add(taskPairOne);taskPairs.add(taskPairTwo);taskPairs.add(taskPairThree);
		taskPairs=CriticalityUtility.fillInCriticality(taskPairs);

		//After correcting the criticality,only the first TaskPair should have be critical.
		assertTrue(taskPairOne.isCritical());
		assertFalse(taskPairTwo.isCritical());
		assertFalse(taskPairThree.isCritical());

	}

}
