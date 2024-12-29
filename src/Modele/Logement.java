package Modele;

public class Logement {

	private String idLogement;
	private double surfaceHabitable;
	private String dateAcquisition;
	private String type_logement;
	private int nbPieces;
	private int numEtage;
	

	public Logement(String idLogement,double surfaceHabitable, String dateAcquisition,String type_logement,int nbPieces, int numEtage) {
		this.idLogement = idLogement;
		this.surfaceHabitable=surfaceHabitable;
		this.dateAcquisition = dateAcquisition;
		this.type_logement = type_logement;
		this.nbPieces = nbPieces;
		this.numEtage = numEtage;
	}


	public String getIdLogement() {
		return idLogement;
	}


	public void setIdLogement(String idLogement) {
		this.idLogement = idLogement;
	}


	public double getSurfaceHabitable() {
		return surfaceHabitable;
	}


	public void setSurfaceHabitable(double surfaceHabitable) {
		this.surfaceHabitable = surfaceHabitable;
	}


	public String getDateAcquisition() {
		return dateAcquisition;
	}


	public void setDateAcquisition(String dateAcquisition) {
		this.dateAcquisition = dateAcquisition;
	}


	public String getType_logement() {
		return type_logement;
	}


	public void setType_logement(String type_logement) {
		this.type_logement = type_logement;
	}


	public int getNbPieces() {
		return nbPieces;
	}


	public void setNbPieces(int nbPieces) {
		this.nbPieces = nbPieces;
	}


	public int getNumEtage() {
		return numEtage;
	}


	public void setNumEtage(int numEtage) {
		this.numEtage = numEtage;
	}


	

}