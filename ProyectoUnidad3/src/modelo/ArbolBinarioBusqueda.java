package modelo;

public class ArbolBinarioBusqueda extends ArbolBinario {

    public ArbolBinarioBusqueda() {
        super(); // Llama al constructor de ArbolBinario que inicializa raiz = null
    }

    public ArbolNodo buscar(Object buscado) {// Búsqueda recursiva
    	System.out.println("\nBúsqueda recursiva iniciada\n");
        if (!(buscado instanceof ArbolComparador)) return null;

        ArbolComparador dato = (ArbolComparador) buscado;

        if (raiz == null) {
        	System.out.println("\nNo hay nada en el arbol\n");
            return null;
        } else {
        	System.out.println("\nSe intentó buscar\n");
            return localizar(raizArbol(), dato);
        }
    }

    protected ArbolNodo localizar(ArbolNodo raizSub, ArbolComparador buscado) {// Método auxiliar recursivo para localizar un ArbolNodo
    	System.out.println("Dato buscado: "+buscado.toString());
        if (raizSub == null) {
        	System.out.println("\nRaíz vacía\n");
            return null;
        }else if (buscado.igualQue(raizSub.getDato())) {
        	System.out.println("\nEs igual\n");
            return raizSub;
        }else if (buscado.menorQue(raizSub.getDato())) {
        	System.out.println("\nEs menor\n");
            return localizar(raizSub.getIzdo(), buscado);
        }else {
        	System.out.println("\nEs mayor\n");
            return localizar(raizSub.getDcho(), buscado);
        }
    }

    public ArbolNodo buscarIterativo(Object buscado) {// Búsqueda iterativa
        if (!(buscado instanceof ArbolComparador)) return null;

        ArbolComparador dato = (ArbolComparador) buscado;
        ArbolNodo raizSub = raiz;

        while (raizSub != null) {
            if (dato.igualQue(raizSub.getDato())) {
                return raizSub;
            } else if (dato.menorQue(raizSub.getDato())) {
                raizSub = raizSub.getIzdo();
            } else {
                raizSub = raizSub.getDcho();
            }
        }

        return null;
    }

    public void insertar(Tareas valor) throws Exception {// Método público para insertar un nodo
        raiz = insertar(raiz, valor);
    }

    protected ArbolNodo insertar(ArbolNodo raizSub, Tareas dato) throws Exception {// Método recursivo para insertar en el árbol
        if (raizSub == null) {
            raizSub = new ArbolNodo(dato);
        } else if (dato.menorQue(raizSub.getDato())) {
        	ArbolNodo iz = insertar(raizSub.getIzdo(), dato);
            raizSub.setIzdo(iz);
        } else if (dato.mayorQue(raizSub.getDato())) {
        	ArbolNodo dr = insertar(raizSub.getDcho(), dato);
            raizSub.setDcho(dr);
        } else {
            throw new Exception("Nodo duplicado");
        }
        return raizSub;
    }

    public void eliminar(Object valor) throws Exception {// Método público para eliminar un nodo
        ArbolComparador dato = (ArbolComparador) valor;
        raiz = eliminar(raiz, dato);
    }

    protected ArbolNodo eliminar(ArbolNodo raizSub, ArbolComparador dato) throws Exception {// Método recursivo para eliminar un nodo
        if (raizSub == null) {
            throw new Exception("No encontrado el nodo con la clave");
        } else if (dato.menorQue(raizSub.getDato())) {
            ArbolNodo iz = eliminar(raizSub.getIzdo(), dato);
            raizSub.setIzdo(iz);
        } else if (dato.mayorQue(raizSub.getDato())) {
            ArbolNodo dr = eliminar(raizSub.getDcho(), dato);
            raizSub.setDcho(dr);
        } else { // Nodo encontrado
            ArbolNodo q = raizSub;
            if (q.getIzdo() == null) {
                raizSub = q.getDcho();
            } else if (q.getDcho() == null) {
                raizSub = q.getIzdo();
            } else {
                q = reemplazar(q);
            }
            q = null;
        }
        return raizSub;
    }

    private ArbolNodo reemplazar(ArbolNodo act) {// Método auxiliar para encontrar el reemplazo (mayor de los menores)
        ArbolNodo a = act.getIzdo();
        ArbolNodo p = act;

        while (a.getDcho() != null) {
            p = a;
            a = a.getDcho();
        }

        act.setDato(a.getDato());

        if (p == act) {
            p.setIzdo(a.getIzdo());
        } else {
            p.setDcho(a.getIzdo());
        }

        return a;
    }

}

