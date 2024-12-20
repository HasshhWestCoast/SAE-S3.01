package vue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class FenetreLocations extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private GestionFenetreLocations gestionnaire;
	private JTable table;
	private JButton btnCharger;
	private JButton btnInserer;
	private JButton btnAnnuler;

	/**
	 * Create the frame.
	 */
	public FenetreLocations() {
		table = new JTable();
		setBorder(new LineBorder(new Color(3, 102, 235), 3));
		setFrameIcon(new ImageIcon(FenetreLocations.class.getResource("/data/key-set.png")));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Mes locations");
		setBounds(100, 100, 700, 470);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 668, 239);
		getContentPane().add(scrollPane);
		table.setBackground(new Color(244, 254, 255));
		table.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Locataire", "Bien", "Logement", "Date d\u00E9but", "Provision", "Date fin"
			}
		));
		scrollPane.setViewportView(table);
		this.gestionnaire = new GestionFenetreLocations(this);
		
		btnCharger = new JButton("Charger");
		btnCharger.addActionListener(this.gestionnaire);
		btnCharger.setBackground(Color.WHITE);
		btnCharger.setBounds(36, 355, 85, 21);
		getContentPane().add(btnCharger);
		
		JButton btnMiseAJour = new JButton("Mise à jour");
		btnMiseAJour.setToolTipText("Bouton indisponible !");
		btnMiseAJour.setEnabled(false);
		btnMiseAJour.addActionListener(this.gestionnaire);
		btnMiseAJour.setBackground(Color.WHITE);
		btnMiseAJour.setBounds(288, 355, 113, 21);
		getContentPane().add(btnMiseAJour);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setToolTipText("Bouton indisponible !");
		btnSupprimer.setEnabled(false);
		btnSupprimer.addActionListener(this.gestionnaire);
		btnSupprimer.setBackground(Color.WHITE);
		btnSupprimer.setBounds(441, 355, 85, 21);
		getContentPane().add(btnSupprimer);
		
		btnInserer = new JButton("Insérer");
		btnInserer.addActionListener(this.gestionnaire);
		btnInserer.setBackground(Color.WHITE);
		btnInserer.setBounds(162, 355, 85, 21);
		getContentPane().add(btnInserer);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.BLACK);
		btnAnnuler.addActionListener(this.gestionnaire);
		btnAnnuler.setBackground(Color.WHITE);
		btnAnnuler.setBounds(567, 355, 85, 21);
		getContentPane().add(btnAnnuler);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setToolTipText("Bouton indisponible !");
		btnRechercher.setEnabled(false);
		btnRechercher.addActionListener(this.gestionnaire);
		btnRechercher.setBackground(Color.WHITE);
		btnRechercher.setIcon(new ImageIcon(FenetreLocations.class.getResource("/data/search.png")));
		btnRechercher.setBounds(275, 399, 139, 21);
		getContentPane().add(btnRechercher);
		
		JLabel lblNewLabel = new JLabel("Mes locations");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 668, 40);
		getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 128));
		separator.setBounds(229, 52, 234, 10);
		getContentPane().add(separator);

	}
	
	public JTable getTable() {
		return table;
	}
}