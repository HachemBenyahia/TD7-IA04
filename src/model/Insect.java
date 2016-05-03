package model;

import sim.engine.SimState;
import sim.engine.Steppable;

public class Insect implements Steppable
{
	private int m_distanceDeplacement;
	private int m_distancePerception;
	private int m_chargeMax;
	private int m_energy;
	private int m_charge;
	
	Insect()
	{
		m_distanceDeplacement = 1;
		m_distancePerception = 1;
		m_chargeMax = 1;
		m_energy = 5;
		m_charge = 0;
	}
	
	Insect(int distanceDeplacement, int distancePerception, int chargeMax)
	{
		m_distanceDeplacement = distanceDeplacement;
		m_distancePerception = distancePerception;
	}
	
	public void step(SimState state) 
	{
	}
}
