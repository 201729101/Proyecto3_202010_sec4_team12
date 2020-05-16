package model.data_structures;

public class Estacion implements Comparable<Estacion>
{
	private int OBJECTID;
	private int EPOCOD_PLAN;
	private String EPOCOD_ENT;
	private String EPOCOD_PROY;
	private int EPOANIO_GEO;
	private long EPOFECHA_INI;
	private long EPOFECHA_FIN;
	private String EPODESCRIP;
	private String EPOEST_PROY;
	private String EPOINTERV_ESP;
	private String EPODIR_SITIO;
	private String EPOCOD_SITIO;
	private double EPOLATITUD;
	private double EPOLONGITU;
	private String EPOSERVICIO;
	private String EPOHORARIO;
	private String EPOTELEFON;
	private String EPOCELECTR;
	private String EPOCONTACT;
	private String EPOPWEB;
	private String EPOIUUPLAN;
	private String EPOIUSCATA ;
	private String EPOIULOCAL;
	private String EPOEASOCIA;
	private String EPOFUNCION;
	private String EPOTEQUIPA;
	private String EPONOMBRE;
	private String EPOIDENTIF;
	private long EPOFECHA_C;

	public Estacion(int a, int b, String c, String d, int e, long f, long g, String h, String i, String j, String k, String l, double m, double n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x, String y, String z, String aa, String bb, long cc)
	{
		OBJECTID = a;
		EPOCOD_PLAN =b ;
		EPOCOD_ENT=c;
		EPOCOD_PROY=d;
		EPOANIO_GEO=e;
		EPOFECHA_INI=f;
		EPOFECHA_FIN=g;
		EPODESCRIP=h;
		EPOEST_PROY=i;
		EPOINTERV_ESP=j;
		EPODIR_SITIO=k;
		EPOCOD_SITIO=l;
		EPOLATITUD=m;
		EPOLONGITU=n;
		EPOSERVICIO=o;
		EPOHORARIO=p;
		EPOTELEFON=q;
		EPOCELECTR=r;
		EPOCONTACT=s;
		EPOPWEB=t;
		EPOIUUPLAN=u;
		EPOIUSCATA=v ;
		EPOIULOCAL=w;
		EPOEASOCIA=x;
		EPOFUNCION=y;
		EPOTEQUIPA=z;
		EPONOMBRE=aa;
		EPOIDENTIF=bb;
		EPOFECHA_C=cc;
	}

	public int getEPOANIO_GEO() {
		return EPOANIO_GEO;
	}
	public String getEPOCELECTR() {
		return EPOCELECTR;
	}
	public String getEPOCOD_ENT() {
		return EPOCOD_ENT;
	}
	public int getEPOCOD_PLAN() {
		return EPOCOD_PLAN;
	}
	public String getEPOCOD_PROY() {
		return EPOCOD_PROY;
	}
	public String getEPOCOD_SITIO() {
		return EPOCOD_SITIO;
	}
	public String getEPOCONTACT() {
		return EPOCONTACT;
	}
	public String getEPODESCRIP() {
		return EPODESCRIP;
	}
	public String getEPODIR_SITIO() {
		return EPODIR_SITIO;
	}
	public String getEPOEASOCIA() {
		return EPOEASOCIA;
	}
	public String getEPOEST_PROY() {
		return EPOEST_PROY;
	}
	public long getEPOFECHA_C() {
		return EPOFECHA_C;
	}
	public long getEPOFECHA_FIN() {
		return EPOFECHA_FIN;
	}
	public long getEPOFECHA_INI() {
		return EPOFECHA_INI;
	}
	public String getEPOFUNCION() {
		return EPOFUNCION;
	}
	public String getEPOHORARIO() {
		return EPOHORARIO;
	}
	public String getEPOIDENTIF() {
		return EPOIDENTIF;
	}
	public String getEPOINTERV_ESP() {
		return EPOINTERV_ESP;
	}
	public String getEPOIULOCAL() {
		return EPOIULOCAL;
	}
	public String getEPOIUSCATA() {
		return EPOIUSCATA;
	}
	public String getEPOIUUPLAN() {
		return EPOIUUPLAN;
	}
	public double getEPOLATITUD() {
		return EPOLATITUD;
	}
	public double getEPOLONGITU() {
		return EPOLONGITU;
	}
	public String getEPONOMBRE() {
		return EPONOMBRE;
	}
	public String getEPOPWEB() {
		return EPOPWEB;
	}
	public String getEPOSERVICIO() {
		return EPOSERVICIO;
	}
	public String getEPOTELEFON() {
		return EPOTELEFON;
	}
	public String getEPOTEQUIPA() {
		return EPOTEQUIPA;
	}
	public int getOBJECTID() {
		return OBJECTID;
	}

	@Override
	public int compareTo(Estacion arg0) {
		if (OBJECTID==arg0.getOBJECTID())
			return 0;
		return OBJECTID>arg0.getOBJECTID()?1:-1;
	}


}
