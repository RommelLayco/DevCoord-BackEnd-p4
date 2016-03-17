package LIBSVMTest;

import static org.junit.Assert.*;

import org.junit.Test;

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

}
