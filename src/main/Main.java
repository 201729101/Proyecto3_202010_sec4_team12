package main;
import controller.Controller;

public class Main {
	
	/**
	 * Corre el programa completo
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.gc();
		Controller controler = new Controller();
		controler.run();
	}
}
