package modelo;

public class Pila {
	//Atributos
	private Nodo tope;
	
	
	//Constructor
	public Pila() {
		tope=null;
	}
	
	//Operaciones
	public void push(Tareas tarea) {
		Nodo nuevo=new Nodo(tarea);//se crea un nuevo nodo con un dato que se pasó por parámetros
		if(tope!=null) {
			nuevo.setEnlace(tope);//si el tope no está vacío se inserta el enlace que tenía el tope al nuevo nodo
		}
		tope=nuevo;//este nuevo nodo ya construído con dato y enlace se lo coloca en el tope.
	}
	
	public Nodo pop() {
		Nodo nodoEliminado=tope;
		if(tope!=null) {
			tope=tope.getEnlace();
			nodoEliminado.setEnlace(null);
		}
		return nodoEliminado;
	}
	
	public boolean isEmpty() {
		return tope==null;
	}
	
	public String mostrar() {
		StringBuilder stringBuilder=new StringBuilder();
    	Nodo current=tope;
    	while(current!=null) {
    		stringBuilder.append(current.getTarea().toString()).append("\n");
    		current =current.getEnlace();
    	}
    	return stringBuilder.toString();
	}
	
	public Nodo obtenerTope() {
		return new Nodo(tope.getTarea());
	}
	
	public Pila moverPila() {
		Pila pilaNueva= new Pila();
		while(!isEmpty()) {			
			pilaNueva.push(pop().getTarea());
		}
		return pilaNueva;
	}
}
