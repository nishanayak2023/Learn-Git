package ReviseJavabasic;

public class callAbstractClass extends AbstractClass{

	public static void main(String[] args) {
		callAbstractClass xy = new callAbstractClass();
		xy.show();
		xy.xyz();

	}

	@Override
	void xyz() 
	{
		
		System.out.println("Called abstract method");
	}

}
