package rapport;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import Modele.Bien;
import Modele.Logement;

public class CreerRapport {

    public static void genererRapportBien(List<Bien> biens) throws IOException {
        // Charger le modèle vide pour le document Word
        InputStream modele = new FileInputStream("src/rapport/vide.docx");
        XWPFDocument document = new XWPFDocument(modele);

        OutputStream fileOut = new FileOutputStream("src/rapport/test.docx");

        // Ajouter un titre principal
        XWPFParagraph titre = document.createParagraph();
        XWPFRun runTitre = titre.createRun();
        runTitre.setText("Annexe 2044 - Déclaration des Revenus Fonciers de 2025");
        runTitre.setBold(true);
        runTitre.setFontSize(16);
        runTitre.addCarriageReturn();

        // Ajouter un sous-titre
        XWPFParagraph sousTitre = document.createParagraph();
        XWPFRun runSousTitre = sousTitre.createRun();
        runSousTitre.setText("Caractéristiques des propriétés");
        runSousTitre.setItalic(true);
        runSousTitre.setFontSize(14);
        runSousTitre.addCarriageReturn();

        // Ajouter un tableau pour afficher les données des biens
        XWPFTable table = document.createTable();

        // Ajouter une ligne d'en-tête
        XWPFTableRow headerRow = table.getRow(0);
        headerRow.getCell(0).setText("ID Bien");
        headerRow.addNewTableCell().setText("Adresse");
        headerRow.addNewTableCell().setText("Ville");
        headerRow.addNewTableCell().setText("Code Postal");
        headerRow.addNewTableCell().setText("Type Bien");
        headerRow.addNewTableCell().setText("Période Construction");

        // Ajouter les données des biens dans le tableau
        for (Bien bien : biens) {
            XWPFTableRow row = table.createRow();
            row.getCell(0).setText(bien.getIdBien()); // ID Bien
            row.getCell(1).setText(bien.getAdresse()); // Adresse
            row.getCell(2).setText(bien.getVille()); // Ville
            row.getCell(3).setText(bien.getCodePostal()); // Code Postal
            row.getCell(4).setText(bien.getTypeBien()); // Type Bien
            row.getCell(5).setText(bien.getPeriodeConstruction()); // Période Construction
        }

        // Sauvegarder le fichier Word
        document.write(fileOut);
        fileOut.close();
        modele.close();
        document.close();
    }
    
    public static void genererRapportLogement(List<Logement> logements) throws IOException {
        // Charger le modèle vide pour le document Word
        InputStream modele = new FileInputStream("src/rapport/vide.docx");
        XWPFDocument document = new XWPFDocument(modele);

        OutputStream fileOut = new FileOutputStream("src/rapport/logements.docx");

        // Ajouter un titre principal
        XWPFParagraph titre = document.createParagraph();
        XWPFRun runTitre = titre.createRun();
        runTitre.setText("Rapport des Logements");
        runTitre.setBold(true);
        runTitre.setFontSize(16);
        runTitre.addCarriageReturn();

        // Ajouter un sous-titre
        XWPFParagraph sousTitre = document.createParagraph();
        XWPFRun runSousTitre = sousTitre.createRun();
        runSousTitre.setText("Informations sur les logements enregistrés");
        runSousTitre.setItalic(true);
        runSousTitre.setFontSize(14);
        runSousTitre.addCarriageReturn();

        // Ajouter un tableau pour afficher les données des logements
        XWPFTable table = document.createTable();

        // Ajouter une ligne d'en-tête
        XWPFTableRow headerRow = table.getRow(0);
        headerRow.getCell(0).setText("ID Logement");
        headerRow.addNewTableCell().setText("Surface Habitable");
        headerRow.addNewTableCell().setText("Date Acquisition");
        headerRow.addNewTableCell().setText("Type Logement");
        headerRow.addNewTableCell().setText("Nombre de Pièces");
        headerRow.addNewTableCell().setText("Numéro d'Étage");

        // Ajouter les données des logements dans le tableau
        for (Logement logement : logements) {
            XWPFTableRow row = table.createRow();
            row.getCell(0).setText(logement.getIdLogement()); // ID Logement
            row.getCell(1).setText(String.valueOf(logement.getSurfaceHabitable())); // Surface Habitable
            row.getCell(2).setText(logement.getDateAcquisition()); // Date Acquisition
            row.getCell(3).setText(logement.getType_logement()); // Type Logement
            row.getCell(4).setText(String.valueOf(logement.getNbPieces())); // Nombre de Pièces
            row.getCell(5).setText(String.valueOf(logement.getNumEtage())); // Numéro d'Étage
        }

        // Sauvegarder le fichier Word
        document.write(fileOut);
        fileOut.close();
        modele.close();
        document.close();
    }

    
    
}
