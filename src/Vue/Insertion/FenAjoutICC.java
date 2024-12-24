package Vue.Insertion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Vue.RoundedButton;

public class FenAjoutICC extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldAnnee;
	private JTextField textFieldTrimestre;
	private JTextField textFieldIndice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenAjoutICC frame = new FenAjoutICC();
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
	public FenAjoutICC() {
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblAnnee = new JLabel("Année");
		lblAnnee.setBounds(80, 94, 45, 13);
		getContentPane().add(lblAnnee);
		
		JLabel lblTrimestre = new JLabel("Trimestre");
		lblTrimestre.setBounds(80, 176, 66, 13);
		getContentPane().add(lblTrimestre);
		
		JLabel lblIndice = new JLabel("Indice");
		lblIndice.setBounds(80, 256, 131, 13);
		getContentPane().add(lblIndice);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.setBounds(80, 353, 85, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.setBounds(215, 353, 85, 21);
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
		
		textFieldAnnee = new JTextField();
		textFieldAnnee.setBounds(77, 112, 226, 19);
		getContentPane().add(textFieldAnnee);
		textFieldAnnee.setColumns(10);
		
		textFieldTrimestre = new JTextField();
		textFieldTrimestre.setBounds(80, 193, 223, 19);
		getContentPane().add(textFieldTrimestre);
		textFieldTrimestre.setColumns(10);
		
		textFieldIndice = new JTextField();
		textFieldIndice.setBounds(80, 273, 226, 19);
		getContentPane().add(textFieldIndice);
		textFieldIndice.setColumns(10);
		setBounds(100, 100, 423, 453);


	}

}
