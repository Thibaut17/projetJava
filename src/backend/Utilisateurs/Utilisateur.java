package backend.Utilisateurs;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;

import com.google.gson.Gson;


/**
 * Class Utilisateur
 */
public class Utilisateur implements ChargeableEnJson, Serializable{

    private String nom;
    private String prenom;
    private String fonction;
    private String service;
    private String adresse;
    private Integer numeroBureau;
    private String numeroTelephone;

    
    /**
     * Constructeur d'utilisateur
     */
    public Utilisateur() {
    }
    /**
     * Constructeur d'utilisateur
     * @param nom Nom de l'utilisateur
     * @param prenom Prenom de l'utilisateur
     * @param fonction Fonction de l'utilisateur
     * @param service Service auquel l'utilisateur appartient
     * @param adresse Adresse de l'utilisateur
     * @param numeroBureau Numero de bureau de l'utilisateur
     * @param numeroTelephone Numero de telephone de l'utilisateur
     */
    public Utilisateur(String nom, String prenom, String fonction, String service, String adresse, Integer numeroBureau,
            String numeroTelephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.fonction = fonction;
        this.service = service;
        this.adresse = adresse;
        this.numeroBureau = numeroBureau;
        this.numeroTelephone = numeroTelephone;
    }

    /**
     * Set the value of nom
     * @param newVar the new value of nom
     */
    public void setNom (String newVar) {
            nom = newVar;
    }

    /**
     * Get the value of nom
     * @return the value of nom
     */
    public String getNom () {
            return nom;
    }

    /**
     * Set the value of prenom
     * @param newVar the new value of prenom
     */
    public void setPrenom (String newVar) {
            prenom = newVar;
    }

    /**
     * Get the value of prenom
     * @return the value of prenom
     */
    public String getPrenom () {
            return prenom;
    }

    /**
     * Set the value of fonction
     * @param newVar the new value of fonction
     */
    public void setFonction (String newVar) {
            fonction = newVar;
    }

    /**
     * Get the value of fonction
     * @return the value of fonction
     */
    public String getFonction () {
            return fonction;
    }

    /**
     * Set the value of service
     * @param newVar the new value of service
     */
    public void setService (String newVar) {
            service = newVar;
    }

    /**
     * Get the value of service
     * @return the value of service
     */
    public String getService () {
            return service;
    }

    /**
     * Set the value of adresse
     * @param newVar the new value of adresse
     */
    public void setAdresse (String newVar) {
            adresse = newVar;
    }

    /**
     * Get the value of adresse
     * @return the value of adresse
     */
    public String getAdresse () {
            return adresse;
    }

    /**
     * Set the value of numeroBureau
     * @param newVar the new value of numeroBureau
     */
    public void setNumeroBureau (Integer newVar) {
            numeroBureau = newVar;
    }

    /**
     * Get the value of numeroBureau
     * @return the value of numeroBureau
     */
    public Integer getNumeroBureau () {
            return numeroBureau;
    }

    /**
     * Set the value of numeroTelephone
     * @param newVar the new value of numeroTelephone
     */
    public void setNumeroTelephone (String newVar) {
            numeroTelephone = newVar;
    }

    /**
     * Get the value of numeroTelephone
     * @return the value of numeroTelephone
     */
    public String getNumeroTelephone () {
            return numeroTelephone;
    }

    /**
     * Sauvegarde l'utilisateur sous forme d'un fichier json dans ./data/users. Si le dossier users n'existe pas, il est créé.
     */
    public void sauvegarde(String pathToData) {

        // Serialization de l'utilisateur en json
        Gson gson = new Gson();
        String json = gson.toJson(this);

        // Verifier si le dossier users existe
        File usersDir = new File(pathToData + "/users");
        if (!usersDir.exists()){
            usersDir.mkdirs();
        }

        // Sauvegarde dans le fichier
        try {
            FileWriter fw = new FileWriter (pathToData + "/users/" + prenom + nom + ".json");
            fw.write(json);
            fw.close();
        } catch (Exception e) {
            System.err.println("Erreur d'écriture :");
            e.printStackTrace();
        }
        
    }

    /**
     * Supprime le fichier json de l'utilisateur
     */
    public void supprimerSauvegarde(String pathToData) {

        try {

            File f = new File(pathToData + "/users/" + prenom + nom + ".json");
            if (!f.delete()) {
                System.err.println("Le fichier " + System.getProperty("user.dir") + "/data/users/" + prenom + nom + ".json" + " n'a pas été supprimé");
            }
            

        } catch (Exception e) {
            System.err.println("Erreur de suppression :");
            e.printStackTrace();
        }
    }
    /**
     * Donne le nom et prénom de l'utilisateur
     * @return une chaine de caractère avec le nom et prénom de l'utilisateur
     */
    public String fullName() {
            return prenom + " " + nom;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj instanceof Utilisateur) {
            Utilisateur autre = (Utilisateur)obj;
            res = (this.nom.equals(autre.nom) 
                && this.prenom.equals(autre.prenom) 
                && this.service.equals(autre.service) 
                && this.fonction.equals(autre.fonction)
                && this.adresse.equals(autre.adresse)
                && this.numeroBureau.equals(autre.numeroBureau)
                && this.numeroTelephone.equals(autre.numeroTelephone));
        }
        return res;
    }

}
