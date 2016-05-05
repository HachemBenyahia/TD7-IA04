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
	private int m_id;
	private boolean m_alive;
	public Stoppable stoppable;
	
	Insect(int id, Spot spot)
	{
		m_id = id;
		m_distanceDeplacement = 1;
		m_distancePerception = 10;
		m_chargeMax = 1;
		m_energy = Constants.defaultInitialEnergy;
		m_charge = 0;
		m_spot = spot;
		m_alive = true;
	}
	
	Insect(int id, Spot spot, int distanceDeplacement, int distancePerception, int chargeMax)
	{
		m_id = id;
		m_spot = spot;
		m_distanceDeplacement = distanceDeplacement;
		m_distancePerception = distancePerception;
		m_chargeMax = chargeMax;
		m_alive = true;
	}
	
	public boolean spotContainsFood(Spot spot)
	{
		Bag bag = m_field.field.getObjectsAtLocation(spot.getX(), spot.getY());
		
		if(bag == null)
			return false;
		
		for(int i = 0 ; i < bag.size() ; i++)
			if(bag.getValue(i).getClass().getSimpleName().equalsIgnoreCase("food"))
				return true;
		
		return false;
	}

	private Spot nearestFoodSpot()
	{
		Spot[][] spots = m_spot.getSurroundingSpots(m_distancePerception);
		
		for(int i = 0 ; i < m_distancePerception * 2 + 1 ; i++)
			for(int j = 0 ; j  < m_distancePerception * 2 + 1 ; j++)
				if(spotContainsFood(spots[i][j]))
					return spots[i][j];
		
		return m_spot;
	}
	
	// spot qui minimise la distance au spot objectif (dans la mesure de la
	// capacité de déplacement maximale)
	void moveTowards(Spot spot)
	{	
		Spot[][] spots = m_spot.getSurroundingSpots(m_distanceDeplacement);

		for(int i = 0 ; i < m_distanceDeplacement * 2 + 1 ; i++)
			for(int j = 0 ; j < m_distanceDeplacement * 2 + 1 ; j++)
				if(spots[i][j].getDistance(spot) < m_spot.getDistance(spot))
					m_spot = spots[i][j];
		
		m_field.field.setObjectLocation(this, m_spot.getX(), m_spot.getY());
	}
	
	public boolean isOnFood()
	{
		return spotContainsFood(m_spot);
	}
	
	public Food getFood(Spot spot)
	{
		Bag bag = m_field.field.getObjectsAtLocation(spot.getX(), spot.getY());
		
		if(bag == null)
			return null;
		
		for(int i = 0 ; i < bag.size() ; i++)
			if(bag.getValue(i).getClass().getSimpleName().equalsIgnoreCase("food"))
				return (Food) bag.getValue(i);
		
		return null;
	}

	public void step(SimState state) 
	{
		Field field = (Field) state;
		m_field = field;

		// s'il n'a plus d'énergie, il meurt
		if(m_energy == 0)
		{
			m_field.field.remove(this);
			stoppable.stop();
			m_alive = false;
		}
		
		// une seule action possible par tour : soit il mange, soit il charge, soit il se déplace
		if(m_alive)
		{
			if(m_charge > 0)
			{
				m_charge--;
				m_energy += Constants.foodEnergy;
				
			}
			else if(isOnFood() && (m_charge < m_chargeMax))
			{
				m_charge++;
				getFood(m_spot).decreaseQuantity();
			}
			else
			{
				Spot nearestFoodSpot = nearestFoodSpot();
				moveTowards(nearestFoodSpot);
			}
			
			m_energy--;
		}
	}
}
