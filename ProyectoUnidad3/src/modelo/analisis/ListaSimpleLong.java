package modelo.analisis;

public class ListaSimpleLong {
    // Nodo interno para Long
    private class NodoLong {
        long dato;
        NodoLong enlace;
        
        public NodoLong(long dato) {
            this.dato = dato;
            this.enlace = null;
        }
    }
    
    private NodoLong inicio;
    
    public ListaSimpleLong() {
        inicio = null;
    }
    
    public void insertarFinal(long dato) {
        NodoLong nuevo = new NodoLong(dato);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            NodoLong actual = inicio;
            while (actual.enlace != null) {
                actual = actual.enlace;
            }
            actual.enlace = nuevo;
        }
    }
    
    public long obtener(int posicion) {
        if (inicio == null) {
            throw new IndexOutOfBoundsException("Lista vacía");
        }
        
        NodoLong actual = inicio;
        int indice = 0;
        
        while (actual != null && indice < posicion) {
            actual = actual.enlace;
            indice++;
        }
        
        if (actual == null) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        
        return actual.dato;
    }
    
    public int contarNodos() {
        int contador = 0;
        NodoLong actual = inicio;
        while (actual != null) {
            contador++;
            actual = actual.enlace;
        }
        return contador;
    }
}