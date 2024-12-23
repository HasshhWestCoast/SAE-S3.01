package Vue;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class FenAjoutLogement extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldAdresse;
	private JTextField textFieldCodePostale;
	private JTextField textFieldVille;
	private JTextField textFieldPeriodeDeConstruction;
	private JTextField textFieldIdImmeuble;

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
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(80, 130, 45, 13);
		getContentPane().add(lblAdresse);
		
		textFieldAdresse = new JTextField();
		textFieldAdresse.setBounds(80, 146, 223, 19);
		getContentPane().add(textFieldAdresse);
		textFieldAdresse.setColumns(10);
		
		JLabel lblCodePostale = new JLabel("Code Postale");
		lblCodePostale.setBounds(80, 186, 66, 13);
		getContentPane().add(lblCodePostale);
		
		textFieldCodePostale = new JTextField();
		textFieldCodePostale.setBounds(80, 201, 223, 19);
		getContentPane().add(textFieldCodePostale);
		textFieldCodePostale.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(80, 243, 45, 13);
		getContentPane().add(lblVille);
		
		textFieldVille = new JTextField();
		textFieldVille.setBounds(80, 262, 223, 19);
		getContentPane().add(textFieldVille);
		textFieldVille.setColumns(10);
		
		JLabel lblTypeDebien = new JLabel("Type de bien");
		lblTypeDebien.setBounds(80, 304, 66, 13);
		getContentPane().add(lblTypeDebien);
		
		JComboBox comboBoxTypeDeBien = new JComboBox();
		comboBoxTypeDeBien.setBounds(80, 320, 223, 21);
		getContentPane().add(comboBoxTypeDeBien);
		
		JLabel lblPeriodeDeConstruction = new JLabel("Période de construction");
		lblPeriodeDeConstruction.setBounds(80, 362, 131, 13);
		getContentPane().add(lblPeriodeDeConstruction);
		
		textFieldPeriodeDeConstruction = new JTextField();
		textFieldPeriodeDeConstruction.setBounds(80, 377, 96, 19);
		getContentPane().add(textFieldPeriodeDeConstruction);
		textFieldPeriodeDeConstruction.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID Immeuble");
		lblNewLabel.setBounds(80, 68, 96, 13);
		getContentPane().add(lblNewLabel);
		
		textFieldIdImmeuble = new JTextField();
		textFieldIdImmeuble.setBounds(80, 84, 223, 19);
		getContentPane().add(textFieldIdImmeuble);
		textFieldIdImmeuble.setColumns(10);
		setBounds(100, 100, 456, 568);

	}
}
