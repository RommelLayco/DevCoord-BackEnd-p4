package nz.ac.auckland.devcoord.machinelearning.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCriticalUtility.class, TestDataToArff.class, TestTrainDataGeneration.class })
public class AllTests {

}
