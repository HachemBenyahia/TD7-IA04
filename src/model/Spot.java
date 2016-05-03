package model;

public class Spot 
{
	private int x;
	private int y;
	
	Spot()
	{
		x = 0;
		y = 0;
	}
	
	Spot(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	void setSpot(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	void setX(int x)
	{
		this.x = x;
	}
	
	void setY(int y)
	{
		this.y = y;
	}
	
	int getX()
	{
		return this.x;
	}
	
	int getY()
	{
		return this.y;
	}
}
