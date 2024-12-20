package Vue;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class FenLocations extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenLocations frame = new FenLocations();
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
	public FenLocations() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 935, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitre = new JPanel();
		contentPane.add(panelTitre, BorderLayout.NORTH);
		panelTitre.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLocations = new JLabel("Mes Locations");
		lblLocations.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocations.setForeground(new Color(31, 153, 88));
		lblLocations.setFont(new Font("Sylfaen", Font.ITALIC, 28));
		panelTitre.add(lblLocations, BorderLayout.CENTER);
		
		JSeparator separator = new JSeparator();
		panelTitre.add(separator, BorderLayout.SOUTH);
		
		JPanel panelCentre = new JPanel(new BorderLayout());
		panelCentre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new BorderLayout(0, 0));
       
		JTable tabMesLocations = new JTable();
        tabMesLocations.setModel(new DefaultTableModel(
            new Object[][] { { null, null, null, null, null } },
            new String[] { "Locataire", "Bien", "Type", "Date début", "Dernière régularisation" }
        ));
        JScrollPane scrollPane = new JScrollPane(tabMesLocations);
        scrollPane.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPane, BorderLayout.CENTER);
        
        JPanel panelSud = new JPanel();
        contentPane.add(panelSud, BorderLayout.SOUTH);
        panelSud.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panelSud.add(panel);
        
        JButton btnICharger = new JButton("Charger");
        panel.add(btnICharger);
        
        JButton btnInserer = new JButton("Inserer");
        panel.add(btnInserer);
        
        JButton btnMiseAJour = new JButton("Mise à jour");
        panel.add(btnMiseAJour);
        
        JButton btnSupprimer = new JButton("Supprimer");
        panel.add(btnSupprimer);
        
        JSeparator separator_1 = new JSeparator();
        panelSud.add(separator_1, BorderLayout.NORTH);
	
	}

}
