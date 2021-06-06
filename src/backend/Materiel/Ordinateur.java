package backend.Materiel;

import java.time.LocalDate;


/**
 * Class Ordinateur
 */
public class Ordinateur extends Materiel {

    private Integer identifiant;
    private String cpu;
    private String gpu;
    private String type;
    private Float tailleEcran;
    private LocalDate renouvellement;

    

        /**
         * Constructeur d'ordinateur
         * @param quantite Quantite du materiel
         * @param modele Nom du modèle
         * @param achat Date d'achat
         * @param identifiant identifiant de l'ordinateur
         * @param cpu Nom du processeur
         * @param gpu Nom du processeur graphique
         * @param type type de l'ordinateur : fixe ou portable
         * @param tailleEcran Taille de l'écran
         * @param renouvellement Date de renouvellement
         */
    public Ordinateur(Integer quantite, String modele, LocalDate achat, Integer identifiant, String cpu, String gpu,
            String type, Float tailleEcran, LocalDate renouvellement) {
        super(quantite, modele, achat);
        this.identifiant = identifiant;
        this.cpu = cpu;
        this.gpu = gpu;
        this.type = type;
        this.tailleEcran = tailleEcran;
        this.renouvellement = renouvellement;
    }

    /**
     * Set the value of identifiant
     * @param newVar the new value of identifiant
     */
    public void setIdentifiant (Integer newVar) {
            identifiant = newVar;
    }

    /**
     * Get the value of identifiant
     * @return the value of identifiant
     */
    public Integer getIdentifiant () {
            return identifiant;
    }

    /**
     * Set the value of cpu
     * @param newVar the new value of cpu
     */
    public void setCpu (String newVar) {
            cpu = newVar;
    }

    /**
     * Get the value of cpu
     * @return the value of cpu
     */
    public String getCpu () {
            return cpu;
    }

    /**
     * Set the value of gpu
     * @param newVar the new value of gpu
     */
    public void setGpu (String newVar) {
            gpu = newVar;
    }

    /**
     * Get the value of gpu
     * @return the value of gpu
     */
    public String getGpu () {
            return gpu;
    }

    /**
     * Set the value of type
     * @param newVar the new value of type
     */
    public void setType (String newVar) {
            type = newVar;
    }

    /**
     * Get the value of type
     * @return the value of type
     */
    public String getType () {
            return type;
    }

    /**
     * Set the value of tailleEcran
     * @param newVar the new value of tailleEcran
     */
    public void setTailleEcran (Float newVar) {
            tailleEcran = newVar;
    }

    /**
     * Get the value of tailleEcran
     * @return the value of tailleEcran
     */
    public Float getTailleEcran () {
            return tailleEcran;
    }

    /**
     * Set the value of renouvellement
     * @param newVar the new value of renouvellement
     */
    public void setRenouvellement (LocalDate newVar) {
            renouvellement = newVar;
    }

    /**
     * Get the value of renouvellement
     * @return the value of renouvellement
     */
    public LocalDate getRenouvellement () {
            return renouvellement;
    }


}
