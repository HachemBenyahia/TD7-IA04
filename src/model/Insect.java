package model;

import model.Constants.Direction;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.util.Bag;

public class Insect implements Steppable
{
	private int m_distanceDeplacement;
	private int m_distancePerception;
	private int m_chargeMax;
	private int m_energy;
	private int m_charge;
	private Field m_field;
	private Spot m_spot;
	public Stoppable stoppable;
	
	Insect(Spot spot)
	{
		m_distanceDeplacement = 1;
		m_distancePerception = 1;
		m_chargeMax = 1;
		m_energy = 5;
		m_charge = 0;
		m_spot = spot;
	}
	
	Insect(Spot spot, int distanceDeplacement, int distancePerception, int chargeMax)
	{
		m_spot = spot;
		m_distanceDeplacement = distanceDeplacement;
		m_distancePerception = distancePerception;
		m_chargeMax = chargeMax;
	}
	
	private Spot nearestFood()
	{
		Spot spot = m_spot;
		Bag bag;
		
		for(Direction direction : Direction.values())
		{
			for(int i = 1 ; i <= m_distancePerception ; i++)
			{
				Constants.getSpot(spot, direction, i);
				
				bag = m_field.field.getObjectsAtLocation(spot.getX(), spot.getY());
				Object[] objects = bag.toArray();
				
				for(int j = 0 ; j < objects.length ; j++)
					if(objects[j].getClass().toString() == "Food")
						return spot;
			}
		}
		
		return spot;
	}
	
	void moveTo(Spot spot)
	{
		if(m_distanceDeplacement >= m_spot.getDistance(spot))
			m_spot = spot;
		else
		{
			switch(m_spot.getDirection(spot))
			{
				case UP_LEFT :
					
				break;
			}
		}
	}
	
	public void step(SimState state) 
	{
		Field field = (Field) state;
		m_field = field;
		
		if(m_energy == 0)
		{
			field.field.remove(this);
			stoppable.stop();
		}
		
		// s'il a besoin de manger
		if(m_energy < Constants.lowEnergy)
		{
			// il va d'abord essayer de consommer ce qu'il a
			if(m_charge > 0)
			{
				m_charge--;
				m_energy += Constants.foodEnergy;
			}
			
			// sinon il va essayer de manger ce qui se trouve près de lui
			Spot nearestFoodSpot = nearestFood();
			Constants.Direction direction = m_spot.getDirection(nearestFoodSpot);
		}
	}
}
