package model;

import sim.engine.SimState;
import sim.field.grid.ObjectGrid2D;
import sim.field.grid.SparseGrid2D;
import sim.util.Bag;
import sim.util.Int2D;

public class Field extends SimState
{
	public static int gridSize = 20; 
	public static int nbInsects = 5;
	public static int nbFood = 3;
	public static int nbDirections = 8;
	public SparseGrid2D field = new SparseGrid2D(gridSize, gridSize);
	
	public Field(long seed) 
	{
		super(seed);
	}
	
	private int[] getFreeSpot()
	{
		int[] spot = {0, 0};
		
    	while(field.getObjectsAtLocation(spot[0], spot[1]) != null)
    	{
    		spot[0] = (int)(Math.random() * gridSize);
    		spot[1] = (int)(Math.random() * gridSize);
    	}
    	
    	return spot;
	}
	
	public void start() 
	{
		int spot[];
		
		System.out.println("Simulation started !");
		super.start();
	    field.clear();
	    
	    for(int i = 0 ; i < nbInsects ; i++)
	    {	
	    	Insect insect = new Insect();
	    	spot = getFreeSpot();
	    	field.setObjectLocation(insect, spot[0], spot[1]);
	    }
	    
	    for(int i = 0 ; i < nbFood ; i++)
	    {
	    	Food food = new Food();
	    	spot = getFreeSpot();
	    	field.setObjectLocation(food, spot[0], spot[1]);
	    }
	}
}