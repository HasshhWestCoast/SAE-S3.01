package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class FenAssurances extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabMesLocations;
	private JComboBox comboBoxIDAssurance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenAssurances frame = new FenAssurances();
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
	public FenAssurances() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 947, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitre = new JPanel();
		contentPane.add(panelTitre, BorderLayout.NORTH);
		panelTitre.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMesAssurances = new JLabel("MesAssurances");
		lblMesAssurances.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesAssurances.setFont(new Font("Sylfaen", Font.ITALIC, 24));
		lblMesAssurances.setForeground(new Color(31, 153, 88));
		panelTitre.add(lblMesAssurances);
		
		JSeparator separatorNord = new JSeparator();
		panelTitre.add(separatorNord, BorderLayout.SOUTH);
		

		JPanel panelCentre = new JPanel(new BorderLayout());
		panelCentre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new BorderLayout(0, 0));
       
		tabMesLocations = new JTable();
        tabMesLocations.setModel(new DefaultTableModel(
            new Object[][] { { null, null, null, null, null, null, null } },
            new String[] { "Numéro police", "ID assurance", "Protection juridique", "Prime", "Quotité", "Date de début", "Date de fin" }
        ));
        JScrollPane scrollPane = new JScrollPane(tabMesLocations);
        scrollPane.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPane, BorderLayout.SOUTH);
        
        comboBoxIDAssurance = new JComboBox();
        comboBoxIDAssurance.setForeground(new Color(0, 0, 0));
        comboBoxIDAssurance.setFont(new Font("Sylfaen", Font.PLAIN, 14));
        comboBoxIDAssurance.setBackground(new Color(31, 153, 88));
        comboBoxIDAssurance.setModel(new DefaultComboBoxModel(new String[] {"ID Assurance"}));
        comboBoxIDAssurance.setToolTipText("ID Assurance");
        panelCentre.add(comboBoxIDAssurance, BorderLayout.NORTH);
        
        JPanel panelEspaceComboTable = new JPanel();
        panelCentre.add(panelEspaceComboTable, BorderLayout.CENTER);
        
        JPanel panelSud = new JPanel();
        contentPane.add(panelSud, BorderLayout.SOUTH);
        panelSud.setLayout(new BorderLayout(0, 0));
        
        JPanel panelBouton = new JPanel();
        panelSud.add(panelBouton);
        
        RoundedButton btnICharger = new RoundedButton("Charger", 20);
        panelBouton.add(btnICharger);
        
        RoundedButton btnInserer = new RoundedButton("Inserer", 20);
        panelBouton.add(btnInserer);
        
        JSeparator separatorSUD = new JSeparator();
        panelSud.add(separatorSUD, BorderLayout.NORTH);
	
	}
	
	public JTable gettabMesLocations() {
		return tabMesLocations;
	}
	
	public JComboBox getcomboBoxIDImmeuble() {
		return comboBoxIDAssurance;
	}

}
