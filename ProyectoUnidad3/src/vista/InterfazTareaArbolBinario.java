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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class InterfazTareaArbolBinario extends JFrame {
	


	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	
	//atributos public
	public JButton btnGuardar;
    public JButton btnBuscar;
    public JButton btnEliminar;
    public JButton btnRecorrer;
    public JButton btnAnalisis;
	public JTextField txtTitulo;
	public JTextField txtFecha;
	public JTextArea taDescripcion;
	public JComboBox cbCategoria;
	public JLabel lblRecorrido;
	public JTextPane tpEstructuraArbol;
	private JLabel lblTituloPrincipal;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazTareaArbolBinario frame = new InterfazTareaArbolBinario();
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
	
public InterfazTareaArbolBinario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1260, 716);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        cbCategoria = new JComboBox();
        cbCategoria.setModel(new DefaultComboBoxModel(new String[] {"Tareas", "Recordatorios"}));
        cbCategoria.setOpaque(false);
        cbCategoria.setFont(new Font("SimSun", Font.PLAIN, 15));
        cbCategoria.setBackground(new Color(255, 255, 255, 100));
        cbCategoria.setBounds(138, 170, 357, 27);
        contentPane.add(cbCategoria);
        
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
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(88, 582, 181, 72);
        btnBuscar.setOpaque(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setForeground(new Color(255, 255, 255));
        btnBuscar.setFont(new Font("SimSun", Font.BOLD, 24));
        contentPane.add(btnBuscar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(625, 582, 181, 72);
        btnEliminar.setOpaque(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setForeground(new Color(255, 255, 255));
        btnEliminar.setFont(new Font("SimSun", Font.BOLD, 24));
        contentPane.add(btnEliminar);
        
        btnRecorrer = new JButton("Recorrer");
        btnRecorrer.setOpaque(false);
        btnRecorrer.setForeground(new Color(255, 255, 255));
        btnRecorrer.setFont(new Font("SimSun", Font.BOLD, 24));
        btnRecorrer.setFocusPainted(false);
        btnRecorrer.setContentAreaFilled(false);
        btnRecorrer.setBorderPainted(false);
        btnRecorrer.setBounds(895, 582, 223, 72);
        contentPane.add(btnRecorrer);
        
        tpEstructuraArbol = new JTextPane();
        tpEstructuraArbol.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tpEstructuraArbol);
        scrollPane.setBounds(625, 142, 572, 291);
        contentPane.add(scrollPane);

        lblTituloPrincipal = new JLabel("ToDo-List con Arboles");
        lblTituloPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloPrincipal.setForeground(Color.WHITE);
        lblTituloPrincipal.setFont(new Font("SimSun", Font.BOLD, 43));
        lblTituloPrincipal.setBounds(10, 24, 1226, 50);
        contentPane.add(lblTituloPrincipal);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(646, 501, 532, 43);
        contentPane.add(scrollPane_1);
        
        lblRecorrido = new JLabel("");
        lblRecorrido.setHorizontalAlignment(SwingConstants.CENTER);
        lblRecorrido.setFont(new Font("SimSun", Font.BOLD, 15));
        lblRecorrido.setBackground(new Color(255, 255, 255));
        lblRecorrido.setOpaque(true);
        scrollPane_1.setViewportView(lblRecorrido);
        
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
