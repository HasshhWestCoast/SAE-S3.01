package Vue.Insertion;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controleur.Ajout.GestionFenAjoutEntreprise;
import Vue.RoundedButton;

public class FenAjoutEntreprise extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldCodePostal;
	private JTextField textFieldNom;
	private JTextField textFieldTelephone;
	private JTextField textFieldMail;
	private JTextField textFieldIBAN;

	private GestionFenAjoutEntreprise gestionClicFenAjoutEntreprise;
	private JTextField textfiledSIRET;
	private JTextField textFieldVille;
	private JTextField textAdresse;
	private JInternalFrame fenPrecedent;

	/**
	 * Create the frame.
	 */
	public FenAjoutEntreprise(JInternalFrame fenPrecedent) {
		
		this.fenPrecedent = fenPrecedent;
		this.gestionClicFenAjoutEntreprise = new GestionFenAjoutEntreprise(this);
				
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(350, 68, 80, 13);
		getContentPane().add(lblAdresse);
		
		textAdresse = new JTextField();
		textAdresse.setBounds(350, 84, 223, 19);
		getContentPane().add(textAdresse);
		textAdresse.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(82, 129, 94, 13);
		getContentPane().add(lblVille);
		
		textFieldVille = new JTextField();
		textFieldVille.setBounds(82, 148, 223, 19);
		getContentPane().add(textFieldVille);
		textFieldVille.setColumns(10);
		
		JLabel lblCodePostale = new JLabel("Code Postal");
		lblCodePostale.setBounds(82, 193, 94, 13);
		getContentPane().add(lblCodePostale);
		
		JLabel lblSIRET = new JLabel("SIRET");
		lblSIRET.setBounds(80, 68, 96, 13);
		getContentPane().add(lblSIRET);
		
		textfiledSIRET = new JTextField();
		textfiledSIRET.setBounds(80, 84, 223, 19);
		getContentPane().add(textfiledSIRET);
		textfiledSIRET.setColumns(10);
		
		JLabel lblAjoutEntreprise = new JLabel("Ajout Entreprise");
		lblAjoutEntreprise.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutEntreprise.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutEntreprise.setBounds(124, 26, 131, 21);
		getContentPane().add(lblAjoutEntreprise);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(93, 45, 188, 2);
		getContentPane().add(separator);
		
		textFieldCodePostal = new JTextField();
		textFieldCodePostal.setBounds(82, 210, 223, 19);
		getContentPane().add(textFieldCodePostal);
		textFieldCodePostal.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(350, 129, 94, 13);
		getContentPane().add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(350, 148, 223, 19);
		getContentPane().add(textFieldNom);
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setBounds(350, 193, 94, 13);
		getContentPane().add(lblTelephone);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(82, 258, 94, 13);
		getContentPane().add(lblMail);
		
		JLabel lblIBAN = new JLabel("IBAN");
		lblIBAN.setBounds(350, 258, 94, 13);
		getContentPane().add(lblIBAN);
		
		textFieldTelephone = new JTextField();
		textFieldTelephone.setColumns(10);
		textFieldTelephone.setBounds(350, 210, 223, 19);
		getContentPane().add(textFieldTelephone);
		
		textFieldMail = new JTextField();
		textFieldMail.setColumns(10);
		textFieldMail.setBounds(82, 276, 223, 19);
		getContentPane().add(textFieldMail);
		
		textFieldIBAN = new JTextField();
		textFieldIBAN.setColumns(10);
		textFieldIBAN.setBounds(350, 276, 223, 19);
		getContentPane().add(textFieldIBAN);
		setBounds(100, 100, 674, 433);

		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.addActionListener(this.gestionClicFenAjoutEntreprise);
		btnAnnuler.setBounds(205, 323, 85, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.addActionListener(this.gestionClicFenAjoutEntreprise);
		btnAjouter.setBounds(359, 323, 85, 21);
		getContentPane().add(btnAjouter);
	}
	
	public String getCodePostal() {
	    return textFieldCodePostal.getText();
	}

	public String getNom() {
	    return textFieldNom.getText();
	}

	public String getTelephone() {
	    return textFieldTelephone.getText();
	}

	public String getMail() {
	    return textFieldMail.getText();
	}

	public String getIBAN() {
	    return textFieldIBAN.getText();
	}

	public String getSIRET() {
	    return textfiledSIRET.getText();
	}

	public String getVille() {
	    return textFieldVille.getText();
	}

	public String getAdresse() {
	    return textAdresse.getText();
	}

	public JInternalFrame getFenPrecedent() {
        return this.fenPrecedent;
    }

}
