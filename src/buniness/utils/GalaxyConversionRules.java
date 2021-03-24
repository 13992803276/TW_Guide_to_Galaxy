package buniness.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import buniness.app.ErrorProcess;


public class GalaxyConversionRules {
	// the Roman character  repeat times dictionary
	private static Map<Character,Integer> mapOfCharRepeatableTimes(){
		Map<Character,Integer> map = new HashMap<Character,Integer>() {
			private static final long serialVersionUID = 1L;

			{
				put('I', 3);
				put('X', 3);
				put('C', 3);
				put('M', 3);
				put('V', 1);
				put('L', 1);
				put('D', 1);
			}
		};
		return map;
	}
	
	//Roman-number control relationship
	public static Map<Character, Integer>  ROMAN_TO_NUMERIC_MAPPING= Collections.unmodifiableMap(
			new HashMap<Character, Integer>() {
				private static final long serialVersionUID = 1L;

				{
					put('I', 1);
					put('V', 5);
					put('X', 10);
					put('L', 50);
					put('C', 100);
					put('D', 500);
					put('M', 1000);
				}
			});
	
	//the rule of subtraction
	public static Map<Character, Character[]> ROMAN_SUBTRACTABLE_MAPPING = Collections.unmodifiableMap(
			new HashMap<Character, Character[]>() {
				private static final long serialVersionUID = 1L;

				{
					put('I', new Character[] {'V', 'X'});
					put('V', new Character[] {});
					put('X', new Character[] {'L', 'C'});
					put('L', new Character[] {});
					put('C', new Character[] {'D', 'M'});
					put('D', new Character[] {});
					put('M', new Character[] {});
				}
			});
	public static boolean checkRomanChar(String romanStr) {
		Map<String, Object> compressioMap =new Utils().compressionChar(romanStr);
		@SuppressWarnings("unchecked")
		ArrayList<Character> isexsitChar = (ArrayList<Character>) compressioMap.get("charList");
		@SuppressWarnings("unchecked")
		Map<Character, Integer> charCountMap = (Map<Character, Integer>) compressioMap.get("charCountMap");
		for (int i = 0; i < isexsitChar.size(); i++) {
			if(charCountMap.get(isexsitChar.get(i)) > mapOfCharRepeatableTimes().get(isexsitChar.get(i))) {
				if(isexsitChar.contains('D')||isexsitChar.contains('L')||isexsitChar.contains('V')) {
					new ErrorProcess().sysoErrorinfo("","2");
					return false;
				}else {
					System.err.println("Error : Roman Numeral "+isexsitChar.get(i)+" cannot repeat 4 times successively");
					return false;
				}
			}
		}
		return true; 
	}
}
