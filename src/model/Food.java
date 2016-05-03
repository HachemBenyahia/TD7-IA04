package model;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;

public class Food implements Steppable
{
	private int m_quantity;
	private Spot m_spot;
	public Stoppable stoppable;
	
	Food(Spot spot)
	{
		m_spot = spot;
		m_quantity = 5;
	}
	
	Food(Spot spot, int quantity)
	{
		m_spot = spot;
		m_quantity = quantity;
	}

	public void step(SimState state) 
	{
	}
}
