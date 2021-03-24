package buniness.utils;


public class RomanToDecimal extends GalaxyConversionRules{
	/**
	 * 
	 * <pre>
	 * describe: converte Roman numerals to Decimal
	 * @param romanNumber
	 * @return  
	 * </pre>
	 */
	public float romanToDecimal(String romanNumber) {
		float decimal = 0;
		char[] romanNumeral = romanNumber.toUpperCase().toCharArray();
		
		if(super.checkRomanChar(romanNumber)&&new Utils().checkSubtractable(romanNumber)) {
			for (int i =0;i< romanNumeral.length; i++ ) {
				Character currCharVlue = romanNumeral[i];
				Character currCharVlueNext = null; 
				if(i==romanNumeral.length-1) { 
					decimal+=ROMAN_TO_NUMERIC_MAPPING.get(currCharVlue);
				}else {
					currCharVlueNext = romanNumeral[i+1];
					if(ROMAN_TO_NUMERIC_MAPPING.get(currCharVlue)>=ROMAN_TO_NUMERIC_MAPPING.get(currCharVlueNext)) {
						decimal +=ROMAN_TO_NUMERIC_MAPPING.get(currCharVlue);
					}else {
						decimal+=(ROMAN_TO_NUMERIC_MAPPING.get(currCharVlueNext)-ROMAN_TO_NUMERIC_MAPPING.get(currCharVlue));
						i=i+1;
					}
				}	
			}
		}
		return decimal;
	}
}
