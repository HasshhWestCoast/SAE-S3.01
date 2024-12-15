package Vue;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;



public class Fenetre_ModificationAssurance extends JInternalFrame {
	// Champs de saisie
	private JTextField textField_numPolice;
	private JTextField textField_montant;
	private JTextField textField_dateEcheance;
	// Boutons
	private JButton btn_ajouter_entreprise;
	private JButton btn_charger_entreprise;

	// Table pour afficher les données d'entreprise
	private JTable table_entreprise;
	private JScrollPane scrollPane_table_entreprise;

	

	public Fenetre_ModificationAssurance() {
	
		// Configuration de la fenêtre interne
		this.setBounds(100, 100, 762, 541);
		this.getContentPane().setLayout(null);

		// Panneau principal
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 755, 511);
		this.getContentPane().add(panel);
		panel.setLayout(null);

		// Libellé "Assurance"		
		Utils.creerLabel("Modifier l'assurance", 302, 39, 163, 20, 16, panel);

		// Champs de saisie
		this.textField_numPolice = Utils.creerTextField("Numéro de police", 135, 166, 197, 40, panel);
		textField_numPolice.setEditable(false); // cle primaire de la table Assurance non modifiable

		this.textField_montant = Utils.creerTextField("Montant", 135, 229, 197, 40, panel);
		
		this.textField_dateEcheance = Utils.creerTextField("Date échéance (YYYY-MM-DD)", 135, 298, 197, 40, panel);
		textField_dateEcheance.setEditable(false); // cle primaire de la table Echeance non modifiable

		// Bouton "Modifier"
		JButton btnModifier = Utils.creerBouton(panel, "Modifier", 276, 431, 94, 31);
		
		// Bouton "Annuler"
		JButton btnAnnuler = Utils.creerBouton(panel, "Annuler", 428, 431, 94, 31);
	

		// Séparateur
		JSeparator separator_Assurance = new JSeparator();
		separator_Assurance.setForeground(new Color(0, 102, 204));
		separator_Assurance.setBounds(302, 72, 190, 16);
		panel.add(separator_Assurance);

		// Séparateur vertical
		JSeparator separator_vertical = new JSeparator();
		separator_vertical.setOrientation(SwingConstants.VERTICAL);
		separator_vertical.setForeground(new Color(0, 102, 204));
		separator_vertical.setBounds(385, 100, 15, 292);
		panel.add(separator_vertical);

		// Partie ENTREPRISE
		this.btn_ajouter_entreprise = Utils.creerBouton(panel, "Insérer", 518, 328, 94, 31);
	

		this.btn_charger_entreprise = Utils.creerBouton(panel, "Charger", 412, 328, 94, 31);
	

		// Bouton "Modifier" pour entreprise
		JButton btn_modifier_entreprise = Utils.creerBouton(panel, "Modifier ", 624, 329, 94, 31);
	
		// JScrollPane pour la table des entreprises
		this.scrollPane_table_entreprise = new JScrollPane();
		this.scrollPane_table_entreprise.setBorder(new LineBorder(new Color(0, 102, 204), 1, true));
		this.scrollPane_table_entreprise.setBounds(443, 196, 247, 97);
		panel.add(this.scrollPane_table_entreprise);

		// Table pour afficher les données d'entreprise
		this.table_entreprise = new JTable();
		this.table_entreprise.setSelectionBackground(new Color(0, 102, 204));
		this.table_entreprise
				.setModel(new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "SIRET", "Nom" }));
		this.table_entreprise.setBounds(499, 80, 135, 16);
		scrollPane_table_entreprise.setViewportView(this.table_entreprise);
	

		JLabel lbl_Entreprise = new JLabel("Entreprise");
		lbl_Entreprise.setForeground(Color.BLACK);
		lbl_Entreprise.setBackground(new Color(0, 102, 204));
		lbl_Entreprise.setBounds(529, 143, 132, 31);
		panel.add(lbl_Entreprise);

	}

	// Getters pour les champs de saisie
	public JTextField getTextField_numPolice() {
		return this.textField_numPolice;
	}

	public JTextField getTextField_montant() {
		return this.textField_montant;
	}

	public JTextField getTextField_dateEcheance() {
		return textField_dateEcheance;
	}

	public JButton getBtn_ajouter_entreprise() {
		return btn_ajouter_entreprise;
	}

	public JButton getBtn_charger_entreprise() {
		return btn_charger_entreprise;
	}

	public JScrollPane getScrollPane_table_entreprise() {
		return scrollPane_table_entreprise;
	}

	public JTable getTable_entreprise() {
		return table_entreprise;
	}

}