package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Cola;
import modelo.Nodo;
import modelo.Pila;
import modelo.Tareas;
import vista.NeuvaVista;

public class ControladorVistaSecundaria implements ActionListener{

	private Pila pila =new Pila();
	private NeuvaVista view=new NeuvaVista();

	//Constructor
	public ControladorVistaSecundaria(NeuvaVista view) {
		this.view=view;
		
		this.view.btnCerrar.addActionListener(this);
		this.view.btnCheck.addActionListener(this);
	}
	
	public void iniciarView() {
		view.setVisible(true);
		System.out.println("\n============================================");
		System.out.println("Se mostró la vista correctamente");
		System.out.println("\n============================================");
	}
	
	public void cargarPila(Pila pila) {
	    this.pila = pila;
	    mostrarTareaActual();
	}

	private void mostrarTareaActual() {
	    if (!pila.isEmpty()) {
	        Tareas tareaActual = pila.obtenerTope().getTarea();  // Nodo con la tarea
	        view.lblTitulo.setText("Título: " + tareaActual.getTitulo());
	        view.lblDescripcion.setText("<html>Descripción: " + tareaActual.getDescripcion() + "</html>");
	        view.lblFecha.setText("Fecha: " + tareaActual.getFecha());
	    } else {
	        view.lblTitulo.setText("Sin tareas");
	        view.lblDescripcion.setText("");
	        view.lblFecha.setText("");
	    }
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==view.btnCerrar) {
			view.dispose();
		}
		
		if(e.getSource()==view.btnCheck) {
			if (!pila.isEmpty()) {
		        pila.pop(); 
		        mostrarTareaActual();
		    }
		}
		
	}

	
}