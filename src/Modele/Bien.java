package Modele;

public class Bien {
	private String idBien;
	private double surfaceHabitable;
	private String adresse;
	private String ville;
	private String typeBien;
	private String codePostal;
	private String periodeConstruction;
	private Logement logement;

	public Bien(String idBien, double surfaceHabitable, String adresse,String ville,String typeBien,String codePostal,String periodeConstruction,Logement logement) {
		this.idBien = idBien;
		this.surfaceHabitable=surfaceHabitable;
		this.adresse = adresse;
		this.ville = ville;
		this.typeBien = typeBien;
		this.codePostal = codePostal;
		this.periodeConstruction = periodeConstruction;
		this.logement = logement;
		
		
	}

	public String getIdBien() {
		return idBien;
	}

	public void setIdBien(String idBien) {
		this.idBien = idBien;
	}

	public double getSurfaceHabitable() {
		return surfaceHabitable;
	}

	public void setSurfaceHabitable(double surfaceHabitable) {
		this.surfaceHabitable = surfaceHabitable;
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

	public Logement getLogement() {
		return logement;
	}

	public void setLogement(Logement logement) {
		this.logement = logement;
	}

	
	

}