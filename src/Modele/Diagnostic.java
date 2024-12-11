package Modele;

public class Diagnostic {
	
	private String idDiagnostic; 
	private String dateValidite;
	private String typeDiagnostic;
	private Bien bien;

	public Diagnostic(String idDiagnostic, String dateValidite, String typeDiagnostic, Bien bien) {
		this.idDiagnostic = idDiagnostic;
		this.dateValidite = dateValidite;
		this.typeDiagnostic = typeDiagnostic;
		this.bien = bien;
	}

	public String getIdDiagnostic() {
		return this.idDiagnostic;
	}

	public void setIdDiagnostic(String idDiagnostic) {
		this.idDiagnostic = idDiagnostic;
	}

	public String getDateValidite() {
		return this.dateValidite;
	}

	public void setDateValidite(String dateValidite) {
		this.dateValidite = dateValidite;
	}

	public String getTypeDiagnostic() {
		return this.typeDiagnostic;
	}

	public void setTypeDiagnostic(String typeDiagnostic) {
		this.typeDiagnostic = typeDiagnostic;
	}

	public Bien getBien() {
		return this.bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}


}