package model;

import sim.util.Bag;

public class Constants 
{
	public static int gridSize = 20; 
	public static int nbInsects = 5;
	public static int nbFood = 3;
	public static int lowEnergy = 3;
	public static int foodEnergy = 1;
	
	public static enum Direction
	{
		UP_LEFT,
		UP,
		UP_RIGHT,
		LEFT,
		RIGHT,
		DOWN_LEFT,
		DOWN,
		DOWN_RIGHT
	}
	
	private static void handleUp(Spot spot, int distance)
	{
		int y;
		
		y = spot.getY() - distance;
		
		if(y < 0)
			spot.setY(gridSize - y);
		else
			spot.setY(y);
	}
	
	private static void handleDown(Spot spot, int distance)
	{
		int y;
		
		y = spot.getY() + distance;
		
		if(y > gridSize - 1)
			spot.setY(y - gridSize);
		else
			spot.setY(y);
	}
	
	private static void handleLeft(Spot spot, int distance)
	{
		int x;
		
		x = spot.getX() - distance;
		
		if(x < 0)
			spot.setX(gridSize - x);
		else
			spot.setX(x);
	}
	
	private static void handleRight(Spot spot, int distance)
	{
		int x;
		
		x = spot.getX() + distance;
		
		if(x > gridSize - 1)
			spot.setX(x - gridSize);
		else
			spot.setX(x);
	}
	
	public static void getSpot(Spot spot, Direction direction, int distance)
	{
		int x, y;
		
		switch(direction)
		{
			case UP_LEFT :
				handleUp(spot, distance);
				handleLeft(spot, distance); // revenir sur ça, je pense que c'est (int)distance/sqrt(2) car on se place sur un cercle
			break;
				
			case UP :
				handleUp(spot, distance);
			break;
			
			case UP_RIGHT :
				handleUp(spot, distance);
				handleRight(spot, distance);
			break;
			
			case LEFT :
				handleLeft(spot, distance);
			break;
			
			case RIGHT :
				handleRight(spot, distance);
			break;
			
			case DOWN_LEFT :
				handleDown(spot, distance);
				handleLeft(spot, distance);
			break;
			
			case DOWN :
				handleDown(spot, distance);
			break;
			
			case DOWN_RIGHT :
				handleDown(spot, distance);
				handleRight(spot, distance);
			break;
		}
	}
}
