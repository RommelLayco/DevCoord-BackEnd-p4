package LIBSVMTest;

import static org.junit.Assert.*;

import org.junit.Test;

import LIBSVM.Component;
import LIBSVM.OS;
import LIBSVM.Platform;
import LIBSVM.Task;

public class TestTask {

	/**
	 * Test to see if task object is initialized correctly
	 */
	@Test
	public void testCreate() {
		String[] line = {"164221","11/11/2006 12:11", "5/01/2008 17:17",
				"3/03/2010 21:52", "Frank",	"RESOLVED",	"0",
				"671.1909722", "P3", "PC", "Windows XP", "Mylyn", "Bugzilla", "enhancement"};
	
		Task task = Task.create(line);
		
		assertEquals(164221, task.getTaskID());
		assertEquals(Platform.PC, task.getPlatform());
		assertEquals(OS.WINDOWS_XP, task.getOS());
		assertEquals(Component.BUGZILLA, task.getComponent());
		
	}

}
