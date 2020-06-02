package view;

import java.util.ArrayList;

import java.util.Date;

import model.data_structures.Cola;
import model.data_structures.Comparendo;
import model.data_structures.Edge;
import model.data_structures.Estacion;
import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
import model.data_structures.Vertice;
import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
	    /**
	     *Imprime el menú 
	     */
		public void printMenu()
		{
			System.out.println("1. Cargar grafo completo con estaciones y vertices");
			System.out.println("2. Graficar camino mas corto");
			System.out.println("3. Graficar red de camaras");
			System.out.println("4. Graficar caminos mas cortos de estaciones a M comparendos más graves");
			System.out.println("5. Gráficar zonas de cobertura");
			System.out.println("6. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		/**
		 * Imprime un mensaje recibido por parámetro
		 * @param mensaje mensaje a imprimir
		 */
		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}	
		
		/**
		 * Imprime un separador
		 */
		public void printSeparador()
		{
			System.out.println("------------------------------------- \n");
		}
		
		/**
		 * Imprime los datos esceniales de una estacion recibida por parámetro
		 * @param inf Estacion a imprimir
		 */
		public void printEstación(Estacion inf)

		{
			System.out.println("[");
			System.out.println("ID: "+inf.getOBJECTID());
			System.out.println("Descripción: "+inf.getEPODESCRIP());
			System.out.println("Dirección: " + inf.getEPODIR_SITIO());
			System.out.println("Horario: "+inf.getEPOHORARIO());
			System.out.println("Telefono: "+inf.getEPOTELEFON());
			System.out.println("Correo electrónico: "+inf.getEPOCELECTR());
			System.out.println("Nombre: "+inf.getEPONOMBRE());
			System.out.println("Función: "+inf.getEPOFUNCION());
			System.out.println("Coordenadas: "+inf.getEPOLATITUD()+" , "+inf.getEPOLONGITU());
			System.out.println("]");
		}
		
		/**
		 * Imprimer la informacion de un comparendo
		 * @param inf Comaprendo a imprimir
		 */
		public void printComparendo(Comparendo inf)
		{
			System.out.println("[");
			System.out.println("ID: "+inf.getId());
			System.out.println("Fecha: "+inf.getFecha());
			System.out.println("Medio de detección: " + inf.getMedio());
			System.out.println("Clase de vehículo: "+inf.getClase());
			System.out.println("Tipo de servicio: "+inf.getTipo());
			System.out.println("Infracción: "+inf.getInfr());
			System.out.println("Descripción: "+inf.getDesc());
			System.out.println("Localidad: "+inf.getLocalidad());
			System.out.println("Municipio: "+inf.getMunicipio());
			System.out.println("Coordenadas: "+inf.getLatitud()+" , "+inf.getLongitud());
			System.out.println("]");
		}
		
		/**
		 * Imprime los datos escenciales de un vertice recibido por parámetro
		 * @param vertice Vertice a imprimir
		 */
		public void printVertice(Vertice vertice)
		{
			System.out.println("[");
			System.out.println("ID: "+vertice.getId());
			System.out.println("Latitud: "+vertice.getLatitud());
			System.out.println("Longitud: " + vertice.getLongitud());
			System.out.println("]");
		}
		
		/**
		 * Imprime los datos escenciales de un arco
		 * @param arco Arco a imprimir
		 */
		public void printArco(Edge arco)
		{
			System.out.println("("+arco.getOrigen()+" --> "+arco.getDestino()+")");
		}
		
		/**
		 * Imprime una cola de arcos recibida por parámetro
		 * @param cola cola a imprimir
		 */
		public void printColaArcos(Cola cola)
		{
			System.out.println("Arcos: {");
			while(!cola.esVacia())
			{
				Edge edge = (Edge) cola.eliminar();
				printArco(edge);
			}
			System.out.println("}");
		}
		
		public void printColaString(Cola cola)
		{
			while(!cola.esVacia())
			{
				System.out.println((String) cola.eliminar());
			}
		}

		public void printCompPorEsta(ListaEncadenada<Estacion> estaciones)
		{
			System.out.println("[");
			for(Object e:estaciones)
			{
				Estacion est = (Estacion) e;
				System.out.println(est.getEPONOMBRE()+" atiende "+est.getNumComp()+" comparendos");
			}
			System.out.println("]");
		}
		
		public void printInfoFinal(int i, String color, String estaciones, int num)
		{
			System.out.println("Componente conectada "+i+": \n");
			System.out.println("Color: "+color);
			System.out.println("Estaciones del componente: "+estaciones);
			System.out.println("Numero de vertices: "+num+"\n");
		}
}
