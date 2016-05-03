package model;

import model.Constants.Direction;

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
	
	public void setSpot(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public Direction getDirection(Spot spotB)
	{
		int x = spotB.getX() - this.getX();
		int y = spotB.getY() - this.getY();
		
		if(y > 0)
		{
			if(x < 0)
				return Direction.UP_LEFT;
			
			if(x == 0)
				return Direction.UP;
			
			if(x > 0)
				return Direction.RIGHT;
		}
		
		if(y == 0)
		{
			if(x < 0)
				return Direction.LEFT;

			if(x > 0)
				return Direction.RIGHT;
		}

		if(y < 0)
		{
			if(x < 0)
				return Direction.DOWN_LEFT;
			
			if(x == 0)
				return Direction.DOWN;
			
			if(x > 0)
				return Direction.DOWN_RIGHT;
		}
		
		return Direction.UP;
	}
	
	public int getDistance(Spot spotB)
	{
		int x = Math.abs(spotB.getX() - this.getX());
		int y = Math.abs(spotB.getY() - this.getY());
		
		return (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}
