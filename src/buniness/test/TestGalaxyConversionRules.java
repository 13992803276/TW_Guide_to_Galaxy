package buniness.test;

import buniness.utils.GalaxyConversionRules;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestGalaxyConversionRules {
	protected String  testStr;

	@Before
	public void setUp() throws Exception {
		testStr = "MCMXLIVVVVV";
	}

	@Test
	public void testRomanFormatter(){
		boolean result = GalaxyConversionRules.checkRomanChar(testStr);
		Assert.assertEquals(true, result);
	}
}
