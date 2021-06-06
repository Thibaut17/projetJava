package backend.Materiel;
import java.util.ArrayList;
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
 * Class GestionnaireMateriel
 */
public class GestionnaireMateriel implements Serializable{

    private ArrayList<Materiel> listeMateriel;

    /**
     * Constructeur du gestionnaire de materiel
     */
    public GestionnaireMateriel() {
        this.listeMateriel = new ArrayList<Materiel>();
    }


    /**
     * Ajoute un materiel au gestionnaire
     * @param materiel Materiel à ajouter
     */
    public void addMateriel(Materiel materiel) {
        listeMateriel.add(materiel);
    }

    /**
     * Supprime un materiel du gestionnaire
     * @param materiel Materiel à supprimer
     */
    public void delMateriel(Materiel materiel) {
        listeMateriel.remove(materiel);
    }

    /**
     * Renvoie la liste complete du materiel de l'entreprise
     * @return liste complete du materiel de l'entreprise
     */
    public ArrayList<Materiel> getListeMateriel() {
        return listeMateriel;
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
			FileOutputStream fichier = new FileOutputStream(pathToData + "/" + "gestionnaireMateriel.data");
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
    public static GestionnaireMateriel charger(String pathToData) {
        GestionnaireMateriel res = null;
        try {

            // Si le dossier data n'existe pas, en crée un nouveau
            File dataDir = new File(pathToData);
            if (!dataDir.exists()){
                dataDir.mkdirs();
            }

            // Si le gestionnaire n'existe pas, en crée un nouveau
            File dataFile = new File(pathToData + "/" + "gestionnaireMateriel.data");
            if (!dataFile.exists()){
                dataFile.createNewFile();
                GestionnaireMateriel gm = new GestionnaireMateriel();
                gm.sauvegarde(pathToData);
            }

            // Charger le gestionnaire
			FileInputStream fichier = new FileInputStream(pathToData + "/" + "gestionnaireMateriel.data");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			res = (GestionnaireMateriel)ois.readObject();
			ois.close();

		} catch (FileNotFoundException e) {
			GestionnaireMateriel gm = new GestionnaireMateriel();
            gm.sauvegarde(pathToData);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return res;
    }
    
    /**
     * Recherche les matériels à partir de leur modèle quantité et date d'achat.
     * @param modele modele à rechercher
     * @param quantite quantite à rechercher
     * @param date date à rechercher
     * @return Matériel ayant le modèle, la quantité et la date d'achat
     */
    public Integer rechercheMaterielStricte(String modele, Integer quantite, LocalDate date) {
        Integer res = 0;
        boolean trouve =false;
        for (Materiel materiel : listeMateriel) {
            if (!(materiel.getModele().equals(modele) && materiel.getQuantite()==quantite && materiel.getAchat().equals(date))&& !trouve) {
                res ++;
            }else{
                trouve =true;
            }
        }

        return res;
    }

    /**
     * Recherche les matériels à partir de leur modèle quantité et date d'achat.
     * @param modele modele à rechercher
     * @return numero du Matériel ayant le modèle dans listeMateriel
     */
    public Integer rechercheMaterielStricte(String modele) {
        Integer res = 0;
        boolean trouve =false;
        for (Materiel materiel : listeMateriel) {
            if (!(materiel.getModele().equals(modele)) && !trouve) {
                res ++;
            }else{
                trouve =true;
            }
        }

        return res;
    }
}
