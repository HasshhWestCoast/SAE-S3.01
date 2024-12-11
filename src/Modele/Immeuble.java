package Modele;

public class Immeuble {

	private String idImmeuble;
	private String adresse;
	private String codePostal;
	private String ville;
	private String periodeConstruction;
	private String type_immeuble;

	public Immeuble(String idImmeuble, String adresse, String codePostal, String ville, String periodeConstruction, String type_immeuble) {
		this.idImmeuble = idImmeuble;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.periodeConstruction = periodeConstruction;
		this.type_immeuble = type_immeuble;
	}

	public String getImmeuble() {
		return idImmeuble;
	}

	public void setImmeuble(String immeuble) {
		this.idImmeuble = immeuble;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getcodePostal() {
		return codePostal;
	}

	public void setcodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPeriodeConstruction() {
		return periodeConstruction;
	}

	public void setPeriodeConstruction(String periodeConstruction) {
		this.periodeConstruction = periodeConstruction;
	}

	public String getType_immeuble() {
		return type_immeuble;
	}

	public void setType_immeuble(String type_immeuble) {
		this.type_immeuble = type_immeuble;
	}

}