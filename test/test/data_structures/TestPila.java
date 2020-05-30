package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Pila;

public class TestPila {

	private Pila pila;

	@Before
	public void before(){
		pila = new Pila();
	}

	public void setUp1(){
		try
		{
			for(int i = 0; i<10 ; i++)
			{
				pila.push(""+i);
			}
		}
		catch(Exception e)
		{
			fail("No debería genera excepción");
		}
	}

	@Test
	public void testPila() {
		assertTrue(pila.esVacia());
		setUp1();
		assertFalse(pila.esVacia());
		assertEquals(10,pila.darTamano());
		assertEquals("9",pila.darPrimero());
	}

	@Test
	public void testPush(){
		setUp1();
		try
		{
			pila.push("10");
			assertEquals(11,pila.darTamano());
			assertEquals("10",pila.darPrimero());
		}
		catch(Exception e)
		{
			fail("No debería generar excepción");
		}
	}

	@Test
	public void testPop(){
		assertNull(pila.pop());
		setUp1();
		String r = (String) pila.pop();
		assertEquals("9",r);
		assertEquals(9,pila.darTamano());
	}

	@Test
	public void testBuscar(){
		assertNull(pila.buscar("3"));
		setUp1();
		assertEquals("3",pila.buscar("3"));
		assertNull(pila.buscar("10"));
	}
}
