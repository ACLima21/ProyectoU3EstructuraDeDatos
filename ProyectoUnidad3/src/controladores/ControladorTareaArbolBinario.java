package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import vista.InterfazTareaArbolBinario;

import modelo.Tareas;
import modelo.ArbolBinarioBusqueda;
import modelo.ArbolNodo;
import modelo.analisis.ListaSimpleLong;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

import java.util.ArrayList;

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
		this.view.btnAnalisis.addActionListener(this);
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
		
		if (e.getSource() == view.btnAnalisis) {
			analizarBusquedaArbol(); // Llamada al método de análisis
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
	
	// Método principal que coordina ambos análisis de búsqueda
	public void analizarBusquedaArbol() {
	    int totalTareas = contarNodosArbol();
	    if (totalTareas < 5) {
	        SwingUtilities.invokeLater(() -> {
	            JOptionPane.showMessageDialog(null, 
	                "Debe registrar al menos 5 tareas para realizar el análisis.",
	                "Advertencia", JOptionPane.WARNING_MESSAGE);
	        });
	        return;
	    }
	    
	    // Realizar análisis de Búsqueda Recursiva
	    StringBuilder analisisRecursivo = analizarBusquedaRecursiva();
	    
	    // Realizar análisis de Búsqueda Iterativa
	    StringBuilder analisisIterativo = analizarBusquedaIterativa();
	    
	    // Combinar ambos análisis
	    StringBuilder analisisCompleto = new StringBuilder();
	    analisisCompleto.append("=== ANÁLISIS DE ALGORITMOS DIVIDE Y VENCERÁS ===\n");
	    analisisCompleto.append("=== BÚSQUEDA EN ÁRBOL BINARIO DE BÚSQUEDA ===\n\n");
	    analisisCompleto.append(analisisRecursivo);
	    analisisCompleto.append("\n" + "=".repeat(60) + "\n\n");
	    analisisCompleto.append(analisisIterativo);
	    
	    // Mostrar análisis en JFrame independiente (no bloqueante)
	    JTextArea textArea = new JTextArea(analisisCompleto.toString());
	    textArea.setEditable(false);
	    textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    scrollPane.setPreferredSize(new Dimension(700, 600));
	    
	    JFrame ventanaAnalisis = new JFrame("Análisis Comparativo - Árbol Binario de Búsqueda");
	    ventanaAnalisis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventanaAnalisis.add(scrollPane);
	    ventanaAnalisis.setSize(750, 650);
	    ventanaAnalisis.setLocationRelativeTo(null);
	    ventanaAnalisis.setVisible(true);
	}

	// Análisis de la Búsqueda Recursiva (Divide y Vencerás)
	private StringBuilder analizarBusquedaRecursiva() {
	    int totalTareas = contarNodosArbol();
	    ListaSimpleLong tiempos = new ListaSimpleLong();
	    
	    // Obtener todas las tareas del árbol para hacer búsquedas
	    ArrayList<Tareas> tareasEnArbol = obtenerTareasDelArbol();
	    
	    // Medir tiempos de búsqueda recursiva
	    for (Tareas tarea : tareasEnArbol) {
	        long inicioTiempo = System.nanoTime();
	        arbolBB.buscar(tarea); // Búsqueda recursiva
	        long finTiempo = System.nanoTime();
	        tiempos.insertarFinal(finTiempo - inicioTiempo);
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
	    
	    // Mostrar gráfica para Búsqueda Recursiva
	    mostrarGraficaArbol(tiempos, "Búsqueda Recursiva (Divide y Vencerás)", 
	                        "Búsqueda Recursiva en ABB");
	    
	    // Calcular altura del árbol para análisis de complejidad
	    int altura = calcularAlturaArbol();
	    
	    // Construir análisis
	    StringBuilder sb = new StringBuilder();
	    sb.append("BÚSQUEDA RECURSIVA - PARADIGMA DIVIDE Y VENCERÁS\n");
	    sb.append("Algoritmo: Búsqueda binaria recursiva en ABB\n");
	    sb.append("-----------------------------------------------------------\n");
	    sb.append("CARACTERÍSTICAS DEL PARADIGMA:\n");
	    sb.append("✓ Divide: El problema se divide en subproblemas más pequeños\n");
	    sb.append("✓ Vencerás: Cada subproblema se resuelve recursivamente\n");
	    sb.append("✓ Combina: No requiere combinación (resultado directo)\n");
	    sb.append("-----------------------------------------------------------\n");
	    sb.append("ANÁLISIS DE COMPLEJIDAD:\n");
	    sb.append("Complejidad temporal:\n");
	    sb.append("   - Mejor caso: O(1) - elemento en la raíz\n");
	    sb.append("   - Caso promedio: O(log n) - árbol balanceado\n");
	    sb.append("   - Peor caso: O(h) donde h es la altura del árbol\n");
	    sb.append("   - Altura actual del árbol: " + altura + " niveles\n");
	    sb.append("Complejidad espacial: O(h) - pila de recursión\n");
	    sb.append("Notación asintótica: O(log n) en árbol balanceado, O(n) degenerado\n");
	    sb.append("-----------------------------------------------------------\n");
	    sb.append("VENTAJAS DE DIVIDE Y VENCERÁS:\n");
	    sb.append("• Reduce la complejidad de O(n) a O(log n)\n");
	    sb.append("• Aprovecha la estructura ordenada del ABB\n");
	    sb.append("• Elimina la mitad del espacio de búsqueda en cada paso\n");
	    sb.append("• Código elegante y fácil de entender\n");
	    sb.append("-----------------------------------------------------------\n");
	    sb.append("RESULTADOS DE " + totalTareas + " BÚSQUEDAS:\n");
	    sb.append("Tiempo mejor caso (ns): " + String.format("%,d", mejor) + "\n");
	    sb.append("Tiempo peor caso (ns): " + String.format("%,d", peor) + "\n");
	    sb.append("Tiempo promedio (ns): " + String.format("%,d", promedio) + "\n");
	    sb.append("Diferencia mejor-peor: " + String.format("%,d", (peor - mejor)) + " ns\n");
	    sb.append("Eficiencia logarítmica: ≈" + String.format("%.2f", Math.log(totalTareas)/Math.log(2)) + " comparaciones teóricas\n");
	    
	    return sb;
	}

	// Análisis de la Búsqueda Iterativa
	private StringBuilder analizarBusquedaIterativa() {
	    int totalTareas = contarNodosArbol();
	    ListaSimpleLong tiempos = new ListaSimpleLong();
	    
	    // Obtener todas las tareas del árbol
	    ArrayList<Tareas> tareasEnArbol = obtenerTareasDelArbol();
	    
	    // Medir tiempos de búsqueda iterativa
	    for (Tareas tarea : tareasEnArbol) {
	        long inicioTiempo = System.nanoTime();
	        arbolBB.buscarIterativo(tarea); // Búsqueda iterativa
	        long finTiempo = System.nanoTime();
	        tiempos.insertarFinal(finTiempo - inicioTiempo);
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
	    
	    // Mostrar gráfica para Búsqueda Iterativa
	    mostrarGraficaArbol(tiempos, "Búsqueda Iterativa", 
	                        "Búsqueda Iterativa en ABB");
	    
	    int altura = calcularAlturaArbol();
	    
	    // Construir análisis
	    StringBuilder sb = new StringBuilder();
	    sb.append("BÚSQUEDA ITERATIVA - IMPLEMENTACIÓN NO RECURSIVA\n");
	    sb.append("Algoritmo: Búsqueda binaria iterativa en ABB\n");
	    sb.append("-----------------------------------------------------------\n");
	    sb.append("CARACTERÍSTICAS DE LA IMPLEMENTACIÓN:\n");
	    sb.append("✓ Usa ciclo while en lugar de recursión\n");
	    sb.append("✓ Misma lógica de divide y vencerás pero iterativa\n");
	    sb.append("✓ Control explícito del flujo de ejecución\n");
	    sb.append("-----------------------------------------------------------\n");
	    sb.append("ANÁLISIS DE COMPLEJIDAD:\n");
	    sb.append("Complejidad temporal:\n");
	    sb.append("   - Mejor caso: O(1) - elemento en la raíz\n");
	    sb.append("   - Caso promedio: O(log n) - árbol balanceado\n");
	    sb.append("   - Peor caso: O(h) donde h es la altura del árbol\n");
	    sb.append("   - Altura actual del árbol: " + altura + " niveles\n");
	    sb.append("Complejidad espacial: O(1) - espacio constante\n");
	    sb.append("Notación asintótica: O(log n) en árbol balanceado, O(n) degenerado\n");
	    sb.append("-----------------------------------------------------------\n");
	    sb.append("VENTAJAS DE LA VERSIÓN ITERATIVA:\n");
	    sb.append("• Menor uso de memoria (sin pila de recursión)\n");
	    sb.append("• Evita posible desbordamiento de pila\n");
	    sb.append("• Generalmente más eficiente en tiempo\n");
	    sb.append("• Control más directo del algoritmo\n");
	    sb.append("-----------------------------------------------------------\n");
	    sb.append("RESULTADOS DE " + totalTareas + " BÚSQUEDAS:\n");
	    sb.append("Tiempo mejor caso (ns): " + String.format("%,d", mejor) + "\n");
	    sb.append("Tiempo peor caso (ns): " + String.format("%,d", peor) + "\n");
	    sb.append("Tiempo promedio (ns): " + String.format("%,d", promedio) + "\n");
	    sb.append("Diferencia mejor-peor: " + String.format("%,d", (peor - mejor)) + " ns\n");
	    sb.append("Comparaciones máximas: ≈" + altura + " (altura del árbol)\n");
	    
	    return sb;
	}

	// Método auxiliar para obtener todas las tareas del árbol
	private ArrayList<Tareas> obtenerTareasDelArbol() {
	    ArrayList<Tareas> tareas = new ArrayList<>();
	    obtenerTareasRecursivo(arbolBB.raizArbol(), tareas);
	    return tareas;
	}

	private void obtenerTareasRecursivo(ArbolNodo nodo, ArrayList<Tareas> tareas) {
	    if (nodo != null) {
	        tareas.add((Tareas) nodo.getDato());
	        obtenerTareasRecursivo(nodo.getIzdo(), tareas);
	        obtenerTareasRecursivo(nodo.getDcho(), tareas);
	    }
	}

	// Método para contar nodos en el árbol
	private int contarNodosArbol() {
	    return contarNodosRecursivo(arbolBB.raizArbol());
	}

	private int contarNodosRecursivo(ArbolNodo nodo) {
	    if (nodo == null) return 0;
	    return 1 + contarNodosRecursivo(nodo.getIzdo()) + contarNodosRecursivo(nodo.getDcho());
	}

	// Método para calcular la altura del árbol
	private int calcularAlturaArbol() {
	    return calcularAlturaRecursivo(arbolBB.raizArbol());
	}

	private int calcularAlturaRecursivo(ArbolNodo nodo) {
	    if (nodo == null) return 0;
	    int alturaIzq = calcularAlturaRecursivo(nodo.getIzdo());
	    int alturaDer = calcularAlturaRecursivo(nodo.getDcho());
	    return Math.max(alturaIzq, alturaDer) + 1;
	}

	// Método mostrarGrafica especializado para análisis de árbol
	private void mostrarGraficaArbol(ListaSimpleLong tiempos, String tipoAlgoritmo, String titulo) {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    
	    for (int i = 0; i < tiempos.contarNodos(); i++) {
	        dataset.addValue(tiempos.obtener(i), "Tiempo (ns)", "Búsqueda " + (i + 1));
	    }
	    
	    JFreeChart chart = ChartFactory.createLineChart(
	        titulo + " - Tiempos de búsqueda", 
	        "Número de búsqueda", 
	        "Tiempo (nanosegundos)", 
	        dataset, 
	        PlotOrientation.VERTICAL, 
	        true, true, false);
	    
	    // Personalizar el gráfico
	    CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundPaint(java.awt.Color.WHITE);
	    plot.setRangeGridlinePaint(java.awt.Color.LIGHT_GRAY);
	    plot.setDomainGridlinePaint(java.awt.Color.LIGHT_GRAY);
	    
	    ChartPanel panel = new ChartPanel(chart);
	    panel.setPreferredSize(new Dimension(800, 600));
	    
	    JFrame ventana = new JFrame("Análisis: " + tipoAlgoritmo);
	    ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventana.add(panel);
	    ventana.setSize(850, 650);
	    ventana.setLocationRelativeTo(null);
	    ventana.setVisible(true);
	    
	    // Pequeña pausa para que las ventanas no se abran simultáneamente
	    try {
	        Thread.sleep(150);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}
}

