package buniness.test;

import buniness.utils.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class TestUnitls {
	protected String  testStr;
	protected String  testStr2;
	protected ArrayList<String> array = new ArrayList<String>();
	
	@Before
	public void setUp() throws Exception {
		testStr = "how many Credits is glob prok Silver ?";
		testStr2 = "IVDMXXIILMMM";
		array.add("glob");
		array.add("prok");
		array.add("Iron");
		array.add("is");
		array.add("782.0");
		array.add("Credits");
	}

	@Test
	public void testSplitQuery(){
		ArrayList<String> array = new Utils().splitQuery(testStr);
		int result = array.size();
		Assert.assertEquals(4, result,00.00);
	}
	@Test
	public void testOutputFormatter(){
		String result = new Utils().outputFormatter(array);
		Assert.assertEquals("Error : Roman Numeral M cannot repeat 4 times successively", result);
	}
	@Test
	public void testCompressionChar(){
		Map<String, Object> result = new Utils().compressionChar(testStr2);
		ArrayList<Character> isexsitChar = (ArrayList<Character>) result.get("charList");
		Map<Character, Integer> charCountMap = (Map<Character, Integer>) result.get("charCountMap");
		Assert.assertEquals(6, isexsitChar.size());
		//Assert.assertEquals("{D=1, V=1, X=2, I=2, L=1, M=3}", charCountMap.toString());
	}
}
