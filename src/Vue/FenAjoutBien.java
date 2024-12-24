package Vue;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class FenAjoutBien extends JInternalFrame {

	private static final long serialVersionUID = 1L;

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
		
		JLabel lblSurfaceHabitable = new JLabel("Surface habitable");
		lblSurfaceHabitable.setBounds(82, 123, 80, 13);
		getContentPane().add(lblSurfaceHabitable);
		
		JTextField textSurfaceHabitable = new JTextField();
		textSurfaceHabitable.setBounds(80, 137, 223, 19);
		getContentPane().add(textSurfaceHabitable);
		textSurfaceHabitable.setColumns(10);
		
		JLabel lblDateAcquisition = new JLabel("Date acquisition");
		lblDateAcquisition.setBounds(82, 179, 94, 13);
		getContentPane().add(lblDateAcquisition);
		
		JTextField textFieldDateAcquisition = new JTextField();
		textFieldDateAcquisition.setBounds(82, 195, 223, 19);
		getContentPane().add(textFieldDateAcquisition);
		textFieldDateAcquisition.setColumns(10);
		
		JLabel lblTypeDeBien = new JLabel("Type de bien");
		lblTypeDeBien.setBounds(82, 234, 64, 13);
		getContentPane().add(lblTypeDeBien);
		
		JLabel lblNombreDePiece = new JLabel("Nombre de pièces");
		lblNombreDePiece.setBounds(82, 293, 94, 13);
		getContentPane().add(lblNombreDePiece);
		
		JLabel lblNombreEtage = new JLabel("Nombre étage");
		lblNombreEtage.setBounds(193, 293, 131, 13);
		getContentPane().add(lblNombreEtage);
		
		JLabel lblIdBien = new JLabel("ID Bien");
		lblIdBien.setBounds(80, 68, 96, 13);
		getContentPane().add(lblIdBien);
		
		JTextField textFieldIdBien = new JTextField();
		textFieldIdBien.setBounds(80, 84, 223, 19);
		getContentPane().add(textFieldIdBien);
		textFieldIdBien.setColumns(10);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.setBounds(80, 401, 85, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.setBounds(211, 401, 85, 21);
		getContentPane().add(btnAjouter);
		
		JLabel lblAjoutBien = new JLabel("Ajout Bien");
		lblAjoutBien.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutBien.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutBien.setBounds(124, 26, 131, 21);
		getContentPane().add(lblAjoutBien);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(93, 45, 188, 2);
		getContentPane().add(separator);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Immeuble", "Maison"}));
		comboBox.setBounds(82, 252, 221, 21);
		getContentPane().add(comboBox);
		
		JSpinner spinnerNbPiece = new JSpinner();
		spinnerNbPiece.setBounds(82, 308, 44, 20);
		getContentPane().add(spinnerNbPiece);
		
		JSpinner spinnerNbEtage = new JSpinner();
		spinnerNbEtage.setBounds(193, 308, 44, 20);
		getContentPane().add(spinnerNbEtage);
		setBounds(100, 100, 423, 568);
	}

}
