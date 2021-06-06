package frontend.materiel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import backend.Materiel.*;

import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;


import java.time.LocalDate;
/**
 * Classe gérant la modification du matériel déjà en stock
 */
public class ModifMateriel extends JFrame implements ActionListener{
    //Eléments principaux de la fenêtre
    private JFrame frame;
    private JPanel panneau;
    //Table pour afficher les différents matériels en stock
    private JTable table;
    private DefaultTableModel model;
    //Gestionnaire de matériel
    private String pathToData;
    private GestionnaireMateriel gestion;
    private Materiel toChange;
    //Champs généraux
    private JTextField quantite;

    //Boutons
    private JButton suivant;
    private JButton valider;
    private JButton annuler;
    /**
     * Constructeur permettant de créer la fenêtre de modification du matériel
     */
    public ModifMateriel() {
        super("Modifier un materiel");
        frame = this;
        //Chargement du matériel déjà en stock
        pathToData = System.getProperty("user.dir") + "/data";
        gestion = GestionnaireMateriel.charger(pathToData);
        // Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        int realheight = (int) (height*0.8);
        int realwidth = (int) (width*0.3);
        int posx=(int) ((width-realwidth)/2);
        int posy=(int) ((height-realheight)/2);
        setBounds(posx, posy, realwidth, realheight);
        //Creation des boutons
        suivant = new JButton("Suivant");
        annuler = new JButton("Annuler");
        //Ajout d'action listenners
        annuler.addActionListener(this);
        suivant.addActionListener(this);
        //Creation de la table des materiels existants et remplissage de celle-ci ainsi que un ascenceur
        String[] fields = { "Modèle", "Quantité", "Acheté le" , "Type" };
        Object[][] tuple = { };
        model = new DefaultTableModel(tuple, fields);
        for(Materiel m : gestion.getListeMateriel()){
            model.addRow(new Object[] { m.getModele(), m.getQuantite(), m.getAchat(), m.getClass().getSimpleName() });
        }
        table = new JTable(model){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);
        //Creation de panneaux pour la table et pour les boutons d'action et ajout de leurs composants repectifs
        JPanel bouton = new JPanel();
        JPanel content = new JPanel();
        bouton.add(annuler);
        bouton.add(suivant);
        content.add(table.getTableHeader());
        content.add(scrollPane);
        //Creation du panneau principal et mise en place de sa disposition
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        //Ajout des panneaux précédemments créés au panneau principal
        panneau.add(content);
        panneau.add(bouton);
        //Ajout du panneau principal à la fenêtre
        frame.getContentPane().add(panneau);
        //Fermeture de la fenêtre en cas d'appui sur la croix
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //Fenêtre visible
        setVisible(true);
    }


    /**
     * Methode ordinateur pour générer le formulaire de modification concernant les ordinateurs
     * @param toChange Objet de type ordinateur
     */
    public void ordinateur(Ordinateur toChange){
        JLabel labelQuantite;
        JLabel labelModele;
        JLabel labelAchat;
        JLabel date;
        JLabel labelProcesseur;
        JLabel labelCG;
        JLabel labelIdentifiant;
        JLabel labelType;
        JLabel labelEcran;
        JLabel modele;
        JLabel processeur;
        JLabel cG;
        JLabel type;
        JLabel ecran;
        JLabel identifiant;
        // Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.3);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        frame.setBounds(posx, posy, realwidth, realheight);
        //Création du panneau principal et mise en place de sa disposition
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        //Creation des labels, champs et boutons du formulaire
        labelModele = new JLabel("Modèle : ");
        modele = new JLabel();
        modele.setText(toChange.getModele());
        labelQuantite = new JLabel("Quantite : ");
        quantite = new JTextField(10);
        quantite.setText(Integer.toString(toChange.getQuantite()));
        labelProcesseur = new JLabel("Processeur : ");
        processeur = new JLabel();
        processeur.setText(toChange.getCpu());
        labelCG = new JLabel("Carte graphique : ");
        cG = new JLabel();
        cG.setText(toChange.getGpu());
        labelAchat = new JLabel("Date achat : ");
        date = new JLabel(toChange.getAchat().toString());
        labelIdentifiant = new JLabel("Identifiant : ");
        identifiant = new JLabel();
        identifiant.setText(Integer.toString(toChange.getIdentifiant()));
        labelType = new JLabel("Type : ");
        type = new JLabel();
        modele.setText(toChange.getType());
        labelEcran = new JLabel("Taille écran : ");
        ecran = new JLabel();
        ecran.setText(Float.toString(toChange.getTailleEcran()));
        valider = new JButton("Valider");
        annuler = new JButton("Annuler");
        //Ajout d'action listenners
        valider.addActionListener(this);
        annuler.addActionListener(this);
        //Creation du panneau de formulaire pour la modification de matériel
        JPanel panneauFormulaire = new JPanel(new GridLayout(9,2));
        //Alignement des labels et champs : centré
        labelModele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelQuantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelProcesseur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAchat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIdentifiant.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEcran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        processeur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        type.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        identifiant.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ecran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //Ajout des composants au panneau de formulaire
        panneauFormulaire.add(labelModele);
        panneauFormulaire.add(modele);
        panneauFormulaire.add(labelQuantite);
        panneauFormulaire.add(quantite);
        panneauFormulaire.add(labelProcesseur);
        panneauFormulaire.add(processeur);
        panneauFormulaire.add(labelCG);
        panneauFormulaire.add(cG);
        panneauFormulaire.add(labelAchat);
        panneauFormulaire.add(date);
        panneauFormulaire.add(labelIdentifiant);
        panneauFormulaire.add(identifiant);
        panneauFormulaire.add(labelType);
        panneauFormulaire.add(type);
        panneauFormulaire.add(labelEcran);
        panneauFormulaire.add(ecran);
        panneauFormulaire.add(annuler);
        panneauFormulaire.add(valider);
        //Ajout du panneau de formulaire au panneau principal
        panneau.add(panneauFormulaire);       
    }

    /**
     * Methode vehicule pour générer le formulaire de modification concernant les véhicules (moto/voiture)
     * @param toChange Objet de type Vehicule
     */
    public void vehicule(Vehicule toChange){
        JLabel labelQuantite;
        JLabel labelModele;
        JLabel labelAchat;
        JLabel date;
        JLabel labelKm;
        JLabel labelMarque;
        JLabel labelPuissance;
        JLabel labelEtat;
        JLabel labelVMax;
        JLabel labelTypeVehicule;
        JLabel labelNbRapport;
        JLabel labelNombre;
        JLabel labelRenouvellement;
        JLabel typeVehicule;
        JLabel modele;
        JLabel km;
        JLabel marque;
        JLabel puissance;
        JLabel etat;
        JLabel vMax;
        JLabel nombre;
        JLabel rapport;
        JLabel renouvellement;
        // Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.5);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        frame.setBounds(posx, posy, realwidth, realheight);
        //Création du panneau principal et mise en place de sa disposition
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        //Creation des labels, champs et boutons d'action
        labelModele = new JLabel("Modele : ");
        modele = new JLabel();
        modele.setText(toChange.getModele());
        labelKm = new JLabel("Kilométrage : ");
        km = new JLabel();
        km.setText(Float.toString(toChange.getKilometrages()));
        labelRenouvellement = new JLabel("Renouvellement (km) : ");
        renouvellement = new JLabel();
        renouvellement.setText(Integer.toString(toChange.getKmRenouvellement()));
        labelMarque = new JLabel("Marque : ");
        marque = new JLabel();
        marque.setText(toChange.getMarque());
        labelPuissance = new JLabel("Puissance : ");
        puissance = new JLabel();
        puissance.setText(Integer.toString(toChange.getPuissance()));
        labelEtat = new JLabel("Etat général : ");
        etat = new JLabel();
        etat.setText(toChange.getEtat());
        labelAchat = new JLabel("Date achat : ");
        date = new JLabel();
        date.setText(toChange.getAchat().toString());
        labelVMax = new JLabel("Vitesse max : ");
        vMax = new JLabel();
        vMax.setText(Integer.toString(toChange.getVMax()));
        labelTypeVehicule = new JLabel("Type de véhicule : ");
        typeVehicule = new JLabel();
        labelNombre = new JLabel();
        nombre = new JLabel();
        //Choix du label de nombre et mise à jour du nombre en fonction du type de vehicule
        if(toChange.getClass().getSimpleName().equals("Voiture")){
            Voiture voiture = (Voiture) toChange;
            labelNombre.setText("Nombre de places : ");
            nombre.setText(Integer.toString(voiture.getNombrePlaces()));
            typeVehicule.setText("Voiture");
        }else{
            Moto moto = (Moto) toChange;
            labelNombre.setText("Nombre de cylindres : ");
            nombre.setText(Integer.toString(moto.getNombreCylindres()));
            typeVehicule.setText("Moto");
        }
        labelNbRapport = new JLabel("Nombre de rapports : ");
        rapport = new JLabel();
        rapport.setText(Integer.toString(toChange.getNbRapports()));
        labelQuantite = new JLabel("Quantite : ");
        quantite = new JTextField(10);
        quantite.setText(Integer.toString(toChange.getQuantite()));
        valider = new JButton("Valider");
        annuler = new JButton("Annuler");
        //Ajout des actions listenners
        valider.addActionListener(this);
        annuler.addActionListener(this);
        //Creation du panneau pour le formulaire
        JPanel panneauFormulaire = new JPanel(new GridLayout(13,2));
        //Centrage des labels et des textes dans les champs
        labelModele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelQuantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPuissance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMarque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAchat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEtat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelVMax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNbRapport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelKm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTypeVehicule.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRenouvellement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        renouvellement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        puissance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        marque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vMax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rapport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        km.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        typeVehicule.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
        panneauFormulaire.add(date);
        panneauFormulaire.add(labelKm);
        panneauFormulaire.add(km);
        panneauFormulaire.add(labelRenouvellement);
        panneauFormulaire.add(renouvellement);
        panneauFormulaire.add(labelTypeVehicule);
        panneauFormulaire.add(typeVehicule);
        panneauFormulaire.add(labelEtat);
        panneauFormulaire.add(etat);
        panneauFormulaire.add(labelVMax);
        panneauFormulaire.add(vMax);
        panneauFormulaire.add(labelNbRapport);
        panneauFormulaire.add(rapport);
        panneauFormulaire.add(labelNombre);
        panneauFormulaire.add(nombre);
        panneauFormulaire.add(annuler);
        panneauFormulaire.add(valider);
        //Ajout du panneau de formulaire au panneau principal
        panneau.add(panneauFormulaire);
    }
    
    /**
     * Methode accessoiresOrdinateur pour générer le formulaire de modification concernant les accessoires des ordinateurs
     * @param toChange Objet de type AccessoireOrdinateur
     */
    public void accessoiresOrdinateur(AccessoiresInformatiques toChange){
        JLabel labelQuantite;
        JLabel labelModele;
        JLabel labelAchat;
        JLabel modele;
        JLabel date;
        // Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.3);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        frame.setBounds(posx, posy, realwidth, realheight);
        //Creation du panneau principal et mise en place de sa disposition
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        //Creation des labels, boutons et champs
        labelModele = new JLabel("Modèle : ");
        modele = new JLabel();
        modele.setText(toChange.getModele());
        labelQuantite = new JLabel("Quantite : ");
        quantite = new JTextField(10);
        quantite.setText(Integer.toString(toChange.getQuantite()));
        labelAchat = new JLabel("Date achat : ");
        date = new JLabel();
        date.setText(toChange.getAchat().toString());
        valider = new JButton("Valider");
        annuler = new JButton("Annuler");
        //Ajout des action listenners
        valider.addActionListener(this);
        annuler.addActionListener(this);
        //Creation du panneau de formulaire
        JPanel panneauFormulaire = new JPanel(new GridLayout(9,2));
        //Centrage des labels et des textes dans les champs
        labelModele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelQuantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAchat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //Ajout des composants au panneau de formulaire
        panneauFormulaire.add(labelModele);
        panneauFormulaire.add(modele);
        panneauFormulaire.add(labelQuantite);
        panneauFormulaire.add(quantite);
        panneauFormulaire.add(labelAchat);
        panneauFormulaire.add(date);
        panneauFormulaire.add(annuler);
        panneauFormulaire.add(valider);
        //Ajout du panneau de formulaire au panneau principal
        panneau.add(panneauFormulaire);
    }
    
    /**
     * Methode accessoiresVehicules pour générer le formulaire de modification concernant les accessoires des véhicules
     * @param toChange Objet de type AccessoireVehicule
     */
    public void accessoiresVehicules(AccessoiresVehicules toChange){
        JLabel labelQuantite;
        JLabel labelModele;
        JLabel labelAchat;
        JLabel modele;
        JLabel date;
        // Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.3);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        frame.setBounds(posx, posy, realwidth, realheight);
        //Creation du panneau principal et mise en place de sa disposition
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        //Creation des labels, boutons et champs
        labelModele = new JLabel("Modèle : ");
        modele = new JLabel();
        modele.setText(toChange.getModele());
        labelQuantite = new JLabel("Quantite : ");
        quantite = new JTextField(10);
        quantite.setText(Integer.toString(toChange.getQuantite()));
        labelAchat = new JLabel("Date achat : ");
        date = new JLabel();
        date.setText(toChange.getAchat().toString());
        valider = new JButton("Valider");
        annuler = new JButton("Annuler");
        //Ajout des action listenners
        valider.addActionListener(this);
        annuler.addActionListener(this);
        //Creation du panneau de formulaire
        JPanel panneauFormulaire = new JPanel(new GridLayout(9,2));
        //Centrage des labels et des textes dans les champs
        labelModele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelQuantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAchat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //Ajout des composants au panneau de formulaire
        panneauFormulaire.add(labelModele);
        panneauFormulaire.add(modele);
        panneauFormulaire.add(labelQuantite);
        panneauFormulaire.add(quantite);
        panneauFormulaire.add(labelAchat);
        panneauFormulaire.add(date);
        panneauFormulaire.add(annuler);
        panneauFormulaire.add(valider);
        //Ajout du panneau de formulaire au panneau principal
        panneau.add(panneauFormulaire);
    }
    
    /**
     * Methode actionPerformes pour gérer les actions sur les différents composants des fenêtres
     * @param evt Objet de type ActionEvent
     */
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        //Le bouton annuler est sélectionné
        if(source == annuler){
            frame.dispose();
        }
        //Le bouton suivant est sélectionné
        if(source == suivant){
            //Un champs est sélectionné pour modification
            if(table.getSelectedRow() >= 0){
                Integer idToChangeTable = table.getSelectedRow();//Numero de ligne pour le JTable
                Integer idToChange = gestion.rechercheMaterielStricte((String) table.getValueAt(idToChangeTable,0), (Integer) table.getValueAt(idToChangeTable,1), (LocalDate) table.getValueAt(idToChangeTable,2)); //Numero de ligne pour le model du Jtable et dans la liste de materiel
                
                toChange = gestion.getListeMateriel().get(idToChange);
                //L'objet à modifier est de type Ordinateur
                if(toChange.getClass().getSimpleName().equals("Ordinateur")){
                    try{
                        //On enlève le panneau précédent : tableau de choix
                        frame.remove(panneau);
                        frame.repaint();
                    }catch(Exception excep){
                        System.err.println(excep.getMessage());
                    }
                    finally{
                        //On crée le panneau du Formulaire de changement et on l'ajoute à la fenêre
                        ordinateur((Ordinateur)(toChange));
                        frame.getContentPane().add(panneau);
                        setVisible(true);
    
                    }
                }
                //L'objet à modifier est de type Vehicule
                if(toChange.getClass().getSimpleName().equals("Moto") || toChange.getClass().getSimpleName().equals("Voiture")){
                    try{
                        //On enlève le panneau précédent : tableau de choix
                        frame.remove(panneau);
                        frame.repaint();
                    }catch(Exception excep){
                        System.err.println(excep.getMessage());
                    }finally{
                        //On crée le panneau du Formulaire de changement et on l'ajoute à la fenêre
                        vehicule((Vehicule)(toChange));   
                        frame.getContentPane().add(panneau);
                        setVisible(true);
                    }
                }
                //L'objet à modifier est de type AccessoiresVehicules
                if(toChange.getClass().getSimpleName().equals("AccessoiresVehicules")){
                    try{
                        //On enlève le panneau précédent : tableau de choix
                        frame.remove(panneau);
                        frame.repaint();
                    }catch(Exception excep){
                        System.err.println(excep.getMessage());
                    }finally{
                        //On crée le panneau du Formulaire de changement et on l'ajoute à la fenêre
                        accessoiresVehicules((AccessoiresVehicules)(toChange));
                        frame.getContentPane().add(panneau);
                        setVisible(true);    
                    }
                }
                //L'objet à modifier est de type AccessoiresInformatiques
                if(toChange.getClass().getSimpleName().equals("AccessoiresInformatiques")){
                    try{
                        //On enlève le panneau précédent : tableau de choix
                        frame.remove(panneau);
                        frame.repaint();
                    }catch(Exception excep){
                        System.err.println(excep.getMessage());
                    }finally{
                        //On crée le panneau du Formulaire de changement et on l'ajoute à la fenêre
                        accessoiresOrdinateur((AccessoiresInformatiques)(toChange));
                        frame.getContentPane().add(panneau);
                        setVisible(true);    
                    }
                }
            }
        }
        if(source == valider){ //L'utilisateur souhaite appliquer la modification
            //L'objet à modifier est de type Ordinateur
            if(toChange.getClass().getSimpleName().equals("Ordinateur")){
                try{
                    Integer q = Integer.valueOf(quantite.getText());
                    if(q>0){
                        toChange.setQuantite(q);
                    }
                    if(q==0){
                        gestion.delMateriel(toChange);
                    }
                    gestion.sauvegarde(pathToData);
                    frame.dispose();
                }catch(Exception excep){
                    System.err.println(excep.getMessage());
                }
            }
            //L'objet à modifier est de type Vehicule
            if(toChange.getClass().getSimpleName().equals("Moto") || toChange.getClass().getSimpleName().equals("Voiture")){
                try{
                    Integer q = Integer.valueOf(quantite.getText());
                    if(q>0){
                        toChange.setQuantite(q);
                    }
                    if(q==0){
                        gestion.delMateriel(toChange);
                    }
                    gestion.sauvegarde(pathToData);
                    frame.dispose();
                }catch(Exception excep){
                    System.err.println(excep.getMessage());
                }
            }
            //L'objet à modifier est de type AccessoiresVehicules
            if(toChange.getClass().getSimpleName().equals("AccessoiresVehicules")){
                try{
                    Integer q = Integer.valueOf(quantite.getText());
                    if(q>0){
                        toChange.setQuantite(q);
                    }
                    if(q==0){
                        gestion.delMateriel(toChange);
                    }
                    gestion.sauvegarde(pathToData);
                    frame.dispose();
                }catch(Exception excep){
                    System.err.println(excep.getMessage());
                }
            }
            //L'objet à modifier est de type AccessoiresInformatiques
            if(toChange.getClass().getSimpleName().equals("AccessoiresInformatiques")){
                try{
                    Integer q = Integer.valueOf(quantite.getText());
                    if(q>0){
                        toChange.setQuantite(q);
                    }
                    if(q==0){
                        gestion.delMateriel(toChange);
                    }
                    gestion.sauvegarde(pathToData);
                    frame.dispose();
                }catch(Exception excep){
                    System.err.println(excep.getMessage());
                }
            }
        }
    }
}
