package rapport;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import Modele.Logement;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoAssurance;
import Modele.Dao.DaoLocataire;
import Modele.Dao.DaoLogement;
public class CreerRapport {

	public static void main(String[] args) throws SQLException  {
		
		
        try {
        	 // Créer un document Word
	        InputStream modele = new FileInputStream("src/rapport/vide.docx");
			XWPFDocument document = new XWPFDocument(modele);
			
			OutputStream fileOut = new FileOutputStream("src/rapport/test.docx");
			
			 // Ajouter un paragraphe d'introduction
            XWPFParagraph paragraph1 = document.createParagraph();
            
            XWPFRun run1 = paragraph1.createRun();
            run1.setText("Planning des cours...");
            
            // Ajouter un message expliquant le contenu
            XWPFParagraph paragraph2 = document.createParagraph();
            XWPFRun run2 = paragraph2.createRun();
            run2.addCarriageReturn();
            run2.addCarriageReturn();
            run2.setText("Veuillez trouver ci-joint, l'ensemble des créneaux affichés dans la table.");
            run2.addCarriageReturn();
            run2.addCarriageReturn();
            
        
            
       
            DaoLogement dao = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
            
            // Récupérer tous les logements
            List<Logement> logements = dao.findAll();
            System.out.println("Nombre de logements récupérés : " + logements.size());
            
         // Ajouter un tableau dans le document Word
            XWPFTable table = document.createTable();
            
         // Ajouter une ligne d'en-têtes
            XWPFTableRow headerRow = table.getRow(0);
            headerRow.getCell(0).setText("ID Logement");
            headerRow.addNewTableCell().setText("Surface Habitable");
            headerRow.addNewTableCell().setText("Date Acquisition");
            headerRow.addNewTableCell().setText("Type");
            headerRow.addNewTableCell().setText("Nombre de Pièces");
            headerRow.addNewTableCell().setText("Étage");

            // Ajouter les données des logements dans le tableau
            for (Logement logement : logements) {
                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(logement.getIdLogement());
                row.getCell(1).setText(String.valueOf(logement.getSurfaceHabitable()));
                row.getCell(2).setText(logement.getDateAcquisition());
                row.getCell(3).setText(logement.getType_logement());
                row.getCell(4).setText(String.valueOf(logement.getNbPieces()));
                row.getCell(5).setText(String.valueOf(logement.getNumEtage()));
            }

            // Sauvegarder le document
            document.write(fileOut);
            fileOut.close();
            modele.close();
            document.close();

            System.out.println("Rapport généré avec succès !");
         

			
		} catch (IOException e) {
			e.printStackTrace();
		}
        

		

	}

}
