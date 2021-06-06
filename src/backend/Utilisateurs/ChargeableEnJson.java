package backend.Utilisateurs;
/**
 * Interface pour les Json
 */
public interface ChargeableEnJson {
    /**
     * Sauvegarde en Json
     * @param pathToData chemin ou enregistrer le Json
     */
    public void sauvegarde(String pathToData);
    /**
     * Supprime le Json
     * @param pathToData chemin vers le Json
     */
    public void supprimerSauvegarde(String pathToData);

}
