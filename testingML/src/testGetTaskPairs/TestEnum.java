package testGetTaskPairs;

import static org.junit.Assert.*;

import org.junit.Test;

import getTaskPairs.Component;
import getTaskPairs.OS;
import getTaskPairs.Platform;

public class TestEnum {

	@Test
	public void testPlatform(){
		assertEquals(Platform.PC, Platform.platformEnum("PC"));
		assertEquals(Platform.ALL, Platform.platformEnum("All"));
		assertEquals(Platform.OTHER, Platform.platformEnum("Other"));
		assertEquals(Platform.MACINTOSH, Platform.platformEnum("Macintosh"));
		assertEquals(Platform.SUN, Platform.platformEnum("Sun"));
		assertNull(Platform.platformEnum("sdvdhlfj"));
	}
	
	@Test
	public void testOS(){
		assertEquals(OS.ALL, OS.osEnum("All"));
		assertEquals(OS.WINDOWS_XP, OS.osEnum("Windows XP"));
		assertEquals(OS.WINDOWS_VISTA, OS.osEnum("Windows Vista"));
		assertEquals(OS.WINDOWS_SERVER, OS.osEnum("Windows Server 2003"));
		assertEquals(OS.WINDOWS7, OS.osEnum("Windows 7"));
		assertEquals(OS.LINUX, OS.osEnum("Linux"));
		assertEquals(OS.MAC, OS.osEnum("Mac OS X"));
		assertEquals(OS.MAC_COCOA, OS.osEnum("Mac OS X - Cocoa"));
		assertNull(OS.osEnum("dslkfl"));
	}
	
	@Test
	public void testComponent(){
		assertEquals(Component.BUGZILLA, Component.componentEnum("Bugzilla"));
		assertEquals(Component.UI, Component.componentEnum("UI"));
		assertEquals(Component.JIRA, Component.componentEnum("Jira"));
		assertEquals(Component.TASK, Component.componentEnum("Tasks"));
		assertEquals(Component.CORE, Component.componentEnum("Core"));
		assertEquals(Component.TRAC, Component.componentEnum("Trac"));
		assertEquals(Component.JAVA, Component.componentEnum("Java"));
		assertEquals(Component.WIKITEXT, Component.componentEnum("Wikitext"));
		assertEquals(Component.FRAMEWORK, Component.componentEnum("Framework"));
		assertEquals(Component.DOC, Component.componentEnum("Doc"));
		assertEquals(Component.XPLANNER, Component.componentEnum("XPlanner"));
		assertNull( Component.componentEnum("dnkjsnf"));
		
	}

}
