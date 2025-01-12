package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modele.Louer;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertLouer extends Requete<Louer> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_louer (date_Debut, date_Sortie, date_Derniere_Reg, nb_Mois, loyer_payer, loyer_mens_TTC, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, Id_Locataire, Id_Bien, ICC) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Louer louer) throws SQLException {
        prSt.setString(1, louer.getDateDebut());
        prSt.setString(2, louer.getDateSortie());
        prSt.setString(3, louer.getDateDerniereRegularisation());
        prSt.setInt(4, louer.getNbMois());
        prSt.setInt(5, louer.getloyePayer());
        prSt.setDouble(6, louer.getLoyerMensTTC());
        prSt.setDouble(7, louer.getprovision_chargeMoisTTC());
        prSt.setDouble(8, louer.getCautionTTC());
        prSt.setString(9, louer.getBail());
        prSt.setString(10, louer.getEtat_lieux());
        prSt.setDouble(11, louer.getMontantReelPaye());
        prSt.setString(12, louer.getLocataire().getIdLocataire());
        prSt.setString(13, louer.getBien().getIdBien());
        prSt.setInt(14, louer.getIcc().getIcc());
    }
}
