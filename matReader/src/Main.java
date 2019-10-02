
import java.io.File;
import java.util.List;

import org.json.simple.JSONArray;


public class Main 
{
	
	public static void main(String[] args) 
	{
		File f = new File("test1.mat");
		matAccess m = new matAccess(f);
		
		JSONArray l = m.pointArray(16, 55, 5, 47, 10000,true,false);
		System.out.println(l.toJSONString());
	}
}