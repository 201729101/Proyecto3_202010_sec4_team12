package test.data_structures;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import model.data_structures.ColaDePrioridad;
import model.data_structures.ColaDePrioridadIndexada;
import model.data_structures.Comparendo;

public class TestColasDePrioridad {

	private ColaDePrioridad cola1;
	
	private ColaDePrioridadIndexada cola2;
	
	private void setUp1()
	{
		cola1 = new ColaDePrioridad<>();
		
		cola2 = new ColaDePrioridadIndexada(100000);
	}
	
	private void setUp2()
	{
		cola1 = new ColaDePrioridad<Comparendo>(1000000);
		cola2 = new ColaDePrioridadIndexada<Comparendo>(100000);
		Date f = new Date("2018/01/01");
		
		Comparendo comp1 = new Comparendo(1,new Date("2018/01/01"),"","","P�blico","A1","SER� INMOVILIZADO","","",0.0,0.0);
		Comparendo comp2 = new Comparendo(2,new Date("2018/02/01"),"","","Particular","B2","SER� INMOVILIZADO","","",0.0,0.0);
		Comparendo comp3 = new Comparendo(3,new Date("2018/03/01"),"","","Oficial","C3","SER� INMOVILIZADO","","",0.0,0.0);
		Comparendo comp4 = new Comparendo(4,new Date("2018/04/01"),"","","P�blico","D4","LICENCIA DE CONDUCCI�N","","",0.0,0.0);
		Comparendo comp5 = new Comparendo(5,new Date("2018/05/01"),"","","Particular","E5","LICENCIA DE CONDUCCION","","",0.0,0.0);
		Comparendo comp6 = new Comparendo(6,new Date("2018/06/01"),"","","Oficial","F6","LICENCIA DE CONDUCCI�N","","",0.0,0.0);
		Comparendo comp7 = new Comparendo(7,new Date("2018/07/01"),"","","P�blico","G7","Otra cosa","","",0.0,0.0);
		Comparendo comp8 = new Comparendo(8,new Date("2018/08/01"),"","","Particular","H8","Otra cosa","","",0.0,0.0);
		Comparendo comp9 = new Comparendo(9,new Date("2018/09/01"),"","","Oficial","I9","Otra cosa","","",0.0,0.0);
		Comparendo comp10 = new Comparendo(10,new Date("2018/10/01"),"","","P�blico","J10","Otra cosa","","",0.0,0.0);
		
		cola1.insertar(comp1);
		cola1.insertar(comp2);
		cola1.insertar(comp3);
		cola1.insertar(comp4);
		cola1.insertar(comp5);
		cola1.insertar(comp6);
		cola1.insertar(comp7);
		cola1.insertar(comp8);
		cola1.insertar(comp9);
		cola1.insertar(comp10);
	}
	
	@Test
	public void testColaDePRioridad()
	{
		setUp1();
		assertTrue(cola1.esVacia());
		assertEquals(0,cola1.tamano());
	}

	@Test
	public void testInsertar()
	{
		setUp2();
		Comparable[] arreglo1 = cola1.getArreglo();
//		ArrayList arreglo2 = cola2.getArreglo();
		
		for(int i=2 ; i<11 ; i++)
		{
			assertNotNull(arreglo1[i]);
			assertTrue(cola1.less(i, i/2));
//			assertNotNull(arreglo2.get(i));
		}
	}
	
	@Test
	public void testEliminarMax()
	{
		setUp2();
		Comparendo r1 = (Comparendo) cola1.eliminarMax();
		Comparendo r2 = (Comparendo) cola1.eliminarMax();
		Comparendo r3 = (Comparendo) cola1.eliminarMax();
		
		assertEquals(10,r1.getId());
		assertEquals("Otra cosa",r1.getDesc());
		assertEquals(7,r2.getId());
		assertEquals("Otra cosa",r2.getDesc());
		assertEquals(4,r3.getId());
		assertEquals("LICENCIA DE CONDUCCI�N",r3.getDesc());		
	}
}
