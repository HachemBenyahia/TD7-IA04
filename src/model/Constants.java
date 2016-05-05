package model;

import sim.util.Bag;

public class Constants 
{
	public static int gridSize = 20; 
	public static int nbInsects = 10;
	public static int nbFood = 5;
	public static int foodEnergy = 5;
	public static int defaultFoodQuantity = 3;
	public static int defaultInitialEnergy = 10;
	public static int nbFrames = 400;
	public static int defaultInitialDeplacement = 2;
	public static int defaultInitialPerception = 5;
	public static int defaultMaxEnergy = 15;
	public static int defaultMaxLoad = 3;
	public static int capacity = 10;
	
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
