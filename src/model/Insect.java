package model;

import model.Constants.Direction;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.util.Bag;

public class Insect implements Steppable
{
	public static int m_numberAlive = Constants.nbInsects;
	private int m_distanceDeplacement;
	private int m_distancePerception;
	private int m_chargeMax;
	private int m_energy;
	private int m_maxEnergy;
	private int m_charge;
	private Field m_field;
	private Spot m_spot;
	private int m_id;
	private boolean m_alive;
	public Stoppable stoppable;
	
	Insect(int id, Spot spot)
	{
		m_id = id;
		m_distanceDeplacement = Constants.defaultInitialDeplacement;
		m_distancePerception = Constants.defaultInitialPerception;
		m_chargeMax = Constants.defaultMaxLoad;
		m_energy = Constants.defaultInitialEnergy;
		m_charge = 0;
		m_spot = spot;
		m_alive = true;
		m_maxEnergy = Constants.defaultMaxEnergy;
	}
	
	Insect(int id, Spot spot, int distanceDeplacement, int distancePerception, int chargeMax)
	{
		m_id = id;
		m_spot = spot;
		m_distanceDeplacement = distanceDeplacement;
		m_distancePerception = distancePerception;
		m_chargeMax = chargeMax;
		m_alive = true;
		m_energy = Constants.defaultInitialEnergy;
		m_charge = 0;
		m_maxEnergy = Constants.defaultMaxEnergy;
	}
	
	public static int[] randomCapacities()
	{
		int distanceDeplacement, distancePerception, maxLoad;
		do
		{
			distanceDeplacement = (int) (Math.random() * Constants.capacity);
			distancePerception = (int) (Math.random() * Constants.capacity);
			maxLoad = (int) (Math.random()* Constants.capacity);
		}
		while(((distanceDeplacement + distancePerception + maxLoad) != 10) || ((distanceDeplacement == 0) || distancePerception == 0)
		|| (maxLoad == 0));
		
		return new int[] {distanceDeplacement, distancePerception, maxLoad};
	}
	
	public void printCapacities()
	{
		System.out.println(m_distanceDeplacement + ", " + m_distancePerception + ", " + m_chargeMax);
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

	public Spot nearestFoodSpot()
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
	public void moveTowards(Spot spot)
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
			m_numberAlive--;
			
			System.out.println(m_numberAlive + " insects still alive");
		}
		
		// une seule action possible par tour : soit il charge, soit il mange, soit il se déplace
		if(m_alive)
		{
			if(isOnFood() && (m_charge < m_chargeMax))
			{
				m_charge++;
				getFood(m_spot).decreaseQuantity();
			}
			else if((m_charge > 0) && ((m_energy + Constants.foodEnergy) <= m_maxEnergy))
			{
				m_charge--;
				m_energy += Constants.foodEnergy;
			}
			else
			{
				Spot nearestFoodSpot = nearestFoodSpot();
				
				// assign a random spot
				if(nearestFoodSpot.equals(m_spot))
					nearestFoodSpot = m_field.getFreeSpot();
				
				moveTowards(nearestFoodSpot);
			}
			
			m_energy--;
		}
	}
}
