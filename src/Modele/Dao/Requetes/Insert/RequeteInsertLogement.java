package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Logement;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertLogement extends Requete<Logement> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_logement (id_Logement, surface_Habitable, date_Acquisition, type_Logement, nb_Pieces, num_Etage, garage, id_Bien) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Logement logement) throws SQLException {
        prSt.setString(1, logement.getIdLogement());
        prSt.setDouble(2, logement.getSurfaceHabitable());
        prSt.setString(3, logement.getDateAcquisition());
        prSt.setString(4, logement.getType_logement());
        prSt.setInt(5, logement.getNbPieces());
        prSt.setInt(6, logement.getNumEtage());
        prSt.setInt(7, logement.getGarage());
        prSt.setString(8, logement.getBien().getIdBien());

    }
}
