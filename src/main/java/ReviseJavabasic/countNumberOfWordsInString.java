package ReviseJavabasic;

public class countNumberOfWordsInString {

	public static void main(String[] args)
	{
		String str = "My name is Nisha i stay in india and india is name of country";
		
		
	 int LengthofN = str.length()-str.replaceAll("i", "").length();
	 System.out.println(LengthofN);
		
		
	

	}

}
