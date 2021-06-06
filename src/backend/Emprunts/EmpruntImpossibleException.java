package backend.Emprunts;
/**
 * Classe pour les emprunts chevauchant
 */
public class EmpruntImpossibleException extends java.lang.Exception{

    Emprunt empruntChevauchant;
    /**
     * Constructeur : lève une exception
     * @param message message à afficher
     * @param emprunt emprunt levant l'exception
     */
    public EmpruntImpossibleException(String message, Emprunt emprunt) {
        super(message);
        empruntChevauchant = emprunt;
    }

    /**
     * Getter d'emprunt chevauchant
     * @return L'emprunt qui chevauche l'emprunt que l'on essai d'ajouter
     */
    public Emprunt getEmpruntChevauchant() {
        return empruntChevauchant;
    }
    
}
