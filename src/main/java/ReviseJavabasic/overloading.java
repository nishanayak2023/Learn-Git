package ReviseJavabasic;

public class overloading {
	
	public void overloadingmethod() 
	{
		System.out.println("Overloading method 1");
	}
	public void overloadingmethod(String a,String b) 
	{
		System.out.println("Overloading method 1");
		//return a;
	
	}

	public static void main(String[] args) {
		overloading b = new overloading();
		b.overloadingmethod();
		b.overloadingmethod("Nisha", "Nayak");

	}

}
