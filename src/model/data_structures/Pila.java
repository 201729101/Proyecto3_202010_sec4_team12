package model.data_structures;

public class Pila<E extends Comparable<E>> implements Comparable<Pila>
{
private static final int CAPACIDAD = 10000000;
	
	private E[] elementos;
	
	private int t; 
	
	public Pila()
	{
		t = -1;
		elementos = (E[]) new Comparable[CAPACIDAD];
	}
	
	public E[] getElementos() 
	{
		return elementos;
	}
	
	public int getT() 
	{
		return t;
	}
	
	public void setElementos(E[] elementos) 
	{
		this.elementos = elementos;
	}
	
	public void setT(int t) 
	{
		this.t = t;
	}
	
	public int darTamano()
	{
		return t+1;
	}
	
	public boolean esVacia()
	{
		return (t==-1);
	}
	
	public void push(E elemento) 
	{
//		if(darTamano() == elementos.length)
//		{
//			throw new Exception("La lista está llena");
//		}
		
		elementos[++t] = elemento;
	}
	
	public E pop()
	{
		if(esVacia())
		{
			return null;
		}
		
		E resp = elementos[t];
		elementos[t] = null;
		t--;
		return resp;
	}
	
	public E buscar(E elemento)
	{
		if(esVacia())
		{
			return null;
		}
		else
		{
			for(int i = 0; i<=t ; i++)
			{
				if(elementos[i].compareTo(elemento)==0)
				{
					return elementos[i];
				}
			}
			return null;
		}
	}
	
	public E darPrimero()
	{
		return elementos[t];
	}

	@Override
	public int compareTo(Pila arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
