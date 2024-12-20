package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;


public class FenAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenAccueil frame = new FenAccueil();
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
	public FenAccueil() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelMenuNorth = new JPanel();
		contentPane.add(panelMenuNorth, BorderLayout.NORTH);
		panelMenuNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel panelMenu = new JPanel();
		panelMenuNorth.add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new BorderLayout(0, 0));
		
		JPanel panelMenuGauche = new JPanel();
		panelMenuGauche.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelMenu.add(panelMenuGauche, BorderLayout.WEST);
		
		JButton btnMesBiens = new JButton("Biens");
		btnMesBiens.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesBiens);
		
		JButton btnMesLoactions = new JButton("Locations");
		btnMesLoactions.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesLoactions);
		
		JButton btnMesFactures = new JButton("Factures");
		btnMesFactures.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesFactures);
		
		JButton btnMesAssurances = new JButton("Assurances");
		btnMesAssurances.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesAssurances);
		
		JButton btnMesDocuments = new JButton("Documents");
		btnMesDocuments.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesDocuments);
		
		JButton btnMesArchives = new JButton("Archives");
		btnMesArchives.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesArchives);
		
		JPanel panelMenuCenter = new JPanel();
		panelMenu.add(panelMenuCenter, BorderLayout.CENTER);
		
		JPanel panelMenuDroite = new JPanel();
		panelMenu.add(panelMenuDroite, BorderLayout.EAST);
		
		JButton btnSeDeconnecter = new JButton("Se Déconnecter");
		btnSeDeconnecter.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		panelMenuDroite.add(btnSeDeconnecter);
		
		JButton btnAccueil = new JButton("New button");
		panelMenuDroite.add(btnAccueil);
		
		JPanel panelMenuCentre = new JPanel();
		contentPane.add(panelMenuCentre, BorderLayout.CENTER);
		panelMenuCentre.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panelNorth = new JPanel();
		panelMenuCentre.add(panelNorth);
		panelNorth.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panelNorthGauche = new JPanel();
		panelNorth.add(panelNorthGauche);
		
		JPanel panelNorthCenter = new JPanel();
		panelNorth.add(panelNorthCenter);
		panelNorthCenter.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Accueil");
		lblNewLabel.setForeground(new Color(31, 153, 88));
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorthCenter.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panelNorthDroite = new JPanel();
		panelNorth.add(panelNorthDroite);
		
		JPanel panelCenter = new JPanel();
		panelMenuCentre.add(panelCenter);
		panelCenter.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panel = new JPanel();
		panelCenter.add(panel);
		
		JPanel panel_1 = new JPanel();
		panelCenter.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panelCenter.add(panel_2);
		
		JPanel panelSouth = new JPanel();
		panelMenuCentre.add(panelSouth);
	}

}
