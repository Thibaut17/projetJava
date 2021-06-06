package backend.Materiel;

import java.time.LocalDate;

/**
 * Class Vehicules
 */
public class Vehicule extends Materiel {

	private Float kilometrages;
	private String marque;
	private Integer puissance;
	private String etat;
	private Integer vMax;
	private Integer nbRapports;
	private Integer kmRenouvellement;

    

	/**
	 * Constructeur de vehicule
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
	 */
	public Vehicule(Integer quantite, String modele, LocalDate achat, Float kilometrages, String marque, Integer puissance,
			String etat, Integer vMax, Integer nbRapports, Integer kmRenouvellement) {
		super(quantite, modele, achat);
		this.kilometrages = kilometrages;
		this.marque = marque;
		this.puissance = puissance;
		this.etat = etat;
		this.vMax = vMax;
		this.nbRapports = nbRapports;
		this.kmRenouvellement = kmRenouvellement;
	}

	/**
	 * Set the value of kilometrages
	 * @param newVar the new value of kilometrages
	 */
	public void setKilometrages (Float newVar) {
		kilometrages = newVar;
	}

	/**
	 * Get the value of kilometrages
	 * @return the value of kilometrages
	 */
	public Float getKilometrages () {
		return kilometrages;
	}

	/**
	 * Set the value of marque
	 * @param newVar the new value of marque
	 */
	public void setMarque (String newVar) {
		marque = newVar;
	}

	/**
	 * Get the value of marque
	 * @return the value of marque
	 */
	public String getMarque () {
		return marque;
	}

	/**
	 * Set the value of puissance
	 * @param newVar the new value of puissance
	 */
	public void setPuissance (Integer newVar) {
		puissance = newVar;
	}

	/**
	 * Get the value of puissance
	 * @return the value of puissance
	 */
	public Integer getPuissance () {
		return puissance;
	}

	/**
	 * Set the value of etat
	 * @param newVar the new value of etat
	 */
	public void setEtat (String newVar) {
		etat = newVar;
	}

	/**
	 * Get the value of etat
	 * @return the value of etat
	 */
	public String getEtat () {
		return etat;
	}

	/**
	 * Set the value of vMax
	 * @param newVar the new value of vMax
	 */
	public void setVMax (Integer newVar) {
		vMax = newVar;
	}

	/**
	 * Get the value of vMax
	 * @return the value of vMax
	 */
	public Integer getVMax () {
		return vMax;
	}

		/**
		 * Set the value of nbRapports
		 * @param newVar the new value of nbRapports
		 */
	public void setNbRapports (Integer newVar) {
		nbRapports = newVar;
	}

		/**
		 * Get the value of nbRapports
		 * @return the value of nbRapports
		 */
	public Integer getNbRapports () {
		return nbRapports;
	}

		/**
		 * Set the value of kmRenouvellement
		 * @param newVar the new value of kmRenouvellement
		 */
	public void setKmRenouvellement (Integer newVar) {
		kmRenouvellement = newVar;
	}

		/**
		 * Get the value of kmRenouvellement
		 * @return the value of kmRenouvellement
		 */
	public Integer getKmRenouvellement () {
		return kmRenouvellement;
	}

		//
		// Other methods
		//

}
