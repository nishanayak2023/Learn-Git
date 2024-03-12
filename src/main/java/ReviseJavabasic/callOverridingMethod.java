package ReviseJavabasic;

public class callOverridingMethod extends overridding 
{

	
	
	public void overriddingmethod()
	{
		super.overriddingmethod(); // This will print parent call method
		
		
	}


public static void main(String[] args)
{
	callOverridingMethod b = new callOverridingMethod();
	b.overriddingmethod();
	
}
	}
