package ProgramPractice;

public class Palindrome {

	public static void main(String[] args) 
	{
		String str = "MADAM";
		String rev = " ";
		
		for(int i=0;i<str.length();i++)
		{
			char ch =str.charAt(i);
			rev = rev + ch;
		}
		System.out.println(rev);

	}

}
