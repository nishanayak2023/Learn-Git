package New21Feb;

public class ReverseTheNumber {

	public static void main(String[] args) {
		// Write a program to reverse a number
		
		int num = 12345;
	    int rev =0;
	    int Rem;
	    
	    while(num>0)
	    {
	    Rem = num%10; //12345
	    rev = rev*10 +Rem;  //12345
	    num = num/10;//12
	    }
	    System.out.println(+num);
	    

	}

}
