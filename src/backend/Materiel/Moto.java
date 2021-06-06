package backend.Materiel;

import java.time.LocalDate;
/**
 * Class Moto
 */
public class Moto extends Vehicule {

    private Integer nombreCylindres;

    
    /**
     * Constructeur de Moto
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
     * @param nombreCylindres Nombre de cylindres de la moto
     */
    public Moto(Integer quantite, String modele, LocalDate achat, Float kilometrages, String marque, Integer puissance,
            String etat, Integer vMax, Integer nbRapports, Integer kmRenouvellement, Integer nombreCylindres) {
        super(quantite, modele, achat, kilometrages, marque, puissance, etat, vMax, nbRapports, kmRenouvellement);
        this.nombreCylindres = nombreCylindres;
    }

    /**
     * Set the value of nombreCylindres
     * @param newVar the new value of nombreCylindres
     */
    public void setNombreCylindres (Integer newVar) {
            nombreCylindres = newVar;
    }


    /**
     * Get the value of nombreCylindres
     * @return the value of nombreCylindres
     */
    public Integer getNombreCylindres () {
            return nombreCylindres;
    }

}
