package model.data_structures;

public class Estacion implements Comparable<Estacion>
{
	/**
	 * ID de la estacion
	 */
	private int OBJECTID;
//	private int EPOCOD_PLAN;
//	private String EPOCOD_ENT;
//	private String EPOCOD_PROY;
//	private int EPOANIO_GEO;
//	private long EPOFECHA_INI;
//	private long EPOFECHA_FIN;
	/**
	 * Descripcion de la estacion
	 */
	private String EPODESCRIP;
//	private String EPOEST_PROY;
//	private String EPOINTERV_ESP;
	
	/**
	 * Direccion de la estacion
	 */
	private String EPODIR_SITIO;
//	private String EPOCOD_SITIO;
	
	/**
	 * Latitud de la estacion
	 */
	private double EPOLATITUD;
	
	/**
	 * Longitud de la estacion
	 */
	private double EPOLONGITU;
//	private String EPOSERVICIO;
	
	/**
	 * Horario de la estacion
	 */
	private String EPOHORARIO;
	
	/**
	 * Telefono de la estacion
	 */
	private String EPOTELEFON;
	
	/**
	 * Correo electronico d ela estacion
	 */
	private String EPOCELECTR;
//	private String EPOCONTACT;
//	private String EPOPWEB;
//	private String EPOIUUPLAN;
//	private String EPOIUSCATA ;
//	private String EPOIULOCAL;
//	private String EPOEASOCIA;
	
	/**
	 * Funcion de la estacion
	 */
	private String EPOFUNCION;
//	private String EPOTEQUIPA;
	
	/**
	 * Nombre de la estacion
	 */
	private String EPONOMBRE;
//	private String EPOIDENTIF;
//	private long EPOFECHA_C;
	
	/**
	 * Id del vertice en que está ubicada la estacion
	 */
	private int vertice;
	
	/**
	 * Numero de comparendos qu eatiende la estación
	 */
	private int numComp;

	/**
	 * Constructor
	 * @param a ID
	 * @param desc Descriocion
	 * @param dir Direccion
	 * @param lat latitud
	 * @param lon longitud
	 * @param hor horario
	 * @param tel telefono
	 * @param corel correo electronico
	 * @param fun funcion
	 * @param nom nombre
	 */
	public Estacion(int a, String desc, String dir, double lat, double lon, String hor, String tel, String corel, String fun, String nom)
	{
//		public Estacion(int a, int b, String c, String d, int e, long f, long g, String h, String i, String j, String k, String l, double m, double n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x, String y, String z, String aa, String bb, long cc)
//		{
		OBJECTID = a;
//		EPOCOD_PLAN =b ;
//		EPOCOD_ENT=c;
//		EPOCOD_PROY=d;
//		EPOANIO_GEO=e;
//		EPOFECHA_INI=f;
//		EPOFECHA_FIN=g;
		EPODESCRIP=desc;
//		EPOEST_PROY=i;
//		EPOINTERV_ESP=j;
		EPODIR_SITIO=dir;
//		EPOCOD_SITIO=l;
		EPOLATITUD=lat;
		EPOLONGITU=lon;
//		EPOSERVICIO=o;
		EPOHORARIO=hor;
		EPOTELEFON=tel;
		EPOCELECTR=corel;
//		EPOCONTACT=s;
//		EPOPWEB=t;
//		EPOIUUPLAN=u;
//		EPOIUSCATA=v ;
//		EPOIULOCAL=w;
//		EPOEASOCIA=x;
		EPOFUNCION=fun;
//		EPOTEQUIPA=z;
		EPONOMBRE=nom;
//		EPOIDENTIF=bb;
//		EPOFECHA_C=cc;
		vertice = -1;
		numComp = 0;
	}

//	public int getEPOANIO_GEO() {
//		return EPOANIO_GEO;
//	}
	
	/**
	 * Retorna el correo electronico 
	 * @return Correo electronico
	 */
	public String getEPOCELECTR() {
		return EPOCELECTR;
	}
//	public String getEPOCOD_ENT() {
//		return EPOCOD_ENT;
//	}
//	public int getEPOCOD_PLAN() {
//		return EPOCOD_PLAN;
//	}
//	public String getEPOCOD_PROY() {
//		return EPOCOD_PROY;
//	}
//	public String getEPOCOD_SITIO() {
//		return EPOCOD_SITIO;
//	}
//	public String getEPOCONTACT() {
//		return EPOCONTACT;
//	}
	
	/**
	 * Retorn ala descripcion
	 * @return descripcion
	 */
	public String getEPODESCRIP() {
		return EPODESCRIP;
	}
	
	/**
	 * Retorna la direccion
	 * @return direccion
	 */
	public String getEPODIR_SITIO() {
		return EPODIR_SITIO;
	}
//	public String getEPOEASOCIA() {
//		return EPOEASOCIA;
//	}
//	public String getEPOEST_PROY() {
//		return EPOEST_PROY;
//	}
//	public long getEPOFECHA_C() {
//		return EPOFECHA_C;
//	}
//	public long getEPOFECHA_FIN() {
//		return EPOFECHA_FIN;
//	}
//	public long getEPOFECHA_INI() {
//		return EPOFECHA_INI;
//	}
	
	/**
	 * Retorna la funcion
	 * @return funcion
	 */
	public String getEPOFUNCION() {
		return EPOFUNCION;
	}
	
	/**
	 * Retorna el horario de la estación
	 * @return Horairo
	 */
	public String getEPOHORARIO() {
		return EPOHORARIO;
	}
//	public String getEPOIDENTIF() {
//		return EPOIDENTIF;
//	}
//	public String getEPOINTERV_ESP() {
//		return EPOINTERV_ESP;
//	}
//	public String getEPOIULOCAL() {
//		return EPOIULOCAL;
//	}
//	public String getEPOIUSCATA() {
//		return EPOIUSCATA;
//	}
//	public String getEPOIUUPLAN() {
//		return EPOIUUPLAN;
//	}
	
	/**
	 * Retorna la latitud
	 * @return Latitud
	 */
	public double getEPOLATITUD() {
		return EPOLATITUD;
	}
	
	/**
	 * Retorna la longitud
	 * @return longitud
	 */
	public double getEPOLONGITU() {
		return EPOLONGITU;
	}
	public String getEPONOMBRE() {
		return EPONOMBRE;
	}
//	public String getEPOPWEB() {
//		return EPOPWEB;
//	}
//	public String getEPOSERVICIO() {
//		return EPOSERVICIO;
//	}
	
	/**
	 * Retorna el telefono
	 * @return telefono
	 */
	public String getEPOTELEFON() {
		return EPOTELEFON;
	}
//	public String getEPOTEQUIPA() {
//		return EPOTEQUIPA;
//	}
	
	/**
	 * Retorna el id
	 * @return id
	 */
	public int getOBJECTID() {
		return OBJECTID;
	}
	
	/**
	 * Retrona el id del vertice
	 * @return id del vertice
	 */
	public int getVertice() {
		return vertice;
	}
	
	/**
	 * Cambia el vertice
	 * @param vertice nuevo vertice
	 */
	public void setVertice(int vertice) {
		this.vertice = vertice;
	}
	
	/**
	 * Retorna el numero de comparendos
	 * @return numeor de comparendos
	 */
	public int getNumComp() {
		return numComp;
	}
	
	/**
	 * Aumenta en 1 el numero de comparendos
	 */
	public void aumentarNum()
	{
		numComp++;
	}

	/**
	 * Comapara dos estaciones
	 */
	@Override
	public int compareTo(Estacion arg0) {
		if (OBJECTID==arg0.getOBJECTID())
			return 0;
		return OBJECTID>arg0.getOBJECTID()?1:-1;
	}


}
