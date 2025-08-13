package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import vista.*;

public class ControladorMenuDeNavegacion implements ActionListener {

	private InterfazMenuDeNavegacion view = new InterfazMenuDeNavegacion();

	public ControladorMenuDeNavegacion(InterfazMenuDeNavegacion view) {
		this.view = view;

		this.view.btnArboles.addActionListener(this);
		this.view.btnGrafos.addActionListener(this);
		this.view.btnListaSimple.addActionListener(this);
	}

	// =============MÉTODO QUE INICIA LA VISTA=============
	public void iniciarView() {
		view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.btnArboles) {
			InterfazTareaArbolBinario viewArbol = new InterfazTareaArbolBinario();
			ControladorTareaArbolBinario arbol = new ControladorTareaArbolBinario(viewArbol);

			arbol.iniciarView();
		}

		if (e.getSource() == view.btnGrafos) {
			SwingUtilities.invokeLater(() -> {
	            VentanaPrincipal ventana = new VentanaPrincipal();
	            ventana.setVisible(true);
	        });
		}

		if (e.getSource() == view.btnListaSimple) {
			InterfazTarea viewLista = new InterfazTarea();
			ControladorVistaTarea listaSimple = new ControladorVistaTarea(viewLista);
			listaSimple.iniciarView();
		}
	}
}
