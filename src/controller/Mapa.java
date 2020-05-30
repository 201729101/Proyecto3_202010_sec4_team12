package controller;

import com.teamdev.jxmaps.Map;

import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.swing.MapView;
import com.sun.prism.paint.Color;

import model.data_structures.Cola;
import model.data_structures.Comparendo;
import model.data_structures.Edge;
import model.data_structures.Estacion;
import model.data_structures.Grafo;
import model.data_structures.GrafoND;
import model.data_structures.ListaEncadenada;
import model.data_structures.Pila;
import model.data_structures.Vertice;
import model.logic.Modelo;
import view.View;
/**
 * Clase del mapa a graficar 
 */
public class Mapa extends MapView
{
	/**
	 * Numero de datos impresos
	 */
	public int i=1;
	
	/**
	 * Mapa sobre el cual se grafica
	 */
	public Map map;
	
	/**
	 * Vista donde se imprime la información
	 */
	public View view;
	
	/**
	 * Paleta de colores
	 */
	public String[] colores = {"#000000","#FF0033","#FF9900","#FFF400","#22FF00","#00FFF9"
			,"#0004FF","#9600FF","#FD00FF","#5A3918","#2E5A18","#99049A","#F95E9D"};
	
	/**
	 * Contructor del mapa. Construye un mapa con una pila de vertices recibida por parámetro y grafica la pila de vertices
	 * @param pila Pila de vertices a graficar
	 * @param vista Objeto de vista donde se grafica la información
	 */
	public Mapa(Pila<Vertice> pila, View vista) 
	{
		view = vista;
		setOnMapReadyHandler(new MapReadyHandler(){

			@Override
			public void onMapReady(MapStatus arg0) 
			{
				if(arg0==MapStatus.MAP_STATUS_OK)
				{
					map = getMap();
					MapOptions op = new MapOptions();
					MapTypeControlOptions es = new MapTypeControlOptions();
					op.setMapTypeControlOptions(es);
					map.setOptions(op);

					map.setCenter(new LatLng(4.6097102, -74.081749));
					map.setZoom(10);
					graficarPila(pila);

				}
			}
		});
		
		System.out.println("Finish");
	}

	/**
	 * Contructor del mapa. Construye un mapa con una lista encadenada de pilas de vertices recibida por parámetro y grafica los vertices de las pilas
	 * @param pila  Lista encadenada de pilas de vertices a graficar
	 * @param i indicador sobre el metodo utilizado para graficar vertices
	 * @param vista Objeto de vista para imprimir información
	 */
	public Mapa(ListaEncadenada pilas, int h, View vista) 
	{
		view = vista;
		setOnMapReadyHandler(new MapReadyHandler(){

			@Override
			public void onMapReady(MapStatus arg0) 
			{
				if(arg0==MapStatus.MAP_STATUS_OK)
				{
					map = getMap();
					MapOptions op = new MapOptions();
					MapTypeControlOptions es = new MapTypeControlOptions();
					op.setMapTypeControlOptions(es);
					map.setOptions(op);

					map.setCenter(new LatLng(4.6097102, -74.081749));
					map.setZoom(10);

					if(h==1)
						graficarLista(pilas);
					else if(h==2)
					{
						int l=0;
						for(Object e: pilas)
						{
							l++;
							i=1;
							Pila pila = (Pila) e;
							double dist = (double) pila.pop();
							Cola cola = (Cola) pila.pop();
							view.printMessage("Camino "+l);
							view.printMessage("Distancia del camino: "+dist+"\n");
							view.printColaArcos(cola);
							view.printSeparador();
							graficarPila2Colores(pila);
						}
					}
					else
						System.out.println("Comando incorrecto");
				}
			}
		});
		
		System.out.println("Finish");
	}

	/**
	 * Contructor del mapa. Construye un mapa con un objeto de clase modelo entregado por parámetro
	 * @param model Modelo con la informacion para graficar 
	 * @param vista Objeto de vista para imprimir información
	 */
	public Mapa(GrafoND grafo,ListaEncadenada estaciones, View vista) 
	{
		view = vista;
		setOnMapReadyHandler(new MapReadyHandler(){

			@Override
			public void onMapReady(MapStatus arg0) 
			{
				if(arg0==MapStatus.MAP_STATUS_OK)
				{
					map = getMap();
					MapOptions op = new MapOptions();
					MapTypeControlOptions es = new MapTypeControlOptions();
					op.setMapTypeControlOptions(es);
					map.setOptions(op);

					map.setCenter(new LatLng(4.6097102, -74.081749));
					map.setZoom(10);
					graficarArcos(grafo);
					graficarEstaciones(estaciones,grafo);

				}
			}
		});
	}

	/**
	 * Grafica las estaciones de policia con una lista de estaciones y un grafo recibidos por parámetro
	 * @param grafo Grafo no dirigido con informacion para graficar
	 * @param estaciones Lista de estaciones para pintar
	 */
	private void graficarEstaciones(ListaEncadenada estaciones, GrafoND grafo)
	{
		for(Object e: estaciones)
		{
			Estacion est = (Estacion) e;
			String color = colores[grafo.id(est.getVertice())];
			double eLat = est.getEPOLATITUD();
			double eLng = est.getEPOLONGITU();

			Circle circle = new Circle(map);
			circle.setCenter(new LatLng(eLat,eLng));
			circle.setRadius(10*(est.getNumComp()+1));
			CircleOptions options = new CircleOptions();
			options.setFillColor(color);
			options.setFillOpacity(100);
			options.setStrokeColor(color);
			circle.setOptions(options);
		}
	}

	/**
	 * Grafica los arcos de un grafo recibido por parámetro
	 * @param grafo Grafo con la informacion de los vertices y arcos
	 */
	private void graficarArcos(GrafoND grafo)
	{
		int n = grafo.V();
		//		GrafoND.CC cc = grafo.count();
		for(int i=0 ; i<n ; i++)
		{
			Vertice vert = (Vertice) grafo.getInfoVertex(i);
			if(vert!=null)
			{
				double vLat = vert.getLatitud();
				double vLng = vert.getLongitud();
				String color = colores[grafo.id(vert.getId())];
				ListaEncadenada arcos = grafo.getEdges(i);
				for(Object e:arcos)
				{
					Edge arc = (Edge) e;
					Vertice vert2 = (Vertice) grafo.getInfoVertex(arc.getDestino());
					LatLng[] path = {new LatLng(vert.getLatitud(), vert.getLongitud()), 
							new LatLng(vert2.getLatitud(),vert2.getLongitud())};
					Polyline polyline = new Polyline(map);
					polyline.setPath(path);
					PolylineOptions options = new PolylineOptions();
					options.setStrokeColor(color);
					polyline.setOptions(options);
				}
			}
		}
	}

	/**
	 * Grafica una pila de vertices
	 * @param coord Pila de vertices
	 */
	public void graficarPila(Pila coord)
	{
		Vertice inicio = (Vertice) coord.pop();
		double latAnt = inicio.getLatitud();
		double lonAnt = inicio.getLongitud();
		if(i<=20)
			view.printVertice(inicio);
		Circle circle = new Circle(map);
		circle.setCenter(new LatLng(latAnt,lonAnt));
		circle.setRadius(100);
		CircleOptions options = new CircleOptions();
		options.setFillColor("#FDFF00");
		options.setFillOpacity(100);
		options.setStrokeColor("#00FF11");
		circle.setOptions(options);
		while(!coord.esVacia())
		{
			Vertice vert = (Vertice) coord.pop();
			i++;
			if(i<=20)
				view.printVertice(vert);
			LatLng[] path = {new LatLng(latAnt, lonAnt), 
					new LatLng(vert.getLatitud(),vert.getLongitud())};
			Polyline polyline = new Polyline(map);
			polyline.setPath(path);
			latAnt = vert.getLatitud();
			lonAnt = vert.getLongitud();
		}
		Circle circlef = new Circle(map);
		circlef.setCenter(new LatLng(latAnt,lonAnt));
		circlef.setRadius(85);
		circlef.setOptions(options);
	}

	/**
	 * Grafica una lista de pilas de vertices
	 * @param pilas Lista de pilas de vertices
	 */
	private void graficarLista(ListaEncadenada pilas)
	{
		for(Object e:pilas)
		{
			Pila pila = (Pila) e;
			graficarPila(pila);
		}
	}

	/**
	 * Grafica una pila de vertices diferenciando entre el origen y el final
	 * @param coord Pila de vertices
	 */
	public void graficarPila2Colores(Pila coord)
	{
		int i=1;
		Vertice inicio = (Vertice) coord.pop();
		if(i<=20)
			view.printVertice(inicio);
		double latAnt = inicio.getLatitud();
		double lonAnt = inicio.getLongitud();
		Circle circle = new Circle(map);
		circle.setCenter(new LatLng(latAnt,lonAnt));
		circle.setRadius(60);
		CircleOptions options = new CircleOptions();
		options.setFillColor("#FDFF00");
		options.setFillOpacity(100);
		options.setStrokeColor("#00FF11");
		circle.setOptions(options);
		while(!coord.esVacia())
		{
			Vertice vert = (Vertice) coord.pop();
			i++;
			if(i<=20)
				view.printVertice(vert);
			LatLng[] path = {new LatLng(latAnt, lonAnt), 
					new LatLng(vert.getLatitud(),vert.getLongitud())};
			Polyline polyline = new Polyline(map);
			polyline.setPath(path);
			latAnt = vert.getLatitud();
			lonAnt = vert.getLongitud();
		}
		Circle circlef = new Circle(map);
		circlef.setCenter(new LatLng(latAnt,lonAnt));
		circlef.setRadius(60);
		CircleOptions optionsf = new CircleOptions();
		optionsf.setFillColor("#399639");
		optionsf.setFillOpacity(100);
		optionsf.setStrokeColor("#2DD22D");
		circlef.setOptions(optionsf);
	}
	
//CODIGOS PARA PRUEBAS
//	private void graficarEstaDif(Grafo grafo, ListaEncadenada estaciones)
//	{
//		for(Object e: estaciones)
//		{
//			Estacion est = (Estacion) e;
//			double eLat = est.getEPOLATITUD();
//			double eLng = est.getEPOLONGITU();
//			double iLat = ((Vertice) grafo.getInfoVertex(est.getVertice())).getLatitud();
//			double iLng = ((Vertice) grafo.getInfoVertex(est.getVertice())).getLongitud();
//
//			Circle circle = new Circle(map);
//			circle.setCenter(new LatLng(eLat,eLng));
//			circle.setRadius(40);
//			CircleOptions options = new CircleOptions();
//			options.setFillColor(colores[0]);
//			options.setFillOpacity(100);
//			options.setStrokeColor(colores[0]);
//			circle.setOptions(options);
//			Circle circle2 = new Circle(map);
//			circle2.setCenter(new LatLng(iLat,iLng));
//			circle2.setRadius(40);
//			CircleOptions options2 = new CircleOptions();
//			options2.setFillColor(colores[1]);
//			options2.setFillOpacity(100);
//			options2.setStrokeColor(colores[1]);
//			circle2.setOptions(options2);
//		}
//	}
//
//	private void graficarCompDif(Grafo grafo, ListaEncadenada estaciones)
//	{
//		for(Object e: estaciones)
//		{
//			Comparendo est = (Comparendo) e;
//			double eLat = est.getLatitud();
//			double eLng = est.getLongitud();
//			double iLat = ((Vertice) grafo.getInfoVertex(est.getVertice())).getLatitud();
//			double iLng = ((Vertice) grafo.getInfoVertex(est.getVertice())).getLongitud();
//
//			Circle circle = new Circle(map);
//			circle.setCenter(new LatLng(eLat,eLng));
//			circle.setRadius(40);
//			CircleOptions options = new CircleOptions();
//			options.setFillColor(colores[0]);
//			options.setFillOpacity(100);
//			options.setStrokeColor(colores[0]);
//			circle.setOptions(options);
//			Circle circle2 = new Circle(map);
//			circle2.setCenter(new LatLng(iLat,iLng));
//			circle2.setRadius(40);
//			CircleOptions options2 = new CircleOptions();
//			options2.setFillColor(colores[1]);
//			options2.setFillOpacity(100);
//			options2.setStrokeColor(colores[1]);
//			circle2.setOptions(options2);
//		}
//	}
}
