package model;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;

public class Insect implements Steppable
{
	private int m_distanceDeplacement;
	private int m_distancePerception;
	private int m_chargeMax;
	private int m_energy;
	private int m_charge;
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
	/*
	private int[] nearestFood()
	{
		int[] spot = {0, 0};
		
		
		return spot;
	}*/
	
	public void step(SimState state) 
	{
		Field field = (Field) state;
		
		if(m_energy == 0)
		{
			field.remove(this);
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
		}
	}
}
