package ReviseJavabasic;

public class multiDArray {

	public static void main(String[] args) 
	{
		int[][] MultiDArrayObj ={{2,4,5},{4,6,9}};

		for(int i=0;i<MultiDArrayObj.length;i++)
		{
			for(int j=0;j<MultiDArrayObj[i].length;j++)
			{
				System.out.print(MultiDArrayObj[i][j]);
			}
		}
	}

}
