package Vue.Insertion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Vue.RoundedButton;
import java.awt.Component;

public class FenAjoutAssurance extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNumeroPolice;
	private JTextField textFieldMontant;
	private JTextField textFieldDateEcheance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenAjoutFacture frame = new FenAjoutFacture();
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
	public FenAjoutAssurance() {
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setBounds(355, 125, 96, 13);
		getContentPane().add(lblMontant);
		
		JLabel lblDateEcheance = new JLabel("Date échéance");
		lblDateEcheance.setBounds(355, 178, 85, 13);
		getContentPane().add(lblDateEcheance);
		
		JLabel lblNumeroPolice = new JLabel("Numero police");
		lblNumeroPolice.setBounds(355, 64, 96, 13);
		getContentPane().add(lblNumeroPolice);
		
		RoundedButton btnAnnulerAssurance = new RoundedButton("Annuler", 20);
		btnAnnulerAssurance.setBounds(355, 339, 85, 21);
		getContentPane().add(btnAnnulerAssurance);
		
		RoundedButton btnAjouterAssurance = new RoundedButton("Ajouter", 20);
		btnAjouterAssurance.setBounds(475, 339, 85, 21);
		getContentPane().add(btnAjouterAssurance);
		
		JLabel lblAjoutAssurance = new JLabel("Ajout Assurance");
		lblAjoutAssurance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutAssurance.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutAssurance.setBounds(388, 27, 131, 21);
		getContentPane().add(lblAjoutAssurance);
		
		JSeparator separatorAjoutAssurance = new JSeparator();
		separatorAjoutAssurance.setForeground(new Color(255, 255, 255));
		separatorAjoutAssurance.setBackground(new Color(31, 151, 83));
		separatorAjoutAssurance.setBounds(362, 45, 188, 2);
		getContentPane().add(separatorAjoutAssurance);
		
		textFieldNumeroPolice = new JTextField();
		textFieldNumeroPolice.setBounds(354, 87, 207, 19);
		getContentPane().add(textFieldNumeroPolice);
		textFieldNumeroPolice.setColumns(10);
		
		textFieldMontant = new JTextField();
		textFieldMontant.setBounds(354, 142, 207, 19);
		getContentPane().add(textFieldMontant);
		textFieldMontant.setColumns(10);
		
		textFieldDateEcheance = new JTextField();
		textFieldDateEcheance.setBounds(354, 198, 207, 19);
		getContentPane().add(textFieldDateEcheance);
		textFieldDateEcheance.setColumns(10);
		
		JLabel lblTrouverEntreprise = new JLabel("Trouver Entreprise");
		lblTrouverEntreprise.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverEntreprise.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverEntreprise.setBounds(672, 27, 131, 21);
		getContentPane().add(lblTrouverEntreprise);
		
		JSeparator separatorTrouverEntreprise = new JSeparator();
		separatorTrouverEntreprise.setForeground(Color.WHITE);
		separatorTrouverEntreprise.setBackground(new Color(31, 151, 83));
		separatorTrouverEntreprise.setBounds(645, 45, 188, 2);
		getContentPane().add(separatorTrouverEntreprise);
	
		JTable tabMesEntreprise = new JTable();
		tabMesEntreprise.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "SIRET", "nom" }
        ));
        JScrollPane scrollPaneEntreprise = new JScrollPane(tabMesEntreprise);
        scrollPaneEntreprise.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneEntreprise.setBounds(612, 87, 268, 134);        
		getContentPane().add(scrollPaneEntreprise);
		
		RoundedButton btnChargerEntreprise = new RoundedButton("Annuler", 20);
		btnChargerEntreprise.setText("Charger");
		btnChargerEntreprise.setBounds(645, 231, 85, 21);
		getContentPane().add(btnChargerEntreprise);
		
		RoundedButton btnInsererEntreprise = new RoundedButton("Annuler", 20);
		btnInsererEntreprise.setText("Inserer");
		btnInsererEntreprise.setBounds(769, 231, 85, 21);
		getContentPane().add(btnInsererEntreprise);
		
		RoundedButton btnChargerLogement = new RoundedButton("Annuler", 20);
		btnChargerLogement.setText("Charger");
		btnChargerLogement.setBounds(42, 231, 85, 21);
		getContentPane().add(btnChargerLogement);
		
		JLabel lblTrouverLogement = new JLabel("Trouver Logement");
		lblTrouverLogement.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverLogement.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverLogement.setBounds(84, 27, 131, 21);
		getContentPane().add(lblTrouverLogement);
		
		JSeparator separatorTrouverLogement = new JSeparator();
		separatorTrouverLogement.setForeground(Color.WHITE);
		separatorTrouverLogement.setBackground(new Color(31, 151, 83));
		separatorTrouverLogement.setBounds(56, 45, 188, 2);
		getContentPane().add(separatorTrouverLogement);
		
		RoundedButton btnInsererLogement = new RoundedButton("Annuler", 20);
		btnInsererLogement.setText("Inserer");
		btnInsererLogement.setBounds(151, 231, 85, 21);
		getContentPane().add(btnInsererLogement);
		
		JTable tabMesLogements = new JTable();
		tabMesLogements.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "Id Immeuble", "adresse" }
        ));
		
		JScrollPane scrollPaneLogement = new JScrollPane(tabMesLogements);
		scrollPaneLogement.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneLogement.setBounds(10, 87, 306, 134);
		getContentPane().add(scrollPaneLogement);
	}
}
