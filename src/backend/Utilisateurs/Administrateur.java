package backend.Utilisateurs;

/**
 * Class Administrateur
 */
public class Administrateur extends Utilisateur {

    private String login;
    private String password;
    /**
     * Constructeur pour créer l'administrateur
     * @param nom nom de l'administrateur
     * @param prenom prenom de l'administrateur
     * @param fonction fonction de l'administrateur
     * @param service service de l'administrateur
     * @param adresse adresse de l'administrateur
     * @param numeroBureau numero de bureau de l'administrateur
     * @param numeroTelephone numero de téléphone de l'administrateur
     * @param login login de l'administrateur
     * @param password mot de passe de l'administrateur
     */
    public Administrateur (String nom, String prenom, String fonction, String service, String adresse, Integer numeroBureau, String numeroTelephone, String login, String password) {
        super(nom, prenom, fonction, service,  adresse, numeroBureau, numeroTelephone);
        this.login = login;
        this.password = password;

    };


    /**
     * Set the value of login
     * @param newVar the new value of login
     */
    public void setLogin (String newVar) {
        login = newVar;
    }

    /**
     * Get the value of login
     * @return the value of login
     */
    public String getLogin () {
        return login;
    }

    /**
     * Set the value of password
     * @param newVar the new value of password
     */
    public void setPassword (String newVar) {
        password = newVar;
    }

    /**
     * Get the value of password
     * @return the value of password
     */
    public String getPassword () {
        return password;
    }


}
