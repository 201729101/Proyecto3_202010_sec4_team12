package controller;

import java.util.ArrayList;


import java.util.Date;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JFrame;

import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.demo.*;

import model.data_structures.*;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Arreglo de colores 
	 */
	public String[] colores = {"Negro","Rojo","Naranja","Amarillo","Verde","Cyan","Azul"
			,"Morado","Fucsia","Cafe","Verde oscuro","Purpuara","Rosa"};

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	/**
	 * Corre el sistema mediante la consola 
	 */
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1: 
//				long startTime1 = System.currentTimeMillis();
				modelo.leerArchivo("./data/Grafo.json");
				Estacion estMayor = modelo.cargarEstaciones("./data/estacionpolicia.geojson.json");
				Comparendo mayor = modelo.cargarComparendos("./data/Comparendos_DEI_2018_Bogotá_D.C_50000_.geojson");
				modelo.asignarCostosComparendos();
//				long endTime1 = System.currentTimeMillis();
//				long duration1 = endTime1 - startTime1;
//				System.out.println("Tiempo: "+duration1);
//				System.out.println(difComp());
//				System.out.println(difEst());
				view.printMessage("Comparendo con mayor OBJECTID:");
				view.printComparendo(mayor);
				view.printSeparador();
				view.printMessage("Estación con mayor OBJECTID:");
				view.printEstación(estMayor);
				view.printSeparador();
				view.printMessage("Numero de vertices en el grafo: "+modelo.grafo.getrV());
				view.printSeparador();
				view.printMessage("Numero de arcos en el grafo: "+modelo.grafo.E());
				view.printSeparador();
				break;

			case 2:
				view.printMessage("Ingrese primera coordenada separada por un guión <Latitud-Longitud>");
				String cord1 = lector.next();
				String[] cords1 = cord1.split(",");
				double lat1 = Double.parseDouble(cords1[0]);
				double lon1 = Double.parseDouble(cords1[1]);
				view.printMessage("Ingrese segunda coordenada separada por un guión <Latitud-Longitud>");
				String cord2 = lector.next();
				String[] cords2 = cord2.split(",");
				double lat2 = Double.parseDouble(cords2[0]);
				double lon2 = Double.parseDouble(cords2[1]);
				
				Pila pila = modelo.caminoMasCorto(lat1, lon1, lat2, lon2);
				double dist = (double) pila.pop();
				JFrame frame2 = new JFrame("Grafito");
				Mapa mapa2 = new Mapa(pila,view);
				view.printMessage("Distancia del camino: "+dist);
				frame2.add(mapa2,BorderLayout.CENTER);
				frame2.setSize(700,500);
				frame2.setVisible(true);
				break;

			case 3:
				view.printMessage("Ingrese numero de comparendos a buscar");
				int M = lector.nextInt();
				long startTime3 = System.currentTimeMillis();
				ListaEncadenada lista = modelo.mComp(M);
				long endTime3 = System.currentTimeMillis();
				long duration3 = endTime3 - startTime3;
				double dist3 = (double) lista.eliminarPrimero();
				Cola cola = (Cola) lista.eliminarPrimero();
				view.printMessage("Tiempo para encontrar solución: "+duration3+" milisegundos");
				view.printSeparador();
				JFrame frame = new JFrame("Grafito");
				Mapa mapa = new Mapa(lista,1,view);
				frame.add(mapa,BorderLayout.CENTER);
				frame.setSize(700,500);
				frame.setVisible(true);
				view.printSeparador();
				view.printColaArcos(cola);
				view.printSeparador();
				view.printMessage("Costo Monetario: U$"+dist3*10000);
				break;

			case 4:
				view.printMessage("Ingrese numero de comparendos a buscar");
				int M4 = lector.nextInt();
				long startTime4 = System.currentTimeMillis();
				ListaEncadenada cola4 = modelo.mEst(M4);
				long endTime4 = System.currentTimeMillis();
				long duration4 = endTime4 - startTime4;
				view.printMessage("Tiempo de duración: "+duration4+" milisegundos");
				view.printSeparador();
				JFrame frame4 = new JFrame("Grafito");
				Mapa mapa4 = new Mapa(cola4,2,view);
				frame4.add(mapa4,BorderLayout.CENTER);
				frame4.setSize(700,500);
				frame4.setVisible(true);
				break;

			case 5:
				
				long startTime5 = System.currentTimeMillis();
				GrafoND grafo = modelo.ultimoPunto();
				int cc = grafo.count();
				informacionFinal(cc, grafo);
				modelo.escribirJson("./data/NuevoGrafo.json", grafo);
//				System.out.println(cc);
				long endTime5 = System.currentTimeMillis();
				long duration5 = endTime5 - startTime5;
				view.printMessage("Tiempo de duración: "+duration5+" milisegundos");
				view.printSeparador();
				view.printMessage("Numero de vertices: "+grafo.getrV());
				view.printSeparador();
				view.printMessage("Numero de arcos: "+grafo.E());
				view.printSeparador();
				view.printCompPorEsta(modelo.estaciones);
				JFrame frame5 = new JFrame("Grafito");
				Mapa mapa5 = new Mapa(grafo,modelo.estaciones,view);
				frame5.add(mapa5,BorderLayout.CENTER);
				frame5.setSize(700,500);
				frame5.setVisible(true);
				break;
				
			case 6: 
				view.printMessage("--------- \n Hasta pronto !! \n---------");
				modelo = null;
				lector.close();
				System.gc();
				fin = true;
				break;	

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}
	
	/**
	 * Obtiene la información de los componenetes conectados y los imprime en la vista
	 * @param cc Numero de componenetes conectados
	 * @param grafo Grafo no dirigido con la informacion
	 */
	public void informacionFinal(int cc,GrafoND grafo)
	{
		ListaEncadenada lista = grafo.getIdsList();
		int i = 1;
		for(Object e: lista)
		{
			if(i>cc)
				System.out.println("Hubo error");
			String color = colores[i];
			int numVert = (int) e;
			String estaciones = estaciones(i,grafo);
			view.printInfoFinal(i, color, estaciones, numVert);
			i++;
		}
	}
	
	/**
	 * Retorna un string con los strings de las estaciones que hacen parte de un mismo comoponenete conectado
	 * @param count Numero de componenetes conectado
	 * @param grafo Grafo no dirigido con los datos
	 * @return String con los IDs de las estaciones del componente conectado
	 */
	public String estaciones(int count,GrafoND grafo)
	{
		String estaciones = "";
		for(Object e : modelo.estaciones)
		{
			Estacion est = (Estacion) e;
			int id = est.getVertice();
			if(grafo.id(id)==count)
				estaciones += est.getOBJECTID()+" , ";
		}
		return estaciones;
	}
	
//	public double difComp()
//	{
//		double suma = 0.0;
//		for(Object e: modelo.comparendos)
//		{
//			Comparendo comp = (Comparendo) e;
//			double iLat = comp.getLatitud();
//			double iLon = comp.getLongitud();
//			double fLat = ((Vertice) modelo.grafo.getInfoVertex(comp.getVertice())).getLatitud();
//			double fLon = ((Vertice) modelo.grafo.getInfoVertex(comp.getVertice())).getLongitud();
//			
//			suma += Math.sqrt((((iLat-fLat)*(iLat-fLat))+((iLon-fLon)*(iLon-fLon))));
//		}
//		
//		return suma/modelo.comparendos.darTamano();
//	}
//	
//	public double difEst()
//	{
//		double suma = 0.0;
//		for(Object e: modelo.estaciones)
//		{
//			Estacion comp = (Estacion) e;
//			double iLat = comp.getEPOLATITUD();
//			double iLon = comp.getEPOLONGITU();
//			double fLat = ((Vertice) modelo.grafo.getInfoVertex(comp.getVertice())).getLatitud();
//			double fLon = ((Vertice) modelo.grafo.getInfoVertex(comp.getVertice())).getLongitud();
//			
//			suma += Math.sqrt((((iLat-fLat)*(iLat-fLat))+((iLon-fLon)*(iLon-fLon))));
//		}
//		
//		return suma/modelo.comparendos.darTamano();
//	}
}
