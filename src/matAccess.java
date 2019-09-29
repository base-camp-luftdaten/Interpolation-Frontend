import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class matAccess 
{
	private double p1[][];
	private double p2[][];
	
	public double lat[][];
	public double lon[][];
	
	public matAccess(File file)
	{
		double [][][] data = MatReader.read(file);
		double[][][] meshGrid = MatReader.meshGrid();
		p1=data[0];
		p2=data[1];
		
		lat=meshGrid[0];
		lon=meshGrid[1];
	}
	
	public List<double[]> pointArray(double lonMax, double latMax, double lonMin, double latMin, int posiblePoints, boolean finish)
	{
		List<double[]> pList;
		if(lonMax<=16 && lonMin>=5 && latMax<=55 && latMin>=47)
		{
			int obererLatIndex = latToIndex(latMax);
			int untererLatIndex = latToIndex(latMin);
			int linkerLonIndex = lonToIndex(lonMin);
			int rechterLonIndex = lonToIndex(lonMax);
			
			int hight = (obererLatIndex-untererLatIndex+1);
			int width = (rechterLonIndex-linkerLonIndex+1);
			int fieldSize = hight*width;
			
			if(fieldSize<=posiblePoints)
			{
				pList=new LinkedList<double[]>();
				for(int i=0;i<=hight;i++)
				{
					for(int j=0;j<=width;j++)
					{
						if(Double.isFinite(p1[i+untererLatIndex][j+linkerLonIndex])&&Double.isFinite(p2[i+untererLatIndex][j+linkerLonIndex]))
						{
							double[] point = new double[4];
							point[0] = lon[i+untererLatIndex][j+linkerLonIndex];
							point[1] = lat[i+untererLatIndex][j+linkerLonIndex];
							point[2] = p1[i+untererLatIndex][j+linkerLonIndex];
							point[3] = p2[i+untererLatIndex][j+linkerLonIndex];
							pList.add(point);
						}
					}
				}
				return pList;
			}
			else
			{
				int reduce = (int)(Math.ceil(((double)fieldSize)/((double)posiblePoints)));
				int[] dist = bestDistance(reduce);
				pList=new LinkedList<double[]>();
				
				for(int i=0;i<=hight;i=i+dist[0])
				{
					int h = i;
					
					for(int j=0;j<=width;j=j+(dist[1]*2))
					{
						if(Double.isFinite(p1[h+untererLatIndex][j+linkerLonIndex])&&Double.isFinite(p2[h+untererLatIndex][j+linkerLonIndex]))
						{
							double[] point = new double[4];
							point[0] = lon[h+untererLatIndex][j+linkerLonIndex];
							point[1] = lat[h+untererLatIndex][j+linkerLonIndex];
							point[2] = p1[h+untererLatIndex][j+linkerLonIndex];
							point[3] = p2[h+untererLatIndex][j+linkerLonIndex];
							pList.add(point);
						}
					}
					
					h=h+(dist[0]/2);
					if(h<=hight)
					{
						for(int j=dist[1];j<=width;j=j+(dist[1]*2))
						{
							if(Double.isFinite(p1[h+untererLatIndex][j+linkerLonIndex])&&Double.isFinite(p2[h+untererLatIndex][j+linkerLonIndex]))
							{
								double[] point = new double[4];
								point[0] = lon[h+untererLatIndex][j+linkerLonIndex];
								point[1] = lat[h+untererLatIndex][j+linkerLonIndex];
								point[2] = p1[h+untererLatIndex][j+linkerLonIndex];
								point[3] = p2[h+untererLatIndex][j+linkerLonIndex];
								pList.add(point);
							}
						}
					}
					
					if(finish==true)
					{
						if(Double.isFinite(p1[i+untererLatIndex][rechterLonIndex])&&Double.isFinite(p2[h+untererLatIndex][rechterLonIndex]))
						{
							double[] point = new double[4];
							point[0] = lon[i+untererLatIndex][rechterLonIndex];
							point[1] = lat[i+untererLatIndex][rechterLonIndex];
							point[2] = p1[i+untererLatIndex][rechterLonIndex];
							point[3] = p2[i+untererLatIndex][rechterLonIndex];
							pList.add(point);
						}
					}
				}
				if(finish==true)
				{
					for(int j=0;j<=width;j=j+(dist[1]*2))
					{
						if(Double.isFinite(p1[obererLatIndex][j+linkerLonIndex])&&Double.isFinite(p2[obererLatIndex][j+linkerLonIndex]))
						{
							double[] point = new double[4];
							point[0] = lon[obererLatIndex][j+linkerLonIndex];
							point[1] = lat[obererLatIndex][j+linkerLonIndex];
							point[2] = p1[obererLatIndex][j+linkerLonIndex];
							point[3] = p2[obererLatIndex][j+linkerLonIndex];
							pList.add(point);
						}
					}
					
					if(Double.isFinite(p1[obererLatIndex][rechterLonIndex])&&Double.isFinite(p2[obererLatIndex][rechterLonIndex]))
					{
						double[] point = new double[4];
						point[0] = lon[obererLatIndex][rechterLonIndex];
						point[1] = lat[obererLatIndex][rechterLonIndex];
						point[2] = p1[obererLatIndex][rechterLonIndex];
						point[3] = p2[obererLatIndex][rechterLonIndex];
						pList.add(point);
					}
				}
				return pList;
			}
		}
		else
		{
			System.out.println("Außerhalb des Interpolations-Radius");
			return null;
		}
		
	}
	
	private int latToIndex(double i)
	{
		return (int) ((i-47.0)/0.002);
	}
	
	public int lonToIndex(double i)
	{
		return (int) ((i-5.0)/0.004);
	}
	
	private int[] bestDistance(int n)
	{
		int nextSquare=(int)(Math.ceil(Math.sqrt(n)));
		
		if(((nextSquare-1)*nextSquare)>=n)
		{			
			if(nextSquare%2==0)
			{
				return new int[]{nextSquare,(nextSquare-1)};
			}
			else
			{
				return new int[]{(nextSquare-1),nextSquare};
			}
		}
		else if(nextSquare%2==0)
		{
			return new int[]{nextSquare,nextSquare};
		}
		else
		{
			return new int[]{(nextSquare+1),nextSquare};
		}
	}
}
