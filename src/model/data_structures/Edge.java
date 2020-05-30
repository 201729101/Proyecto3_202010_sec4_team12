package model.data_structures;

public class Edge implements Comparable<Edge>
{
	/**
	 * Id del vertice de origen
	 */
	private int origen;

	/**
	 * Id del vetice de destino
	 */
	private int destino;

	/**
	 * Distancia dl arco
	 */
	private double distance;

	/**
	 * Numero de comparendos
	 */
	private int numComparendos;

	/**
	 * Contructor
	 * @param pOrigen
	 * @param pDestino
	 * @param pDis
	 */
	public Edge(int pOrigen, int pDestino, double pDis)
	{
		origen = pOrigen;
		destino=pDestino;
		distance=pDis;
		numComparendos = 0;
	}

	public double getDistance() {
		return distance;
	}

	public int getDestino() {
		return destino;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setNumComparendos(int numComparendos) {
		this.numComparendos = numComparendos;
	}
	
	public int getNumComparendos() {
		return numComparendos;
	}

	@Override
	public int compareTo(Edge o) {
		if(distance==o.getDistance())
			return 0;
		return distance>o.getDistance()?1:-1;
	}
	
	public boolean equals(Edge e)
	{
//		System.out.println("Se utilizó");
		if(this.origen==e.getOrigen() && this.destino==e.getDestino())
			return true;
		return false;
	}

	public int other(int vertex)   
	{      
		if      (vertex == origen) return destino;      
		else if (vertex == destino) return origen;      
		else throw new RuntimeException("Inconsistent edge");   
	} 
	
	public int getOrigen() {
		return origen;
	}

}
