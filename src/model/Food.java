package model;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;

public class Food implements Steppable
{
	private int m_quantity;
	private Spot m_spot;
	public Stoppable stoppable;
	Field m_field;
	int m_id;
	
	Food(int id, Spot spot)
	{
		m_id = id;
		m_spot = spot;
		m_quantity = Constants.defaultFoodQuantity;
	}
	
	Food(int id, Spot spot, int quantity)
	{
		m_id = id;
		m_spot = spot;
		m_quantity = quantity;
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
		
		System.out.println(m_quantity);
		
		if(m_quantity < 0)
		{
			m_field.field.remove(this);
			stoppable.stop();
		}
	}
}
