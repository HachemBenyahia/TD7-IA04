package gui;

import java.awt.Color;

import javax.swing.JFrame;

import model.Constants;
import model.Field;
import model.Food;
import model.Insect;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.grid.ObjectGridPortrayal2D;
import sim.portrayal.grid.SparseGridPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;

public class Ui extends GUIState
{
	public static int nbFrames = Constants.nbFrames;
	public JFrame frame;
	public Display2D display;
	SparseGridPortrayal2D fieldPortrayal = new SparseGridPortrayal2D();
	
	public Ui(SimState state) 
	{
		super(state);
	}

	public void start() 
	{
		super.start();
		setupPortrayals();
	}
	
	public void load(SimState state) 
	{
		super.load(state);
		setupPortrayals();
	}
	
	public void init(Controller controller) 
	{
		super.init(controller);
		
		display = new Display2D(nbFrames, nbFrames, this);
		display.setClipping(false);
		
		frame = display.createFrame();
		frame.setTitle("TD7 IA04 - Simulation");
		
		controller.registerFrame(frame);
		frame.setVisible(true);
		
		display.attach(fieldPortrayal, "Field");
	}
	
	private OvalPortrayal2D getInsectPortrayal() 
	{
		OvalPortrayal2D portrayal = new OvalPortrayal2D();
		
		portrayal.paint = Color.blue;
		portrayal.filled = true;
		
		return portrayal;
	}

	private OvalPortrayal2D getFoodPortrayal() 
	{
		OvalPortrayal2D portrayal = new OvalPortrayal2D();
		
		portrayal.paint = Color.red;
		portrayal.filled = true;
		
		return portrayal;
	}
	
	public void setupPortrayals() 
	{
		Field field = (Field) state;
		
		fieldPortrayal.setField(field.field);
		fieldPortrayal.setPortrayalForClass(Insect.class, getInsectPortrayal());
		fieldPortrayal.setPortrayalForClass(Food.class, getFoodPortrayal());
		
		display.reset();
		display.setBackdrop(Color.white);
	  
		display.repaint();
	}
}
