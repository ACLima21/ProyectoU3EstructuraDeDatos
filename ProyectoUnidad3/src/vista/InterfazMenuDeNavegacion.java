package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class InterfazMenuDeNavegacion extends JFrame {

	public JButton btnListaSimple;
	public JButton btnArboles;
    public JButton btnGrafos;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazMenuDeNavegacion frame = new InterfazMenuDeNavegacion();
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
	public InterfazMenuDeNavegacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 718);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("MENÚ");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 38));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(65, 55, 404, 51);
        contentPane.add(lblNewLabel);
        
        btnListaSimple = new JButton("ToDo-List Lista");
        btnListaSimple.setForeground(new Color(255, 255, 255));
        btnListaSimple.setContentAreaFilled(false);
        btnListaSimple.setBorderPainted(false);
        btnListaSimple.setBackground(new Color(255, 255, 255));
        btnListaSimple.setOpaque(false);
        btnListaSimple.setFont(new Font("SimSun", Font.BOLD, 35));
        btnListaSimple.setBounds(65, 300, 404, 51);
        contentPane.add(btnListaSimple);
        
        btnArboles = new JButton("ToDo-List Arbol");
        btnArboles.setOpaque(false);
        btnArboles.setForeground(Color.WHITE);
        btnArboles.setFont(new Font("SimSun", Font.BOLD, 35));
        btnArboles.setContentAreaFilled(false);
        btnArboles.setBorderPainted(false);
        btnArboles.setBackground(Color.WHITE);
        btnArboles.setBounds(65, 423, 404, 51);
        contentPane.add(btnArboles);

        btnGrafos = new JButton("Rutas - Grafos");
        btnGrafos.setOpaque(false);
        btnGrafos.setForeground(Color.WHITE);
        btnGrafos.setFont(new Font("SimSun", Font.BOLD, 35));
        btnGrafos.setContentAreaFilled(false);
        btnGrafos.setBorderPainted(false);
        btnGrafos.setBackground(Color.WHITE);
        btnGrafos.setBounds(65, 548, 404, 51);
        contentPane.add(btnGrafos);
        
        JLabel lblFondo_1 = new JLabel("");
        lblFondo_1.setIcon(new ImageIcon(getClass().getResource("/vista/fondoMenu3.jpg")));
        lblFondo_1.setBackground(new Color(0, 0, 64));
        lblFondo_1.setBounds(0, 0, 531, 681);
        contentPane.add(lblFondo_1);
	}
}