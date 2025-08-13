package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

public class InterfazTarea extends JFrame {
	


	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	
	//atributos public
	public JButton btnGuardar;
    public JButton btnEditar;
    public JButton btnEliminar;
    public JButton btnPilaTareas;
    public JTable tbTablaTareas;
	public JTextField txtTitulo;
	public JTextField txtFecha;
	public JTextArea taDescripcion;
	public JComboBox cbCategoria;
	public JComboBox cbInsertar;
	public JButton btnMezclar;
	public JButton btnAnalisis;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazTarea frame = new InterfazTarea();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
public InterfazTarea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1260, 716);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Crear la tabla con el modelo
        tbTablaTareas = new JTable(new DefaultTableModel(
        	new Object[][] {        	},
        	new String[] {"ID","Titulo", "Categor\u00EDa", "Fecha"}));
        
        // Personalizar el aspecto de la tabla - Hacerla transparente
        tbTablaTareas.setFont(new Font("SimSun", Font.PLAIN, 15));
        tbTablaTareas.setRowHeight(25);
        tbTablaTareas.setForeground(Color.BLACK); // Color del texto
        tbTablaTareas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tbTablaTareas.getTableHeader().setBackground(new Color(50, 50, 50));
        tbTablaTareas.getTableHeader().setForeground(Color.WHITE);
        
        // Agregar la tabla a un JScrollPane para permitir desplazamiento
        JScrollPane scrollPane = new JScrollPane(tbTablaTareas);
        scrollPane.setEnabled(false);
        scrollPane.setBounds(625, 128, 572, 322);
        scrollPane.getViewport().setOpaque(false); // Hacer el viewport transparente
        
        cbCategoria = new JComboBox();
        cbCategoria.setModel(new DefaultComboBoxModel(new String[] {"Tareas", "Recordatorios"}));
        cbCategoria.setOpaque(false);
        cbCategoria.setFont(new Font("SimSun", Font.PLAIN, 15));
        cbCategoria.setBackground(new Color(255, 255, 255, 100));
        cbCategoria.setBounds(138, 170, 357, 27);
        contentPane.add(cbCategoria);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Quitar el borde para más transparencia
        contentPane.add(scrollPane);
        
        // Crear los TextFields transparentes
        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("SimSun", Font.PLAIN, 15));
        txtTitulo.setBounds(138, 118, 357, 26);
        txtTitulo.setOpaque(false); // Hacer el campo de texto transparente
        txtTitulo.setBackground(new Color(0, 0, 0, 0)); // Fondo completamente transparente
        txtTitulo.setForeground(new Color(255, 255, 255)); // Color del texto
        txtTitulo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Solo borde inferior
        contentPane.add(txtTitulo);
        txtTitulo.setColumns(10);
        
        // TextArea transparente
        taDescripcion = new JTextArea();
        taDescripcion.setFont(new Font("SimSun", Font.PLAIN, 15));
        taDescripcion.setBounds(51, 342, 436, 194);
        taDescripcion.setOpaque(false); // Hacer el área de texto transparente
        taDescripcion.setBackground(new Color(0, 0, 0, 0)); // Fondo completamente transparente
        taDescripcion.setForeground(new Color(255, 255, 255)); // Color del texto
        taDescripcion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Borde delgado para ver los límites
        contentPane.add(taDescripcion);
        
        txtFecha = new JTextField();
        txtFecha.setFont(new Font("SimSun", Font.PLAIN, 15));
        txtFecha.setColumns(10);
        txtFecha.setBounds(138, 229, 357, 26);
        txtFecha.setOpaque(false); // Hacer el campo de texto transparente
        txtFecha.setBackground(new Color(0, 0, 0, 0)); // Fondo completamente transparente
        txtFecha.setForeground(new Color(255, 255, 255)); // Color del texto
        txtFecha.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Solo borde inferior
        contentPane.add(txtFecha);
        
        JLabel lblCategoria = new JLabel("Categoria: ");
        lblCategoria.setForeground(new Color(255, 255, 255));
        lblCategoria.setFont(new Font("SimSun", Font.BOLD, 15));
        lblCategoria.setBounds(41, 170, 93, 27);
        contentPane.add(lblCategoria);
        
        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setForeground(new Color(255, 255, 255));
        lblDescripcion.setFont(new Font("SimSun", Font.BOLD, 15));
        lblDescripcion.setBounds(41, 294, 223, 27);
        contentPane.add(lblDescripcion);
        
        JLabel lblTitulo_1 = new JLabel("Insertar: ");
        lblTitulo_1.setForeground(new Color(255, 255, 255));
        lblTitulo_1.setFont(new Font("SimSun", Font.BOLD, 15));
        lblTitulo_1.setBounds(657, 509, 90, 27);
        contentPane.add(lblTitulo_1);
        
        // ComboBox transparente
        cbInsertar = new JComboBox();
        cbInsertar .setModel(new DefaultComboBoxModel(new String[] {"Inicio", "Final","Posición"}));
        cbInsertar.setFont(new Font("SimSun", Font.PLAIN, 15));
        cbInsertar.setBounds(747, 509, 147, 27);
        cbInsertar.setOpaque(false); // Hacer el combobox transparente
        cbInsertar.setBackground(new Color(255, 255, 255, 100)); // Fondo semi-transparente
        contentPane.add(cbInsertar);
        
        JLabel lblTitulo = new JLabel("Titulo: ");
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setFont(new Font("SimSun", Font.BOLD, 15));
        lblTitulo.setBounds(48, 118, 67, 27);
        contentPane.add(lblTitulo);
        
        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setForeground(new Color(255, 255, 255));
        lblFecha.setFont(new Font("SimSun", Font.BOLD, 15));
        lblFecha.setBounds(51, 229, 58, 27);
        contentPane.add(lblFecha);
        
        // Botones transparentes
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(362, 582, 181, 72);
        btnGuardar.setOpaque(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setForeground(new Color(255, 255, 255));
        btnGuardar.setFont(new Font("SimSun", Font.BOLD, 24));
        contentPane.add(btnGuardar);
        
        btnEditar = new JButton("Editar");
        btnEditar.setBounds(88, 582, 181, 72);
        btnEditar.setOpaque(false);
        btnEditar.setContentAreaFilled(false);
        btnEditar.setBorderPainted(false);
        btnEditar.setFocusPainted(false);
        btnEditar.setForeground(new Color(255, 255, 255));
        btnEditar.setFont(new Font("SimSun", Font.BOLD, 24));
        contentPane.add(btnEditar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(625, 582, 181, 72);
        btnEliminar.setOpaque(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setForeground(new Color(255, 255, 255));
        btnEliminar.setFont(new Font("SimSun", Font.BOLD, 24));
        contentPane.add(btnEliminar);
        
        btnPilaTareas = new JButton("Pila de tareas");
        btnPilaTareas.setOpaque(false);
        btnPilaTareas.setForeground(new Color(255, 255, 255));
        btnPilaTareas.setFont(new Font("SimSun", Font.BOLD, 24));
        btnPilaTareas.setFocusPainted(false);
        btnPilaTareas.setContentAreaFilled(false);
        btnPilaTareas.setBorderPainted(false);
        btnPilaTareas.setBounds(895, 582, 223, 72);
        contentPane.add(btnPilaTareas);
        
        btnMezclar = new JButton("Mezclar");
        btnMezclar.setOpaque(false);
        btnMezclar.setForeground(new Color(255, 255, 255));
        btnMezclar.setFont(new Font("SimSun", Font.BOLD, 20));
        btnMezclar.setFocusPainted(false);
        btnMezclar.setContentAreaFilled(false);
        btnMezclar.setBorderPainted(false);
        btnMezclar.setBounds(1011, 494, 132, 56);
        contentPane.add(btnMezclar);
        
        btnAnalisis = new JButton("");
        btnAnalisis.setOpaque(false);
        btnAnalisis.setForeground(Color.WHITE);
        btnAnalisis.setFont(new Font("SimSun", Font.BOLD, 24));
        btnAnalisis.setFocusPainted(false);
        btnAnalisis.setContentAreaFilled(false);
        btnAnalisis.setBorderPainted(false);
        btnAnalisis.setIcon(new ImageIcon(getClass().getResource("analysisImage.png")));
        btnAnalisis.setBounds(1136, 10, 100, 72);
        contentPane.add(btnAnalisis);
        
        JLabel lblFondo_1 = new JLabel("");
        lblFondo_1.setIcon(new ImageIcon(getClass().getResource("TextoDelParrafo.jpg")));
        lblFondo_1.setBackground(new Color(0, 0, 64));
        lblFondo_1.setBounds(0, 0, 1244, 677);
        contentPane.add(lblFondo_1);
	}
}
