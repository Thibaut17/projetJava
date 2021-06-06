package frontend.materiel;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import backend.Materiel.*;

import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.time.LocalDate;

import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import utils.*;

/**
 * Classe AjoutMateriel : gère l'interface graphique pour l'ajout de nouveau matériel
 */
public class AjoutMateriel extends JFrame implements ItemListener, ActionListener {
    private JComboBox<String> choixType;
    private JLabel labelTypeMateriel;
    private JFrame frame;
    private JPanel panneauType;
    private JPanel panneau;
    private Properties p;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private String[] choix = { "Choisissez ci-dessous", "Ordinateurs", "Véhicules", "Accessoires Ordinateur",
            "Accessoires Véhicules" };

    private JButton valider;
    private JButton reset;
    // Gestionnaire 
    private GestionnaireMateriel gestion;
    private String pathToData;
    // Champs génériques
    private JTextField modele;
    private JTextField quantite;
    // Champs Ordinateurs
    private JTextField processeur;
    private JTextField cG;
    private JTextField identifiant;
    private JTextField type;
    private JTextField ecran;
    // Champs Vehicules
    private JTextField km;
    private JTextField marque;
    private JTextField puissance;
    private JTextField etat;
    private JTextField vMax;
    private JTextField rapport;
    private JTextField nombre;
    private JRadioButton moto;
    private JRadioButton voiture;

    /**
     * Constructeur AjoutMateriel : gère l'interface graphique pour l'ajout de nouveau matériel
     */
    public AjoutMateriel() {
        super("Ajouter un materiel");
        frame = this;
        //Gestionnaire
        pathToData = System.getProperty("user.dir") + "/data";
        gestion = GestionnaireMateriel.charger(pathToData);
        // Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.3);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        setBounds(posx, posy, realwidth, realheight);

        // Creation de la box de choix et ajout d'un item listenner
        choixType = new JComboBox<>(choix);
        choixType.addItemListener(this);
        // Creation du label et alignement horizontal
        labelTypeMateriel = new JLabel("Type de matériel : ");
        labelTypeMateriel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        // panneau pour le choix du type de materiel et ajout des composants
        panneauType = new JPanel();
        panneau = new JPanel();
        panneauType.add(labelTypeMateriel);
        panneauType.add(choixType);
        panneau.add(panneauType);
        // Ajout du panneau à la fenêtre
        getContentPane().add(panneau);
        // Fermeture de la fenetre en cas d'appui sur la croix
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Fenêtre visible
        setVisible(true);
    }

    /**
     * Création et affichage du formulaire pour l'ajout d'un ordinateur
     */
    public void ordinateur() {
        JLabel labelQuantite;
        JLabel labelModele;
        JLabel labelAchat;
        JLabel labelProcesseur;
        JLabel labelCG;
        JLabel labelIdentifiant;
        JLabel labelType;
        JLabel labelEcran;
        // Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.3);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        frame.setBounds(posx, posy, realwidth, realheight);
        // Creation du calendrier pour le choix de la date d'achat
        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        UtilDateModel modelDate = new UtilDateModel();
        datePanel = new JDatePanelImpl(modelDate, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        // Création du panneau principal et mise en place d'une disposition
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        // Création du panneau contenant le formulaire
        JPanel panneauFormulaire = new JPanel(new GridLayout(9, 2));
        // Création des labels, des boutons et des champs
        labelModele = new JLabel("Modèle : ");
        modele = new JTextField(10);
        labelQuantite = new JLabel("Quantite : ");
        quantite = new JTextField(10);
        labelProcesseur = new JLabel("Processeur : ");
        processeur = new JTextField(10);
        labelCG = new JLabel("Carte graphique : ");
        cG = new JTextField(10);
        labelAchat = new JLabel("Date achat : ");
        labelIdentifiant = new JLabel("Identifiant : ");
        identifiant = new JTextField(10);
        labelType = new JLabel("Type : ");
        type = new JTextField(10);
        labelEcran = new JLabel("Taille écran : ");
        ecran = new JTextField(10);
        valider = new JButton("Valider");
        reset = new JButton("Reset");
        // Ajout des action listenners
        valider.addActionListener(this);
        reset.addActionListener(this);
        // Centrage des labels
        labelModele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelQuantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelProcesseur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAchat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIdentifiant.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEcran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        // Ajout des composants au formulaire
        panneauFormulaire.add(labelModele);
        panneauFormulaire.add(modele);
        panneauFormulaire.add(labelQuantite);
        panneauFormulaire.add(quantite);
        panneauFormulaire.add(labelProcesseur);
        panneauFormulaire.add(processeur);
        panneauFormulaire.add(labelCG);
        panneauFormulaire.add(cG);
        panneauFormulaire.add(labelAchat);
        panneauFormulaire.add(datePicker);
        panneauFormulaire.add(labelIdentifiant);
        panneauFormulaire.add(identifiant);
        panneauFormulaire.add(labelType);
        panneauFormulaire.add(type);
        panneauFormulaire.add(labelEcran);
        panneauFormulaire.add(ecran);
        panneauFormulaire.add(reset);
        panneauFormulaire.add(valider);
        // Ajout deu panneau de la comboBox définie dans le constructeur et du formulare
        // au panneau principal
        panneau.add(panneauType);
        panneau.add(panneauFormulaire);

    }

    /**
     * Création et affichage du formulaire pour l'ajout d'un ordinateur
     */
    public void vehicule() {
        JLabel labelQuantite;
        JLabel labelModele;
        JLabel labelAchat;
        JLabel labelKm;
        JLabel labelMarque;
        JLabel labelPuissance;
        JLabel labelEtat;
        JLabel labelVMax;
        JLabel labelTypeVehicule;
        JLabel labelNbRapport;
        JLabel labelNombre;
        ButtonGroup typeVehicule;
        // Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.5);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        frame.setBounds(posx, posy, realwidth, realheight);
        // Creation du calendrier pour le choix de la date d'achat
        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        UtilDateModel modelDate = new UtilDateModel();
        datePanel = new JDatePanelImpl(modelDate, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        //Creation du panneau principal et définition de sa disposition
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        // Creation des labels, boutons et champs du formulaire
        labelModele = new JLabel("Modele : ");
        modele = new JTextField(10);
        labelKm = new JLabel("Kilométrage : ");
        km = new JTextField(10);
        labelMarque = new JLabel("Marque : ");
        marque = new JTextField(10);
        labelPuissance = new JLabel("Puissance : ");
        puissance = new JTextField(10);
        labelEtat = new JLabel("Etat général : ");
        etat = new JTextField(10);
        labelAchat = new JLabel("Date achat : ");
        labelVMax = new JLabel("Vitesse max : ");
        vMax = new JTextField(10);
        labelTypeVehicule = new JLabel("Type de véhicules");
        moto = new JRadioButton("Moto");
        voiture = new JRadioButton("Voiture");
        labelNombre = new JLabel("Nombre de cylindres : ");
        nombre = new JTextField(10);
        labelNbRapport = new JLabel("Nombre de rapports : ");
        rapport = new JTextField(10);
        labelQuantite = new JLabel("Quantite : ");
        quantite = new JTextField(10);
        valider = new JButton("Valider");
        reset = new JButton("Reset");
        //Ajout des action listenners
        valider.addActionListener(this);
        reset.addActionListener(this);
        // Groupe de bouton pour le type de vehicule
        typeVehicule = new ButtonGroup();
        typeVehicule.add(moto);
        typeVehicule.add(voiture);
        // Selon le type de vehicule, le label de nombre change
        moto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                labelNombre.setText("Nombre de cylindres");
            }
        });
        voiture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                labelNombre.setText("Nombre de places");
            }
        });
        //Panneau pour le choix du type de vehicule
        JPanel vehicule = new JPanel();
        vehicule.add(moto);
        vehicule.add(voiture);
        moto.setSelected(true);
        //Creation du panneau de formulaire
        JPanel panneauFormulaire = new JPanel(new GridLayout(12, 2));
        //Alignement des labels : Centré
        labelModele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelQuantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPuissance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMarque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAchat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEtat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelVMax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNbRapport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelKm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTypeVehicule.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //Ajout des composants au panneau de formulaire
        panneauFormulaire.add(labelModele);
        panneauFormulaire.add(modele);
        panneauFormulaire.add(labelQuantite);
        panneauFormulaire.add(quantite);
        panneauFormulaire.add(labelMarque);
        panneauFormulaire.add(marque);
        panneauFormulaire.add(labelPuissance);
        panneauFormulaire.add(puissance);
        panneauFormulaire.add(labelAchat);
        panneauFormulaire.add(datePicker);
        panneauFormulaire.add(labelKm);
        panneauFormulaire.add(km);
        panneauFormulaire.add(labelTypeVehicule);
        panneauFormulaire.add(vehicule);
        panneauFormulaire.add(labelEtat);
        panneauFormulaire.add(etat);
        panneauFormulaire.add(labelVMax);
        panneauFormulaire.add(vMax);
        panneauFormulaire.add(labelNbRapport);
        panneauFormulaire.add(rapport);
        panneauFormulaire.add(labelNombre);
        panneauFormulaire.add(nombre);
        panneauFormulaire.add(reset);
        panneauFormulaire.add(valider);
        //Ajout du panneau de choix du materiel et le panneau de formulaire au panneau principal
        panneau.add(panneauType);
        panneau.add(panneauFormulaire);
    }

    /**
     * Création et affichage du formulaire pour l'ajout d'accessoire informatiques / de véhicules
     */
    public void accessoires() {
        JLabel labelQuantite;
        JLabel labelModele;
        JLabel labelAchat;
        // Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.3);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        frame.setBounds(posx, posy, realwidth, realheight);
        // Creation du calendrier pour le choix de la date d'achat
        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        UtilDateModel modelDate = new UtilDateModel();
        datePanel = new JDatePanelImpl(modelDate, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        //Création et mise en place de la disposition du panneau principal
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        //Creation du panneau de formulaire
        JPanel panneauFormulaire = new JPanel(new GridLayout(9, 2));
        //Création des labels, boutons et champs de formulaire
        labelModele = new JLabel("Modèle : ");
        modele = new JTextField(10);
        labelQuantite = new JLabel("Quantite : ");
        quantite = new JTextField(10);
        labelAchat = new JLabel("Date achat : ");
        valider = new JButton("Valider");
        reset = new JButton("Reset");
        //Ajout des action listenners
        valider.addActionListener(this);
        reset.addActionListener(this);
        //Alignement des labels : centré
        labelModele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelQuantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAchat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //Ajout des composants au panneau de formulaire
        panneauFormulaire.add(labelModele);
        panneauFormulaire.add(modele);
        panneauFormulaire.add(labelQuantite);
        panneauFormulaire.add(quantite);
        panneauFormulaire.add(labelAchat);
        panneauFormulaire.add(datePicker);
        panneauFormulaire.add(reset);
        panneauFormulaire.add(valider);
        //ajout du panneau de choix de type de matériel et de formulaire au panneau principal 
        panneau.add(panneauType);
        panneau.add(panneauFormulaire);
    }

    /**
     * Action à exécuter quand un des boutons est sélectionné
     * 
     * @param evt évènement déclencheur
     */
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        Object selection = choixType.getSelectedItem();
        //Si reset est selectionné : remise à zéro des champs des formulaires
        if (source == reset) {
            //Champs généraux
            modele.setText("");
            quantite.setText("");
            //Champs pour les ordinateurs
            if (selection.equals(choix[1])) {
                processeur.setText("");
                cG.setText("");
                identifiant.setText("");
                type.setText("");
                ecran.setText("");
            }
            //Champs pour les vehicules
            if (selection.equals(choix[2])) {
                marque.setText("");
                km.setText("");
                puissance.setText("");
                vMax.setText("");
                rapport.setText("");
                nombre.setText("");
                etat.setText("");
            }

        }
        //Si on veut ajouter un matériel, ie valider est selectionné
        if (source == valider) {
            //Le type de matériel est ordinateur
            if (selection.equals(choix[1])) {
                try {
                    Integer q = Integer.valueOf(quantite.getText());
                    Integer id = Integer.valueOf(identifiant.getText());
                    Float size = Float.valueOf(ecran.getText());
                    LocalDate achat = LocalDate.parse(datePicker.getJFormattedTextField().getText());
                    LocalDate renew = achat.plusYears(5); //renouvellement tous les 5 ans
                    Ordinateur pc = new Ordinateur(q, modele.getText(), achat, id, processeur.getText(), cG.getText(),
                            type.getText(), size, renew);
                    
                    gestion.addMateriel(pc);
                    gestion.sauvegarde(pathToData);
                    frame.dispose();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame,"La quantite, l'identifiant et la taille d'ecran doivent être des nombres & ne pas laisser de champs vides","Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
            //Le type de matériel est véhicule
            if (selection.equals(choix[2])) {
                try {
                    Integer q = Integer.valueOf(quantite.getText());
                    Float kilometre = Float.valueOf(km.getText());
                    Integer p = Integer.valueOf(puissance.getText());
                    Integer v = Integer.valueOf(vMax.getText());
                    Integer nbR = Integer.valueOf(rapport.getText());
                    Integer nb = Integer.valueOf(nombre.getText());
                    LocalDate achat = LocalDate.parse(datePicker.getJFormattedTextField().getText());
                    Integer renew = (Integer)Math.round(kilometre) + 3000; //Renouvellement tous les 3000 kms
                    
                    //Le type de vehicule est moto
                    if (moto.isSelected()) {
                        Moto vehiculeToAdd = new Moto(q, modele.getText(), achat, kilometre, marque.getText(), p,
                                etat.getText(), v, nbR, renew, nb);
                        gestion.addMateriel(vehiculeToAdd);
                    }
                    //le type de vehicule est voiture
                    else {
                        Voiture vehiculeToAdd = new Voiture(q, modele.getText(), achat, kilometre, marque.getText(), p,
                                etat.getText(), v, nbR, renew, nb);
                        gestion.addMateriel(vehiculeToAdd);
                    }
                    gestion.sauvegarde(pathToData);
                    frame.dispose();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame,"La quantite, le nombre de kilomètres, la puissance, la vitesse, le nombre de rapports, le nombre de cylindres/places doivent être des nombres & ne pas laisser de champs vides","Erreur",JOptionPane.ERROR_MESSAGE);
                }

            }
            //Le type de matériel est accessoire informatique
            if (selection.equals(choix[3])) {

                try {
                    Integer q = Integer.valueOf(quantite.getText());
                    LocalDate achat = LocalDate.parse(datePicker.getJFormattedTextField().getText());
                    
                    AccessoiresInformatiques accessoire = new AccessoiresInformatiques(q, modele.getText(), achat);
                    gestion.addMateriel(accessoire);
                    gestion.sauvegarde(pathToData);
                    frame.dispose();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame,"La quantite doit être un nombre & ne pas laisser de champs vides","Erreur",JOptionPane.ERROR_MESSAGE);
                }

            }
            //Le type de matériel est accessoire véhicule
            if (selection.equals(choix[4])) {

                try {
                    Integer q = Integer.valueOf(quantite.getText());
                    LocalDate achat = LocalDate.parse(datePicker.getJFormattedTextField().getText());
                    AccessoiresVehicules accessoire = new AccessoiresVehicules(q, modele.getText(), achat);
                    gestion.addMateriel(accessoire);
                    gestion.sauvegarde(pathToData);
                    frame.dispose();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame,"La quantite doit être un nombre & ne pas laisser de champs vides","Erreur",JOptionPane.ERROR_MESSAGE);
                }

            }

        }
    }
    /**
     * Action à exécuter quand un type de materiel est choisi
     * 
     * @param e évènement déclencheur
     */
    public void itemStateChanged(ItemEvent e) {
        // si l'état du combobox est modifiée
        if (e.getStateChange() == ItemEvent.SELECTED) {
            Object selection = choixType.getSelectedItem();
            //Ordinateur est sélectionné
            if (selection.equals(choix[1])) {
                frame.remove(panneauType);
                try {
                    //On enlève le panneau précédent
                    frame.remove(panneau);
                } finally {
                    //On actualise la fenêtre
                    frame.repaint();
                    //on crée le formulaire pour les ordinateurs et on l'ajoute à la fenetre que l'on rend visible
                    ordinateur();
                    frame.getContentPane().add(panneau);
                    setVisible(true);

                }
            }
            //Vehicule est sélectionné
            if (selection.equals(choix[2])) {
                frame.remove(panneauType);
                try {
                    //On enlève le panneau précédent
                    frame.remove(panneau);
                } finally {
                    //On actualise la fenêtre
                    frame.repaint();
                    //on crée le formulaire pour les vehicules et on l'ajoute à la fenetre que l'on rend visible
                    vehicule();
                    frame.getContentPane().add(panneau);
                    setVisible(true);

                }
            }
            //Accesoire ordinateur est sélectionné
            if (selection.equals(choix[3])) {
                frame.remove(panneauType);
                try {
                    //On enlève le panneau précédent
                    frame.remove(panneau);
                } finally {
                    //On actualise la fenêtre
                    frame.repaint();
                    //on crée le formulaire pour les accessoires ordinateurs et on l'ajoute à la fenetre que l'on rend visible
                    accessoires();
                    frame.getContentPane().add(panneau);
                    setVisible(true);
                }
            }
            //Accessoire vehicule est sélectionné
            if (selection.equals(choix[4])) {
                frame.remove(panneauType);
                try {
                    //On enlève le panneau précédent
                    frame.remove(panneau);
                } finally {
                    //On actualise la fenêtre
                    frame.repaint();
                    //on crée le formulaire pour les accessoires vehicules et on l'ajoute à la fenetre que l'on rend visible
                    accessoires();
                    frame.getContentPane().add(panneau);
                    setVisible(true);
                }
            }
        }
    }
}
