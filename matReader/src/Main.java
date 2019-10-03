
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
		//public JSONArray pointArray(double lonMax, -> Longitude Obergrenze
		//double latMax,  -> Latitude Obergrenze
		//double lonMin, -> Longitude Untergrenze
		//double latMin, -> Latitude Untergrenze
		//int posiblePoints, -> Anzahl der gewünschten Heatmap Punkte n
		//boolean finish, -> True: Fügt Extrapunkte Am Rand ein um Streifen am Rand zu vermeiden, es können jedoch <sqrt(n)*2 Punkte dazu kommen
		//boolean P1P2) -> False: Heatmap für P1; True: Heatmap für P2
		System.out.println(l.toJSONString());
	}
}
