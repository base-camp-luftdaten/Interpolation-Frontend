import java.io.File;



public class Main 
{
	public static void main(String[] args) 
	{
		//mat-Datei erstellt mit dem Backend-Part
		File file = new File("test.mat");
		
		//erzeugt das Mesh-Grid zum auslesen der Koordinaten: meshGrid[0] := Latitude , meshGrid[1] := Longitude
		//
		// Matrix Aufbau: lat=meshGrid[0];
		// 
		// lat[0][0] lat[0][1] lat[0][2]
		// lat[1][0] lat[1][1] lat[1][2]
		// lat[2][0] lat[2][1] lat[2][2]
		//
		double[][][] meshGrid = MatReader.meshGrid();
		//liest die Luftverschmutzungswerte aus der mat-Datei: data[0] := P1 , data[1] := P2
		//Verfügbare Varianten: "MatReader.read(InputStream str)" und "MatReader.read(File file)"
		//
		// Matrix Aufbau: p1=data[0];
		// 
		// p1[0][0] p1[0][1] p1[0][2]
		// p1[1][0] p1[1][1] p1[1][2]
		// p1[2][0] p1[2][1] p1[2][2]
		//
		double data[][][] = MatReader.read(file);
		
		int indexY = 3210;
		int indexX = 1234;
		
		System.out.println("Lat: "+meshGrid[0][indexY][indexX]);
		System.out.println("Lon: "+meshGrid[1][indexY][indexX]);
		System.out.println("P1: "+data[0][indexY][indexX]);
		System.out.println("P2: "+data[1][indexY][indexX]);
	}
}
