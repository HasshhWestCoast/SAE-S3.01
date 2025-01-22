package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Modele.Locataire;
import Modele.Louer;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoLocataire;
import Modele.Dao.DaoLouer;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.FenInfosLocataire;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutLocation;

public class GestionFenDocument implements ActionListener {

    private FenAccueil fenAc;

    public GestionFenDocument(FenAccueil fenAc) {
        this.fenAc = fenAc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText();
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesdocuments().getModel();

        if (texte != null) {
            switch (texte) {
                case "Charger":
                    System.out.println("Vous Charger les données dans Documents !");
                    try {
                        // Charger le fichier Excel et récupérer les données
                        List<Object[]> donnees = this.chargerDonneesDepuisExcel();

                        if (donnees == null || donnees.isEmpty()) {
                            System.out.println("Aucune donnée trouvée dans le fichier !");
                            break;
                        }

                        // Réinitialiser le modèle de la table
                        modeleTable.setRowCount(donnees.size());

                        // Parcourir les données et remplir la table
                        for (int i = 0; i < donnees.size(); i++) {
                            this.ecrireLigneTable(donnees.get(i), i);
                        }

                    } catch (Exception ex) {
                        System.out.println("Erreur lors du chargement des données : " + ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                default:
                    System.out.println("Action non reconnue !");
            }
        }
    }
    private List<Object[]> chargerDonneesDepuisExcel() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Fichiers Excel", "xls", "xlsx"));

        int returnValue = fileChooser.showOpenDialog(fenAc);
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            return null; // Aucun fichier sélectionné
        }

        File fichier = fileChooser.getSelectedFile();
        List<Object[]> donnees = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fichier);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0); // Lire la première feuille
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Ignorer la ligne d'entête

                // Adaptez selon l'ordre des colonnes dans votre fichier Excel
                Object[] ligne = new Object[5];
                ligne[0] = row.getCell(0) != null ? row.getCell(0).toString() : ""; // IDLogement
                ligne[1] = row.getCell(1) != null ? row.getCell(1).toString() : ""; // IDLocataire
                ligne[2] = row.getCell(2) != null ? row.getCell(2).toString() : ""; // Date
                ligne[3] = row.getCell(3) != null ? row.getCell(3).toString() : ""; // Loyer
                ligne[4] = row.getCell(4) != null ? row.getCell(4).toString() : ""; // Provision
                donnees.add(ligne);
            }
        }
        return donnees;
    }



    private void ecrireLigneTable(Object[] ligne, int rowIndex) {
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesdocuments().getModel();

        modeleTable.setValueAt(ligne[0], rowIndex, 0); // IDLogement
        modeleTable.setValueAt(ligne[1], rowIndex, 1); // IDLocataire
        modeleTable.setValueAt(ligne[2], rowIndex, 2); // Date
        modeleTable.setValueAt(ligne[3], rowIndex, 3); // Loyer
        modeleTable.setValueAt(ligne[4], rowIndex, 4); // Provision
    }


}