package backend.Emprunts;

import backend.Utilisateurs.Utilisateur;

import backend.Materiel.Materiel;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class Emprunt
 */
public class Emprunt implements Serializable{

    private Materiel materiel;
    private LocalDate debut;
    private LocalDate fin;
    private Integer quantite;
    private Utilisateur utilisateur;
    private boolean termine;

    /**
     * Constructeur
     */
    public Emprunt() {
    }

    /**
     * Constructeur d'emprunt à partir de materiel
     * @param materiel Materiel emprunté
     */
    public Emprunt(Materiel materiel) {
        this.termine = false;
        this.materiel = materiel;
    }

    /**
     * Constructeur d'emprunt
     * @param materiel Materiel emprunté
     * @param debut Date de début de l'emprunt
     * @param fin Date de fin de l'emprunt
     * @param quantite Nombre d'unité de materiel emprunté
     * @param utilisateur Utilisateur qui emprunte le materiel
     */
    public Emprunt(Materiel materiel, LocalDate debut, LocalDate fin, Integer quantite, Utilisateur utilisateur) {
        this.materiel = materiel;
        this.debut = debut;
        this.fin = fin;
        this.quantite = quantite;
        this.utilisateur = utilisateur;
        this.termine = false;
    }

    /**
     * Constructeur d'emprunt à partir d'un autre emprunt
     * @param emprunt Emprunt de départ
     */
    public Emprunt(Emprunt emprunt) {
        this.materiel = emprunt.materiel;
        this.debut = emprunt.debut;
        this.fin = emprunt.fin;
        this.quantite = emprunt.quantite;
        this.utilisateur = emprunt.utilisateur;
        this.termine = emprunt.termine;
    }

    /**
     * Set the value of materiel
     * @param newVar the new value of materiel
     */
    public void setMateriel (Materiel newVar) {
            materiel = newVar;
    }

    /**
     * Get the value of materiel
     * @return the value of materiel
     */
    public Materiel getMateriel () {
            return materiel;
    }

    /**
     * Set the value of debut
     * @param newVar the new value of debut
     */
    public void setDebut (LocalDate newVar) {
            debut = newVar;
    }

    /**
     * Get the value of debut
     * @return the value of debut
     */
    public LocalDate getDebut () {
            return debut;
    }

    /**
     * Set the value of fin
     * @param newVar the new value of fin
     */
    public void setFin (LocalDate newVar) {
            fin = newVar;
    }

    /**
     * Get the value of fin
     * @return the value of fin
     */
    public LocalDate getFin () {
            return fin;
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
     * Set the value of utilisateur
     * @param newVar the new value of utilisateur
     */
    public void setUtilisateur (Utilisateur newVar) {
            utilisateur = newVar;
    }

    /**
     * Get the value of utilisateur
     * @return the value of utilisateur
     */
    public Utilisateur getUtilisateur () {
            return utilisateur;
    }

    /**
     * Évalue si l'emprunt est en retard ou non
     * @param date Date à laquelle comparer la date de fin (date d'aujourd'hui)
     * @return Vrai si l'emprunt est en retard
     */
    public boolean isEnRetard(LocalDate date) {
        //return this.getFin().isAfter(date);
        return date.isAfter(this.getFin());
    }

    /**
     * Évalue si l'emprunt est en cours ou non
     * @param date Date à laquelle comparer la date de fin (date d'aujourd'hui)
     * @return Vrai si l'emprunt est en cours
     */
    public boolean isEnCours(LocalDate date) {
        return isDateInside(date, debut, fin);

    
    }

    /**
     * Evalue si l'emprunt est terminé ou non
     * @return renvoi true si l'emprunt est terminé
     */
    public boolean isTermine () {
        return termine;
    }

    /**
     * Termine un emprunt
    */
    public void setTermine() {
        termine = true;
    }
    /**
     * Vérifie si l'emprunt est possible et qu'il ne chevauche pas les dates de e
     * @param e emprunt
     * @return booléen permettant de savoir si l'emprunt est compatible avec l'emprunt e
     */
    public boolean isCompatible(Emprunt e) {
        boolean res = true;
        if (materiel.equals(e.materiel) && !e.isTermine() 
        && (isDateInside(debut, e.debut, e.fin) || isDateInside(fin, e.debut, e.fin) || (isDateInside(e.debut, debut, fin) && isDateInside(e.fin, debut, fin)))) {
            res = false;
        }

        return res;
    }
    /**
     * Détermine si la date middle est comprise entre le début et la fin
     * @param middle date à vérifier
     * @param debut date de début de l'intervalle
     * @param fin date de fin de l'intervalle
     * @return renvoi vrai si debut est comprit entre debut et fin
     */
    private static boolean isDateInside(LocalDate middle, LocalDate debut, LocalDate fin) {
        return middle.isAfter(debut) && middle.isBefore(fin);
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj instanceof Emprunt) {
            Emprunt emp = (Emprunt)obj;
            res = (this.materiel.equals(emp.materiel) 
                && this.debut.isEqual(emp.debut) 
                && this.fin.isEqual(emp.fin) 
                && this.quantite.equals(emp.quantite)
                && this.utilisateur.equals(emp.utilisateur)
                && this.termine == emp.termine);
        }
        return res;
    }

    @Override
    public String toString() {
        return "Emprunt de " + materiel.getModele() + " du " + debut.toString() + " au " + fin.toString() + " par " + utilisateur.fullName();
    }

}
