package Modele;

public class Diagnostic {
	private int idDiagnostic; //Sequence
	private String dateValidite;
	private String typeDiagnostic;
	private Bien bien;

	public Diagnostic(String dateValidite, String typeDiagnostic, Bien bien) {
		this.dateValidite = dateValidite;
		this.typeDiagnostic = typeDiagnostic;
		this.bien = bien;
	}

	public int getIdDiagnostic() {
		return this.idDiagnostic;
	}

	public void setIdDiagnostic(int idDiagnostic) {
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