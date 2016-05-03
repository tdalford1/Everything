
public class Gobbler {
	static String color;
	static int size;
	
	public Gobbler(String c, int s)
	{
		color = c;
		size = s;
	}
	
	public String color()
	{
		return color;
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean equals(Gobbler gobb)
	{
		if (gobb.size() == size && gobb.color() == color)
		{
			return true;
		}		
		return false;
	}
}