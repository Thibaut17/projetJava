package backend.Utilisateurs;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * Class GestionnaireUtilisateur
 */
public class GestionnaireUtilisateur {
    private ArrayList<Utilisateur> listeUtilisateur;

    /**
     * Constructeur du gestionnaire de materiel
     */
    public GestionnaireUtilisateur() {
        this.listeUtilisateur = new ArrayList<Utilisateur>();
    }

    /**
     * Constructeur du gestionnaire de materiel avec chargement des utilisateur
     * @param pathToUsers Chemin du dossier des fichiers de sauvegarde des utilisateurs
     */
    public GestionnaireUtilisateur(String pathToUsers) {
        this.listeUtilisateur = new ArrayList<Utilisateur>();
        this.chargerUtilisateurs(pathToUsers);
    }


    /**
     * Ajoute un utilisateur au gestionnaire
     * @param utilisateur Utilisateur à ajouter
     */
    public void addUtilisateur(Utilisateur utilisateur) {
        listeUtilisateur.add(utilisateur);
    }

    /**
     * Supprime un utilisateur du gestionnaire
     * @param utilisateur Utilisateur à supprimer
     */
    public void delUtilisateur(Utilisateur utilisateur) {
        listeUtilisateur.remove(utilisateur);
    }

    /**
     * Revoie la liste des utilisateurs du gestionnaire
     * @return renvoi la liste des utilisateurs
     */
    public ArrayList<Utilisateur> getListe() {
        return listeUtilisateur;
    }

    /**
     * Charge les utilisateurs sauvegardés dans le tableau d'utilisateurs
     * @param pathToData Chemin du dossier des utilisateur dans le dossier local
     */
    private void chargerUtilisateurs(String pathToData) {
        File dossier = new File(pathToData + "/" + "users");
        if (!dossier.exists()){
            dossier.mkdirs();
        }
        String[] nomFichiers = dossier.list();
        Gson gson = new Gson();
        for (String nomFichier : nomFichiers) {
            try {

                FileReader fr = new FileReader(pathToData + "/users/" + nomFichier);
                
                char tableauChar[] = new char[2048];
                int n = fr.read(tableauChar);

                String chaine = new String(tableauChar);
                chaine = chaine.substring(0, n);
                if (chaine.charAt(2) == 'l') {
                    listeUtilisateur.add(gson.fromJson(chaine, Administrateur.class));
                } else {
                    listeUtilisateur.add(gson.fromJson(chaine, Utilisateur.class));
                }
                fr.close();

            } catch (Exception e) {
                System.err.println("Erreur de lecture :");
                e.printStackTrace();
            }
            
        }
    }

    /**
     * Recherche les utilisateurs à partir de leur noms et prénoms.
     * @param nom Nom à rechercher
     * @param prenom Prénom à rechercher
     * @return Liste des utilisateur ayant soit le nom, soit le prenom donné
     */
    public ArrayList<Utilisateur> rechercheUtilisateur(String nom, String prenom) {
        ArrayList<Utilisateur> res = new ArrayList<Utilisateur>();

        for (Utilisateur utilisateur : listeUtilisateur) {
            if (utilisateur.getNom().equals(nom) || utilisateur.getPrenom().equals(prenom)) {
                res.add(utilisateur);
            }
        }

        return res;
    }

    /**
     * Recherche les utilisateurs à partir de leur noms et prénoms.
     * @param nom Nom à rechercher
     * @param prenom Prénom à rechercher
     * @return Utilisateur ayant le nom et le prenom
     */
    public Utilisateur rechercheUtilisateurStricte(String nom, String prenom) {
        Utilisateur res = new Utilisateur();

        for (Utilisateur utilisateur : listeUtilisateur) {
            if (utilisateur.getNom().equals(nom) && utilisateur.getPrenom().equals(prenom)) {
                res = utilisateur;
            }
        }

        return res;
    }


}
