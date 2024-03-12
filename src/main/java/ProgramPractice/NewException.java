package ProgramPractice;

public class NewException {

	public static void main(String[] args)
	{
		try
		{
			int age =17;
			if(age<18)
			{
				throw new NewExceptionCustomised("Age must be above 18");
			}
			
	
		}
			
			catch(NewExceptionCustomised e)
			{
				System.out.println(e.getMessage());
			}
		
	
		
 
		}}
	
	
	


