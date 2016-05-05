package model;

import sim.util.Bag;

public class Constants 
{
	public static int gridSize = 20; 
	public static int nbInsects = 5;
	public static int nbFood = 3;
	public static int lowEnergy = 3;
	public static int foodEnergy = 3;
	public static int defaultFoodQuantity = 3;
	public static int defaultInitialEnergy = 20;
	
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
}
