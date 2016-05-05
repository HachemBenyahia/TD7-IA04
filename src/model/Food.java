package model;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;

public class Food implements Steppable
{
	private int m_quantity;
	private int m_initialQuantity;
	private Spot m_spot;
	public Stoppable stoppable;
	Field m_field;
	int m_id;
	
	Food(int id, Spot spot)
	{
		m_id = id;
		m_spot = spot;
		m_quantity = Constants.defaultFoodQuantity;
		m_initialQuantity = m_quantity;
	}
	
	Food(int id, Spot spot, int quantity)
	{
		m_id = id;
		m_spot = spot;
		m_quantity = quantity;
		m_initialQuantity = m_quantity;
	}

	public void decreaseQuantity()
	{
		m_quantity--;
	}
	
	public int getId()
	{
		return m_id;
	}
	
	public void step(SimState state) 
	{
		Field field = (Field) state;
		m_field = field;
		
		if(m_quantity < 0)
		{
			Spot freeSpot = m_field.getFreeSpot();
			m_field.field.setObjectLocation(this, freeSpot.getX(), freeSpot.getY());
			m_quantity = m_initialQuantity;
		}
	}
}
