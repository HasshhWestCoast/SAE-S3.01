package Modele;

import java.util.Objects;

public class Logement {

	private String idLogement;
	private double surfaceHabitable;
	private String dateAcquisition;
	private String type_logement;
	private int nbPieces;
	private int numEtage;
	private int garage;
	private Bien bien;

	public Logement(String idLogement,double surfaceHabitable, String dateAcquisition,String type_logement,int nbPieces, int numEtage, int garage, Bien bien) {
		this.idLogement = idLogement;
		this.surfaceHabitable=surfaceHabitable;
		this.dateAcquisition = dateAcquisition;
		this.type_logement = type_logement;
		this.nbPieces = nbPieces;
		this.numEtage = numEtage;
		this.garage = garage;
		this.bien = bien;
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

	public int getGarage() {
		return garage;
	}


	public void setGarage(int garage) {
		this.garage = garage;
	}
	
	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idLogement);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Logement)) {
			return false;
		}
		Logement other = (Logement) obj;
		return Objects.equals(idLogement, other.idLogement);
	}

	@Override
	public String toString() {
		return "Logement [idLogement=" + idLogement + ", surfaceHabitable=" + surfaceHabitable + ", dateAcquisition="
				+ dateAcquisition + ", type_logement=" + type_logement + ", nbPieces=" + nbPieces + ", numEtage="
				+ numEtage +  "gargae="+garage + ", bien=" + "]";
	}


}