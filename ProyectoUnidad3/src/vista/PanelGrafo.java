package vista;

import modelo.Arista;
import modelo.Vertice;
import modelo.Grafo;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelGrafo extends JPanel {
    private Grafo grafo;
    private List<Vertice> caminoMinimo;

    public PanelGrafo(Grafo grafo) {
        this.grafo = grafo;
        this.caminoMinimo = null;
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.white);
    }

    public void setCaminoMinimo(List<Vertice> camino) {
        this.caminoMinimo = camino;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja aristas
        for (Arista a : grafo.getAristas()) {
            g.setColor(Color.gray);
            int x1 = a.getOrigen().getX(), y1 = a.getOrigen().getY();
            int x2 = a.getDestino().getX(), y2 = a.getDestino().getY();
            g.drawLine(x1, y1, x2, y2);
            // Dibuja peso
            int mx = (x1 + x2) / 2, my = (y1 + y2) / 2;
            g.setColor(Color.blue);
            g.drawString(String.valueOf(a.getPeso()), mx, my);
        }
        // Dibuja camino mínimo
        if (caminoMinimo != null && caminoMinimo.size() > 1) {
            g.setColor(Color.red);
            for (int i = 0; i < caminoMinimo.size() - 1; i++) {
                Vertice v1 = caminoMinimo.get(i), v2 = caminoMinimo.get(i+1);
                g.drawLine(v1.getX(), v1.getY(), v2.getX(), v2.getY());
            }
        }
        // Dibuja vértices
        for (Vertice v : grafo.getVertices()) {
            g.setColor(Color.orange);
            g.fillOval(v.getX()-15, v.getY()-15, 30, 30);
            g.setColor(Color.black);
            g.drawOval(v.getX()-15, v.getY()-15, 30, 30);
            g.drawString(v.getNombre(), v.getX()-10, v.getY()+5);
        }
    }
}