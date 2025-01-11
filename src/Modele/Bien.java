package Modele;

import java.util.Objects;

public class Bien {
	private String idBien;
	private String adresse;
	private String ville;
	private String typeBien;
	private String codePostal;
	private String periodeConstruction;

	public Bien(String idBien, String adresse,String ville,String typeBien,String codePostal,String periodeConstruction) {
		this.idBien = idBien;
		this.adresse = adresse;
		this.ville = ville;
		this.typeBien = typeBien;
		this.codePostal = codePostal;
		this.periodeConstruction = periodeConstruction;
	}

	public String getIdBien() {
		return idBien;
	}

	public void setIdBien(String idBien) {
		this.idBien = idBien;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTypeBien() {
		return typeBien;
	}

	public void setTypeBien(String typeBien) {
		this.typeBien = typeBien;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getPeriodeConstruction() {
		return periodeConstruction;
	}

	public void setPeriodeConstruction(String periodeConstruction) {
		this.periodeConstruction = periodeConstruction;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idBien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Bien)) {
			return false;
		}
		Bien other = (Bien) obj;
		return Objects.equals(idBien, other.idBien);
	}

	@Override
	public String toString() {
		return "Bien [idBien=" + idBien + ", adresse=" + adresse + ", ville="
				+ ville + ", typeBien=" + typeBien + ", codePostal=" + codePostal + ", periodeConstruction="
				+ periodeConstruction + "]";
	}
	
	

	
	
	

}