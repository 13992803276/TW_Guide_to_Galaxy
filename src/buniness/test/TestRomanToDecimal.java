package buniness.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import buniness.utils.RomanToDecimal;

public class TestRomanToDecimal {
	protected String  testStr;
	protected String  testMissStr;

	@Before
	public void setUp() throws Exception {
		testStr = "MCMXLIV";
		testMissStr = "DDDDD";
	}

	@Test
	public void testRomanToDecimal(){
		float result = new RomanToDecimal().romanToDecimal(testStr);
		Assert.assertEquals(4.00, result,00.00);
	}
	@Test
	public void testMissRomanToDecimal(){
		float result = new RomanToDecimal().romanToDecimal(testMissStr);
		Assert.assertEquals("Error : Roman Numeral M cannot repeat 4 times successively", result);
	}
}
