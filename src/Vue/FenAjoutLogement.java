package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class FenAjoutLogement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldAdresse;
	private JTextField textFieldCodePostale;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 625);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(8, 1, 0, 0));
		
		JPanel panelAdresse = new JPanel();
		contentPane.add(panelAdresse);
		panelAdresse.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresse.setVerticalAlignment(SwingConstants.BOTTOM);
		panelAdresse.add(lblAdresse);
		
		JPanel paneltextFieldAdresse = new JPanel();
		panelAdresse.add(paneltextFieldAdresse);
		paneltextFieldAdresse.setLayout(new BorderLayout(0, 0));
		
		textFieldAdresse = new JTextField();
		paneltextFieldAdresse.add(textFieldAdresse, BorderLayout.SOUTH);
		textFieldAdresse.setColumns(10);
		
		JPanel panelAdresseVide = new JPanel();
		panelAdresse.add(panelAdresseVide);
		
		JPanel panelCodePostale = new JPanel();
		contentPane.add(panelCodePostale);
		panelCodePostale.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel lblCodePostale = new JLabel("Code postale :");
		lblCodePostale.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodePostale.setVerticalAlignment(SwingConstants.BOTTOM);
		panelCodePostale.add(lblCodePostale);
		
		JPanel paneltextFieldCodePostale = new JPanel();
		panelCodePostale.add(paneltextFieldCodePostale);
		paneltextFieldCodePostale.setLayout(new BorderLayout(0, 0));
		
		textFieldCodePostale = new JTextField();
		paneltextFieldCodePostale.add(textFieldCodePostale, BorderLayout.SOUTH);
		textFieldCodePostale.setColumns(10);
		
		JPanel panelCodePostaleVide = new JPanel();
		panelCodePostale.add(panelCodePostaleVide);
		
		JPanel panelVille = new JPanel();
		contentPane.add(panelVille);
		panelVille.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel lblVille = new JLabel("Ville :");
		lblVille.setHorizontalAlignment(SwingConstants.CENTER);
		lblVille.setVerticalAlignment(SwingConstants.BOTTOM);
		panelVille.add(lblVille);
		
		JPanel paneltextFieldVille = new JPanel();
		panelVille.add(paneltextFieldVille);
		paneltextFieldVille.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		paneltextFieldVille.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
		
		JPanel panelVilleVide = new JPanel();
		panelVille.add(panelVilleVide);
		
		JPanel panelTypeDeBien = new JPanel();
		contentPane.add(panelTypeDeBien);
		panelTypeDeBien.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel lblTypeDeBien = new JLabel("Type de bien :");
		lblTypeDeBien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeDeBien.setVerticalAlignment(SwingConstants.BOTTOM);
		panelTypeDeBien.add(lblTypeDeBien);
		
		JPanel paneltextTypeDeBien = new JPanel();
		panelTypeDeBien.add(paneltextTypeDeBien);
		paneltextTypeDeBien.setLayout(new BorderLayout(0, 0));
		
		JComboBox comboBoxTypeDeBien = new JComboBox();
		comboBoxTypeDeBien.setModel(new DefaultComboBoxModel(new String[] {"Type De Bien"}));
		paneltextTypeDeBien.add(comboBoxTypeDeBien, BorderLayout.SOUTH);
		
		JPanel panelTypeDeBienVide = new JPanel();
		panelTypeDeBien.add(panelTypeDeBienVide);
		
		JPanel panelNombrePieceEtEtage = new JPanel();
		contentPane.add(panelNombrePieceEtEtage);
		panelNombrePieceEtEtage.setLayout(new GridLayout(1, 6, 0, 0));
		
		JPanel panelNombreDePieceVide = new JPanel();
		panelNombrePieceEtEtage.add(panelNombreDePieceVide);
		
		JLabel lblNbPiece = new JLabel("Nombre de Pièce :");
		lblNbPiece.setVerticalAlignment(SwingConstants.BOTTOM);
		panelNombrePieceEtEtage.add(lblNbPiece);
		
		JPanel panelComboBoxPiece = new JPanel();
		panelNombrePieceEtEtage.add(panelComboBoxPiece);
		panelComboBoxPiece.setLayout(new BorderLayout(0, 0));
		
		JSpinner spinnerPiece = new JSpinner();
		panelComboBoxPiece.add(spinnerPiece, BorderLayout.SOUTH);
		
		JLabel lblNbEtage = new JLabel("Nombre d'Etage :");
		lblNbEtage.setVerticalAlignment(SwingConstants.BOTTOM);
		panelNombrePieceEtEtage.add(lblNbEtage);
		
		JPanel panelComboBoxEtage = new JPanel();
		panelNombrePieceEtEtage.add(panelComboBoxEtage);
		panelComboBoxEtage.setLayout(new BorderLayout(0, 0));
		
		JSpinner spinnerEtage = new JSpinner();
		panelComboBoxEtage.add(spinnerEtage, BorderLayout.SOUTH);
		
		JPanel panelNombreDEtageVide = new JPanel();
		panelNombrePieceEtEtage.add(panelNombreDEtageVide);
		
		JPanel panelPeriodeConstruction = new JPanel();
		contentPane.add(panelPeriodeConstruction);
		panelPeriodeConstruction.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panelDateAcquisition = new JPanel();
		contentPane.add(panelDateAcquisition);
		panelDateAcquisition.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panelSuperficie = new JPanel();
		contentPane.add(panelSuperficie);
		panelSuperficie.setLayout(new GridLayout(1, 0, 0, 0));
	}

}
