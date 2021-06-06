package backend.Materiel;

import java.io.Serializable;

import java.time.LocalDate;

/**
 * Class Materiel
 */
abstract public class Materiel implements Serializable{

    private Integer quantite;
    private String modele;
    private LocalDate achat;

    
    /**
     * Constructeur de materiel
     * @param quantite Quantite du materiel
     * @param modele Nom du modèle
     * @param achat Date d'achat
     */
    public Materiel(Integer quantite, String modele, LocalDate achat) {
        this.quantite = quantite;
        this.modele = modele;
        this.achat = achat;
    }

    /**
     * Set the value of quantite
     * @param newVar the new value of quantite
     */
    public void setQuantite (Integer newVar) {
            quantite = newVar;
    }

    /**
     * Get the value of quantite
     * @return the value of quantite
     */
    public Integer getQuantite () {
            return quantite;
    }

    /**
     * Set the value of modele
     * @param newVar the new value of modele
     */
    public void setModele (String newVar) {
            modele = newVar;
    }

    /**
     * Get the value of modele
     * @return the value of modele
     */
    public String getModele () {
            return modele;
    }

    /**
     * Set the value of achat
     * @param newVar the new value of achat
     */
    public void setAchat (LocalDate newVar) {
            achat = newVar;
    }

    /**
     * Get the value of achat
     * @return the value of achat
     */
    public LocalDate getAchat () {
            return achat;
    }
    /**
     * Vérifie si le matériel est le même que m
     * @param m matériel à comparer
     * @return true si le matériel et m sont identiques
     */
    public boolean equals(Materiel m) {
            return achat.equals(m.getAchat()) && modele.equals(m.getModele());
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj instanceof Materiel) {
            Materiel emp = (Materiel)obj;
            res = (this.modele.equals(emp.modele) 
                && this.achat.isEqual(emp.achat) 
                && this.quantite.equals(emp.quantite)
                && this.quantite.equals(emp.quantite));
        }
        return res;
    }


}
