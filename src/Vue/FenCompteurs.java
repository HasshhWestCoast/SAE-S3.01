package Vue;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controleur.Afficher.GestionFenCompteurs;

public class FenCompteurs extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private GestionFenCompteurs gestionClicFenCompLog;
	private JTable tabMesCompteurs;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FenCompteurs() throws SQLException {
		
		this.gestionClicFenCompLog = new GestionFenCompteurs(this);
		
		getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);
		
		 // JTable pour "Bien"
        tabMesCompteurs = new JTable();
        tabMesCompteurs.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null},
            },
            new String[] {
                "ID Compteur", "Type Compteur", "Id Logement", "Id Bien"
            }
        ));
        JScrollPane scrollPaneCompteursLogements = new JScrollPane(tabMesCompteurs);
        scrollPaneCompteursLogements.setBounds(134, 76, 580, 367);
        scrollPaneCompteursLogements.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        getContentPane().add(scrollPaneCompteursLogements);
        
        JLabel lblMesCompteurs = new JLabel("Mes Compteurs");
        lblMesCompteurs.setHorizontalAlignment(SwingConstants.CENTER);
        lblMesCompteurs.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        lblMesCompteurs.setBackground(Color.WHITE);
        lblMesCompteurs.setBounds(311, 27, 216, 21);
        getContentPane().add(lblMesCompteurs);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.WHITE);
        separator.setBackground(new Color(31, 151, 83));
        separator.setBounds(322, 46, 225, 2);
        getContentPane().add(separator);
        
        RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
        btnAnnuler.addActionListener(this.gestionClicFenCompLog);
        btnAnnuler.setBounds(161, 472, 104, 21);
        getContentPane().add(btnAnnuler);
        
        RoundedButton btnAfficherDateReleve = new RoundedButton("Afficher date relevé", 20);
        btnAfficherDateReleve.addActionListener(this.gestionClicFenCompLog);
        btnAfficherDateReleve.setBounds(556, 472, 158, 21);
        getContentPane().add(btnAfficherDateReleve);
        
        RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
        btnAjouter.addActionListener(this.gestionClicFenCompLog);
        btnAjouter.setBounds(275, 472, 104, 21);
        getContentPane().add(btnAjouter);
        
        RoundedButton rndbtnCharger = new RoundedButton("Charger", 20);
        rndbtnCharger.addActionListener(this.gestionClicFenCompLog);
        rndbtnCharger.setBounds(443, 472, 104, 21);
        getContentPane().add(rndbtnCharger);
        
		setBounds(100, 100, 911, 626);

	}
	
	public JTable gettabMesCompteurs() {
		return tabMesCompteurs;
	}
}
