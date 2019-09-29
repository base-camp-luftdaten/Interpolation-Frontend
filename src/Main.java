
import java.io.File;
import java.util.List;


public class Main 
{
	
	public static void main(String[] args) 
	{
		File f = new File("test1.mat");
		matAccess m = new matAccess(f);
		
		List<double[]> l = m.pointArray(16, 55, 5, 47, 100000,true);
		
	}
}