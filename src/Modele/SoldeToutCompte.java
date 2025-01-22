package Modele;

public class SoldeToutCompte {
    private double provisionSurCharges;
    private double chargeReelles;
    private double caution;
    private double travauxImputables;
    private double restantLoyer;
    private double reste;

    // Constructeur
    public SoldeToutCompte(double provisionSurCharges, double chargeReelles, double caution,
                           double travauxImputables, double restantLoyer, double reste) {
        this.provisionSurCharges = provisionSurCharges;
        this.chargeReelles = chargeReelles;
        this.caution = caution;
        this.travauxImputables = travauxImputables;
        this.restantLoyer = restantLoyer;
        this.reste = reste;
    }

    // Getters et setters
    public double getProvisionSurCharges() {
        return provisionSurCharges;
    }

    public void setProvisionSurCharges(double provisionSurCharges) {
        this.provisionSurCharges = provisionSurCharges;
    }

    public double getChargeReelles() {
        return chargeReelles;
    }

    public void setChargeReelles(double chargeReelles) {
        this.chargeReelles = chargeReelles;
    }

    public double getCaution() {
        return caution;
    }

    public void setCaution(double caution) {
        this.caution = caution;
    }

    public double getTravauxImputables() {
        return travauxImputables;
    }

    public void setTravauxImputables(double travauxImputables) {
        this.travauxImputables = travauxImputables;
    }

    public double getRestantLoyer() {
        return restantLoyer;
    }

    public void setRestantLoyer(double restantLoyer) {
        this.restantLoyer = restantLoyer;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }
}
