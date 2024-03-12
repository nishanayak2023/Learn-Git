package ProgramPractice;

import java.util.HashMap;

public class duplicateStringCount {

	public static void main(String[] args)
	{
		String str ="Nisha will come once Nisha once will not";
		
		Integer count =1;
		
		String[] arr=str.split(" ");
		
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
			
			System.out.println("Print the count of word : " +x+  "word" +map.get(x));
			if(map.get(x)>1)
			{
				System.out.println("Duplicate");
			}
		}
		

	}

}
