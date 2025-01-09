package Vue;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controleur.Afficher.GestionFenCompteursBien;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class FenCompteursBien extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private GestionFenCompteursBien gestionClicFenCompBien;
	private JTable tabMesCompteurs;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FenCompteursBien() throws SQLException {
		
		this.gestionClicFenCompBien = new GestionFenCompteursBien(this);
		
		getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);
		
		 // JTable pour "Bien"
        tabMesCompteurs = new JTable();
        tabMesCompteurs.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null},
            },
            new String[] {
                "ID Compteur", "Type Compteur", "Index", "Date relevé"
            }
        ));
        JScrollPane scrollPaneBiens = new JScrollPane(tabMesCompteurs);
        scrollPaneBiens.setBounds(23, 77, 580, 367);
        scrollPaneBiens.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        getContentPane().add(scrollPaneBiens);
        
        JLabel lblMesCompteursbien = new JLabel("Mes Compteurs Biens");
        lblMesCompteursbien.setHorizontalAlignment(SwingConstants.CENTER);
        lblMesCompteursbien.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        lblMesCompteursbien.setBackground(Color.WHITE);
        lblMesCompteursbien.setBounds(194, 27, 194, 21);
        getContentPane().add(lblMesCompteursbien);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.WHITE);
        separator.setBackground(new Color(31, 151, 83));
        separator.setBounds(200, 46, 176, 2);
        getContentPane().add(separator);
        
        RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
        btnAnnuler.addActionListener(this.gestionClicFenCompBien);
        btnAnnuler.setBounds(177, 472, 96, 21);
        getContentPane().add(btnAnnuler);
        
        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.addActionListener(this.gestionClicFenCompBien);
        btnCharger.setBounds(304, 472, 96, 21);
        getContentPane().add(btnCharger);
        
		setBounds(100, 100, 669, 550);

	}
	
	public JTable getTabMesCompteurs() {
		return tabMesCompteurs;
	}
}
