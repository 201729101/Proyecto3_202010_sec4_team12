package model.logic;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
import model.data_structures.Pila;
import model.data_structures.TablaSectores;
import model.data_structures.Cola;
import model.data_structures.ColaDePrioridad;
import model.data_structures.Comparendo;
import model.data_structures.Edge;
import model.data_structures.Estacion;
import model.data_structures.Grafo;
import model.data_structures.GrafoND;
import model.data_structures.Vertice;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo
{	
	/**
	 * Grafo con informacion vial
	 */
	public Grafo grafo;

	/**
	 * Lista de esatciones
	 */
	public ListaEncadenada estaciones;

	/**
	 * Cola de prioridad de comparendos
	 */
	public ListaEncadenada<Comparendo> comparendos;

	/**
	 * Tabla de sectores
	 */
	public TablaSectores tablaSec;

	/**
	 * Constructor
	 */
	public Modelo ()
	{
		grafo =  new Grafo(228046);
		estaciones = new ListaEncadenada();
		comparendos = new ListaEncadenada<Comparendo>();
	}

	/**
	 * Inicia la lectura del archivo JSON y rellena la lista de estaciones
	 * @param path, ruta del archivo a leer
	 */
	public Estacion cargarEstaciones(String PATH) 
	{
		JsonReader reader;
		Estacion mayor = null;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();
			for(JsonElement e: e2) {
				JsonObject a = e.getAsJsonObject().get("properties").getAsJsonObject();
				int OBJECTID = a.get("OBJECTID").getAsInt();
				//				int EPOCOD_PLAN = a.get("EPOCOD_PLAN").getAsInt();
				//				String EPOCOD_ENT = a.get("EPOCOD_ENT").getAsString();
				//				String EPOCOD_PROY = a.get("EPOCOD_PROY").getAsString();
				//				int EPOANIO_GEO= a.get("EPOANIO_GEO").getAsInt();
				//				long EPOFECHA_INI = a.get("EPOFECHA_INI").getAsLong();
				//				long EPOFECHA_FIN = a.get("EPOFECHA_FIN").getAsLong();
				String EPODESCRIP = a.get("EPODESCRIP").getAsString();
				//				String EPOEST_PROY = a.get("EPOEST_PROY").getAsString();
				//				String EPOINTERV_ESP = a.get("EPOINTERV_ESP").getAsString();
				String EPODIR_SITIO = a.get("EPODIR_SITIO").getAsString();
				//				String EPOCOD_SITIO = a.get("EPOCOD_SITIO").getAsString();
				double EPOLATITUD = a.get("EPOLATITUD").getAsDouble();
				double EPOLONGITU = a.get("EPOLONGITU").getAsDouble();
				//				String EPOSERVICIO = a.get("EPOSERVICIO").getAsString();
				String EPOHORARIO = a.get("EPOHORARIO").getAsString();
				String EPOTELEFON = a.get("EPOTELEFON").getAsString();
				String EPOCELECTR = a.get("EPOCELECTR").getAsString();
				//				String EPOCONTACT = a.get("EPOCONTACT").getAsString();
				//				String EPOPWEB = a.get("EPOPWEB").getAsString();
				//				String EPOIUUPLAN = a.get("EPOIUUPLAN").getAsString();
				//				String EPOIUSCATA = a.get("EPOIUSCATA").getAsString();
				//				String EPOIULOCAL = a.get("EPOIULOCAL").getAsString();
				//				String EPOEASOCIA = a.get("EPOEASOCIA").getAsString();
				String EPOFUNCION = a.get("EPOFUNCION").getAsString();
				//				String EPOTEQUIPA = a.get("EPOTEQUIPA").getAsString();
				String EPONOMBRE = a.get("EPONOMBRE").getAsString();
				//				String EPOIDENTIF = a.get("EPOIDENTIF").getAsString();
				//				long EPOFECHA_C = a.get("EPOFECHA_C").getAsLong();

				//				Estacion est =  new Estacion(OBJECTID ,EPOCOD_PLAN ,EPOCOD_ENT,EPOCOD_PROY,EPOANIO_GEO,EPOFECHA_INI,EPOFECHA_FIN,EPODESCRIP,EPOEST_PROY,EPOINTERV_ESP,EPODIR_SITIO,EPOCOD_SITIO,EPOLATITUD,EPOLONGITU,EPOSERVICIO,EPOHORARIO,EPOTELEFON,EPOCELECTR,EPOCONTACT,EPOPWEB,EPOIUUPLAN,EPOIUSCATA,EPOIULOCAL,EPOEASOCIA,EPOFUNCION,EPOTEQUIPA,EPONOMBRE,EPOIDENTIF,EPOFECHA_C);
				Estacion est =  new Estacion(OBJECTID ,EPODESCRIP,EPODIR_SITIO,EPOLATITUD,EPOLONGITU,EPOHORARIO,EPOTELEFON,EPOCELECTR,EPOFUNCION,EPONOMBRE);
				estaciones.agregarFinal(est);
				asignarVerticeEstacion(est);
				if(mayor==null)
					mayor=est;
				else if(OBJECTID>mayor.getOBJECTID())
					mayor=est;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Hubo un error en la carga");
			e.printStackTrace();
		}
		return mayor;
	}

	/**
	 * Inicia la lectura del archivo JSON de comparendos y rellena la lista
	 * @param path, ruta del archivo a leer
	 */
	public Comparendo cargarComparendos(String PATH) 
	{
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();

			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Comparendo mayor = null;
			int num = 0;
			for(JsonElement e: e2) 
			{
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				String[] s1 = s.split("T");
				String[] s2 = s1[0].split("-");
				String s3 = s2[0]+"/"+s2[1]+"/"+s2[2]+" "+s1[1];
				Date FECHA_HORA = parser.parse(s3); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRACCION").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();
				String MUNICIPIO = e.getAsJsonObject().get("properties").getAsJsonObject().get("MUNICIPIO").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, DES_INFRAC, LOCALIDAD, MUNICIPIO, latitud, longitud);
				asignarVerticeComparendo(c);
				comparendos.agregarFinal(c);
				//				System.out.println(++num);
				if(mayor == null) 
					mayor =c;
				else if(c.getId()>mayor.getId())
					mayor = c;
			}
			e2 =null;
			return mayor;
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}	
	}

	/**
	 * Asigna el costo por numero de comparendos a cada arco
	 */
	public void asignarCostosComparendos()
	{
		for(int i=0 ; i<grafo.V() ; i++)
		{
			ListaEncadenada<Edge> adj = grafo.getEdges(i);
			int num = ((Vertice) grafo.getInfoVertex(i)).getComparendos().darTamano();
			for(Object e:adj)
			{
				Edge edge = (Edge) e;
				num += ((Vertice) grafo.getInfoVertex(edge.getDestino())).getComparendos().darTamano();
				edge.setNumComparendos(num);
			}
		}
	}

	/**
	 * Clase para la distancia Haversiana entre coordenadas
	 */
	public class Haversine 
	{
		private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

		public double distance(double startLat, double startLong, double endLat, double endLong) 
		{

			double dLat  = Math.toRadians((endLat - startLat));
			double dLong = Math.toRadians((endLong - startLong));

			startLat = Math.toRadians(startLat);
			endLat   = Math.toRadians(endLat);

			double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

			return EARTH_RADIUS * c; // <-- d
		}

		public double haversin(double val) {
			return Math.pow(Math.sin(val / 2), 2);
		}
	}

	/**
	 * Escribe un archivo JSON con los datos del grafo
	 * @param path Ruta delarchivo a escribir
	 */
	public void escribirJson(String path,GrafoND g)
	{	
		try
		{
			FileWriter file = new FileWriter(path);
			JSONArray graf = new JSONArray();
			int max = g.V();
			for(int i = 0; i<g.V() ; i++)
			{
				JSONObject vertice =  new JSONObject();
				Vertice vert = (Vertice) g.getInfoVertex(i);
				if(vert!=null)
				{
					vertice.put("OBJECT_ID", vert.getId());
					vertice.put("LONGITUD", vert.getLongitud());
					vertice.put("LATITUD", vert.getLatitud());
					vertice.put("NUM_COMPARENDOS", vert.getComparendos().darTamano());
					if(vert.getEstacion()!=null)
						vertice.put("ESTACION", vert.getEstacion().getOBJECTID());
					else
						vertice.put("ESTACION", "");
					JSONArray arcos = new JSONArray();
					ListaEncadenada<Edge> edges = g.getEdges(i);
					for(Object e: edges)
					{
						JSONObject arco = new JSONObject();
						Edge arc = (Edge) e;
						arco.put("ORIGEN", arc.getOrigen());
						arco.put("DESTINO", arc.getDestino());
						arco.put("DISTANCIA", arc.getDistance());
						arco.put("NUMERO_COMP", arc.getNumComparendos());
						arcos.add(arco);
					}
					vertice.put("arcos", arcos);
					graf.add(vertice);
				}
			}

			file.write(graf.toJSONString());
			file.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Lee el archivo JSON creado previamente
	 * @param path Ruta del archivp
	 * @return String con numero de vertices y arcos.
	 */
	public void leerArchivo(String path)
	{
		tablaSec = new TablaSectores();
		JsonReader reader;
		try 
		{
			reader = new JsonReader(new FileReader(path));
			JsonArray elem = JsonParser.parseReader(reader).getAsJsonArray();
			for(JsonElement e: elem)
			{
				int id = e.getAsJsonObject().get("OBJECTID").getAsInt();
				double lon = e.getAsJsonObject().get("LONGITUD").getAsDouble();
				double lat = e.getAsJsonObject().get("LATITUD").getAsDouble();
				tablaSec.agregar(lat, lon, id);
				Vertice vert = new Vertice(id,lon,lat);
				grafo.addVertex(id, vert);
				JsonArray arcos = e.getAsJsonObject().get("arcos").getAsJsonArray();
				for(JsonElement o:arcos)
				{
					int ID = o.getAsJsonObject().get("DESTINO").getAsInt();
					double dis = o.getAsJsonObject().get("DISTANCIA").getAsDouble();
					Edge ar = new Edge(id,ID,dis);
					grafo.addEdge(id, ID, dis);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Returna el vertice mas cercano a una latitud y una longitud
	 * @param lat double de la latitud
	 * @param lon dounle de la longitud
	 * @return vertice mas cercano
	 */
	public Vertice darMenorDistancia(double lat, double lon)
	{
		double minDist = 10000000000.0;
		Vertice menor = null;

		ListaEncadenada<Integer> idsSector = tablaSec.darIdsSector(lat, lon);
		//		System.out.println("Empieza "+idsSector.darTamano());
		int num = 0;
		for(Object e : idsSector)
		{
			int i = (int) e;
			if(num>idsSector.darTamano())
				System.out.println("ERROR!!!! " +(num-idsSector.darTamano()));
			Vertice vert = ((Vertice) grafo.getInfoVertex(i));
			double vLat = vert.getLatitud();
			double vLon = vert.getLongitud();
			double difLat = vLat - lat;
			double difLon = vLon - lon;
			double mod = (difLat*difLat)+(difLon*difLon);
			double dist = Math.sqrt(mod);

			if (dist<minDist)
				menor = vert;
			num++;
		}
		//		System.out.println("Termina");
		return menor;
	}

	/**
	 * Asigna el vertice mas cercano a un comparendo
	 * @param comp Comparendo
	 */
	public void asignarVerticeComparendo(Comparendo comp)
	{
		Vertice vert = darMenorDistancia(comp.getLatitud(), comp.getLongitud());
		vert.agregarComparendo(comp);
		comp.setVertice(vert.getId());
	}

	/**
	 * Asigna el vertice mas cercano a una estacion
	 * @param est Estacion
	 */
	public void asignarVerticeEstacion(Estacion est)
	{
		Vertice vert = darMenorDistancia(est.getEPOLATITUD(), est.getEPOLONGITU());
		vert.setEstacion(est);
		est.setVertice(vert.getId());
	}

	/**
	 * Retorna la pila de vertice de camino mas corto entre dos coordenadas
	 * @param iLat latitud incial
	 * @param iLon longitud incial 
	 * @param fLat latitud final
	 * @param fLon longitud final
	 * @return pila de vertices dle camino mas corto
	 */
	public Pila caminoMasCorto(double iLat, double iLon , double fLat , double fLon)
	{
		Vertice inicial = darMenorDistancia(iLat, iLon);
		Vertice finale = darMenorDistancia(fLat, fLon);
		double dist = 0.0;
		try
		{
			Edge[] path = grafo.shortestPath(inicial.getId(), finale.getId());
			Pila retorno = new Pila();
			//			Edge p = path[finale.getId()];
			//			int id = p.other(finale.getId());
			int id = finale.getId();
			retorno.push(finale);
			while(id!=inicial.getId())
			{
				Vertice vert = (Vertice) grafo.getInfoVertex(id);
				retorno.push(vert);
				Edge p = path[id];
				id = p.other(id);
				dist += p.getDistance();
			}
			retorno.push(inicial);
			retorno.push(dist);
			return retorno;
		}
		catch(Exception e)
		{
			System.out.println("No hay camino entre el vertice "+inicial.getId()+" y el vertice "+finale.getId());
			return null;
		}
	}

	/**
	 * Retorna lista de encadenada con distancia y pila de vertices de caminos mas cortos para la red de comparendos mas graves
	 * @param M Numero de comparendos mas graves a buscar
	 * @return Lista encadenada con distancia y pilas de vertices
	 */
	public ListaEncadenada mComp(int M)
	{
		ColaDePrioridad<Comparendo> cola = new ColaDePrioridad<Comparendo>();
		for(Object e: comparendos)
		{
			Comparendo comp = (Comparendo) e;
			cola.insertar(comp);
		}

		int n = 0;
		ListaEncadenada<Integer> retorno = new ListaEncadenada<Integer>();
		while(n<M)
		{
			int vert = cola.eliminarMax().getVertice();
			retorno.agregarFinal(vert);
			n++;
		}

		return arbolExpansion(retorno);
	}

	/**
	 * Retorna la lista encadenada de pilas de vertices de los caminos mas cortos
	 * @param ids Lista de indices de los vertices con los comparendos mas graves
	 * @return Lista de pilas de vertices con los caminos
	 */
	public ListaEncadenada arbolExpansion(ListaEncadenada<Integer> ids)
	{
		ListaEncadenada pilas = new ListaEncadenada();
		Cola arcos = new Cola();
		double dist = 0.0;
		int ini = ids.eliminarPrimero();
		ListaEncadenada<Integer> copia = new ListaEncadenada<Integer>();
		for(Object e: ids)
		{
			int vert = (int) e;
			copia.agregarFinal(vert);
		}
		Edge[] path = grafo.spanningTree(ini,ids);
		Vertice inicio = (Vertice) grafo.getInfoVertex(ini);
		for(Object e:copia)
		{
			Pila<Vertice> retorno = new Pila<Vertice>();
			int id = (int) e;
			while(id!=ini)
			{
				Vertice vert = (Vertice) grafo.getInfoVertex(id);
				try
				{
					Edge edge = path[id];
					id = edge.other(id);
					retorno.push(vert);
					dist += edge.getDistance();
					if(arcos.darTamano() <=20)
						arcos.agregar(edge);
				}
				catch(Exception h)
				{
					System.out.println("El comaprendo "+id+" es inaccesible");
					break;
				}
			}
			retorno.push(inicio);
			pilas.agregarFinal(retorno);
		}
		pilas.agregarInicio(arcos);
		pilas.agregarInicio(dist);
		return pilas;

	}

	/**
	 * Retorna lista de encadenada con distancia y pila de vertices de caminos mas cortos entre los comparendos mas graves y las estaciones
	 * @param M Numero de comparendos mas graves a buscar
	 * @return Lista encadenada con distancia y pilas de vertices
	 */
	public ListaEncadenada mEst(int M)
	{
		ColaDePrioridad<Comparendo> cola = new ColaDePrioridad<Comparendo>();
		for(Object e: comparendos)
		{
			Comparendo comp = (Comparendo) e;
			cola.insertar(comp);
		}

		int n = 0;
		Cola<Integer> retorno = new Cola<Integer>();
		while(n<M)
		{
			int vert = cola.eliminarMax().getVertice();
			retorno.agregar(vert);
			n++;
		}

		return caminosEstaciones(retorno);
	}

	/**
	 * Retorna la lista encadenada de pilas de vertices de los caminos mas cortos
	 * @param ids Cola de indices de los vertices con los comparendos mas graves
	 * @return Lista de pilas de vertices con los caminos
	 */
	public ListaEncadenada caminosEstaciones(Cola<Integer> ids)

	{
		ListaEncadenada pilas = new ListaEncadenada();
		while(!ids.esVacia())
		{
			double dist = 0.0;
			Cola arcos = new Cola();
			int ini = ids.eliminar();
			Pila pila = new Pila();
			Grafo.DijkstraSP info = grafo.estaciones(ini);
			int id = info.getVert();
			if(id!=-1)
			{
				Edge[] path = info.getEdgeTo();
				while(id!=ini)
				{
					Vertice vert = (Vertice) grafo.getInfoVertex(id);
					pila.push(vert);
					Edge edge = path[id];
					id = edge.other(id);
					dist += edge.getDistance();
					if(arcos.darTamano()<=20)
						arcos.agregar(edge);
					//						arcos.agregar("("+edge.getOrigen()+" --> "+edge.getDestino()+")");
				}
				Vertice inicio = (Vertice) grafo.getInfoVertex(ini);
				pila.push(inicio);
				pila.push(arcos);
				pila.push(dist);
				pilas.agregarFinal(pila);
			}
			else
			{
				System.out.println("El comparendo "+ini+" es inaccesible");
			}
		}
		return pilas;
	}

	/**
	 * Crea el grafo no dirigido con los caminos mas cortos entre cada comparendo y una estación
	 * @return Grafo no dirigido
	 */
	public GrafoND ultimoPunto()
	{
		GrafoND grafoND = new GrafoND(228046);
		int num = 0;
		for(Object e: comparendos)
		{
			Comparendo comp = (Comparendo) e;
			int ini = comp.getVertice();
			Vertice inicio = (Vertice) grafo.getInfoVertex(ini);
			Grafo.DijkstraSP info = grafo.estaciones(ini);
			int id = info.getVert();
			if(id!=-1)
			{
				if(grafoND.getInfoVertex(ini)==null)
					grafoND.addVertex(ini, inicio);
				((Vertice) grafo.getInfoVertex(id)).getEstacion().aumentarNum();
				Edge[] path = info.getEdgeTo();
				while(id!=ini)
				{
					Vertice vert = (Vertice) grafo.getInfoVertex(id);
					Edge act = path[id];
					if(grafoND.getInfoVertex(id)==null)
						grafoND.addVertex(id, vert);
					grafoND.addEdge(act.getOrigen(), act.getDestino(), act.getDistance());
					id = act.other(id);
				}
			}
			else
			{
				num++;
			}
		}
		//		System.out.println(num);
		return grafoND;
	}

	//CODIGOS UTILIES

	//	/**
	//	 * Lee el archivo de vertices y llena le grafo
	//	 * @param PATH Ruta del archivo
	//	 */
	//	public void cargarVertices(String PATH) 
	//	{
	//		tablaSec = new TablaSectores();
	//		try 
	//		{
	//			BufferedReader lectura = new BufferedReader( new FileReader(new File(PATH)));
	//			String linea = lectura.readLine();
	//			int i =1;
	//			while(linea!=null)
	//			{
	//				String[] datos = linea.split(",");
	//				int id = Integer.parseInt(datos[0]);
	//				double lon = Double.parseDouble(datos[1]);
	//				double lat = Double.parseDouble(datos[2]);
	//				tablaSec.agregar(lat, lon, id);
	//				Vertice vert = new Vertice(id,lon,lat);
	//				grafo.addVertex(id, vert);
	//				linea = lectura.readLine();
	//				i++;
	//			}
	//			lectura.close();
	//		} 
	//		catch (Exception e) 
	//		{
	//			//			System.out.println(e.getMessage());
	//			e.printStackTrace();
	//		}	
	//	}
	//
	//	/**
	//	 * Lee el archivo de arcos y rellena el grafo
	//	 * @param PATH Archivo a leer
	//	 */
	//	public void cargarArcos(String PATH)
	//	{
	//		try
	//		{
	//			Haversine dis = new Haversine();
	//			BufferedReader lectura = new BufferedReader( new FileReader(new File(PATH)));
	//			String linea = lectura.readLine();
	//			linea = lectura.readLine();
	//			linea = lectura.readLine();
	//			linea =lectura.readLine();
	//			while(!linea.equals("")||!linea.equals(null))
	//			{
	//				String[] datos = linea.split(" ");
	//				int org = Integer.parseInt(datos[0]);
	//				for(int i=1;i<datos.length;i++)
	//				{
	//					int id = Integer.parseInt(datos[i]);
	//					Vertice vert = (Vertice) grafo.getInfoVertex(org);
	//					Vertice two = (Vertice) grafo.getInfoVertex(id);
	//					double distance = 0.0;
	//
	//					if(vert!=null && two != null)
	//					{
	//						double long1 = vert.getLongitud();
	//						double lat1 = vert.getLatitud();
	//						double long2 = two.getLongitud();
	//						double lat2 = two.getLatitud();
	//						distance = dis.distance(lat1, long1, lat2, long2);
	//						grafo.addEdge(org, id, distance);
	//					}
	//				}
	//				linea = lectura.readLine();
	//			}
	//		}
	//		catch(Exception e)
	//		{
	//
	//		}
	//	}
}
