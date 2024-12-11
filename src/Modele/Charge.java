package Modele;

import java.util.ArrayList;
import java.util.List;

public class Charge {
    private int idCharge; // Identifiant unique
    private String nom; // Nom de la charge
    private double montantReel; // Montant réel de la charge
    private double montantPrevisionnel; // Montant prévisionnel de la charge
    private boolean deductible; // Charge déductible ou non
    private Compteur compteur; // Relation avec la classe Compteur

    // Liste des associations avec Louer via Retenir
    private List<Retenir> retenirList;

    // Constructeur avec paramètres
    public Charge(int idCharge, String nom, double montantReel, double montantPrevisionnel, boolean deductible, Compteur compteur) {
        this.idCharge = idCharge;
        this.nom = nom;
        this.montantReel = montantReel;
        this.montantPrevisionnel = montantPrevisionnel;
        this.deductible = deductible;
        this.compteur = compteur;
        this.retenirList = new ArrayList<>();
    }

    // Getters et Setters
    public int getIdCharge() {
        return idCharge;
    }

    public void setIdCharge(int idCharge) {
        this.idCharge = idCharge;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
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

    public boolean isDeductible() {
        return deductible;
    }

    public void setDeductible(boolean deductible) {
        this.deductible = deductible;
    }

    public Compteur getCompteur() {
        return compteur;
    }

    public void setCompteur(Compteur compteur) {
        this.compteur = compteur;
    }

    public List<Retenir> getRetenirList() {
        return retenirList;
    }

    public void setRetenirList(List<Retenir> retenirList) {
        this.retenirList = retenirList;
    }

    // Méthode pour ajouter une association
    public void addRetenir(Retenir retenir) {
        this.retenirList.add(retenir);
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Charge{" +
                "idCharge=" + idCharge +
                ", nom='" + nom + '\'' +
                ", montantReel=" + montantReel +
                ", montantPrevisionnel=" + montantPrevisionnel +
                ", deductible=" + deductible +
                ", compteur=" + (compteur != null ? compteur.getIdCompteur() : "null") +
                ", retenirList=" + retenirList.size() + " association(s)" +
                '}';
    }
}
