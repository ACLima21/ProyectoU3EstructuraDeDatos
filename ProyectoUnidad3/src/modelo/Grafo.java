package modelo;

import java.util.*;

public class Grafo {
    private List<Vertice> vertices;
    private List<Arista> aristas;

    public Grafo() {
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public void agregarVertice(Vertice v) {
        vertices.add(v);
    }

    public void agregarArista(Arista a) {
        aristas.add(a);
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    // Dijkstra: devuelve lista del camino mínimo
    public List<Vertice> caminoMinimo(Vertice origen, Vertice destino) {
        Map<Vertice, Integer> dist = new HashMap<>();
        Map<Vertice, Vertice> prev = new HashMap<>();
        Set<Vertice> visitados = new HashSet<>();
        for (Vertice v : vertices) {
            dist.put(v, Integer.MAX_VALUE);
            prev.put(v, null);
        }
        dist.put(origen, 0);

        while (visitados.size() < vertices.size()) {
            Vertice actual = null;
            int minDist = Integer.MAX_VALUE;
            for (Vertice v : vertices) {
                if (!visitados.contains(v) && dist.get(v) < minDist) {
                    minDist = dist.get(v);
                    actual = v;
                }
            }
            if (actual == null) break;
            visitados.add(actual);
            for (Arista a : aristas) {
                if (a.getOrigen() == actual) {
                    Vertice vecino = a.getDestino();
                    int nuevaDist = dist.get(actual) + a.getPeso();
                    if (nuevaDist < dist.get(vecino)) {
                        dist.put(vecino, nuevaDist);
                        prev.put(vecino, actual);
                    }
                }
            }
        }
        // Reconstruir camino
        List<Vertice> camino = new ArrayList<>();
        for (Vertice at = destino; at != null; at = prev.get(at)) {
            camino.add(0, at);
        }
        if (camino.get(0) != origen) return new ArrayList<>(); // No hay camino
        return camino;
    }
}
