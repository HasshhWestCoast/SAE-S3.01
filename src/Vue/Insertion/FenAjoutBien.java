package Vue.Insertion;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Vue.RoundedButton;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenAjoutBien extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldCodePostale;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenAjoutBien frame = new FenAjoutBien();
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
	public FenAjoutBien() {
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(82, 123, 80, 13);
		getContentPane().add(lblAdresse);
		
		JTextField textAdresse = new JTextField();
		textAdresse.setBounds(80, 137, 223, 19);
		getContentPane().add(textAdresse);
		textAdresse.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(82, 179, 94, 13);
		getContentPane().add(lblVille);
		
		JTextField textFieldVille = new JTextField();
		textFieldVille.setBounds(82, 195, 223, 19);
		getContentPane().add(textFieldVille);
		textFieldVille.setColumns(10);
		
		JLabel lblTypeDeBien = new JLabel("Type de bien");
		lblTypeDeBien.setBounds(82, 234, 80, 13);
		getContentPane().add(lblTypeDeBien);
		
		JLabel lblCodePostale = new JLabel("Code Postal");
		lblCodePostale.setBounds(82, 293, 94, 13);
		getContentPane().add(lblCodePostale);
		
		JLabel lblIdBien = new JLabel("ID Bien");
		lblIdBien.setBounds(80, 68, 96, 13);
		getContentPane().add(lblIdBien);
		
		JTextField textFieldIdBien = new JTextField();
		textFieldIdBien.setBounds(80, 84, 223, 19);
		getContentPane().add(textFieldIdBien);
		textFieldIdBien.setColumns(10);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.setBounds(82, 419, 85, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.setBounds(218, 419, 85, 21);
		getContentPane().add(btnAjouter);
		
		JLabel lblAjoutBien = new JLabel("Ajout Bien");
		lblAjoutBien.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutBien.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutBien.setBounds(124, 26, 131, 21);
		getContentPane().add(lblAjoutBien);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(93, 45, 188, 2);
		getContentPane().add(separator);
		
		JComboBox comboBoxTypeDeBien = new JComboBox();
		comboBoxTypeDeBien.setBounds(82, 252, 221, 21);
		comboBoxTypeDeBien.setForeground(new Color(0, 0, 0));
		comboBoxTypeDeBien.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		comboBoxTypeDeBien.setBackground(new Color(31, 153, 88));
		comboBoxTypeDeBien.setModel(new DefaultComboBoxModel(new String[] {"Immeuble", "Maison"}));
		comboBoxTypeDeBien.setToolTipText("ID Immeuble");
		getContentPane().add(comboBoxTypeDeBien);
		
		RoundedButton btnAjoutCompteur = new RoundedButton("Compteur", 20);
		btnAjoutCompteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAjoutCompteur.setText("Ajout Compteur");
		btnAjoutCompteur.setBounds(146, 461, 109, 21);
		getContentPane().add(btnAjoutCompteur);
		
		textFieldCodePostale = new JTextField();
		textFieldCodePostale.setBounds(80, 311, 223, 19);
		getContentPane().add(textFieldCodePostale);
		textFieldCodePostale.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Période construction");
		lblNewLabel.setBounds(82, 352, 109, 13);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(80, 370, 223, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		setBounds(200, 100, 423, 540);
	}
}
