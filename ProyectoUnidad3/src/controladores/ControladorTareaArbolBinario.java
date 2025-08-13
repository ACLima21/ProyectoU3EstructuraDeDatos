package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vista.InterfazTareaArbolBinario;

import modelo.Nodo;
import modelo.Tareas;
import modelo.ArbolBinarioBusqueda;
import modelo.ArbolNodo;

public class ControladorTareaArbolBinario implements ActionListener {

	private InterfazTareaArbolBinario view = new InterfazTareaArbolBinario();
	private ArbolBinarioBusqueda arbolBB = new ArbolBinarioBusqueda();

	// =============CONSTRUCTOR=============
	public ControladorTareaArbolBinario(InterfazTareaArbolBinario view) {
		this.view = view;

		this.view.btnGuardar.addActionListener(this);
		this.view.btnBuscar.addActionListener(this);
		this.view.btnEliminar.addActionListener(this);
		this.view.btnRecorrer.addActionListener(this);
	}

	// =============MÉTODO QUE INICIA LA VISTA=============
	public void iniciarView() {
		view.setVisible(true);
		System.out.println("\n============================================");
		System.out.println("Se mostró la vista correctamente");
		System.out.println("\n============================================");
	}

	// =============VALIDADORES=============
	public boolean validarEntradas() {
		// Validación título
		if (view.txtTitulo.getText().isBlank()) {
			messagesJOptionPane(1);
			return false;
		} else if (view.txtTitulo.getText().length() == 0) {
			messagesJOptionPane(2);
			return false;
		}
		// Validación Descripción
		if (view.taDescripcion.getText().isBlank()) {
			messagesJOptionPane(3);
			return false;
		}

		// Validación Fecha
		if (view.txtFecha.getText().isBlank()) {
			messagesJOptionPane(4);
			return false;
		} else if (!view.txtFecha.getText().matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{2}$")) {
			messagesJOptionPane(4);
			System.out.println("\n============================================");
			System.out.println("La fecha se ingresó incorrectamente");
			System.out.println("\n============================================");
			return false;
		}
		return true;
	}

	public void cleanFields() {
		view.txtFecha.setText("");
		view.txtTitulo.setText("");
		view.taDescripcion.setText("");
	}

	// =============DESARROLLO=============
	public Tareas generarTarea() {
		Tareas tarea = new Tareas(view.txtTitulo.getText(), view.taDescripcion.getText(), view.txtFecha.getText(),
				view.cbCategoria.getSelectedItem().toString());

		System.out.println("\n============================================");
		System.out.println("ID: " + tarea.getId());
		System.out.println("Título: " + tarea.getTitulo());
		System.out.println("Descripción: " + tarea.getDescripcion());
		System.out.println("Fecha: " + tarea.getFecha());
		System.out.println("Categoría: " + tarea.getCategoria());
		System.out.println("\n============================================");

		return tarea;
	}

	public void saveTask() {
		Tareas taskToSave = generarTarea();
		try {
			arbolBB.insertar(taskToSave); // Método de ArbolBinarioBusqueda
			JOptionPane.showMessageDialog(view, "✅ Tarea insertada correctamente.");

			cleanFields();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "⚠ Error: " + ex.getMessage());
		}
		
		mostrarArbol();//muestra el árbol cada que se guarda
	}

	public void searchTask() {
		String id = JOptionPane.showInputDialog(view, "Ingrese el ID de la tarea a buscar:");

		if (id == null || id.trim().isEmpty()) {
			JOptionPane.showMessageDialog(view, "❗ Debe ingresar un ID.");
			return;
		}

		Tareas buscada = new Tareas(id.trim());
		System.out.println(buscada.getId());
		// Elegir tipo de búsqueda
		String[] opciones = { "Búsqueda Normal", "Búsqueda Iterativa" };
		int tipo = JOptionPane.showOptionDialog(view, "¿Qué tipo de búsqueda desea usar?", "Tipo de Búsqueda",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		if (tipo == JOptionPane.CLOSED_OPTION)
			return;

		ArbolNodo resultado = null;
		if (tipo == 0) {
			resultado = arbolBB.buscar(buscada);
		} else {
			resultado = arbolBB.buscarIterativo(buscada);
		}

		if (resultado != null) {
			JOptionPane.showMessageDialog(view, "📄 Tarea encontrada:\n\n" + resultado.getDato().toString());
		} else {
			JOptionPane.showMessageDialog(view, "❌ No se encontró ninguna tarea con ese ID.");
		}
	}

	public void deleteTask() {
		String id = JOptionPane.showInputDialog(view, "Ingrese el ID de la tarea a eliminar:");

		if (id == null || id.trim().isEmpty()) {
			JOptionPane.showMessageDialog(view, "❗ Debe ingresar un ID válido.");
			return;
		}

		Tareas aEliminar = new Tareas(id.trim());

		try {
			arbolBB.eliminar(aEliminar);
			JOptionPane.showMessageDialog(view, "✅ Tarea eliminada correctamente.");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "⚠ Error: " + ex.getMessage());
		}
	}
	
	public void recorrerArbol() {
	    String[] opciones = { "Inorden", "Preorden", "Postorden" };
	    int opcion = JOptionPane.showOptionDialog(view, "Seleccione el tipo de recorrido:",
	            "Recorrido del Árbol", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
	            opciones, opciones[0]);

	    StringBuilder resultado;
	    switch (opcion) {
	        case 0:
	            resultado = arbolBB.inorden(arbolBB.raizArbol());
	            break;
	        case 1:
	            resultado = arbolBB.preorden(arbolBB.raizArbol());
	            break;
	        case 2:
	            resultado = arbolBB.postorden(arbolBB.raizArbol());
	            break;
	        default:
	            return; // Usuario cerró la ventana
	    }

	    if(resultado.isEmpty()) {
	    	view.lblRecorrido.setText("El árbol está vacío");
	    }else {
	    	view.lblRecorrido.setText(resultado.toString());
	    }
	}
	
	private void mostrarArbol() {
		StringBuilder builder = new StringBuilder();
		dibujarArbol(arbolBB.raizArbol(), builder, 0);
		view.tpEstructuraArbol.setText(builder.toString());
	}

	private void dibujarArbol(ArbolNodo nodo, StringBuilder builder, int nivel) {
		if (nodo != null) {
			for (int i = 0; i < nivel; i++)
				builder.append("   ");
			builder.append("[").append(nodo.getDato().getId()).append("|").append(nodo.getDato().getTitulo()).append("]\n");
			dibujarArbol(nodo.getIzdo(), builder, nivel + 1);
			dibujarArbol(nodo.getDcho(), builder, nivel + 1);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.btnGuardar) {
			saveTask();
		}

		if (e.getSource() == view.btnBuscar) {
			searchTask();
		}

		if (e.getSource() == view.btnEliminar) {
			deleteTask();
		}
		
		if (e.getSource() == view.btnRecorrer) {
		    recorrerArbol();
		}

	}

	// =============EXTRAS=============
	public void messagesJOptionPane(int pane) {
		switch (pane) {
		case 1:
			JOptionPane.showMessageDialog(view, "Ingrese un título para la tarea por favor", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(view, "Ingrese por lo menos un caracter", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			break;
		case 3:
			JOptionPane.showMessageDialog(view, "Ingrese una descripción para la tarea por favor", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			break;
		case 4:
			JOptionPane.showMessageDialog(view, "Ingrese una fecha para la tarea por favor, con el formato dd/mm/aa",
					"ERROR", JOptionPane.WARNING_MESSAGE);
			break;
		case 5:
			JOptionPane.showMessageDialog(view,
					"Debe ingresar la posición en formato número, inténtelo de nuevo por favor", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			break;
		}

	}
}
