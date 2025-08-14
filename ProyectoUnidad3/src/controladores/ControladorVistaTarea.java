package controladores;

import modelo.Nodo;
import modelo.Tareas;
import modelo.Pila;
import modelo.Cola;
import modelo.ListaSimple;
import modelo.analisis.ListaSimpleLong;

import vista.InterfazTarea;
import vista.NeuvaVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

public class ControladorVistaTarea implements ActionListener{
	
	Nodo inicio;

	private Nodo nodoTarea;
	private Tareas tarea;
	private Cola cola = new Cola();
	private InterfazTarea view=new InterfazTarea();
	private int posicionNodo=0;

	//=============CONSTRUCTOR=============
	public ControladorVistaTarea(InterfazTarea view) {
		this.view=view;
		
		this.view.btnGuardar.addActionListener(this);
		this.view.btnEditar.addActionListener(this);
		this.view.btnEliminar.addActionListener(this);
		this.view.btnPilaTareas.addActionListener(this);
		this.view.btnMezclar.addActionListener(this);
		this.view.btnAnalisis.addActionListener(this);
		
		inicio=null;
	}
	
	//=============MÉTODO QUE INICIA LA VISTA=============
	public void iniciarView() {
		view.setVisible(true);
		System.out.println("\n============================================");
		System.out.println("Se mostró la vista correctamente");
		System.out.println("\n============================================");
	}
	
	//=============SETTERS Y GETTERS=============
	public void setPosicion(int posicion) {
		this.posicionNodo=posicion;
	}
	
	public Tareas getTareaBuscada() {
		return tarea;
	}

	public void setTareaBuscada(Tareas tarea) {
		this.tarea = tarea;
	}
	
	public void cleanFields() {
  		view.txtFecha.setText("");
  		view.txtTitulo.setText("");
  		view.taDescripcion.setText("");
  	}
	
	//=============VALIDADORES=============
	public boolean isEmpty() {
		return inicio==null;
	}
	
	public boolean validarEntradas(){
		//Validación título
        if(view.txtTitulo.getText().isBlank()) {
        	messagesJOptionPane(1);
        	return false;
        }else if(view.txtTitulo.getText().length()==0) {
        	messagesJOptionPane(2);
        	return false;
        }
        //Validación Descripción
        if(view.taDescripcion.getText().isBlank()) {
        	messagesJOptionPane(3);
        	return false;
        }
        
        //Validación Fecha
        if(view.txtFecha.getText().isBlank()) {
        	messagesJOptionPane(4);
        	return false;
        }else if(!view.txtFecha.getText().matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{2}$")) {
        	messagesJOptionPane(4);
        	System.out.println("\n============================================");
    		System.out.println("La fecha se ingresó incorrectamente");
    		System.out.println("\n============================================");
        	return false;
        }
        return true;
    }
	
	//=============DESARROLLO=============	
	public Pila formarPila() {
		ListaSimple nodos = new ListaSimple();
	    Nodo actual = inicio;
	    while (actual != null) {
	        nodos.insertarFinal(actual);
	        actual = actual.getEnlace();
	    }

	    // Ordenar la lista por fecha (de más reciente a más antigua)
	    nodos.ordenarPorFecha();

	    Pila pila = new Pila();

	    // Recorrer la ListaSimple manualmente con un nombre diferente para la variable
	    Nodo nodoActual = nodos.getInicio(); // Variable con nombre diferente
	    while (nodoActual != null) {
	        pila.push(nodoActual.getTarea());
	        nodoActual = nodoActual.getEnlace();
	    }

	    return pila;
		
        /*List<Nodo> nodos = new ArrayList<>();
        Nodo actual = inicio;
        while (actual != null) {
            nodos.add(actual);
            actual = actual.getEnlace();
        }

        nodos.sort((n1, n2) -> n2.getTarea().getFecha().compareTo(n1.getTarea().getFecha()));*/

        /*Pila pila = new Pila();
        for (Nodo nodo : nodos) {
            pila.push(nodo.getTarea());
        }

        return pila;*/
    }
	
	public Cola formarCola() {
		Nodo actual = inicio;

	    while (actual != null) {
	        cola.enqueue(new Nodo(actual.getTarea())); // Encolamos una copia del nodo
	        actual = actual.getEnlace();
	    }

	    armarTabla();
	    return cola;
		
	}
    
    public void armarTabla() {
    	DefaultTableModel tablaColocar=(DefaultTableModel) view.tbTablaTareas.getModel();
        tablaColocar.setRowCount(0);//Pa borrar la tabla
        
        for(Nodo aux=inicio;aux!=null;aux=aux.getEnlace()) {
        	tablaColocar.addRow(new Object[]{aux.getTarea().getId(), aux.getTarea().getTitulo(),aux.getTarea().getCategoria(),aux.getTarea().getFecha()});
        	System.out.println("\n============================================");
    		System.out.println("ID: "+aux.getTarea().getId());
    		System.out.println("\n============================================");
        }
        
        // Crear la tabla con el modelo
        view.tbTablaTareas.setModel(tablaColocar);
    }
    
    public Nodo generarTarea(){
        tarea=new Tareas(view.txtTitulo.getText(),view.taDescripcion.getText(),view.txtFecha.getText(),view.cbCategoria.getSelectedItem().toString());
        System.out.println("\n============================================");
		System.out.println("ID: "+tarea.getId());
		System.out.println("Título: "+tarea.getTitulo());
		System.out.println("Descripción: "+tarea.getDescripcion());
		System.out.println("Fecha: "+tarea.getFecha());
		System.out.println("Categoría: "+tarea.getCategoria());
		System.out.println("\n============================================");
        nodoTarea=new Nodo(tarea);
        
        return nodoTarea;
    }
  	public void insertarInicio(Nodo tareaColocar) {
  		Nodo nuevo=new Nodo(tareaColocar.getTarea());
  		if(inicio!=null) {
  			nuevo.setEnlace(inicio);
  		}
  		inicio=nuevo;
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
  	
  	public Nodo buscarNodo(Tareas buscado) {//Método de Lista simple para buscar editado con posición
  	    Nodo encontrado = null;
  	    int posicion = 0; // Contador para registrar la posición

  	    for (Nodo aux = inicio; aux != null && encontrado == null; aux = aux.getEnlace()) {
  	        if (aux.getTarea().getId().equals(buscado.getId())) {
  	            encontrado = aux;
  	            setPosicion(posicion);
  	        } else {
  	            posicion++;
  	        }
  	    }

  	    return encontrado;
  	}

	public void editarTarea() {
  	    // Obtener el modelo de la tabla y la fila seleccionada
  	    DefaultTableModel modelo = (DefaultTableModel) view.tbTablaTareas.getModel();
  	    int filaSeleccionada = view.tbTablaTareas.getSelectedRow();

  	    if (filaSeleccionada == -1) {
  	        JOptionPane.showMessageDialog(null, "Seleccione una tarea para editar.");
  	        return;
  	    }

  	    // Obtener el ID de la fila seleccionada
  	    String id = modelo.getValueAt(filaSeleccionada, 0).toString();

  	    // Crear objeto tarea para buscar
  	    Tareas tareaBuscar = new Tareas(id);

  	    // Buscar el nodo correspondiente
  	    Nodo nodoEncontrado = buscarNodo(tareaBuscar);

  	    if (nodoEncontrado == null) {
  	        JOptionPane.showMessageDialog(null, "Tarea no encontrada.");
  	        return;
  	    }

  	    // Guardar posición del nodo (en el atributo global de la clase)
  	    this.posicionNodo = filaSeleccionada;

  	    setTareaBuscada(tareaBuscar);

  	    // Cargar datos de la tarea en los campos de la vista
  	    view.txtTitulo.setText(nodoEncontrado.getTarea().getTitulo());
  	    view.cbCategoria.setSelectedItem(nodoEncontrado.getTarea().getCategoria());
  	    view.txtFecha.setText(nodoEncontrado.getTarea().getFecha());
  	    view.taDescripcion.setText(nodoEncontrado.getTarea().getDescripcion());

  	    // Cambiar texto del botón Editar a "Actualizar"
  	    view.btnEditar.setText("Actualizar");

  	    // Deshabilitar botones Guardar y Eliminar
  	    view.btnGuardar.setEnabled(false);
  	    view.btnEliminar.setEnabled(false);
  	}


  	public void actualizarTarea() {
  	    // Validar campos
  	    if (!validarEntradas()) {
  	        JOptionPane.showMessageDialog(null, "Complete correctamente todos los campos.");
  	        return;
  	    }

  	    // Obtener datos desde la vista
  	    String id = view.tbTablaTareas.getValueAt(view.tbTablaTareas.getSelectedRow(), 0).toString();
  	    String titulo = view.txtTitulo.getText();
  	    String descripcion = view.taDescripcion.getText();
  	    String fecha = view.txtFecha.getText();
  	    String categoria = view.cbCategoria.getSelectedItem().toString();

  	    // Crear nueva tarea con los datos actualizados
  	    Tareas tareaEditada = new Tareas(id, titulo, descripcion, fecha, categoria);
  	    Nodo nodoNuevo = new Nodo(tareaEditada);
  	    // 	Eliminar el nodo de la lista
  	    eliminarNodo(getTareaBuscada());
  	    // Insertar la nueva tarea en la posición original
  	    insertarEnPosicion(nodoNuevo, this.posicionNodo);

  	    // Restaurar estado de botones
  	    view.btnGuardar.setEnabled(true);
  	    view.btnEliminar.setEnabled(true);
  	    view.btnEditar.setText("Editar");
  	    
  	    cleanFields();
  	}
  	
  	public Nodo eliminarInicio() {
  	    Nodo nodoEliminado = inicio;
  	    if (inicio != null) {
  	        inicio = inicio.getEnlace();
  	        nodoEliminado.setEnlace(null);
  	    }
  	    armarTabla(); // Actualizar tabla después de eliminar
  	    return nodoEliminado;
  	}

  	
  	public Nodo eliminarFinal() {
  	    Nodo nodoEliminado = null;
  	    if (inicio == null) {
  	        return null;
  	    }
  	    if (inicio.getEnlace() == null) {
  	        nodoEliminado = inicio;
  	        inicio = null;
  	    } else {
  	        Nodo actual = inicio;
  	        Nodo anterior = null;
  	        while (actual.getEnlace() != null) {
  	            anterior = actual;
  	            actual = actual.getEnlace();
  	        }
  	        nodoEliminado = actual;
  	        anterior.setEnlace(null);
  	    }
  	    armarTabla(); // Actualizar tabla después de eliminar
  	    return nodoEliminado;
  	}
  	
  	public boolean eliminarNodo(Tareas t) {
  	    Nodo actual = inicio;
  	    Nodo anterior = null;
  	    boolean encontrado = false;

  	    while (actual != null && !encontrado) {
  	        if (actual.getTarea().getId().equals(t.getId())) { // comparar por ID
  	            encontrado = true;
  	        } else {
  	            anterior = actual;
  	            actual = actual.getEnlace();
  	        }
  	    }

  	    if (encontrado && actual != null) {
  	        if (actual == inicio) {
  	            inicio = actual.getEnlace();
  	        } else {
  	            anterior.setEnlace(actual.getEnlace());
  	        }
  	        actual.setEnlace(null);
  	        armarTabla(); // Actualizar la tabla
  	    }

  	    return encontrado;
  	}
  	
  	public Nodo mezclaNatural(Nodo inicio) {
  	    if (inicio==null || inicio.getEnlace() == null) {//lista vacía o un solo elemento
  	        return inicio;
  	    }

  	    //variables para controlar los runs ordenados
  	    Nodo lista1 = null;
  	    Nodo lista2 = null;
  	    Nodo actual = inicio;
  	    Nodo ultimo1 = null;
  	    Nodo ultimo2 = null;
  	    boolean alternar = true;

  	    while (actual != null) {//bucle para generar las runs hasta que se acabe la lista principal
  	        Nodo runInicio = actual;//se guarda el Nodo actual
  	        while (actual.getEnlace() != null && actual.getTarea().getId().compareTo(actual.getEnlace().getTarea().getId()) <= 0) {//Avanza hasta que termine de guardarse el run ordenado
  	            actual = actual.getEnlace();
  	        }
  	       
  	        Nodo siguiente = actual.getEnlace(); // Guarda el siguiente run
  	        actual.setEnlace(null); // Corta el run

  	        if (alternar) {//alterna entre las listas auxiliares
  	            if (lista1 == null) {//si aún no se añade ninguna run
  	                lista1 = runInicio;
  	                ultimo1 = actual;
  	            } else {//si ya se ha añadido alguna run
  	                ultimo1.setEnlace(runInicio);
  	                ultimo1 = actual;
  	            }
  	        } else {
  	            if (lista2 == null) {//si aún no se añade ninguna run
  	                lista2 = runInicio;
  	                ultimo2 = actual;
  	            } else {//si ya se ha añadido alguna run
  	                ultimo2.setEnlace(runInicio);
  	                ultimo2 = actual;
  	            }
  	        }

  	        alternar = !alternar;// para alterar las listas auxiliares
  	        actual = siguiente;// asignación del siguiente valor para buscar más runs
  	    }

  	    Nodo resultado = mezclarListas(lista1, lista2);//Mezclar las dos listas ordenadas
  	    
  	    if (resultado == inicio) {// Si ya está ordenado, termina; si no, repite recursivamente
  	        return resultado;//devuelve la lista ya ordenada
  	    } else {
  	        return mezclaNatural(resultado); // Llama recursivamente hasta que esté ordenado
  	    }
  	}
  	
  	private Nodo mezclarListas(Nodo a, Nodo b) {
  		//Condiciones de retorno para el caso de que una de las listas venga vacía
  	    if (a == null) return b;
  	    if (b == null) return a;

  	    Nodo resultado;//variable para almacenar el resultado tras mezclar las listas

  	    if (a.getTarea().getId().compareTo(b.getTarea().getId()) <= 0) {
  	        resultado = a;
  	        resultado.setEnlace(mezclarListas(a.getEnlace(), b));
  	    } else {
  	        resultado = b;
  	        resultado.setEnlace(mezclarListas(a, b.getEnlace()));
  	    }

  	    return resultado;
  	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==view.btnGuardar) {
			if(validarEntradas()){
				Nodo tareaGenerada=generarTarea();
				System.out.println("\n============================================");
	    		System.out.println("ID: "+tareaGenerada.getTarea().getId());
	    		System.out.println("\n============================================");
				switch(view.cbInsertar.getSelectedIndex()) {
				case 0:
					insertarInicio(tareaGenerada);
					formarCola();
					break;
				case 1:
					insertarFinal(tareaGenerada);
					formarCola();
					break;
				case 2:
					String respuesta = JOptionPane.showInputDialog(view, "Ingrese la posición para insertar la tarea (int).", "INSERTAR EN POSICIÓN", JOptionPane.QUESTION_MESSAGE);
		        	if(!respuesta.matches("\\d+")) {
		        		insertarEnPosicion(tareaGenerada, Integer.parseInt(respuesta));
		        		formarCola();
		        	}else {
		        		messagesJOptionPane(5);
		        	}
					break;
				}
				cleanFields();
			}else {
				System.out.println("\n============================================");
				System.out.println("Hubo un error en la entrada de texto");
				System.out.println("\n============================================");
			}
		}
		
		if(e.getSource()==view.btnEditar) {
			if(view.btnEditar.getText()=="Editar") {
				editarTarea();
			}else {
				actualizarTarea();
			}
		}
		
		if(e.getSource() == view.btnEliminar) {
		    DefaultTableModel modelo = (DefaultTableModel) view.tbTablaTareas.getModel();
		    int filaSeleccionada = view.tbTablaTareas.getSelectedRow();

		    switch(view.cbInsertar.getSelectedIndex()) {
		        case 0: // Eliminar del inicio
		            eliminarInicio();
		            break;
		        case 1: // Eliminar del final
		            eliminarFinal();
		            break;
		        case 2: // Eliminar por posición (a partir de selección en tabla)
		        	if (filaSeleccionada == -1) {
				        JOptionPane.showMessageDialog(null, "Seleccione una tarea para eliminar.");
				        return;
				    }
		            String id = modelo.getValueAt(filaSeleccionada, 0).toString();
		            Tareas tareaEliminar = new Tareas(id);
		            boolean eliminado = eliminarNodo(tareaEliminar);
		            if (!eliminado) {
		                JOptionPane.showMessageDialog(null, "No se pudo eliminar la tarea.");
		            }
		            break;
		    }
		}
		
		if(e.getSource()==view.btnPilaTareas) {
			NeuvaVista nuevaVista = new NeuvaVista();
			ControladorVistaSecundaria controlador = new ControladorVistaSecundaria(nuevaVista);
			controlador.cargarPila(formarPila());
			controlador.iniciarView();
		}
		
		if (e.getSource() == view.btnMezclar) {
		    inicio=mezclaNatural(inicio);
		    System.out.println("\n\nSe ha mezclado la tabla\n\n");
		    armarTabla();
		}
		
		if (e.getSource() == view.btnAnalisis) {
			analizarBusqueda();
		}
	}
	
	//=============EXTRAS=============
	public void messagesJOptionPane(int pane) {
		switch(pane) {
		case 1:
			JOptionPane.showMessageDialog(view, "Ingrese un título para la tarea por favor", "ERROR", JOptionPane.WARNING_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(view, "Ingrese por lo menos un caracter", "ERROR", JOptionPane.WARNING_MESSAGE);
			break;
		case 3:
			JOptionPane.showMessageDialog(view, "Ingrese una descripción para la tarea por favor", "ERROR", JOptionPane.WARNING_MESSAGE);
			break;
		case 4:
			JOptionPane.showMessageDialog(view, "Ingrese una fecha para la tarea por favor, con el formato dd/mm/aa", "ERROR", JOptionPane.WARNING_MESSAGE);
			break;
		case 5:
			JOptionPane.showMessageDialog(view, "Debe ingresar la posición en formato número, inténtelo de nuevo por favor", "ERROR", JOptionPane.WARNING_MESSAGE);
			break;
		}
		
	}
	
	public void analizarBusqueda() {//Método principal que coordina ambos análisis
	    int totalTareas = contarTareas();
	    if (totalTareas < 5) {
	        JOptionPane.showMessageDialog(view, 
	            "Debe registrar al menos 5 tareas para realizar el análisis.",
	            "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    // Realizar análisis de Lista Simple propia
	    StringBuilder analisisListaSimple = analizarBusquedaListaSimple();
	    
	    // Realizar análisis de Lista Nativa de Java
	    StringBuilder analisisListaNativa = analizarBusquedaListaNativa();
	    
	    // Combinar ambos análisis
	    StringBuilder analisisCompleto = new StringBuilder();
	    analisisCompleto.append("=== COMPARACIÓN DE ALGORITMOS DE BÚSQUEDA ===\n\n");
	    analisisCompleto.append(analisisListaSimple);
	    analisisCompleto.append("\n" + "=".repeat(50) + "\n\n");
	    analisisCompleto.append(analisisListaNativa);
	    
	    // Mostrar análisis completo
	    JTextArea textArea = new JTextArea(analisisCompleto.toString());
	    textArea.setEditable(false);
	    textArea.setFont(new Font("SimSun", Font.PLAIN, 15));
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    scrollPane.setPreferredSize(new Dimension(600, 500));
	    
	    JOptionPane.showMessageDialog(null, scrollPane, 
	        "Análisis Comparativo de Algoritmos", JOptionPane.INFORMATION_MESSAGE);
	}

	// Análisis de la Lista Simple propia
	private StringBuilder analizarBusquedaListaSimple() {
	    int totalTareas = contarTareas();
	    ListaSimpleLong tiempos = new ListaSimpleLong();
	    Nodo aux = inicio;
	    
	    // Medir tiempos de búsqueda en Lista Simple
	    while (aux != null) {
	        long inicioTiempo = System.nanoTime();
	        buscarNodo(aux.getTarea());
	        long finTiempo = System.nanoTime();
	        tiempos.insertarFinal(finTiempo - inicioTiempo);
	        aux = aux.getEnlace();
	    }
	    
	    // Calcular estadísticas
	    long mejor = tiempos.obtener(0);
	    long peor = tiempos.obtener(0);
	    long suma = 0;
	    
	    for (int i = 0; i < tiempos.contarNodos(); i++) {
	        long tiempo = tiempos.obtener(i);
	        if (tiempo < mejor) mejor = tiempo;
	        if (tiempo > peor) peor = tiempo;
	        suma += tiempo;
	    }
	    
	    long promedio = suma / totalTareas;
	    
	    // Mostrar gráfica para Lista Simple
	    mostrarGrafica(tiempos, "Lista Simple Propia", "Búsqueda en Lista Simple");
	    
	    // Construir análisis
	    StringBuilder sb = new StringBuilder();
	    sb.append("LISTA SIMPLE PROPIA\n");
	    sb.append("Análisis del algoritmo: buscarNodo\n");
	    sb.append("-----------------------------------\n");
	    sb.append("Eficiencia: Búsqueda exhaustiva (lineal)\n");
	    sb.append("Complejidad temporal:\n");
	    sb.append("   - Mejor caso: O(1) - elemento en primera posición\n");
	    sb.append("   - Peor caso: O(n) - elemento en última posición o no existe\n");
	    sb.append("   - Promedio:  O(n/2) ≈ O(n)\n");
	    sb.append("Complejidad espacial: O(1) - espacio constante adicional\n");
	    sb.append("Notación asintótica: O(n)\n");
	    sb.append("-----------------------------------\n");
	    sb.append("Resultados de " + totalTareas + " búsquedas:\n");
	    sb.append("Tiempo mejor caso (ns): " + String.format("%,d", mejor) + "\n");
	    sb.append("Tiempo peor caso (ns): " + String.format("%,d", peor) + "\n");
	    sb.append("Tiempo promedio (ns): " + String.format("%,d", promedio) + "\n");
	    sb.append("Diferencia mejor-peor: " + String.format("%,d", (peor - mejor)) + " ns\n");
	    
	    return sb;
	}

	// Análisis de la Lista Nativa de Java
	private StringBuilder analizarBusquedaListaNativa() {
	    int totalTareas = contarTareas();
	    
	    // Convertir Lista Simple a ArrayList para comparación
	    ArrayList<Tareas> listaNativa = new ArrayList<>();
	    Nodo aux = inicio;
	    while (aux != null) {
	        listaNativa.add(aux.getTarea());
	        aux = aux.getEnlace();
	    }
	    
	    // Medir tiempos de búsqueda en ArrayList
	    ListaSimpleLong tiemposNativa = new ListaSimpleLong();
	    
	    for (Tareas tarea : listaNativa) {
	        long inicioTiempo = System.nanoTime();
	        buscarEnListaNativa(listaNativa, tarea.getId());
	        long finTiempo = System.nanoTime();
	        tiemposNativa.insertarFinal(finTiempo - inicioTiempo);
	    }
	    
	    // Calcular estadísticas
	    long mejor = tiemposNativa.obtener(0);
	    long peor = tiemposNativa.obtener(0);
	    long suma = 0;
	    
	    for (int i = 0; i < tiemposNativa.contarNodos(); i++) {
	        long tiempo = tiemposNativa.obtener(i);
	        if (tiempo < mejor) mejor = tiempo;
	        if (tiempo > peor) peor = tiempo;
	        suma += tiempo;
	    }
	    
	    long promedio = suma / totalTareas;
	    
	    // Mostrar gráfica para Lista Nativa
	    mostrarGrafica(tiemposNativa, "ArrayList Java", "Búsqueda en ArrayList");
	    
	    // Construir análisis
	    StringBuilder sb = new StringBuilder();
	    sb.append("ARRAYLIST NATIVA DE JAVA\n");
	    sb.append("Análisis del algoritmo: búsqueda lineal con stream/indexOf\n");
	    sb.append("-----------------------------------\n");
	    sb.append("Eficiencia: Búsqueda optimizada (lineal con optimizaciones JVM)\n");
	    sb.append("Complejidad temporal:\n");
	    sb.append("   - Mejor caso: O(1) - elemento en primera posición\n");
	    sb.append("   - Peor caso: O(n) - elemento en última posición o no existe\n");
	    sb.append("   - Promedio:  O(n/2) ≈ O(n)\n");
	    sb.append("Complejidad espacial: O(1) - espacio constante adicional\n");
	    sb.append("Notación asintótica: O(n)\n");
	    sb.append("Optimizaciones: JVM, acceso directo por índice, cache locality\n");
	    sb.append("-----------------------------------\n");
	    sb.append("Resultados de " + totalTareas + " búsquedas:\n");
	    sb.append("Tiempo mejor caso (ns): " + String.format("%,d", mejor) + "\n");
	    sb.append("Tiempo peor caso (ns): " + String.format("%,d", peor) + "\n");
	    sb.append("Tiempo promedio (ns): " + String.format("%,d", promedio) + "\n");
	    sb.append("Diferencia mejor-peor: " + String.format("%,d", (peor - mejor)) + " ns\n");
	    
	    return sb;
	}

	// Método auxiliar para buscar en ArrayList
	private Tareas buscarEnListaNativa(ArrayList<Tareas> lista, String id) {
	    // Implementación similar a tu buscarNodo pero para ArrayList
	    for (Tareas tarea : lista) {
	        if (tarea.getId().equals(id)) {
	            return tarea;
	        }
	    }
	    return null;
	}

	// Método mostrarGrafica modificado para recibir parámetros adicionales
	private void mostrarGrafica(ListaSimpleLong tiempos, String tipoLista, String titulo) {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    
	    for (int i = 0; i < tiempos.contarNodos(); i++) {
	        dataset.addValue(tiempos.obtener(i), "Tiempo (ns)", "Búsqueda " + (i + 1));
	    }
	    
	    JFreeChart chart = ChartFactory.createBarChart(
	        titulo + " - Tiempos de búsqueda", 
	        "Iteración", 
	        "Tiempo (nanosegundos)", 
	        dataset, 
	        PlotOrientation.VERTICAL, 
	        true, true, false);
	    
	    // Personalizar el gráfico
	    CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundPaint(java.awt.Color.WHITE);
	    plot.setRangeGridlinePaint(java.awt.Color.GRAY);
	    
	    ChartPanel panel = new ChartPanel(chart);
	    panel.setPreferredSize(new Dimension(700, 500));
	    
	    JFrame ventana = new JFrame("Análisis: " + tipoLista);
	    ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventana.add(panel);
	    ventana.setSize(750, 550);
	    ventana.setLocationRelativeTo(null);
	    ventana.setVisible(true);
	    
	    // Pequeña pausa para que las ventanas no se abran exactamente al mismo tiempo
	    try {
	        Thread.sleep(100);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}
	
	private int contarTareas() {
	    int count = 0;
	    Nodo aux = inicio;
	    while (aux != null) {
	        count++;
	        aux = aux.getEnlace();
	    }
	    return count;
	}
}

/*public void analizarBusqueda() {
	    int totalTareas = contarTareas();
	    if (totalTareas < 5) {
	        JOptionPane.showMessageDialog(null, 
	            "Debe registrar al menos 5 tareas para realizar el análisis.",
	            "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    ListaSimpleLong tiempos = new ListaSimpleLong();
	    Nodo aux = inicio;
	    
	    while (aux != null) {
	        long inicioTiempo = System.nanoTime();
	        buscarNodo(aux.getTarea());
	        long finTiempo = System.nanoTime();
	        tiempos.insertarFinal(finTiempo - inicioTiempo);
	        aux = aux.getEnlace();
	    }
	    
	    // Calcular estadísticas usando ListaSimpleLong
	    long mejor = tiempos.obtener(0);
	    long peor = tiempos.obtener(0);
	    long suma = 0;
	    
	    for (int i = 0; i < tiempos.contarNodos(); i++) {
	        long tiempo = tiempos.obtener(i);
	        if (tiempo < mejor) mejor = tiempo;
	        if (tiempo > peor) peor = tiempo;
	        suma += tiempo;
	    }
	    
	    long promedio = suma / totalTareas;
	    
	    // Mostrar análisis en JOptionPane
	    StringBuilder sb = new StringBuilder();
	    sb.append("Análisis del algoritmo: buscarNodo\n");
	    sb.append("-----------------------------------\n");
	    sb.append("Eficiencia: Búsqueda exhaustiva (lineal)\n");
	    sb.append("Complejidad temporal:\n");
	    sb.append("   - Mejor caso: O(1)\n");
	    sb.append("   - Peor caso: O(n)\n");
	    sb.append("   - Promedio:  O(n)\n");
	    sb.append("Complejidad espacial: O(1)\n");
	    sb.append("Notación asintótica: O(n)\n");
	    sb.append("-----------------------------------\n");
	    sb.append("Tiempo mejor caso (ns): " + mejor + "\n");
	    sb.append("Tiempo peor caso (ns): " + peor + "\n");
	    sb.append("Tiempo promedio (ns): " + promedio + "\n");
	    
	    // 	Mostrar gráfica con JFreeChart
	    mostrarGrafica(tiempos);
	    
	    JOptionPane.showMessageDialog(null, sb.toString(), 
	        "Análisis de Algoritmo", JOptionPane.INFORMATION_MESSAGE);
	}

	private void mostrarGrafica(ListaSimpleLong tiempos) {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    
	    for (int i = 0; i < tiempos.contarNodos(); i++) {
	        dataset.addValue(tiempos.obtener(i), "Tiempo (ns)", "Búsqueda " + (i + 1));
	    }
	    
	    JFreeChart chart = ChartFactory.createBarChart(
	        "Tiempos de búsqueda", 
	        "Iteración", 
	        "Tiempo (ns)", 
	        dataset, 
	        PlotOrientation.VERTICAL, 
	        true, true, false);
	    
	    ChartPanel panel = new ChartPanel(chart);
	    JFrame ventana = new JFrame("Gráfica de análisis");
	    ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventana.add(panel);
	    ventana.setSize(600, 400);
	    ventana.setLocationRelativeTo(null);
	    ventana.setVisible(true);
	}

	*/