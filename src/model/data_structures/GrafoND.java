package model.data_structures;

public class GrafoND<E extends Comparable<E>>
{
	/**
	 * Numero de vertices
	 */
	private int V;  

	/**
	 * Numero de arcos
	 */
	private int E; 

	/**
	 * Arreglo de objetos vertex del grafp
	 */
	private Object[] adj;  

	/**
	 * Numero de vertices añadidos al grafo
	 */
	private int rV;

	/**
	 * Contrulle un grafo con una cantidad de vertices maxima por parámetro
	 * @param v Numero maximo de vertices
	 */
	public GrafoND(int v)
	{
		V=v;
		E=0;
		adj = new Object[v];      // Create array of lists.      
		for (int h = 0; h < v; h++)             // Initialize all lists          
			adj[h] = new Vertex(h,null);
	}

	/**
	 * Clase que represente un vertice y una lista de arcos en el grafo
	 */
	private class Vertex
	{
		/**
		 * Indice del vertex
		 */
		private int index;

		/**
		 * Elemento del vertex
		 */
		private E elemento;

		/**
		 * Lista de arcos
		 */
		private ListaEncadenada<Edge> edges;

		/**
		 * Contructor
		 * @param i Id
		 * @param elem Elemento
		 */
		public Vertex(int i, E elem)
		{
			index = i;
			elemento = elem;
			edges = new ListaEncadenada<Edge>();
		}

		/**
		 * Retorna la lista de arcos 
		 * @return Lista de arcos
		 */
		public ListaEncadenada<Edge> getEdges() {
			return edges;
		}

		/**
		 * Retorna el elemento 
		 * @return Elemento
		 */
		public E getElemento() {
			return elemento;
		}

		/**
		 * Cambia el elemento 
		 * @param elemento Nuevo elemento
		 */
		public void setElemento(E elemento) {
			this.elemento = elemento;
		}

		/**
		 * Retorna el indice del vertex
		 * @return indice
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * Indica si hay un arco en la lista de arcos
		 * @param edge Arco a buscar
		 * @return true si esta false de lo contrario
		 */
		public boolean contains(Edge edge)
		{
			for(Object e : edges)
			{
				Edge act = (Edge) e;
				if(act.getDestino()==edge.getDestino())
					return true;
			}
			return false;
		}
	}

	/**
	 * Retorna el numero de vertices
	 * @return numero de vertices
	 */
	public int V()  {  return V;  } 

	/**
	 * Retorna le numero de arcos
	 * @return Numero de arcos
	 */
	public int E()  {  return E;  } 

	/**
	 * Retorna el numero de arcos añadidos
	 * @return Numero de arcos añadidos
	 */
	public int getrV() {
		return rV;
	}

	/**
	 * Agrega un Arco
	 * @param v origen
	 * @param w destino
	 * @param costArc costo
	 */
	public void addEdge(int v, int w, double costArc)   
	{      
		Edge one = new Edge(v,w,costArc);
		Edge two = new Edge(w,v,costArc);
		if(!contains(v, one) && !contains(w, two))
		{
//			System.out.println("Nooo!");
			((Vertex) adj[v]).getEdges().agregarFinal(one);      
			((Vertex) adj[w]).getEdges().agregarFinal(two);
			E++;    
		}
//		else
//			System.out.println("Pasó");
	}   

	/**
	 * Retorna la lista de arcos de un vertice
	 * @param v id del vertice
	 * @return lista de arcos
	 */
	public Iterable<Integer> adj(int v)   
	{  
		return ((Vertex) adj[v]).getEdges();  
	} 

	/**
	 * Retorna el elemento de un vertex
	 * @param idVertex id del vertex
	 * @return elemento del vertex
	 */
	public E getInfoVertex(int idVertex)
	{
		return ((Vertex) adj[idVertex]).getElemento();
	}

	/**
	 * Retorna el vertex en un indice de un arreglo
	 * @param id indice a buscar
	 * @return vertex en el indice
	 */
	public Object getInfo(int id)
	{
		return adj[id];
	}

	/**
	 * Cambia la informacion de in vertice
	 * @param idVertex id del vertex
	 * @param Vertex elemento 
	 */
	public void setInfoVertex(int idVertex, E Vertex)
	{
		((Vertex) adj[idVertex]).setElemento(Vertex);
	}

	/**
	 * Retorna el costo de un arco
	 * @param idVertexIni vertice de inicio
	 * @param idVertexFin vertice final
	 * @return costo del arco
	 */
	public double getCostArc(int idVertexIni, int idVertexFin)
	{
		ListaEncadenada lista = ((Vertex) adj[idVertexIni]).getEdges();
		for(Object e:lista)
		{
			Edge edge = (Edge) e;
			if(edge.getDestino()==idVertexFin)
			{
				return edge.getDistance();
			}
		}
		return -1;
	}

	/**
	 * Cambia el costo de un arco
	 * @param idVertexIni vertice de inicio
	 * @param idVertexFin vertice final
	 * @param costArc costo del arco
	 */
	public void setCostArc(int idVertexIni, int idVertexFin, double costArc)
	{
		ListaEncadenada lista = ((Vertex) adj[idVertexIni]).getEdges();
		for(Object e:lista)
		{
			Edge edge = (Edge) e;
			if(edge.getDestino()==idVertexFin)
			{
				edge.setDistance(costArc);
			}
		}
	}

	/**
	 * Añade un vertice al grafo
	 * @param idVertex id del vertice
	 * @param Vertex elemento
	 */
	public void addVertex(int idVertex, E Vertex)
	{
		((Vertex) adj[idVertex]).setElemento(Vertex);
		rV++;
	}

	/**
	 * Retorna los arcos d eun vertice
	 * @param id id del vertice
	 * @return arcos dle vertice
	 */
	public ListaEncadenada<Edge> getEdges(int id)
	{
		return ((Vertex) adj[id]).getEdges();
	}

	/**
	 * Elementos marcados en el dfs
	 */
	private boolean[] marked;   

	/**
	 * Componente a que pertenece cada vertice
	 */
	private int[] id; 

	/**
	 * Conteo de componenetes conexos
	 */
	private int count=1;

	/**
	 * Numero de vertices por conteo de componenete
	 */
	private int num;

	/**
	 * Lista con numero de vertices en cada componenete
	 */
	private ListaEncadenada<Integer> idsList;

	/**
	 * Cuanta los componentes conexos del grafo
	 */
	public void CC()   
	{      
		marked = new boolean[V()];      
		id = new int[V()];  
		idsList = new ListaEncadenada<Integer>();
		for (int s = 0; s < V(); s++)        
			if (!marked[s] && getInfoVertex(s)!=null)         
			{    
				num = 0;
				dfs(s);             
				count++;
				idsList.agregarFinal(num);
			}  
			else if(getInfoVertex(s)==null)
				id[s]=0;
	}   

	/**
	 * Busca compoennetes conectados en un grafo 
	 * @param v id de origen
	 */
	private void dfs(int v)   
	{      
		marked[v] = true;      
		id[v] = count;
		num++;

		for (Object w : adj(v))
		{
			Edge h = (Edge) w;
			int s = h.other(v);
			if (!marked[s])             
				dfs(s); 
		}
	}   

	/**
	 * Retorna el numero de componenete de un vertice
	 * @param v id dle vertice
	 * @return numero de componente conectado
	 */
	public int id(int v)   
	{  return id[v];  }  

	/**
	 * Retorna la lista de numero de ertices por componente
	 * @return Lsiat de numero de vertice por componente
	 */
	public ListaEncadenada<Integer> getIdsList() {
		return idsList;
	}

	/**
	 * Retorna el conteo de componenetes conectados en el grafo
	 * @return conteo de componentes conectados en el grafo
	 */
	public int count()   
	{  
		CC();
		return count;  
	}

	/**
	 * iIndica si un vertice tiene un arco
	 * @param id id del vertice
	 * @param edge Arco a buscar
	 * @return true si esta false de lo contrario
	 */
	public boolean contains(int id, Edge edge)
	{
		return ((Vertex) getInfo(id)).contains(edge);
	}
	//	}

	//	public CC count()
	//	{
	//		try
	//		{
	//			CC ret = new CC();
	//			return ret;
	//		}
	//		catch(Exception e)
	//		{
	//			e.printStackTrace();
	//			return null;
	//		}
	//	}
}
