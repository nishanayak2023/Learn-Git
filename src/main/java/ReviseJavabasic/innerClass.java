package ReviseJavabasic;

public class innerClass
{
	   void OuterClass() 
	   {
		System.out.println("OuterClass");   
	   }
	public class classinsideClass
	{
		void printInnerClass() 
		{
			System.out.println("InnerClass");
		}
	}
	public static void main(String[] args)
	{
		innerClass c1 = new innerClass();
		c1.OuterClass();
		//If you want to call methods of inner class then you will have to create obj of inner class also
		
		classinsideClass c2 = c1.new classinsideClass();
		c2.printInnerClass();
	}

}

