package Modele;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(idDiagnostic);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Diagnostic)) {
			return false;
		}
		Diagnostic other = (Diagnostic) obj;
		return Objects.equals(idDiagnostic, other.idDiagnostic);
	}

	@Override
	public String toString() {
		return "Diagnostic [idDiagnostic=" + idDiagnostic + ", dateValidite=" + dateValidite + ", typeDiagnostic="
				+ typeDiagnostic + ", bien=" + bien + "]";
	}
	
	
	
	


}