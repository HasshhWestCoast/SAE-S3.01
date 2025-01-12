package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Diagnostic;
import Modele.Dao.Requetes.Requete; 

public class RequeteInsertDiagnostic extends Requete<Diagnostic> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_Diagnostic (id_Diagnostic, date_Validite, type_Diagnostic, id_Bien) "
        		+ "VALUES (?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Diagnostic diagnostic) throws SQLException {
        prSt.setString(1, diagnostic.getIdDiagnostic());
        prSt.setString(2, diagnostic.getDateValidite());
        prSt.setString(3, diagnostic.getTypeDiagnostic());
        prSt.setString(4, diagnostic.getBien().getIdBien());
    }
}
