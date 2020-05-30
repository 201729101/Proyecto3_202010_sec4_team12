package model.data_structures;

/**
 * Clase que reprencenta un vertice del grafo
 */
public class Vertice implements Comparable<Vertice> 
{
	/**
	 * Iddel vertice
	 */
	private int id;
	
	/**
	 * Longitud del vertice
	 */
	private double longitud;
	
	/**
	 * Latitud del vertice
	 */
	private double latitud;
	
	/**
	 * Lista de comparendos del vertice
	 */
	private ListaEncadenada<Comparendo> comparendos;
	
	/**
	 * Estacion del policia en el vertice
	 */
	private Estacion estacion;
	
	/**
	 * Constructor de un vertice con un id un alongitud y un alatitud
	 * @param pId Id dle vertice
	 * @param pLong Longitud dle vertice
	 * @param pLat Latitud del vetice
	 */
	public Vertice(int pId, double pLong, double pLat)
	{
		id = pId;
		longitud = pLong;
		latitud = pLat;
		comparendos = new ListaEncadenada<Comparendo>();
		estacion = null;
	}
	
	/**
	 * Retorna el id del vertice
	 * @return Id del vertice
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Retorna la latitud del vertice
	 * @return latitud del vertice
	 */
	public double getLatitud() {
		return latitud;
	}
	
	/**
	 * Retorna la longitud del vertico
	 * @return longitud del vertice
	 */
	public double getLongitud() {
		return longitud;
	}
	
	/**
	 * Agrega un comparendo a la lista de comparendos
	 * @param comp comparendo a agregar
	 */
	public void agregarComparendo(Comparendo comp)
	{
		comparendos.agregarFinal(comp);
	}
	
	/**
	 * Retorna la lista de comparendos
	 * @return
	 */
	public ListaEncadenada<Comparendo> getComparendos() {
		return comparendos;
	}
	
	/**
	 * Cambia la estacion del vertice
	 * @param estacion nueva estacion 
	 */
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
	
	/**
	 * Retorna la estación del vertice
	 * @return estacion del vertice
	 */
	public Estacion getEstacion() {
		return estacion;
	}

	/**
	 * Comapra dos vertices
	 */
	@Override
	public int compareTo(Vertice arg0) {
		// TODO Auto-generated method stub
		if(id==arg0.getId())
			return 0;
		return id>arg0.getId()? 1:-1;
	}
	

}
