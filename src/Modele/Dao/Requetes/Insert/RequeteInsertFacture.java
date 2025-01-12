package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Facture;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertFacture extends Requete<Facture> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_facture (Id_Facture, date_Emission, date_Paiement, mode_Paiement, numero_Devis, designation, montant_Reel_Verse, montant, imputable_Locataire, acompte_Verse, ID_Logement, ID_Bien, SIRET) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Facture facture) throws SQLException {
        prSt.setString(1, facture.getIdFacture());
        prSt.setString(2, facture.getDateEmission());
        prSt.setString(3, facture.getDatePaiement() != null ? facture.getDatePaiement() : null);
        prSt.setString(4, facture.getModePaiement());
        prSt.setString(5, facture.getNumeroDevis());
        prSt.setString(6, facture.getDesignation());
        prSt.setDouble(7, facture.getmontantReelVerse());
        prSt.setDouble(8, facture.getMontant());
        prSt.setInt(9, (int) facture.getImputableLocataire());
        prSt.setDouble(10, facture.getacompteVerse());
        
        if (facture.getLogement() != null) {
            prSt.setString(11, facture.getLogement().getIdLogement());
        } else {
            prSt.setNull(11, java.sql.Types.VARCHAR);
        }

        if (facture.getBien() != null) {
            prSt.setString(12, facture.getBien().getIdBien());
        } else {
            prSt.setNull(12, java.sql.Types.VARCHAR);
        }

        prSt.setString(13, facture.getEntreprise().getSiret());
        
    }
}
