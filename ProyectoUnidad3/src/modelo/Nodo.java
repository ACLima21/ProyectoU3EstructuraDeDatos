package modelo;

public class Nodo {
	//Información
	Tareas tarea;
	
	//Referenica o enlace
	Nodo enlace;

	//Constructores
	public Nodo() {
		tarea=new Tareas();
		enlace=null;
	}
	
	public Nodo(Tareas tarea) {
		this.tarea = tarea;
		enlace = null;
	}
	
	public Nodo(Tareas tarea, Nodo enlace) {
		this.tarea = tarea;
		this.enlace = enlace;
	}

	//Setters y Getters
	public Tareas getTarea() {
		return tarea;
	}

	public void setTarea(Tareas tarea) {
		this.tarea = tarea;
	}

	public Nodo getEnlace() {
		return enlace;
	}

	public void setEnlace(Nodo enlace) {
		this.enlace = enlace;
	}
}
