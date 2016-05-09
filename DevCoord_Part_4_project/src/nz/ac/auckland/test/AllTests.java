package nz.ac.auckland.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Database.class, ReadXMLTest.class, TaskPairTest.class })

public class AllTests {

}
