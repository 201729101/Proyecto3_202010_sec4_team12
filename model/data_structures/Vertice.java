package model.data_structures;

public class Vertice implements Comparable<Vertice> 
{
	private int id;
	
	private double longitud;
	
	private double latitud;
	
	public Vertice(int pId, double pLong, double pLat)
	{
		id = pId;
		longitud = pLong;
		latitud = pLat;
	}
	
	public int getId() {
		return id;
	}
	
	public double getLatitud() {
		return latitud;
	}
	public double getLongitud() {
		return longitud;
	}

	@Override
	public int compareTo(Vertice arg0) {
		// TODO Auto-generated method stub
		if(id==arg0.getId())
			return 0;
		return id>arg0.getId()? 1:-1;
	}
	

}
