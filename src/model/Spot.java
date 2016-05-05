package model;

import model.Constants.Direction;

public class Spot 
{
	private int m_x;
	private int m_y;
	
	Spot()
	{
		m_x = 0;
		m_y = 0;
	}
	
	Spot(int x, int y)
	{
		m_x = x;
		m_y = y;
	}
	
	public void setSpot(int x, int y)
	{
		m_x = x;
		m_y = y;
	}
	
	public void setX(int x)
	{
		m_x = x;
	}
	
	public void setY(int y)
	{
		m_y = y;
	}
	
	public int getX()
	{
		return m_x;
	}
	
	public int getY()
	{
		return m_y;
	}
	
	public Direction getDirection(Spot spotB)
	{
		int x = spotB.getX() - m_x;
		int y = spotB.getY() - m_y;
		
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
	
	public int getDistance(Spot spot)
	{
		int x = Math.abs(spot.getX() - m_x);
		int y = Math.abs(spot.getY() - m_y);
		
		return x + y;
	}
	
	private Spot handleUp(Spot spot)
	{
		int y = m_y - 1;
		
		if(y < 0)
			spot.setY(y + Constants.gridSize);
		else
			spot.setY(y);
		
		return spot;
	}
	
	private Spot handleDown(Spot spot)
	{
		int y = m_y + 1;
		
		if(y > Constants.gridSize - 1)
			spot.setY(y - Constants.gridSize);
		else
			spot.setY(y);
		
		return spot;
	}
	
	private Spot handleLeft(Spot spot)
	{
		int x = spot.getX() - 1;
		
		if(x < 0)
			spot.setX(x + Constants.gridSize);
		else
			spot.setX(x);
		
		return spot;
	}
	
	private Spot handleRight(Spot spot)
	{
		int x = spot.getX() + 1;
		
		if(x > Constants.gridSize - 1)
			spot.setX(x - Constants.gridSize);
		else
			spot.setX(x);
		
		return spot;
	}
	
	public Spot nextSpot(Direction direction)
	{
		Spot spot = new Spot(m_x, m_y);
		
		switch(direction)
		{
			case UP_LEFT :
				spot = handleUp(spot);
				spot = handleLeft(spot);
			break;
				
			case UP :
				spot = handleUp(spot);
			break;
			
			case UP_RIGHT :
				spot = handleUp(spot);
				spot = handleRight(spot);
			break;
			
			case LEFT :
				spot = handleLeft(spot);
			break;
			
			case RIGHT :
				spot = handleRight(spot);
			break;
			
			case DOWN_LEFT :
				spot = handleDown(spot);
				spot = handleLeft(spot);
			break;
			
			case DOWN :
				spot = handleDown(spot);
			break;
			
			case DOWN_RIGHT :
				spot = handleDown(spot);
				spot = handleRight(spot);
			break;
		}
		
		return spot;
	}
	
	public Spot[][] getSurroundingSpots(int distance)
	{
		Spot[][] spots = new Spot[distance * 2 + 1][distance * 2 + 1];
		Spot spot = this, first = null;
		
		for(int i = 0 ; i < distance ; i++)
			spot = spot.nextSpot(Direction.UP_LEFT);
		
		spot = spot.nextSpot(Direction.LEFT);
		first = spot;
		
		for(int i = 0 ; i < distance * 2 + 1; i++)
		{
			for(int j = 0 ; j < distance * 2 + 1; j++)
			{
				spot = spot.nextSpot(Direction.RIGHT);
				spots[i][j] = spot;
			}
			
			spot = first.nextSpot(Direction.DOWN);
			first = spot;
		}
		
		return spots;
	}
	
	public void printSurroundings(int distance)
	{
		Spot[][] spots = getSurroundingSpots(distance);
		
		for(int i = 0 ; i < distance * 2 + 1; i++)
		{
			for(int j = 0 ; j < distance * 2  + 1; j++)
			{
				spots[i][j].print();
				System.out.print(" ");
			}
			
			System.out.println();
		}
	}
	
	public void print()
	{
		System.out.print("(" + m_x + ", " + m_y + ")");
	}
	
	public String toString()
	{
		return "(" + m_x + ", " + m_y + ")";
	}
	
	public boolean equals(Spot spot)
	{
		return ((m_x == spot.getX()) && (m_y == spot.getY()));
	}
}
