package main;

import sim.display.Console;
import gui.Ui;
import model.Field;

public class Main 
{
	public static void runUI() 
	{
		Field field = new Field(System.currentTimeMillis());
		Ui ui = new Ui(field);
		
		Console console = new Console(ui);
		console.setVisible(true);
	}	

	public static void main(String[] args) 
	{
		runUI();
	}
}
