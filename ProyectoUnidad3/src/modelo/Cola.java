package modelo;

public class Cola {
	private Nodo head;
	private Nodo tail;
	
	public Cola() {
        head= null;
        tail= null;
    }
	
	public boolean isEmpty() {
    	return head==null;
    }
	
	public Cola enqueue(Nodo dato) {
    	Nodo newNode=new Nodo(dato.getTarea());//crea un nuevo nodo
        
    	if (isEmpty()) {//si la lista está vacía cabeza y cola apuntan a nuevo
            head=newNode; 
            tail=newNode;
        }else {
        	tail.setEnlace(newNode);
        	
        }
    	tail=newNode;
    	return this;
    }
	
	public Nodo dequeue() {
		Nodo nodoEliminado=head;
		if(head!=null) {
			head=head.getEnlace();
			nodoEliminado.setEnlace(null);
		}
		return nodoEliminado;
	}
	
	public String visualizar() {
		StringBuilder stringBuilder=new StringBuilder();
		Nodo aux=head;
		for(aux=head;aux!=null;aux=aux.getEnlace()){
			stringBuilder.append(aux.getTarea()).append("\n");
		}
		return stringBuilder.toString();
	}
	
	public Nodo front() {
		return new Nodo(head.getTarea());
	}
	
	public Nodo rear() {
		return new Nodo(tail.getTarea());
	}
	
	public int contarNodos() {
	    int contador = 0;
	    Nodo actual = head;
	    while (actual != null) {
	        contador++;
	        actual = actual.getEnlace();
	    }
	    return contador;
	}
	
	public Cola moverCola() {
		Cola colaNueva= new Cola();
		while(!isEmpty()) {			
			colaNueva.enqueue(dequeue());
		}
		return colaNueva;
	}
}
