package buniness.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import buniness.app.ErrorProcess;

public class Utils extends GalaxyConversionRules{
	/**
	 * <pre>
	 * Splits the query and returns an ArrayList containing only Roman numerals and elements
	 * @param query
	 * @return
	 * </pre>
	 */
	public ArrayList<String> splitQuery(String query){
		ArrayList<String> queryArray = new ArrayList<String>(Arrays.asList(query.split(" ")));
		int startIndex = 0, endIndex = 0;
		for (int i = 0; i < queryArray.size(); i++) {
			if(queryArray.get(i).toLowerCase().equals("is")){
				startIndex = i+1;
			}
			else if(queryArray.get(i).toLowerCase().equals("?")){
				endIndex = i;

			}
		}
		String[] array = queryArray.toArray(new String[queryArray.size()]);
		return new ArrayList<String>(Arrays.asList(java.util.Arrays.copyOfRange(array, startIndex, endIndex)));
	}
	/**
	 * <pre>
	 * Formats the response to a query and displays it in readable format
	 * @param output
	 * @return
	 * </pre>
	 */
	public  String outputFormatter(ArrayList<String> output){
		return output.toString().replace(",", "").replace("[", "").replace("]", "");
	}
	/**
	 * 
	 * <pre>
	 * describe: compresses a 'String'.
	 *           For example, input the 'IIVXXXDMI' , this method will return a map.
	 *           you'll get two parameters 'charList' and 'charCountMap' in the map, 
	 *           charList is {'I','V','X','D','M'}, charCountMap is {I=2,V=1,X=3,D=1,M=1}
	 * @param romanNumber
	 * @return  
	 * </pre>
	 */
	public Map<String, Object> compressionChar(String romanNumber) {
		char[] romanNumeral = romanNumber.toUpperCase().toCharArray();
		Map<Character, Integer> charCountMap = new HashMap <Character, Integer>();
		ArrayList<Character> isexsitChar = new ArrayList<>();
		Map<String, Object> charListAndcountMap = new HashMap <String, Object>();
		int charCount = 0;
		for (int i = 0; i < romanNumeral.length; i++) {
			Character currChar = romanNumeral[i];
			if(!ROMAN_TO_NUMERIC_MAPPING.keySet().contains(currChar)) {
				new ErrorProcess().sysoErrorinfo("","3");
			}
			for(int j=i+1;j<romanNumeral.length;j++) {
				if(currChar==romanNumeral[j]) {
					i++;
					charCount ++;
					continue;
				}else {
					break;
				}
			}
			if(!isexsitChar.contains(currChar)) isexsitChar.add(currChar);
			if(isexsitChar.contains(currChar)&&
					charCount+1>(charCountMap.get(currChar)==null?0:charCountMap.get(currChar))) {
				charCountMap.put(currChar, charCount+1);
			}
			charCount= 0;
		}
		charListAndcountMap.put("charList", isexsitChar);
		charListAndcountMap.put("charCountMap", charCountMap);
		return charListAndcountMap;
	}
	
	public String arrayToString(ArrayList<Character> list) {
		 StringBuilder str = new StringBuilder();
		    for (Character character : list) {
		        str.append(character);
		    }
		    return str.toString();
	}
	/**
	 * 
	 * <pre>
	 * describe: verificate Roman numerals whether satisfy the rule of subtraction
	 * @param romanNumber
	 * @return  
	 * </pre>
	 */
	public boolean checkSubtractable(String romanNumber) {
		char[] romanNumeral = romanNumber.toUpperCase().toCharArray();
		for (int i =0;i< romanNumeral.length; i++ ) {
			Character currCharVlue = romanNumeral[i];
			Character currCharVlueNext = null; 
			if(i==romanNumeral.length-1) {
				return true;
			}else {
				currCharVlueNext = romanNumeral[i+1];
				 if(ROMAN_TO_NUMERIC_MAPPING.get(currCharVlue)<ROMAN_TO_NUMERIC_MAPPING.get(currCharVlueNext)
						 &&!Arrays.asList(ROMAN_SUBTRACTABLE_MAPPING.get(currCharVlue)).contains(currCharVlueNext)) {
					 new ErrorProcess().sysoErrorinfo("","4");
					 return false;
				}
			}	
		}
		return true;
	}
	
	public static void main(String[] args) {
		//ArrayList<String> array = new Utils().splitQuery("how many Credits is glob prok Silver ?");
		System.out.println(new Utils().compressionChar("IVXLCDM"));
	}
}
