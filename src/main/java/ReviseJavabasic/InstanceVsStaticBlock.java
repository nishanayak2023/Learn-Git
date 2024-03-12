package ReviseJavabasic;

public class InstanceVsStaticBlock
{
	{
		System.out.println("Instance block1");
	}
	
	static {
		System.out.println("Static method1");
	}
	
	{
		System.out.println("Instance block2");
	}
	
	static{
		System.out.println("Static method2");
	}
	
	public static void main(String []args)
	{
		System.out.println("main");
		InstanceVsStaticBlock a = new InstanceVsStaticBlock();
		
	}

}
