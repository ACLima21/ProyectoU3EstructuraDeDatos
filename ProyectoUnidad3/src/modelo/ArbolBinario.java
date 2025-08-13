package modelo;

public class ArbolBinario {
	protected ArbolNodo raiz;

	public ArbolBinario() {// Constructor sin parámetros: crea un árbol vacío
		raiz = null;
	}

	public ArbolBinario(ArbolNodo raiz) {// Constructor con un nodo raíz dado
		this.raiz = raiz;
	}

	public ArbolNodo raizArbol() {// Retorna la raíz del árbol
		return raiz;
	}

	public boolean esVacio() {// Verifica si el árbol está vacío
		return raiz == null;
	}

	public static ArbolNodo nuevoArbol(ArbolNodo ramaIzqda, Tareas dato, ArbolNodo ramaDrcha) {
		return new ArbolNodo(ramaIzqda, dato, ramaDrcha);
	}

	public static StringBuilder preorden(ArbolNodo r) {// Recorrido de un árbol binario en preorden
		StringBuilder sb = new StringBuilder();
		if (r != null) {
			sb.append("[").append(r.getDato().getId()).append("|").append(r.getDato().getTitulo()).append("] ");
			sb.append(preorden(r.getIzdo()));
			sb.append(preorden(r.getDcho()));
		}
		return sb;
	}

	public static StringBuilder inorden(ArbolNodo r) {// Recorrido de un árbol binario en inorden
		StringBuilder sb = new StringBuilder();
		if (r != null) {
			sb.append(inorden(r.getIzdo()));
			sb.append("[").append(r.getDato().getId()).append("|").append(r.getDato().getTitulo()).append("] ");
			sb.append(inorden(r.getDcho()));
		}
		return sb;
	}

	public static StringBuilder postorden(ArbolNodo r) {// Recorrido de un árbol binario en postorden
		StringBuilder sb = new StringBuilder();
		if (r != null) {
			sb.append(postorden(r.getIzdo()));
			sb.append(postorden(r.getDcho()));
			sb.append("[").append(r.getDato().getId()).append("|").append(r.getDato().getTitulo()).append("] ");
		}
		return sb;
	}

	public static int numNodos(ArbolNodo raiz) {// Cuenta el número total de nodos en un árbol binario
		if (raiz == null) {
			return 0; // Árbol vacío
		} else {
			// Cuenta el nodo actual + nodos del subárbol izquierdo + nodos del subárbol
			// derecho
			return 1 + numNodos(raiz.getIzdo()) + numNodos(raiz.getDcho());
		}
	}

}
