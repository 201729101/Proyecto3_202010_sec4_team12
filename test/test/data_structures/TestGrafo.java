package test.data_structures;

import static org.junit.Assert.*;


import org.junit.Test;

import model.data_structures.Grafo;

public class TestGrafo {

	public Grafo grafo; 
	
	public void setUp1()
	{
		grafo = new Grafo(100);
		for(int i=1 ; i<100 ; i++)
		{
			grafo.addVertex(i, i);
			grafo.addEdge(i, 100-i,100-i-i);
			grafo.addEdge(i, (int) (100-i)/2, 0);
		}
	}
	
	@Test
	public void testVyE() 
	{
		setUp1();
		assertEquals(100,grafo.V());
		assertEquals(198,grafo.E());
	}
	
	@Test
	public void getInfoVertex() 
	{
		setUp1();
		for(int i=1 ; i<100 ; i++)
		{
			assertEquals(i,grafo.getInfoVertex(i));
		}
	}
	

}