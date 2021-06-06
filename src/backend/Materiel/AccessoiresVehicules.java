package backend.Materiel;

import java.time.LocalDate;

/**
 * Class AccessoiresVehicules
 */
public class AccessoiresVehicules extends Materiel {

	/**
	 * Constructeur d'Accessoires Vehicules
     * @param quantite Quantite du materiel
     * @param modele Nom du modèle
     * @param achat Date d'achat
	 */
	public AccessoiresVehicules(Integer quantite, String modele, LocalDate achat) {
		super(quantite, modele, achat);
	}
}
