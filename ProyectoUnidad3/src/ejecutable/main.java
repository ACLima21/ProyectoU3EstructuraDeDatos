package ejecutable;

import vista.InterfazMenuDeNavegacion;

import controladores.ControladorMenuDeNavegacion;

import modelo.ArbolBinarioBusqueda;
import modelo.ArbolNodo;
import modelo.Tareas;

public class main {

	public static void main(String[] args) {
		
		InterfazMenuDeNavegacion view= new InterfazMenuDeNavegacion();
		ControladorMenuDeNavegacion start = new ControladorMenuDeNavegacion(view);
		
		start.iniciarView();
    }

}
