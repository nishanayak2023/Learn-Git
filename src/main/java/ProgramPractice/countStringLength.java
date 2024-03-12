package ProgramPractice;

import java.util.HashMap;

public class countStringLength {

	public static void main(String[] args) 
	{
		String str ="Lets count the word in the word book";
		Integer count =1;
		String[] arr =str.split(" ");
		
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		for(int i=0;i<arr.length;i++)
		{
			if(!map.containsKey(arr[i]))
			{
				map.put(arr[i], count);
			}
		
		else
		{
			map.put(arr[i],map.get(arr[i])+1);
		}
	    
		}
		
		for(String x :map.keySet())
		{
			System.out.println("let check the count of word  : " +x+   "   " +map.get(x));
		}
		
	}

}
