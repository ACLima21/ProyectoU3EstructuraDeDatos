package modelo;

import modelo.ListaSimple;
import modelo.Nodo;
import modelo.Tareas;
import vista.InterfazTarea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaSimple{
	//Dato
	private Nodo inicio;
	
	
	//Constructor
	
	public ListaSimple() {
		inicio=null;
	}
	
	public Nodo getInicio() {
		return inicio;
	}

	//Operaciones
	public void insertarInicio(Nodo e1) {//Método para ingresar un nuevo nodo al inicio de la lista
		Nodo nuevo=new Nodo(e1.getTarea());//se crea un nuevo nodo con un dato que se pasó por parámetros
		if(inicio!=null) {
			nuevo.setEnlace(inicio);//si el inicio no está vacío se inserta el enlace que tenía el inicio al nuevo nodo
		}
		inicio=nuevo;//este nuevo nodo ya construído con dato y enlace se lo coloca en inicio.
	}
	
	public void insertarFinal(Nodo e1) {
		Nodo nuevo = new Nodo(e1.getTarea());
		Nodo actual= inicio;
		if(inicio==null) {
			inicio=nuevo;
		}else {
			while(actual.getEnlace()!=null) {
				actual=actual.getEnlace();
			}
			actual.setEnlace(nuevo);
		}
	}
	
	public int contarNodos() {
	    int contador = 0;
	    Nodo actual = inicio;
	    while (actual != null) {
	        contador++;
	        actual = actual.getEnlace();
	    }
	    return contador;
	}
	
	public void insertarEnPosicion(Nodo e1, int posicion) {
	    Nodo nuevo = new Nodo(e1.getTarea());

	    if (posicion <= 0 || inicio == null) {
	        // Si la posición es 0 o negativa, o la lista está vacía, insertar al inicio
	        nuevo.setEnlace(inicio);
	        inicio = nuevo;
	    } else {
	        Nodo actual = inicio;
	        int indice = 0;
	        // Recorre hasta el nodo anterior a la posición deseada
	        while (actual.getEnlace() != null && indice < posicion - 1) {
	            actual = actual.getEnlace();
	            indice++;
	        }
	        // Inserta el nuevo nodo en la posición deseada
	        nuevo.setEnlace(actual.getEnlace());
	        actual.setEnlace(nuevo);
	    }
	}
	
	public void mostrarLista() {
		Nodo aux=inicio;
		for(aux=inicio;aux!=null;aux=aux.getEnlace()){
			System.out.print(aux.getTarea()+" -> ");
		}
		System.out.println("null");
	}
	
	public Nodo buscarNodo(Tareas buscado) {
		Nodo encontrado=null;
		for(Nodo aux=inicio;aux!=null && encontrado==null;aux=aux.getEnlace()) {
			if(aux.getTarea().getTitulo().equals(buscado.getTitulo())) {
				encontrado=aux;
			}
		}
		return encontrado;
	}
	
	public Nodo eliminarInicio() {
		Nodo nodoEliminado=inicio;
		if(inicio!=null) {
			inicio=inicio.getEnlace();
			nodoEliminado.setEnlace(null);;
		}
		return nodoEliminado;
	}
	
	public Nodo eliminarFinal() {
		Nodo nodoEliminado=inicio;
		if(inicio==null) {
			return nodoEliminado;
		}
		if(inicio.getEnlace()==null) {
			inicio=null;
			return nodoEliminado;
		}
		Nodo actual =inicio;
		while(actual.getEnlace()!=null) {
			actual=actual.getEnlace();
		}
		nodoEliminado=actual.getEnlace();
		actual.setEnlace(null);
		return nodoEliminado;
	}
	
	public boolean eliminarNodo(Tareas t) {
		Nodo anterior=null,actual=null;
		boolean encontrado=false;
		
		for(actual=inicio;actual!=null && encontrado==false;actual=actual.getEnlace()) {
			if(actual.getTarea().getTitulo().equals(t.getTitulo())) {
				anterior=actual;
				encontrado=true;
			}
		}
		
		if(actual!=null) {
			if(actual==inicio) {
				inicio=actual.getEnlace();
			}else {
				anterior.setEnlace(actual.getEnlace());
			}
			actual.setEnlace(null);
		}
		return encontrado;
	}
	
	public void ordenarPorFecha() {
	    if (inicio == null || inicio.getEnlace() == null) {
	        return; // Lista vacía o con un solo elemento
	    }
	    
	    // Implementación de ordenamiento burbuja para listas enlazadas
	    boolean intercambio;
	    do {
	        intercambio = false;
	        Nodo actual = inicio;
	        
	        while (actual != null && actual.getEnlace() != null) {
	            Nodo siguiente = actual.getEnlace();
	            
	            // Comparar fechas (orden descendente: más reciente primero)
	            if (actual.getTarea().getFecha().compareTo(siguiente.getTarea().getFecha()) < 0) {
	                // Intercambiar las tareas de los nodos
	                Tareas temp = actual.getTarea();
	                actual.setTarea(siguiente.getTarea());
	                siguiente.setTarea(temp);
	                intercambio = true;
	            }
	            
	            actual = actual.getEnlace();
	        }
	    } while (intercambio);
	}

	// Alternativa más eficiente usando ordenamiento por selección:
	public void ordenarPorFechaSeleccion() {
	    if (inicio == null || inicio.getEnlace() == null) {
	        return;
	    }
	    
	    Nodo actual = inicio;
	    
	    while (actual != null) {
	        Nodo nodoMayor = actual;
	        Nodo temp = actual.getEnlace();
	        
	        // Buscar el nodo con la fecha más reciente
	        while (temp != null) {
	            if (temp.getTarea().getFecha().compareTo(nodoMayor.getTarea().getFecha()) > 0) {
	                nodoMayor = temp;
	            }
	            temp = temp.getEnlace();
	        }
	        
	        // Intercambiar las tareas si se encontró una fecha más reciente
	        if (nodoMayor != actual) {
	            Tareas tempTarea = actual.getTarea();
	            actual.setTarea(nodoMayor.getTarea());
	            nodoMayor.setTarea(tempTarea);
	        }
	        
	        actual = actual.getEnlace();
	    }
	}
}
