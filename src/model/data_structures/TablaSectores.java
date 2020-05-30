package model.data_structures;

public class TablaSectores 
{	
	private final static double MINLAT = 3.819966340000007;
	private final static double MAXLAT = 4.836643219999986;
	private final static double MINLON= -74.39470032000005;
	private final static double MAXLON = -73.99132694999996;
	public final static int M = 1000;

	/**
	 * Arreglo de valores de la tabla
	 */
	private ListaEncadenada<Integer>[][] matriz;

	//	private class KeyValue
	//	{
	//		private String key;
	//
	//		private ListaEncadenada<Integer> values;
	//
	//		public KeyValue(String pKey) 
	//		{
	//			key = pKey;
	//			values = new ListaEncadenada<Integer>();
	//		}
	//
	//		public String getKey() {
	//			return key;
	//		}
	//
	//		public ListaEncadenada<Integer> getValues() {
	//			return values;
	//		}
	//	}

	/**
	 * Constructor de la tabla
	 */
	public TablaSectores() 
	{
		matriz = new ListaEncadenada[M][M];
	}

	/**
	 * Agrega un nuevo valor recibido por parámetro
	 * @param key Llave a agregar
	 * @param value Valor a agregar
	 */
	public void agregar(double lat, double lon, int value)throws Exception
	{
		double difLat = (MAXLAT - MINLAT)/M;
		double difLon = (MAXLON - MINLON)/M;

		int valLat = (int) Math.floor((lat-MINLAT)/difLat);
		int valLon = (int) Math.floor((lon-MINLON)/difLon);

		ListaEncadenada<Integer> r = matriz[valLat][valLon];

		if(r==null)
			r=new ListaEncadenada<Integer>();
		r.agregarFinal(value);
		matriz[valLat][valLon]=r;
	}

	/**
	 * Retorna un valor correspondiente a la llave recibida por parámetro
	 * @param key Llave a buscar 
	 * @return Valor correspondiente a la llave
	 */
	public ListaEncadenada<Integer> darIdsSector(double lat, double lon)
	{
		double difLat = (MAXLAT - MINLAT)/M;
		double difLon = (MAXLON - MINLON)/M;

		int valLat = (int) Math.floor((lat-MINLAT)/difLat);
		int valLon = (int) Math.floor((lon-MINLON)/difLon);

		ListaEncadenada<Integer> r = matriz[valLat][valLon];		

		if(r==null)
		{
			//			System.out.println("Entró");
			int fLat = valLat;
			int fLon = valLon;
			while(r==null)
			{
				if(valLat>0)
					valLat--; 
				if(valLon>0)
					valLon--;
				if(fLat<M-1)
					fLat++;
				if(fLon<M-1)
					fLon++;

				for(int i = valLat ; i<M && i<=fLat ; i++)
					for(int j = valLon ; j<M && j<fLon; j++)
					{
//						ListaEncadenada<Integer> act = matriz[i][j];
//						r = concatenar(r, act);
						r = matriz[i][j];
						if(r!=null)
							return r;
					}
			}
			//			System.out.println("Salió");
		}
		return r;
	}

	/**
	 * Retorna la matriz 
	 * @return mstriz
	 */
	public ListaEncadenada<Integer>[][] getMatriz() {
		return matriz;
	}

//		public ListaEncadenada concatenar(ListaEncadenada<Integer> inicio , ListaEncadenada<Integer> finale)
//		{
//			try
//			{
//				if(finale==null)
//					return inicio;
//				if(inicio==null)
//					return finale;
//	
//				ListaEncadenada inicopy = (ListaEncadenada) inicio.clone();
//				ListaEncadenada fincopy = (ListaEncadenada) finale.clone();
////				inicopy.concatenar(finale);
//	
//				return inicopy.concatenar(finale);
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//				return null;
//			}
//		}

}