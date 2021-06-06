package backend.Materiel;

import java.time.LocalDate;

/**
 * Class Voiture
 */
public class Voiture extends Vehicule {

	private Integer nombrePlaces;

		
	/**
	 * Constructeur de voiture
	 * @param quantite Quantite du materiel
     * @param modele Nom du modèle
     * @param achat Date d'achat
	 * @param kilometrages Kilométrage du vehicule
	 * @param marque Marque du vehicule
	 * @param puissance Puissance du vehicule
	 * @param etat Etat du vehicule
	 * @param vMax Vitesse maximale du vehicule
	 * @param nbRapports Nombre de rapports du vehicule
	 * @param kmRenouvellement Kilomètres de renouvellement du vehicule
	 * @param nombrePlaces Nombre de places de la voiture
	 */
	public Voiture(Integer quantite, String modele, LocalDate achat, Float kilometrages, String marque, Integer puissance,
			String etat, Integer vMax, Integer nbRapports, Integer kmRenouvellement, Integer nombrePlaces) {
		super(quantite, modele, achat, kilometrages, marque, puissance, etat, vMax, nbRapports, kmRenouvellement);
		this.nombrePlaces = nombrePlaces;
	}

	/**
	 * Set the value of nombrePlaces
	 * @param newVar the new value of nombrePlaces
	 */
	public void setNombrePlaces (Integer newVar) {
		nombrePlaces = newVar;
	}

	/**
	 * Get the value of nombrePlaces
	 * @return the value of nombrePlaces
	 */
	public Integer getNombrePlaces () {
		return nombrePlaces;
	}


}
