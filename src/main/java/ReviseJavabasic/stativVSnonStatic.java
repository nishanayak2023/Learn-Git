package ReviseJavabasic;

import java.io.PrintStream;

public class stativVSnonStatic {

	static String companyName = "TIAA";
	
	String name;
	
	public void nonstaticMethod()
	{
		System.out.println("Non static method");
	}
	
	public static void staticMethod()
	{
		System.out.println("Static method");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Print company name which is static variable" +companyName);
		
		stativVSnonStatic sns = new stativVSnonStatic();
		sns.name ="Nisha"; 
		sns.nonstaticMethod();
		staticMethod();
		
		

	}

}
