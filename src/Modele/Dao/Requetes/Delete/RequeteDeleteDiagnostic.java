package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Diagnostic;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteDiagnostic extends Requete<Diagnostic> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_Diagnostic WHERE Id_Diagnostic = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Diagnostic diagnostic) throws SQLException {
        prSt.setString(1, diagnostic.getIdDiagnostic());
    }
}
