package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class NeuvaVista extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	
	//atributos public
	public JButton btnCheck;
    public JButton btnCerrar;
    public JLabel lblDescripcion;
    public JLabel lblTitulo;
    public JLabel lblFondo;
    public JLabel lblFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NeuvaVista frame = new NeuvaVista();
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
	public NeuvaVista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 789, 529);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Botones transparentes
        btnCheck = new JButton("Check");
        btnCheck.setBounds(484, 412, 181, 72);
        btnCheck.setOpaque(false);
        btnCheck.setContentAreaFilled(false);
        btnCheck.setBorderPainted(false);
        btnCheck.setFocusPainted(false);
        btnCheck.setForeground(new Color(255, 255, 255));
        btnCheck.setFont(new Font("SimSun", Font.BOLD, 24));
        contentPane.add(btnCheck);
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(111, 412, 181, 72);
        btnCerrar.setOpaque(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setForeground(new Color(255, 255, 255));
        btnCerrar.setFont(new Font("SimSun", Font.BOLD, 24));
        contentPane.add(btnCerrar);
        
        lblFecha = new JLabel("");
        lblFecha.setForeground(new Color(255, 255, 255));
        lblFecha.setFont(new Font("SimSun", Font.BOLD, 24));
        lblFecha.setBounds(35, 307, 709, 27);
        contentPane.add(lblFecha);
        
        lblDescripcion = new JLabel("");
        lblDescripcion.setForeground(new Color(255, 255, 255));
        lblDescripcion.setFont(new Font("SimSun", Font.BOLD, 17));
        lblDescripcion.setBounds(35, 91, 661, 177);
        contentPane.add(lblDescripcion);
        
        lblTitulo = new JLabel("");
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setFont(new Font("SimSun", Font.BOLD, 30));
        lblTitulo.setBounds(35, 21, 709, 27);
        contentPane.add(lblTitulo);
        
        lblFondo = new JLabel("");
        lblFondo.setIcon(new ImageIcon("C:\\Users\\Ismael Lima\\Documents\\GitHub\\ESPE\\TERCER SEMESTRE\\EstructuraDeDatosProyectoPrimerSemestre\\ProyectoUnidad1\\src\\Vista\\imgVista2.jpg"));
        lblFondo.setFont(new Font("SimSun", Font.PLAIN, 21));
        lblFondo.setBackground(new Color(0, 0, 64));
        lblFondo.setBounds(0, 0, 778, 497);
        contentPane.add(lblFondo);
	}
}
