package Modele;

import java.util.ArrayList;
import java.util.List;

public class Charge {
    private String idCharge; 
    private String nom; 
    private double montantReel; 
    private String dateCharge;
    private double montantPrevisionnel;
    private int deductible; // C'EST QUOI CA 
    private Bien bien; 

    private List<Retenir> retenirList;

    public Charge(String idCharge, String nom, double montantReel, double montantPrevisionnel, String dateCharge, int deductible, Bien bien) {
        this.idCharge = idCharge;
        this.nom = nom;
        this.montantReel = montantReel;
        this.dateCharge = dateCharge;
        this.montantPrevisionnel = montantPrevisionnel;
        this.deductible = deductible;
        this.bien = bien;
        this.retenirList = new ArrayList<>();
    }

    public String getIdCharge() {
        return idCharge;
    }

    public void setIdCharge(String idCharge) {
        this.idCharge = idCharge;
    }

    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public double getMontantReel() {
        return montantReel;
    }

    public void setMontantReel(double montantReel) {
        this.montantReel = montantReel;
    }
    
    
    public double getMontantPrevisionnel() {
        return montantPrevisionnel;
    }

    public void setMontantPrevisionnel(double montantPrevisionnel) {
        this.montantPrevisionnel = montantPrevisionnel;
    }
	
    
    public int isDeductible() {
        return deductible;
    }

    public void setDeductible(int deductible) {
        this.deductible = deductible;
    }

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}
	
    public List<Retenir> getRetenirList() {
        return retenirList;
    }

    public void setRetenirList(List<Retenir> retenirList) {
        this.retenirList = retenirList;
    }

    public void addRetenir(Retenir retenir) {
        this.retenirList.add(retenir);
    }

	public String getDateCharge() {
		return dateCharge;
	}

	public void setDateCharge(String dateCharge) {
		this.dateCharge = dateCharge;
	}

}
