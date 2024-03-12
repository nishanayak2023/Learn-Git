package ProgramPractice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FindNonReapetedChar {

	public static void main(String[] args)
	{
		String str ="Swiss";
		
		Map<Character,Integer> map = new LinkedHashMap<Character,Integer>();
		Integer count =1;
		
		for(int i=0;i<str.length();i++)
		{
			if(!map.containsKey(str.charAt(i)))
			{
				map.put(str.charAt(i), count);
			}
			else
			{
				map.put(str.charAt(i),map.get(str.charAt(i))+1);
			}
		}
		
		for(Entry<Character, Integer> ch: map.entrySet())
		{
			if(ch.getValue()==1)
			
				System.out.println("print the value:  "+ch+  "Print entry set" +map.entrySet()) ;
			break;
		}
	}

}
