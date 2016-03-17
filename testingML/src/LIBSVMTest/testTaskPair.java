package LIBSVMTest;

import static org.junit.Assert.*;

import org.junit.Test;

import LIBSVM.TaskPair;

public class testTaskPair {

	@Test
	public void testCreate() {
		String[] line = {"228910","279316",	"14.8614046", "0","8", "2"};

		
		TaskPair tp = TaskPair.create(line);
		
		
		assertEquals(228910, tp.getT1());
		assertEquals(279316, tp.getT2());
		assertEquals(14.8614046f, tp.getPscore(), 0.00000002);
		assertEquals(8, tp.getSLSM());
		assertEquals(2, tp.getAL());
		
	}

}
