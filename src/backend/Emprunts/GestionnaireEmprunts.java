package backend.Emprunts;

import java.util.ArrayList;

import backend.Utilisateurs.Utilisateur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class GestionnaireEmprunts
 */
public class GestionnaireEmprunts implements Serializable{


    private ArrayList<Emprunt> emprunts;

    
    /**
     * Constructeur du gestionnaire d'emprunts
     */
    public GestionnaireEmprunts() {
        this.emprunts = new ArrayList<Emprunt>();
    }


    /**
     * Renvoie la liste des emprunts
     * @return Liste des emprunts
     */
    public ArrayList<Emprunt> getEmprunts() {
        return emprunts;
    }

    /**
     * Renvoie la liste des emprunts d'un utilisateur
     * @param utilisateur Utilisateur dont il faut renvoyer la liste
     * @return Liste des emprunts de l'utilisateur
     */
    public ArrayList<Emprunt> getEmprunts(Utilisateur utilisateur) {
        ArrayList<Emprunt> ret = new ArrayList<Emprunt> ();

        for (Emprunt e : emprunts) {
            
            if (e.getUtilisateur().equals(utilisateur)) ret.add(e);
        }
        return ret;
    }

    /**
     * Renvoie la liste des emprunts qui sont en cours par rapport à une date
     * @param date Date à laquelle comparer les dates
     * @return Liste des emprunts en cours
     */
    public ArrayList<Emprunt> getEmpruntsEnCours(LocalDate date) {
        ArrayList<Emprunt> ret = new ArrayList<Emprunt> ();

        for (Emprunt e : emprunts) {
            
            if (e.isEnCours(date)) ret.add(e);
        }
        return ret;
    }

    

    /**
     * Renvoie la liste des emprunts en cours d'un utilisateur par rapport à une date
     * @param date Date à laquelle comparer les dates
     * @param utilisateur Utilisateur dont il faut renvoyer la liste des retards
     * @return Liste des emprunts en cours de l'utilisateur
     */
    public ArrayList<Emprunt> getEmpruntsEnCours(LocalDate date, Utilisateur utilisateur) {
        ArrayList<Emprunt> ret = new ArrayList<Emprunt> ();

        for (Emprunt e : emprunts) {
            
            if (e.isEnCours(date) && e.getUtilisateur().equals(utilisateur)) ret.add(e);
        }
        return ret;
    }

    /**
     * Renvoie la liste des emprunts qui sont prévus
     * @return Liste des emprunts en cours
     */
    public ArrayList<Emprunt> getEmpruntsPrevus() {
        ArrayList<Emprunt> ret = new ArrayList<Emprunt> ();

        for (Emprunt e : emprunts) {
            
            if (!e.isTermine()) ret.add(e);
        }
        return ret;
    }

    /**
     * Renvoie la liste des emprunts prévus d'un utilisateur
     * @param utilisateur Utilisateur dont il faut renvoyer la liste des retards
     * @return Liste des emprunts en cours de l'utilisateur
     */
    public ArrayList<Emprunt> getEmpruntsPrevus(Utilisateur utilisateur) {
        ArrayList<Emprunt> ret = new ArrayList<Emprunt> ();

        for (Emprunt e : emprunts) {
            
            if (!e.isTermine() && e.getUtilisateur().equals(utilisateur)) ret.add(e);
        }
        return ret;
    }

    

    /**
     * Renvoie la liste des emprunts dont la date limite est dépassée par rapport à une date
     * @param date Date à laquelle comparer les dates limites
     * @return Liste des emprunts dont la date limite est dépassée
     */
    public ArrayList<Emprunt> getEmpruntsEnRetard(LocalDate date) {
        ArrayList<Emprunt> ret = new ArrayList<Emprunt> ();

        for (Emprunt e : emprunts) {
            
            if (e.isEnRetard(date)) ret.add(e);
        }
        return ret;
    }

    /**
     * Renvoie la liste des emprunts d'un utilisateur dont la date limite est dépassée par rapport à une date
     * @param date Date à laquelle comparer les dates limites
     * @param utilisateur Utilisateur dont il faut renvoyer la liste des retards
     * @return Liste des emprunts de l'utilisateur dont la date limite est dépassée
     */
    public ArrayList<Emprunt> getEmpruntsEnRetard(LocalDate date, Utilisateur utilisateur) {
        ArrayList<Emprunt> ret = new ArrayList<Emprunt> ();

        for (Emprunt e : emprunts) {
            
            if (e.isEnRetard(date) && e.getUtilisateur().equals(utilisateur)) ret.add(e);
        }
        return ret;
    }

    /**
     * Renvoie la liste des utilisateurs ayant un emprunt en cours
     * @return Liste des utilisateurs ayant un emprunt en cours
     */
    public ArrayList<Utilisateur> getUtilisateurs() {
        ArrayList<Utilisateur> ret = new ArrayList<Utilisateur> ();

        for (Emprunt e : emprunts) {
            
            if (!ret.contains(e.getUtilisateur()) && !e.isTermine()) ret.add(e.getUtilisateur());
        }
        return ret;
    }

    /**
     * Renvoie la liste des utilisateurs ayant un emprunt en cours
     * @param date date à laquelle comparer la date de fin des emprunts
     * @return Liste des utilisateurs ayant un emprunt en cours
     */
    public ArrayList<Utilisateur> getUtilisateursEnCours(LocalDate date) {
        ArrayList<Utilisateur> ret = new ArrayList<Utilisateur> ();

        for (Emprunt e : emprunts) {
            
            if (!ret.contains(e.getUtilisateur()) && e.isEnCours(date)) ret.add(e.getUtilisateur());
        }
        return ret;
    }

    /**
     * Renvoie la liste des utilisateurs ayant un emprunt en retard
     * @param date date à laquelle comparer la date de fin des emprunts
     * @return Liste des utilisateurs ayant un emprunt en retard
     */
    public ArrayList<Utilisateur> getUtilisateursEnRetard(LocalDate date) {
        ArrayList<Utilisateur> ret = new ArrayList<Utilisateur> ();

        for (Emprunt e : emprunts) {
            
            if (!ret.contains(e.getUtilisateur()) && e.isEnRetard(date)) ret.add(e.getUtilisateur());
        }
        return ret;
    }

    /**
     * Vérifie si un emprunt peut etre ajouté au gestionnaire
     * @param emprunt Emprunt a ajouter
     * @return renvoi vrai si l'emprunt est ajoutable
     */
    public boolean ajoutPossible(Emprunt emprunt) {
        boolean res = true;
        int i = 0;
        while (res && i < emprunts.size()) {
            if (!emprunt.isCompatible(emprunts.get(i)) && !emprunt.equals(emprunts.get(i))) res = false;
            i++;
        }

        return res;
    }

    /**
     * Ajoute un emprunt au gestionnaire en vérifiant d'abord si il peut etre ajouté
     * @param emprunt Emprunt à ajouter
     * @throws EmpruntImpossibleException exception
     */
    public void addEmprunt(Emprunt emprunt) throws EmpruntImpossibleException {

        // Vérifier si l'emprunt est possible
        for (Emprunt emp : emprunts) {
            if (!emprunt.isCompatible(emp) && !emprunt.equals(emp)) throw new EmpruntImpossibleException("Materiel non disponible", emp);
        }

        emprunts.add(emprunt);
    }

    /**
     * Supprime un emprunt du gestionnaire
     * @param emprunt Emprunt à supprimer
     */
    public void endEmprunt(Emprunt emprunt) {
        emprunt.setTermine();
    }

    /**
     * Sauvegarde le gestionnaire de materiel dans un ficher .data au chemin indiqué
     * @param pathToData Chemin du dossier qui va contenir le fichier .data
     */
    public void sauvegarde(String pathToData) {
        
        try {

            // Si le dossier data n'existe pas, en crée un nouveau
            File dataDir = new File(pathToData);
            if (!dataDir.exists()){
                dataDir.mkdirs();
            }

            // Sérialiser le gestionnaire dans le fichier
			FileOutputStream fichier = new FileOutputStream(pathToData + "/" + "gestionnaireEmprunts.data");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * Charge en mémoire le gestionnaire de materiel stocké. Si il n'y en a pas, en cree un nouveau.
     * @param pathToData Chemin vers le dossier du .data
     * @return gestionnaire de materiel chargé
     */
    public static GestionnaireEmprunts charger(String pathToData) {
        GestionnaireEmprunts res = null;
        try {

            // Si le dossier data n'existe pas, en crée un nouveau
            File dataDir = new File(pathToData);
            if (!dataDir.exists()){
                dataDir.mkdirs();
            }

            // Si le gestionnaire n'existe pas, en crée un nouveau
            File usersDir = new File(pathToData + "/" + "gestionnaireEmprunts.data");
            if (!usersDir.exists()){
                usersDir.createNewFile();
                GestionnaireEmprunts ge = new GestionnaireEmprunts();
                ge.sauvegarde(pathToData);
            }

            // Charger le gestionnaire
			FileInputStream fichier = new FileInputStream(pathToData + "/" + "gestionnaireEmprunts.data");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			res = (GestionnaireEmprunts)ois.readObject();
			ois.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return res;
    }

    /**
     * Renvoie la liste des utilisateurs ayant un emprunt en cours
     * @return Liste des utilisateurs ayant un emprunt en cours
     * @param nom Nom de l'utilisateur
     * @param prenom Prénom de l'utilisateur
     */
    public ArrayList<Utilisateur> getUtilisateurs(String nom, String prenom) {
        ArrayList<Utilisateur> ret = new ArrayList<Utilisateur> ();

        for (Emprunt e : emprunts) {
            
            if (!ret.contains(e.getUtilisateur()) && (e.getUtilisateur().getNom().equals(nom) || e.getUtilisateur().getPrenom().equals(prenom))) ret.add(e.getUtilisateur());
        }
        return ret;
    }

    /**
     * Renvoie l'emprunt dont le materiel est materiel et qui commence à la date date
     * @param materiel nom du modèle
     * @param date date de debut de l'emprunt
     * @return Emprunt souhaité
     */
    public Emprunt getEmprunt(String materiel, LocalDate date) {
        Emprunt ret = new Emprunt();
        boolean trouve = false;

        for (Emprunt e : emprunts) {
            
            if (!trouve && e.getMateriel().getModele().equals(materiel) && e.getDebut().equals(date)) ret = e;
        }
        return ret;
    }

    /**
     * Mise à jour des dates d'une emprunt. Si la date n'est pas compatible avec les autres emprunts, renvoie une exception
     * @param emprunt Emprunt à modifier
     * @param nouveauDebut Nouvelle date de début
     * @param nouveauFin Nouvelle date de fin
     * @throws EmpruntImpossibleException Exception levée lors ce que les dates ne sont pas compatibles avec les autres emprunts
     */
    public void updateEmprunt (Emprunt emprunt, LocalDate nouveauDebut, LocalDate nouveauFin) throws EmpruntImpossibleException {
        
        // Enlever l'emprunt du gestionaire
        emprunts.remove(emprunt);

        // Tenter d'ajouter le nouvel emprunt
        Emprunt nouveauEmprunt = new Emprunt(emprunt);
        nouveauEmprunt.setDebut(nouveauDebut);
        nouveauEmprunt.setFin(nouveauFin);
        try {
            this.addEmprunt(nouveauEmprunt);
        } catch (EmpruntImpossibleException e) {
            // Remettre l'emprunt original dans le gestionnaire
            this.addEmprunt(emprunt);
            throw e;
        }
    }

}
