package Vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controleur.Afficher.GestionFenCompteursBien;
import Controleur.Afficher.GestionFenLocataire;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenLocataire extends JInternalFrame {
	
	private GestionFenLocataire gestionFenLocataire;

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_6;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	
	
	public FenLocataire() throws SQLException {
		this.gestionFenLocataire = new GestionFenLocataire(this);
		getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);
		
		 // JTable pour "Bien"
        JTable tabMesBiens = new JTable();
        tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null},
            },
            new String[] {
                "Bien", "période du", "au", "charge rééll", "Ordures ménagère", "Total charges", "Restant du loyer", "Total provision","reste"
            }
        ));
        JScrollPane scrollPaneBiens = new JScrollPane(tabMesBiens);
        scrollPaneBiens.setBounds(324, 119, 580, 71);
        scrollPaneBiens.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        getContentPane().add(scrollPaneBiens);
        
        JLabel lblMesCompteursbien = new JLabel("Mon locataire");
        lblMesCompteursbien.setBounds(526, 22, 194, 21);
        lblMesCompteursbien.setHorizontalAlignment(SwingConstants.CENTER);
        lblMesCompteursbien.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        lblMesCompteursbien.setBackground(Color.WHITE);
        getContentPane().add(lblMesCompteursbien);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(534, 43, 176, 2);
        separator.setForeground(Color.WHITE);
        separator.setBackground(new Color(31, 151, 83));
        getContentPane().add(separator);
        
        RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
        btnAnnuler.addActionListener(this.gestionFenLocataire);
        btnAnnuler.setText("Annuler");
        btnAnnuler.setBackground(new Color(31, 153, 88));
        btnAnnuler.setBounds(478, 468, 96, 21);
        getContentPane().add(btnAnnuler);
        
        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.setText("Retour");
        getContentPane().add(btnCharger);
        
        JLabel label_operations = new JLabel("Opérations");
        label_operations.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_operations.setBounds(324, 96, 96, 21);
        getContentPane().add(label_operations);
        
        JTable tab_2 = new JTable();
        tab_2.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null},
            },
            new String[] {
                "Provision sur charge", "", "Charge Réelles", "", "Caution", "", "Travaux imputable", "","Restant du loyer","","Reste"
            }
        ));
        JScrollPane table_2 = new JScrollPane(tab_2);
        table_2.setBounds(324, 327, 580, 71);
        table_2.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        getContentPane().add(table_2);
        
        RoundedButton btn_solde_tout_compte = new RoundedButton("Charger", 20);
        btn_solde_tout_compte.setText("Solde tout compte ");
        btn_solde_tout_compte.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn_solde_tout_compte.setBounds(495, 291, 247, 21);
        btn_solde_tout_compte.setBackground(new Color(31, 151, 83));
        getContentPane().add(btn_solde_tout_compte);
        
        JLabel lblSiVotreReste = new JLabel("si votre reste est positif, alors vous devez de l'argent a votre locataire. sinon, il vous doit de l'argent.");
        lblSiVotreReste.setBounds(324, 189, 570, 13);
        getContentPane().add(lblSiVotreReste);
        
        JLabel lblSiVotreReste_2 = new JLabel("si votre reste est négatif, alors votre locataire vous doit de l'argent. sinon, vous lui en devez.");
        lblSiVotreReste_2.setBounds(324, 396, 570, 13);
        getContentPane().add(lblSiVotreReste_2);
        
        RoundedButton btnRegularisation = new RoundedButton("Charger", 20);
        btnRegularisation.setText("Régularisation des charges");
        btnRegularisation.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRegularisation.setBackground(new Color(31, 153, 88));
        btnRegularisation.setBounds(495, 78, 247, 21);
        getContentPane().add(btnRegularisation);
        
        JLabel lblSiVotreReste_2_1 = new JLabel("Départ de votre locataire :");
        lblSiVotreReste_2_1.setBounds(549, 268, 128, 13);
        getContentPane().add(lblSiVotreReste_2_1);
        
        JSeparator separatorVertical = new JSeparator();
        separatorVertical.setOrientation(SwingConstants.VERTICAL);
        separatorVertical.setForeground(Color.WHITE);
        separatorVertical.setBackground(new Color(31, 151, 83));
        separatorVertical.setBounds(288, 39, 11, 450);
        getContentPane().add(separatorVertical);
        
        JLabel lebelDetailLocataire = new JLabel("Détail locataire");
        lebelDetailLocataire.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lebelDetailLocataire.setBounds(70, 52, 142, 21);
        getContentPane().add(lebelDetailLocataire);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Identifiant", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textField.setBounds(29, 103, 194, 41);
        getContentPane().add(textField);
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Nom", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textField_6.setBounds(29, 170, 194, 41);
        getContentPane().add(textField_6);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Prénom", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textField_1.setBounds(29, 232, 194, 41);
        getContentPane().add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Num Téléphone", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textField_2.setBounds(29, 356, 194, 41);
        getContentPane().add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Mail", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textField_3.setBounds(29, 413, 194, 41);
        getContentPane().add(textField_3);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Date Naissance", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textField_4.setBounds(29, 292, 194, 41);
        getContentPane().add(textField_4);
        
        RoundedButton btnAnnuler_1 = new RoundedButton("Annuler", 20);
        btnAnnuler_1.setText("Modifier");
        btnAnnuler_1.setBackground(new Color(31, 153, 88));
        btnAnnuler_1.setBounds(622, 468, 96, 21);
        getContentPane().add(btnAnnuler_1);
        
		setBounds(100, 100, 948, 550);
	}
}
