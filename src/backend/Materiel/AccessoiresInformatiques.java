package backend.Materiel;

import java.time.LocalDate;


/**
 * Class AccessoiresInformatiques
 */
public class AccessoiresInformatiques extends Materiel {

	/**
	 * Constructeur d'Accessoires Informatique
     * @param quantite Quantite du materiel
     * @param modele Nom du modèle
     * @param achat Date d'achat
	 */
	public AccessoiresInformatiques(Integer quantite, String modele, LocalDate achat) {
		super(quantite, modele, achat);
	}

}
