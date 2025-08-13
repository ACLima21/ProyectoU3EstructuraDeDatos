package modelo;

public class ArbolNodo {
	protected Tareas dato;
	protected ArbolNodo izdo;
	protected ArbolNodo dcho;
	
	public ArbolNodo(Tareas valor){
	dato = valor;
	izdo = dcho = null;
	}
	
	public ArbolNodo(ArbolNodo ramaIzdo, Tareas valor, ArbolNodo ramaDcho){
		this.dato=valor;
		this.izdo = ramaIzdo;
		this.dcho = ramaDcho;
	}
	
	public Tareas getDato() {
		return dato;
	}

	public void setDato(Tareas dato) {
		this.dato = dato;
	}

	public ArbolNodo getIzdo() {
		return izdo;
	}

	public void setIzdo(ArbolNodo izdo) {
		this.izdo = izdo;
	}

	public ArbolNodo getDcho() {
		return dcho;
	}

	public void setDcho(ArbolNodo dcho) {
		this.dcho = dcho;
	}
	
	public void visitar(){
		System.out.print(dato.toString() + "\n");
    }

	@Override
	public String toString() {
		return "ArbolNodo [dato=" + dato + "]";
	}
	
	
	
}



