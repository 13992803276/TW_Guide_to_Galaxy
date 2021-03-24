package buniness.app;

import java.util.ArrayList;
import java.util.Map;

import buniness.utils.RomanToDecimal;
import buniness.utils.Utils;

public class GalaxyOutputProgram extends GalaxyInputProgram{
	public static void outputMainProcess() {
		Map <String,String> questionsMap = needAnsweredQuestionsMap;
		if(questionsMap.isEmpty()) {
			System.err.println("sorry, there's nothing to answer !");
			System.exit(1);
		}
		for (Map.Entry<String, String> entry : questionsMap.entrySet()) {
			if(questionsMap.get(entry.getKey()).equals("0")) {
				new ErrorProcess().sysoErrorinfo(entry.getKey(),"1");
			}else {
				processQuestion(entry.getKey());
			}
		}
	}
	
	private static void processQuestion(String question) {
		if(question.toLowerCase().startsWith("how many")) {
			answerHowManyQuestion(question);
		}else if(question.toLowerCase().startsWith("how much")) {
			answerHowMuchQuestion(question);
		}else {
			new ErrorProcess().sysoErrorinfo(question,"1");
		}
	}
	
	/**
	 * answerHowManyQuestion() processes those queries seeking the decimal equivalent of any RomanNumeral and prints the result.
	 * @param question
	 */
	private static void answerHowMuchQuestion(String question) {
		ArrayList<Character> mtrlToRoman = new ArrayList<Character>();
		ArrayList<String> mtrlValue =new Utils().splitQuery(question);
		for (int i = 0; i < mtrlValue.size(); i++) {
			mtrlToRoman.add(specificConditionsMap.get(mtrlValue.get(i)));
		}
		float answerVaule = new RomanToDecimal().romanToDecimal(new Utils().arrayToString(mtrlToRoman));
		mtrlValue.add("is");
		mtrlValue.add(Float.toString(answerVaule));
		System.out.println(question+" "+new Utils().outputFormatter(mtrlValue));
	}
	
	/**
	 * answerHowMuchQuestion() processes those queries seeking the Credit value of any quantity of elements and prints the result.
	 * @param question
	 */
	private static void  answerHowManyQuestion(String question){
		
		ArrayList<Character> mtrlToRoman = new ArrayList<Character>();
		ArrayList<String> mtrlValue = new Utils().splitQuery(question);
		String element = null;
		for (int i = 0; i < mtrlValue.size(); i++) {
			if(specificConditionsMap.get(mtrlValue.get(i)) != null){
				mtrlToRoman.add(specificConditionsMap.get(mtrlValue.get(i)));
			}
			else if (MtrlValueMap.get(mtrlValue.get(i)) != null){
				element = mtrlValue.get(i);
			}
			else{
				new ErrorProcess().sysoErrorinfo(question,"1");
			}
		}
		float elementValue = (new RomanToDecimal().romanToDecimal(new Utils().arrayToString(mtrlToRoman)) * MtrlValueMap.get(element));
		mtrlValue.add("is");
		mtrlValue.add(Float.toString(elementValue));
		mtrlValue.add("Credits");
		System.out.println(question+" "+new Utils().outputFormatter(mtrlValue));
	}
}
