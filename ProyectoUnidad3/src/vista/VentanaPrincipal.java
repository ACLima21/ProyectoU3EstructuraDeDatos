package vista;

import modelo.Grafo;
import modelo.Vertice;
import modelo.Arista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private Grafo grafo;
    private PanelGrafo panelGrafo;
    private JComboBox<String> cbOrigen, cbDestino;
    private JTextField tfNombre, tfX, tfY, tfPeso;
    private JButton btnAgregarVertice, btnAgregarArista, btnRutaMinima;

    public VentanaPrincipal() {
        grafo = new Grafo();
        panelGrafo = new PanelGrafo(grafo);

        setTitle("Simulador de Rutas de Transporte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());

        // Panel de control
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new GridLayout(0, 2));

        tfNombre = new JTextField();
        tfX = new JTextField();
        tfY = new JTextField();
        btnAgregarVertice = new JButton("Agregar Ciudad");

        panelControl.add(new JLabel("Ciudad:"));
        panelControl.add(tfNombre);
        panelControl.add(new JLabel("X:"));
        panelControl.add(tfX);
        panelControl.add(new JLabel("Y:"));
        panelControl.add(tfY);
        panelControl.add(btnAgregarVertice);
        panelControl.add(new JLabel(""));

        cbOrigen = new JComboBox<>();
        cbDestino = new JComboBox<>();
        tfPeso = new JTextField();
        btnAgregarArista = new JButton("Agregar Camino");

        panelControl.add(new JLabel("Origen:"));
        panelControl.add(cbOrigen);
        panelControl.add(new JLabel("Destino:"));
        panelControl.add(cbDestino);
        panelControl.add(new JLabel("Peso:"));
        panelControl.add(tfPeso);
        panelControl.add(btnAgregarArista);
        panelControl.add(new JLabel(""));

        btnRutaMinima = new JButton("Ruta Mínima");
        panelControl.add(btnRutaMinima);

        add(panelGrafo, BorderLayout.CENTER);
        add(panelControl, BorderLayout.EAST);

        // Listeners
        btnAgregarVertice.addActionListener(e -> agregarVertice());
        btnAgregarArista.addActionListener(e -> agregarArista());
        btnRutaMinima.addActionListener(e -> calcularRutaMinima());
    }

    private void agregarVertice() {
        String nombre = tfNombre.getText().trim();
        int x, y;
        try {
            x = Integer.parseInt(tfX.getText().trim());
            y = Integer.parseInt(tfY.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Coordenadas inválidas");
            return;
        }
        Vertice v = new Vertice(nombre, x, y);
        grafo.agregarVertice(v);
        cbOrigen.addItem(nombre);
        cbDestino.addItem(nombre);
        panelGrafo.repaint();
    }

    private void agregarArista() {
        String origen = (String) cbOrigen.getSelectedItem();
        String destino = (String) cbDestino.getSelectedItem();
        int peso;
        try {
            peso = Integer.parseInt(tfPeso.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Peso inválido");
            return;
        }
        Vertice v1 = grafo.getVertices().stream().filter(v -> v.getNombre().equals(origen)).findFirst().orElse(null);
        Vertice v2 = grafo.getVertices().stream().filter(v -> v.getNombre().equals(destino)).findFirst().orElse(null);
        if (v1 == null || v2 == null || v1 == v2) {
            JOptionPane.showMessageDialog(this, "Origen y destino inválidos");
            return;
        }
        grafo.agregarArista(new Arista(v1, v2, peso));
        panelGrafo.repaint();
    }

    private void calcularRutaMinima() {
        String origen = (String) cbOrigen.getSelectedItem();
        String destino = (String) cbDestino.getSelectedItem();
        Vertice v1 = grafo.getVertices().stream().filter(v -> v.getNombre().equals(origen)).findFirst().orElse(null);
        Vertice v2 = grafo.getVertices().stream().filter(v -> v.getNombre().equals(destino)).findFirst().orElse(null);
        if (v1 == null || v2 == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar origen y destino");
            return;
        }
        List<Vertice> camino = grafo.caminoMinimo(v1, v2);
        if (camino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No existe camino");
        }
        panelGrafo.setCaminoMinimo(camino);
    }
}