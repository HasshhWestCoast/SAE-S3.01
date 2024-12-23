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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelMenuNorth = new JPanel();
		contentPane.add(panelMenuNorth, BorderLayout.NORTH);
		panelMenuNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(33, 153, 88));
		panelMenuNorth.add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new BorderLayout(0, 0));
		
		JPanel panelMenuGauche = new JPanel();
		panelMenuGauche.setBackground(new Color(33, 153, 88));
		panelMenuGauche.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelMenu.add(panelMenuGauche, BorderLayout.WEST);
		
		RoundedButton btnMesBiens = new RoundedButton("Biens", 20);
		btnMesBiens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMesBiens.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesBiens);
		
		RoundedButton btnMesLoactions = new RoundedButton("Locations", 20);
		btnMesLoactions.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesLoactions);
		
		RoundedButton btnMesFactures = new RoundedButton("Factures", 20);
		btnMesFactures.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesFactures);
		
		RoundedButton btnMesAssurances = new RoundedButton("Assurances", 20);
		btnMesAssurances.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesAssurances);
		
		RoundedButton btnMesDocuments = new RoundedButton("Documents", 20);
		btnMesDocuments.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesDocuments);
		
		RoundedButton btnMesArchives = new RoundedButton("Archives", 20);
		btnMesArchives.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesArchives);
		
		JPanel panelMenuCenter = new JPanel();
		panelMenuCenter.setBackground(new Color(33, 153, 88));
		panelMenu.add(panelMenuCenter, BorderLayout.CENTER);
		
		JPanel panelMenuDroite = new JPanel();
		panelMenuDroite.setBackground(new Color(33, 153, 88));
		panelMenu.add(panelMenuDroite, BorderLayout.EAST);
		
		RoundedButton btnSeDeconnecter = new RoundedButton("Se Déconnecter", 20);
		btnSeDeconnecter.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		panelMenuDroite.add(btnSeDeconnecter);
		
		JPanel panelMenuCentre = new JPanel();
		panelMenuCentre.setBackground(new Color(255, 255, 255));
		contentPane.add(panelMenuCentre, BorderLayout.CENTER);
		panelMenuCentre.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panelNorth = new JPanel();
		panelMenuCentre.add(panelNorth);
		panelNorth.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panelNorthGauche = new JPanel();
		panelNorthGauche.setBackground(new Color(255, 255, 255));
		panelNorth.add(panelNorthGauche);
		
		JPanel panelNorthCenter = new JPanel();
		panelNorthCenter.setBackground(new Color(255, 255, 255));
		panelNorth.add(panelNorthCenter);
		panelNorthCenter.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Accueil");
		lblNewLabel.setForeground(new Color(31, 153, 88));
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorthCenter.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panelNorthDroite = new JPanel();
		panelNorthDroite.setBackground(new Color(255, 255, 255));
		panelNorth.add(panelNorthDroite);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(new Color(255, 255, 255));
		panelMenuCentre.add(panelCenter);
		panelCenter.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panelCenter.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panelCenter.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panelCenter.add(panel_2);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(new Color(255, 255, 255));
		panelMenuCentre.add(panelSouth);
	}

}
