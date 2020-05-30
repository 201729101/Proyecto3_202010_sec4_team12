package model.data_structures;

public class Grafo<E extends Comparable<E>>
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
	 * Numero de vertices añadidos
	 */
	private int rV;

	/**
	 * Arreglo de objetos que tiene el grafo
	 */
	private Object[] vertex;    

	/**
	 * Contructor con un numero de vertices por parámetro
	 * @param pV Numero de vertices
	 */
	public Grafo(int pV)
	{
		V=pV;
		E=0;
		vertex = new Object[V];            
		for (int v = 0; v < V; v++)                       
			vertex[v] = new Vertex(v,null);
	}

	/**
	 * Clase que contiene un vertice y una lista de arcos como objetos en el arreglo del grafo
	 */
	private class Vertex
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
		private ListaEncadenada<Edge> edges;

		/**
		 * Constructor con un indice y un elemento por parámetro
		 * @param i indice del vertice
		 * @param elem Vertice
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

		public void addEdge(Edge edge){
			edges.agregarFinal(edge);
		}
	}

	/**
	 * Clase para busqueda de caminos mas cortos
	 */
	public class  DijkstraSP 
	{   
		/**
		 * Arreglo que indica vertice de llagada a cada vertice
		 */
		private Edge[] edgeTo;  

		/**
		 * Arreglo que indica la distancia a acada vertice
		 */
		private double[] distTo;   

		/**
		 * Cola de prioridad indexada con las distancias entre cada camino
		 */
		private ColaDePrioridadIndexada<Double> pq; 

		/**
		 * Vertice encontrado por el algoritmo
		 */
		private int vert=-1;

		/**
		 * Contructor del algoritmo de Dijkstra para camino mas corto
		 * @param s Vertice de origen
		 */
		public DijkstraSP(int s,int f)   
		{      
			edgeTo = new Edge[V()];      
			distTo = new double[V()];      
			pq = new ColaDePrioridadIndexada<Double>(V());      
			for (int v = 0; v < V(); v++)         
				distTo[v] = Double.POSITIVE_INFINITY;      
			distTo[s] = 0.0; 
			pq.insert(s,0.0);      
			while (!pq.isEmpty())         
				relax(pq.delMin(),f);   
		}  
		
		public DijkstraSP(int s,ListaEncadenada ids)   
		{      
			edgeTo = new Edge[V()];      
			distTo = new double[V()];      
			pq = new ColaDePrioridadIndexada<Double>(V());      
			for (int v = 0; v < V(); v++)         
				distTo[v] = Double.POSITIVE_INFINITY;      
			distTo[s] = 0.0; 
			pq.insert(s,0.0);      
			while (!pq.isEmpty())         
				relaxCompleto(pq.delMin());   
		} 
		
		/**
		 * Contructor del algoritmo de Dijkstra para camino mas corto
		 * @param s Vertice de origen
		 */
		public DijkstraSP(int s)   
		{      
			edgeTo = new Edge[V()];      
			distTo = new double[V()];      
			pq = new ColaDePrioridadIndexada<Double>(V());      
			for (int v = 0; v < V(); v++)         
				distTo[v] = Double.POSITIVE_INFINITY;      
			distTo[s] = 0.0; 
			pq.insert(s,0.0); 
			while (!pq.isEmpty())         
				relax(pq.delMin());   
		} 

		private void relax(int v,int f)   
		{      
			for(Object e : adj(v))      
			{         
				Edge edge = (Edge) e;
				int w = edge.getDestino();         
				if (distTo[w] > distTo[v] + edge.getDistance())         
				{            
					distTo[w] = distTo[v] + edge.getDistance();            
					edgeTo[w] = edge; 
					if(w==f)
					{
						pq.empty();
						break;
					}
					if (pq.contains(w)) 
						pq.change(w, distTo[w]);            
					else                
						pq.insert(w, distTo[w]);         
				}      
			}   
		} 

		private void relax(int v)   
		{      
			for(Object e : adj(v))      
			{         
				Edge edge = (Edge) e;
				int w = edge.other(v);         
				if (distTo[w] > distTo[v] + edge.getDistance())         
				{            
					distTo[w] = distTo[v] + edge.getDistance();            
					edgeTo[w] = edge; 
					if(((Vertice) getInfoVertex(w)).getEstacion()!=null)
					{
						pq.empty();
						vert = w;
						break;
					}
					if (pq.contains(w)) 
						pq.change(w, distTo[w]);            
					else                
						pq.insert(w, distTo[w]);         
				}    
			}
		}
		
		private void relaxCompleto(int v)   
		{      
			for(Object e : adj(v))      
			{         
				Edge edge = (Edge) e;
				int w = edge.getDestino();         
				if (distTo[w] > distTo[v] + edge.getDistance())         
				{            
					distTo[w] = distTo[v] + edge.getDistance();            
					edgeTo[w] = edge; 
					if (pq.contains(w)) 
						pq.change(w, distTo[w]);            
					else                
						pq.insert(w, distTo[w]);  
				}      
			}   
		}
		
		public Edge[] getEdgeTo() {
			return edgeTo;
		}

		public int getVert() {
			return vert;
		}
	}

	/**
	 * Retorna el numero de vertices del grafo
	 * @return Numero de vertices del grafo
	 */
	public int V()  {  return V;  } 

	/**
	 * Retorna el numero de vertices añadidos al grafo
	 * @return numero de vertices añadidos
	 */
	public int getrV() {
		return rV;
	}

	/**
	 * Retorna el numero de arcos del grafo
	 * @return numero de arcos del grafo
	 */
	public int E()  {  return E;  } 

	/**
	 * Añade un arco al grafo con los parametros de entrada
	 * @param v id del vertice de origen del arco
	 * @param w id del vertice de destino del arco
	 * @param costArc Costo del arco
	 */
	public void addEdge(int v, int w, double costArc)   
	{      
		Edge one = new Edge(v,w,costArc);
		//		Edge two = new Edge(w,v,costArc);
		((Vertex) vertex[v]).addEdge(one);
		//		((Vertex) vertex[w]).addEdge(two);
		E++;
	}   

	/**
	 * Retorna un alista iterable con los arcos de un vertice
	 * @param v indice del vertice
	 * @return Lista de arcos
	 */
	public Iterable<Integer> adj(int v)   
	{  
		return ((Vertex) vertex[v]).getEdges();  
	} 

	/**
	 * Retorna la indforacion de un vertice
	 * @param idVertex indice dle vertice
	 * @return informacion dle vertice
	 */
	public E getInfoVertex(int idVertex)
	{
		return ((Vertex) vertex[idVertex]).getElemento();
	}

	/**
	 * Retorna los arcos de un vertice
	 * @param id indice del vertice
	 * @return Lista de arcos
	 */
	public ListaEncadenada<Edge> getEdges(int id)
	{
		return ((Vertex) vertex[id]).getEdges();
	}

	/**
	 * Cambia la informacion de un vertice
	 * @param idVertex id del vertice
	 * @param Vertex Informacion dle vertice
	 */
	public void setInfoVertex(int idVertex, E Vertex)
	{
		((Vertex) vertex[idVertex]).setElemento(Vertex);
	}

	/**
	 * Retorn el costo de un arco 
	 * @param idVertexIni
	 * @param idVertexFin
	 * @return
	 */
	public double getDistanceEdge(int idVertexIni, int idVertexFin)
	{
		ListaEncadenada lista = ((Vertex) vertex[idVertexIni]).getEdges();
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
	 * Camia le costo de un arco
	 * @param idVertexIni indice del vertice de origen
	 * @param idVertexFin indice del arco
	 * @param costArc costo nuevo
	 */
	public void setDistanceEdge(int idVertexIni, int idVertexFin, double costArc)
	{
		ListaEncadenada lista = ((Vertex) vertex[idVertexIni]).getEdges();
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
	 * Agrega un vertice al grafo
	 * @param idVertex indice dle vertice
	 * @param Vertex Vertice
	 */
	public void addVertex(int idVertex, E Vertex)
	{
		((Vertex) vertex[idVertex]).setElemento(Vertex);
		rV++;
	}

	/**
	 * Retorna los edge del arbol de camino mas corto
	 * @param s origen del arbol
	 * @param f fin del arbol
	 * @return arreglo de Edges
	 */
	public Edge[] shortestPath(int s, int f)
	{
		DijkstraSP path = new DijkstraSP(s,f);
		return path.edgeTo;
	}

	/**
	 * Retorna los edges el arbol de caminos mas cortos
	 * @param org origen dle arbol
	 * @param ids vertices de finalizacion del arbol
	 * @return Arreglo de edges
	 */
	public Edge[] spanningTree(int org, ListaEncadenada<Integer> ids)
	{
		//		PrimMST prim = new PrimMST(org,ids);
		DijkstraSP prim = new DijkstraSP(org,ids);
		return prim.edgeTo;
	}

	/**
	 * Retorna informacion del arbol de caminos mas cortos
	 * @param s origen del arbol
	 * @return Onjeto Dijkstra con informacion del arbol de caminos mas cortos
	 */
	public DijkstraSP estaciones(int s)
	{
		DijkstraSP path = new DijkstraSP(s);
		return path;
	}

//CODIGOS UTILES
//	public class  PrimMST 
//	{   
//		private Edge[] edgeTo;          // shortest edge from tree vertex   
//
//		private double[] distTo;        // distTo[w] = edgeTo[w].weight()   
//
//		private boolean[] marked;       // true if v on tree   
//
//		private ColaDePrioridadIndexada<Double> pq;  // eligible crossing edges   
//
//		public PrimMST(int s, ListaEncadenada<Integer> ids)   
//		{      
//			edgeTo = new Edge[V()];      
//			distTo = new double[V()];      
//			marked = new boolean[V()];      
//			for (int v = 0; v < V(); v++)         
//				distTo[v] = Double.POSITIVE_INFINITY;      
//			pq = new ColaDePrioridadIndexada<Double>(V());      
//			distTo[s] = 0.0;      
//			pq.insert(s, 0.0);              // Initialize pq with 0, weight 0.      
//			while (!pq.isEmpty())         
//				visit(pq.delMin(),ids);       // Add closest vertex to tree.   
//		}   
//
//		private void visit(int v,ListaEncadenada<Integer> ids)   
//		{  // Add v to tree; update data structures.      
//			marked[v] = true;      
//			for (Object e : adj(v))      
//			{         
//				Edge edge = (Edge) e;
//				int w = edge.other(v);         
//				if (marked[w]) continue;     // v-w is ineligible.         
//				if (edge.getDistance() < distTo[w])         
//				{  // Edge e is new best connection from tree to w.            
//					edgeTo[w] = edge;             
//					distTo[w] = edge.getDistance();             
//					if (pq.contains(w)) pq.change(w, distTo[w]);            
//					else                pq.insert(w, distTo[w]);
//					//					if(done(ids))
//					//					{
//					//						pq.empty();
//					//						break;
//					//					}
//				}      
//			}   
//		}
//
//		private boolean done(ListaEncadenada<Integer> ids)
//		{			
//			for(Object e: ids)
//			{
//				if(!marked[(int) e])
//					return false;
//				ids.eliminarPrimero();
//			}
//			return true;
//		}
//	}
	
//	private class DFS 
	//	{
	//		/**
	//		 * Arreglo de vertices marcados 
	//		 */
	//		private boolean[] marked;  
	//
	//		/**
	//		 * Arreglo de arcos por revisar
	//		 */
	//		private int[] edgeTo;
	//
	//		public DFS(int s) {
	//			marked = new boolean[V]; 
	//			edgeTo = new int[V];
	//			dfs(s);
	//		}
	//
	//		/**
	//		 * Realica la deapth first search
	//		 * @param v indice de origen
	//		 */
	//		private void dfs(int v) 
	//		{ 
	//			marked[v] = true; 
	//			for (int w : adj(v)) 
	//				if (!marked[w]) 
	//				{ 
	//					edgeTo[w] = v; 
	//					dfs(w);
	//					count++;
	//				} 
	//		}
	//	}
}