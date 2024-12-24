package Vue.Insertion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Vue.RoundedButton;

public class FenAjoutPréstataire extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldCodePostal;
	private JTextField textFieldMail;
	private JTextField textFieldVille;
	private JTextField textFieldIBAN;
	private JTextField textFieldAdresse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenAjoutPréstataire frame = new FenAjoutPréstataire();
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
	public FenAjoutPréstataire() {
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblSIRET = new JLabel("SIRET");
		lblSIRET.setBounds(384, 68, 80, 13);
		getContentPane().add(lblSIRET);
		
		JTextField textFileldSiret = new JTextField();
		textFileldSiret.setBounds(384, 84, 223, 19);
		getContentPane().add(textFileldSiret);
		textFileldSiret.setColumns(10);
		
		JLabel lblNTelephone = new JLabel("N° Téléphone");
		lblNTelephone.setBounds(80, 136, 94, 13);
		getContentPane().add(lblNTelephone);
		
		JTextField textFieldNTelephone = new JTextField();
		textFieldNTelephone.setBounds(80, 159, 223, 19);
		getContentPane().add(textFieldNTelephone);
		textFieldNTelephone.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(80, 206, 64, 13);
		getContentPane().add(lblAdresse);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(80, 68, 96, 13);
		getContentPane().add(lblNom);
		
		JTextField textFieldIdNom = new JTextField();
		textFieldIdNom.setBounds(80, 84, 223, 19);
		getContentPane().add(textFieldIdNom);
		textFieldIdNom.setColumns(10);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.setBounds(218, 374, 85, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.setBounds(384, 374, 85, 21);
		getContentPane().add(btnAjouter);
		
		JLabel lblAjoutPrestataire = new JLabel("Ajout Prestataire");
		lblAjoutPrestataire.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutPrestataire.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutPrestataire.setBounds(275, 23, 131, 21);
		getContentPane().add(lblAjoutPrestataire);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(241, 42, 188, 2);
		getContentPane().add(separator);
		
		textFieldCodePostal = new JTextField();
		textFieldCodePostal.setBounds(384, 159, 223, 19);
		getContentPane().add(textFieldCodePostal);
		textFieldCodePostal.setColumns(10);
		
		JLabel lblCodePostale = new JLabel("Code Postal");
		lblCodePostale.setBounds(384, 136, 80, 13);
		getContentPane().add(lblCodePostale);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(384, 206, 45, 13);
		getContentPane().add(lblMail);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(384, 225, 223, 19);
		getContentPane().add(textFieldMail);
		textFieldMail.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(82, 284, 45, 13);
		getContentPane().add(lblVille);
		
		textFieldVille = new JTextField();
		textFieldVille.setBounds(80, 301, 223, 19);
		getContentPane().add(textFieldVille);
		textFieldVille.setColumns(10);
		
		JLabel lblIBAN = new JLabel("IBAN");
		lblIBAN.setBounds(384, 284, 45, 13);
		getContentPane().add(lblIBAN);
		
		textFieldIBAN = new JTextField();
		textFieldIBAN.setBounds(384, 301, 223, 19);
		getContentPane().add(textFieldIBAN);
		textFieldIBAN.setColumns(10);
		
		textFieldAdresse = new JTextField();
		textFieldAdresse.setBounds(80, 225, 223, 19);
		getContentPane().add(textFieldAdresse);
		textFieldAdresse.setColumns(10);
		setBounds(100, 100, 738, 493);

	}

}
