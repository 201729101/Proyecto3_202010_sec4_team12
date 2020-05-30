package model.data_structures;

import java.util.Date;

public class Comparendo implements Comparable<Comparendo>
{
	/**
	 * ID del comparendo
	 */
	private int id;

	/**
	 * Fecha del comparendo en String
	 */
	private Date fecha;

	/**
	 * Medio de detecci�n del comparendo
	 */
	private String medio;

	/**
	 * Clase de veh�culo comparentado
	 */
	private String clase;

	/**
	 * Tipo de servicio del veh�culo comparentado
	 */
	private String tipo;

	/**
	 * C�digo de la infracci�n colocada
	 */
	private String infr;

	/**
	 * Descripci�n del comparendo
	 */
	private String desc;

	/**
	 * Localidad en que se coloc� el comparendo
	 */
	private String localidad;
	
	/**
	 * Municipio en que se coloc� el comparendo
	 */
	private String municipio;

	/**
	 * Latitud en que se coloc� el comparendo
	 */
	private double latitud;

	/**
	 * Longitud en que se coloc� el comparendo
	 */
	private double longitud;
	
	/**
	 * Id del vertice mas cercano
	 */
	private int vertice;
	
//	/**
//	 * Estaci�n de policia que lo atiende mas rapido
//	 */
//	private Estacion estacion;

	/**
	 * Contructor
	 * @param pId ID del comparendo
	 * @param pFecha Fecha del comparendo
	 * @param pMedio Medio de detecci�n del comparendo
	 * @param pClase Clase de veh�culo comparentado
	 * @param pTipo Tipo de servicio del vah�culo comparentado
	 * @param pInfr C�digo de la infracci�n colocada
	 * @param pDesc Descripcion del comparendo
	 * @param pLoc Localidad donde se puso el comparendo
	 * @param pLat Latitud donde se puso el comparendo
	 * @param pLong Longitud donde se puso el comparendo
	 */
	public Comparendo(int pId, Date pFecha, String pMedio, String pClase, String pTipo, String pInfr, String pDesc, String pLoc, String munip, double pLat, double pLong)
	{
		id = pId;
		fecha = pFecha;
		medio = pMedio;
		clase = pClase;
		tipo = pTipo;
		infr = pInfr;
		desc = pDesc;
		localidad = pLoc;
		municipio = munip;
		latitud = pLat;
		longitud = pLong;
		vertice = -1;
//		estacion = null;
	}

	/**
	 * Reotna el tipo de servicio
	 * @return tipo de servicio
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Retorna la latidud 
	 * @return latitud
	 */
	public double getLatitud() {
		return latitud;
	}

	/**
	 * Retorna la longitud
	 * @return longitud
	 */
	public double getLongitud() {
		return longitud;
	}

	/**
	 * Retorna la descripci�n
	 * @return descripci�n
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Retorna la fecha
	 * @return fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/** 
	 * Retorna el id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retorna la infracci�n
	 * @return infracci�n
	 */
	public String getInfr() {
		return infr;
	}

	/**
	 * Retorna el medio
	 * @return medio
	 */
	public String getMedio() {
		return medio;
	}

	/**
	 * Retorna la clase
	 * @return clase
	 */
	public String getClase() {
		return clase;
	}

	/**
	 * Retorna la localidad
	 * @return
	 */
	public String getLocalidad() {
		return localidad;
	}
	
	/**
	 * Retorna el municipio en que se puso un comparendo
	 * @return 
	 */
	public String getMunicipio() {
		return municipio;
	}
	
	/**
	 * Cambia el id del vertice del comparendo
	 * @param vertice
	 */
	public void setVertice(int vertice) {
		this.vertice = vertice;
	}
	
	public int getVertice() {
		return vertice;
	}
	
//	public Estacion getEstacion() {
//		return estacion;
//	}
//	
//	public void setEstacion(Estacion estacion) {
//		this.estacion = estacion;
//	}

	/**
	 * Compara dos comparendos
	 */
	@Override
	public int compareTo(Comparendo compk) 
	{
		if(this.getTipo().equals(compk.getTipo()))
		{
			return this.getInfr().compareTo(compk.getInfr());
		}
		else if(!this.getTipo().equals("P�blico")&&!compk.getTipo().equals("P�blico"))
		{
			return this.getTipo().equals("Particular")?-1:1;
		}
		else
		{
			return this.getTipo().equals("P�blico")?1:-1;
		}
	}
	
	@Override
	public int hashCode() 
	{
		int hash = 17; 
		hash = 31*hash + fecha.hashCode(); 
		hash = 31*hash + clase.hashCode(); 
		hash = 31*hash + infr.hashCode(); 
		return hash;
	}
}
