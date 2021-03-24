package buniness.app;


public class ErrorProcess {
	private final static String ERRORMSASSGE1 = " : I have no idea what you are talking about.";
	private final static String ERRORMSASSGE2 = "Error : Roman Numeral V,L,D cannot be repeated.";
	private final static String ERRORMSASSGE3 = "Error : Current Roman numer do not exist in  ROMAN_TO_NUMERIC_MAPPING dictionary.";
	private final static String ERRORMSASSGE4 = "Error : Current Roman numer don't fit the rule of subtraction.";
	/**
	 * output the error message on console
	 * @param output
	 * @return
	 */
	public void sysoErrorinfo(String query,String flag){
		if("1".equals(flag)) {
			System.err.println(query+ERRORMSASSGE1);
		}else if("2".equals(flag)) {
			System.err.println(query+ERRORMSASSGE2);
		}else if ("3".equals(flag)) {
			System.err.println(query+ERRORMSASSGE3);
		}else if ("4".equals(flag)) {
			System.err.println(query+ERRORMSASSGE4);
		}
		System.exit(1);
	}
}
