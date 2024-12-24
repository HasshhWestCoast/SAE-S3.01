package Vue.Insertion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import Vue.RoundedButton;

import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;

public class FenAjoutLogement extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldAcquisition;
	private JTextField textFieldIdLogement;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenAjoutLogement frame = new FenAjoutLogement();
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
	public FenAjoutLogement() {
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblDateAcquisition = new JLabel("Date d'acquisition");
		lblDateAcquisition.setBounds(80, 130, 96, 13);
		getContentPane().add(lblDateAcquisition);
		
		textFieldAcquisition = new JTextField();
		textFieldAcquisition.setBounds(80, 146, 223, 19);
		getContentPane().add(textFieldAcquisition);
		textFieldAcquisition.setColumns(10);
		
		JLabel lbNombreDePiece = new JLabel("Nombre de pieces");
		lbNombreDePiece.setBounds(80, 302, 112, 13);
		getContentPane().add(lbNombreDePiece);
		
		JLabel lblTypeDebien = new JLabel("Type de bien");
		lblTypeDebien.setBounds(80, 185, 66, 13);
		getContentPane().add(lblTypeDebien);

		JComboBox comboBoxTypeDeBien = new JComboBox();
		comboBoxTypeDeBien.setBounds(80, 205, 223, 21);
		comboBoxTypeDeBien.setForeground(new Color(0, 0, 0));
		comboBoxTypeDeBien.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		comboBoxTypeDeBien.setBackground(new Color(31, 153, 88));
		comboBoxTypeDeBien.setModel(new DefaultComboBoxModel(new String[] {"Appartement", "Maison", "Garage"}));
		comboBoxTypeDeBien.setToolTipText("ID Immeuble");
		getContentPane().add(comboBoxTypeDeBien);
		
		JLabel lblIdLogement = new JLabel("ID Logement");
		lblIdLogement.setBounds(80, 68, 96, 13);
		getContentPane().add(lblIdLogement);
		
		textFieldIdLogement = new JTextField();
		textFieldIdLogement.setBounds(80, 84, 223, 19);
		getContentPane().add(textFieldIdLogement);
		textFieldIdLogement.setColumns(10);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.setBounds(80, 413, 85, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.setBounds(213, 413, 85, 21);
		getContentPane().add(btnAjouter);
		
		JLabel lblNewLabel_1 = new JLabel("Ajout Logement");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(124, 26, 131, 21);
		getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(93, 45, 188, 2);
		getContentPane().add(separator);
		
		JSpinner spinnerNbPiece = new JSpinner();
		spinnerNbPiece.setBounds(80, 320, 49, 20);
		getContentPane().add(spinnerNbPiece);
		
		JLabel lblNumEtage = new JLabel("N° etage");
		lblNumEtage.setBounds(198, 302, 71, 13);
		getContentPane().add(lblNumEtage);
		
		JSpinner spinnerNumEtage = new JSpinner();
		spinnerNumEtage.setBounds(198, 320, 49, 20);
		getContentPane().add(spinnerNumEtage);
		
		JLabel lblSurfaceHabitable = new JLabel("Surface habitable");
		lblSurfaceHabitable.setBounds(80, 243, 90, 13);
		getContentPane().add(lblSurfaceHabitable);
		
		textField = new JTextField();
		textField.setBounds(80, 261, 223, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		setBounds(100, 100, 423, 529);

	}
}
