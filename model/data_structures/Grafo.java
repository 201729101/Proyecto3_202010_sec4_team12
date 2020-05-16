package model.data_structures;

public class GrafoND<E extends Comparable<E>>
{
	/**
	 * Numero de vertices del grafo
	 */
	private int V;  

	/**
	 * Numero de arcos del grafo
	 */
	private int E; 

	/**
	 * Arreglo de objetos que tiene el grafo
	 */
	private Object[] adj;   
	
	/**
	 * Arreglo de vertices marcados 
	 */
	private boolean[] marked;  
	
	/**
	 * Arreglo de arcos por revisar
	 */
	private int[] edgeTo; 
	
	/**
	 * Origen de la busqueda
	 */
	private final int s=0;
	
	/**
	 * Nomero de componentes conectados
	 */
	private int count; 

	/**
	 * Contructor con un numero de vertices por parámetro
	 * @param pV Numero de vertices
	 */
	public GrafoND(int pV)
	{
		V=pV;
		E=0;
		adj = new Object[V];      // Create array of lists.      
		for (int v = 0; v < V; v++)             // Initialize all lists          
			adj[v] = new Objeto(v,null);
		marked = new boolean[V]; 
		edgeTo = new int[V]; 
//		s = ps; 
		dfs(s); 
	}

	/**
	 * Clase que contiene un vertice y una lista de arcos como objetos en el arreglo del grafo
	 */
	private class Objeto
	{
		/**
		 * Indice del vertice
		 */
		private int index;

		/**
		 * Elemento generico (Vertice) 
		 */
		private E elemento;

		/**
		 * Lista Encadenada de arcos
		 */
		private ListaEncadenada<Arc> edges;

		/**
		 * Constructor con un indice y un elemento por parámetro
		 * @param i indice del vertice
		 * @param elem Vertice
		 */
		public Objeto(int i, E elem)
		{
			index = i;
			elemento = elem;
			edges = new ListaEncadenada<Arc>();
		}

		/**
		 * Retorna la lista de arcos
		 * @return Lista de arcos
		 */
		public ListaEncadenada<Arc> getEdges() {
			return edges;
		}

		/**
		 * Retorna el elemento (Vertice)
		 * @return Vertice
		 */
		public E getElemento() {
			return elemento;
		}

		/**
		 * Cambia el elemento (Vertice)
		 * @param elemento Vertice a cambiar
		 */
		public void setElemento(E elemento) {
			this.elemento = elemento;
		}

		/**
		 * Retorna el indice
		 * @return indice
		 */
		public int getIndex() {
			return index;
		}
	}

	/**
	 * Retorna le numero de vertices dle grafo
	 * @return Numero de vertices dle grafo
	 */
	public int V()  {  return V;  } 

	/**
	 * Retirna el numero de arcos del grafo
	 * @return numero de arcos del grafo
	 */
	public int E()  {  return E;  } 

	public void addEdge(int v, int w, double costArc)   
	{      
		Arc one = new Arc(w,costArc);
		Arc two = new Arc(v,costArc);
		((Objeto) adj[v]).getEdges().agregarFinal(one);      
		((Objeto) adj[w]).getEdges().agregarFinal(two);
		E++;
	}   

	/**
	 * Retorna un alista iterable con los arcos de un vertice
	 * @param v indice del vertice
	 * @return Lista de arcos
	 */
	public Iterable<Integer> adj(int v)   
	{  
		return ((Objeto) adj[v]).getEdges();  
	} 

	/**
	 * Retornta la indforacion de unvertove
	 * @param idVertex indice dle vertice
	 * @return informacion dle vertice
	 */
	public E getInfoVertex(int idVertex)
	{
		return ((Objeto) adj[idVertex]).getElemento();
	}
	
	/**
	 * Retorna los arcos de un vertice
	 * @param id indice del vertice
	 * @return Lista de arcos
	 */
	public ListaEncadenada<Arc> getEdges(int id)
	{
		return ((Objeto) adj[id]).getEdges();
	}

	/**
	 * Cambia la informacion de un vertice
	 * @param idVertex id del vertice
	 * @param Vertex Informacion dle vertice
	 */
	public void setInfoVertex(int idVertex, E Vertex)
	{
		((Objeto) adj[idVertex]).setElemento(Vertex);
	}

	/**
	 * Retorn el costo de un arco 
	 * @param idVertexIni
	 * @param idVertexFin
	 * @return
	 */
	public double getCostArc(int idVertexIni, int idVertexFin)
	{
		ListaEncadenada lista = ((Objeto) adj[idVertexIni]).getEdges();
		for(Object e:lista)
		{
			Arc edge = (Arc) e;
			if(edge.getId()==idVertexFin)
			{
				return edge.getDistance();
			}
		}
		return -1;
	}
	
	/**
	 * Camia le costo de un arco
	 * @param idVertexIni indice del vertice de origen
	 * @param idVertexFin indice del arco
	 * @param costArc costo nuevo
	 */
	public void setCostArc(int idVertexIni, int idVertexFin, double costArc)
	{
		ListaEncadenada lista = ((Objeto) adj[idVertexIni]).getEdges();
		for(Object e:lista)
		{
			Arc edge = (Arc) e;
			if(edge.getId()==idVertexFin)
			{
				edge.setDistance(costArc);
			}
		}
	}
	
	/**
	 * Agrega un vertice al grafo
	 * @param idVertex indice dle vertice
	 * @param Vertex Vertice
	 */
	public void addVertex(int idVertex, E Vertex)
	{
		((Objeto) adj[idVertex]).setElemento(Vertex);
	}
	
	/**
	 * Realica la deapth first search
	 * @param v indice de origen
	 */
	private void dfs(int v) 
	{ 
		marked[v] = true; 
		for (int w : adj(v)) 
			if (!marked[w]) 
			{ 
				edgeTo[w] = v; 
				dfs(w);
				count++;
			} 
	}
	
	/**
	 * Retorna si hay un camino entre dos vertices
	 * @param v indice de origen
	 * @return true si hay false si no
	 */
	public boolean hasPathTo(int v) 
	{ return marked[v]; }
	
	/**
	 * Cuenta el numero de componentes conectados
	 * @return numero de componentes conectados
	 */
	public int CC() 
	{ return count; }

}
