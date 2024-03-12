package ReviseJavabasic;

import java.util.HashMap;

public class CountWordOfStrings {

	public static void main(String[] args) 
	{
		String str ="count the the word count for me";
		
		Integer count =1;
		
		String arr[] =str.split(" ");
		
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		for(int i=0;i<arr.length;i++)
		{
			
			if(!map.containsKey(arr[i]))
			{
				map.put(arr[i],count);
			}
			
		
		else
		{
			map.put(arr[i], map.get(arr[i])+1);
		}
		}
		
		for(String x: map.keySet())
		{
			System.out.println("The count of word "+x+" = " +map.get(x));
		}
		

	}

}
