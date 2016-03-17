package LIBSVMTest;

import static org.junit.Assert.*;

import org.junit.Test;

import LIBSVM.OS;
import LIBSVM.Platform;

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

}
