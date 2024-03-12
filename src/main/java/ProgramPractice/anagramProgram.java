package ProgramPractice;

import java.util.Arrays;

public class anagramProgram {

	public static void main(String[] args) 
	{
		//Anagram means char in string should match the char of another string
		String str1 = "earth";
		String str2 ="heart";
		
		char[] changedstr1toChar = str1.toCharArray();
		char[] changedstr2toChar = str2.toCharArray();
		
		Arrays.sort(changedstr1toChar);
		Arrays.sort(changedstr2toChar);
		
		if(Arrays.equals(changedstr1toChar, changedstr2toChar))
		{
			System.out.println("Given string is anagram");
		}
		
		else
		{
			System.out.println("Given string is not anagram");
		}

	}

}
