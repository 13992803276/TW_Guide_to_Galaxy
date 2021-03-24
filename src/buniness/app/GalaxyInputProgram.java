package buniness.app;

import buniness.utils.GalaxyConversionRules;
import buniness.utils.RomanToDecimal;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;


public class GalaxyInputProgram extends GalaxyConversionRules{
	/*-----------step1:Creat global variable to save known condition and questions etc--------*/
	
	private final static Pattern RGXASSIGNMENT = Pattern.compile("^([a-z]+) is ([I|V|X|L|C|D|M])$");
	private final static Pattern CREDITS = Pattern.compile("^(.*) (\\b\\w+\\b) is (\\d+) Credits$");
	private final static Pattern QUESTION_HOW_MANY = Pattern.compile("^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([a-zA-Z]\\w+) \\?$");
	private final static Pattern QUESTION_HOW_MUCH = Pattern.compile("^how much is ((?:\\w+[^0-9] )+)\\?$");
	static Map<String, Character> specificConditionsMap = new HashMap<String, Character>();
	static Map<String, Float> conditionsIntegerValueMap = new HashMap<String, Float>(); 
	static Map<String, String> needAnsweredQuestionsMap = new HashMap<String, String>();  
	static ArrayList<String> abstractConditionList = new ArrayList<String>(); 
	static Map<String, Float> MtrlValueMap = new HashMap<String, Float>(); 
	
	/*-----------step2:Process input Value and Initialization variable of step1 etc--------*/
	/**
	 * 
	 * <pre>
	 * describe: read the file and process line by processLine(),
	 * 			 Read the input file in the project by default
	 * @param filePath
	 * @throws IOException  
	 * </pre>
	 */
	public static void processInputFile(String filePath) throws IOException {
		BufferedReader bufferedReader = null;
		if (filePath == null){
			InputStream in = GalaxyInputProgram.class.getResourceAsStream("GalaxyMerchantInput");
			bufferedReader =new BufferedReader(new InputStreamReader(in));
		}else{
			FileReader fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
		}
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			processLine(line);
		}
		processData();
		bufferedReader.close();
	}
	
	/**
	 * 
	 * <pre>
	 * describe: Divide the current row data into different types and save it to the corresponding variables
	 * @param line  
	 * </pre>
	 */
	public static void processLine(String line){
		String arr[] = line.split(" ");
		if (RGXASSIGNMENT.matcher(line).matches()){
			specificConditionsMap.put(arr[0], arr[arr.length-1].charAt(0));
		}else if(CREDITS.matcher(line).matches()) {
			abstractConditionList.add(line);
		}else if(line.endsWith("?")) {
			if(QUESTION_HOW_MUCH.matcher(line).matches()||QUESTION_HOW_MANY.matcher(line).matches()) {
				needAnsweredQuestionsMap.put(line, "1");
			}else {
				needAnsweredQuestionsMap.put(line, "0");
			}
		}else {
			new ErrorProcess().sysoErrorinfo(line,"1");
		}
	}
	/*-----------step3:Processing the corresponding data for class GalaxyInputProgram--------*/
	private static void processData() {
		Iterator it = specificConditionsMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry token = (Map.Entry)it.next();
			float exist_mtrl_price = ROMAN_TO_NUMERIC_MAPPING.get(specificConditionsMap.get(token.getKey()));
			conditionsIntegerValueMap.put(token.getKey().toString(), exist_mtrl_price);
		}
		for (int i = 0; i < abstractConditionList.size(); i++) {
			getMissMtrlPrice(abstractConditionList.get(i));
		}
	}
	
	private static void getMissMtrlPrice(String condition) {
		String array[] = condition.split(" ");
		int totalCredits = 0; int mtrlIndex=0;String mtrlName = "";
		String[] exist_mtrl = null;
		for (int i = 0; i < array.length; i++) {
			if(array[i].toLowerCase().equals("credits")){
				totalCredits = Integer.parseInt(array[i-1]);
			}
			if(array[i].toLowerCase().equals("is")) {
				mtrlIndex = i-1;
				mtrlName = array[i-1];
			}
		}
		exist_mtrl = java.util.Arrays.copyOfRange(array, 0, mtrlIndex);
		StringBuilder tempRomaStr = new StringBuilder();
		for (int i = 0; i < exist_mtrl.length; i++) {
			tempRomaStr.append(specificConditionsMap.get(exist_mtrl[i]));
		}
		float tempRomaStrToDecimal = new RomanToDecimal().romanToDecimal(tempRomaStr.toString());
		if(Math.abs(tempRomaStrToDecimal) < 0.001) {
			System.err.println("Error: error occurred while evaluating the value of "+mtrlName+".");	
			//System.exit(0);
		}
		MtrlValueMap.put(mtrlName,((float)Math.round((totalCredits/tempRomaStrToDecimal)*100)/100));
	}
	
	public static void main(String[] args) {
		String filePath = null;
		if (args.length != 0)
			filePath = args[0];
		try{
			processInputFile(filePath);
		}catch(Exception e){
			System.out.println("Oops !! File Not Found ");
		}
		System.out.println("已知条件物品对应罗马字符map-----》"+specificConditionsMap.toString());
		System.out.println("已知条件物品对应数字map-----》"+conditionsIntegerValueMap.toString());
		System.out.println("解析出来的问题map-----》"+needAnsweredQuestionsMap.toString());
		System.out.println("解析出来的抽象条件map-----》"+abstractConditionList.toString());
		System.out.println("商品对应价格map-----》"+MtrlValueMap.toString());
		//System.out.println(QUESTION_HOW_MUCH.matcher("how much wood could a woodchuck chuck if a woodchuck could chuck= wood ??").matches());
	}
}
