package frontend.emprunts;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.time.LocalDate;

import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;


import backend.Materiel.*;
import backend.Utilisateurs.*;
import backend.Emprunts.*;
import utils.*;

/**
 * Classe AjoutEmprunt : génère l'interface graphique permettant d'ajouter un nouvel emprunt
 */
public class AjoutEmprunt extends JFrame implements ItemListener, ActionListener {
    private JComboBox<String> choixType;
    private JComboBox<String> choixMateriel;
    private JLabel labelNouvelEmprunt;
    private String[] choix = { "Choisissez ci-dessous", "Ordinateur", "Moto", "Voiture", "Accessoires Informatiques",
            "Accessoires Véhicules" };
    private JPanel panneauMateriel;
    private JPanel panneauDescription;
    private JPanel panneauRecherche;
    private JPanel panneauTable;
    private JPanel panneauDate;
    private JPanel panneau;
    private JPanel panneauBouton;
    private JTextArea description;
    private JButton annuler;
    private JButton precedent;
    private JButton suivant;
    private JButton valider;
    private JFrame window;
    private JTable table;
    private DefaultTableModel model;
    private JLabel labelNom;
    private JLabel labelPrenom;
    private JTextField nom;
    private JTextField prenom;
    private JButton rechercher;
    private JFrame dateWindow;
    private JButton reserver;
    private Properties pBegin;
    private Properties pEnd;
    private JDatePanelImpl datePanelBegin;
    private JDatePanelImpl datePanelEnd;
    private JDatePickerImpl datePickerBegin;
    private JDatePickerImpl datePickerEnd;
    private JLabel labelDateBegin;
    private JLabel labelDateEnd;
    private String pathToData;
    private GestionnaireMateriel gestionMateriel;
    private GestionnaireEmprunts gestionEmprunts;
    private GestionnaireUtilisateur gestionUtilisateur;
    private Emprunt aEmprunter;
    private Materiel materielSelected;
    private int selected=1;
    private String[] choice = { "Choisissez ci-dessous", "Ordinateur", "Moto", "Voiture", "AccessoiresInformatiques",
    "AccessoiresVehicules" };
    /**
     * Constructeur AjoutEmprunt : génère la fenêtre pour effectuer un nouvel emprunt
     */
    public AjoutEmprunt() {
        super("Nouvel emprunt");
        //Chargement des différents gestionnaires
        pathToData = System.getProperty("user.dir") + "/data";
        gestionMateriel = GestionnaireMateriel.charger(pathToData);
        gestionEmprunts = GestionnaireEmprunts.charger(pathToData);
        gestionUtilisateur = new GestionnaireUtilisateur(pathToData);
        //Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.3);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        setBounds(posx, posy, realwidth, realheight);
        //Fermeture de la fenêtre en cas d'appui sur la croix
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Creation des Box pour choisir le type de materiel et le materiel
        choixType = new JComboBox<>(choix);
        choixMateriel = new JComboBox<>();
        choixMateriel.addItem("Choisissez ci-dessous");
        labelNouvelEmprunt = new JLabel("Nouvel Emprunt : ");
        //Ajout d'un item listener à la box de choix de type de materiel
        choixType.addItemListener(this);
        //Description générique
        description = new JTextArea("\n \n Choisissez votre type de matériel \n et votre matériel");
        //Creation des boutons et ajout de leur action listener
        annuler = new JButton("Annuler");
        suivant = new JButton("Suivant");
        annuler.addActionListener(this);
        suivant.addActionListener(this);
        //Creation de panneaux pour le materiel et la description et mise en place de sa disposition
        panneauMateriel = new JPanel();
        panneauDescription = new JPanel();
        panneauMateriel.setLayout(new BoxLayout(panneauMateriel, BoxLayout.Y_AXIS));
        panneauDescription.setLayout(new BoxLayout(panneauDescription, BoxLayout.Y_AXIS));
        //Ajout des composants aux panneaux précédemment définis
        panneauMateriel.add(labelNouvelEmprunt);
        panneauMateriel.add(choixType);
        panneauMateriel.add(choixMateriel);
        panneauMateriel.add(annuler);
        panneauDescription.add(description);
        panneauDescription.add(suivant);
        //Ajout d'un action listener pour le choix de matériel
        choixMateriel.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //Si un materiel est selectionne on affiche son modele
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    if(!(item.equals("Choisissez ci-dessous"))){
                        description.setText("\n \n" + (String)(item));
                        description.setEditable(false);
                    }
                }
                
            }
        });
        //Mise en place de la disposition de la fenêtre
        setLayout(new GridLayout(1, 2));
        //Ajout des deux panneaux à la fenetre
        getContentPane().add(panneauMateriel);
        getContentPane().add(panneauDescription);
        //Ne pas changer la taille de la fenetre
        setResizable(false);
        //Fenetre visible
        setVisible(true);
    }
    /**
     * Génère la fenêtre de choix de l'emprunteur
     */
    public void choixUser() {

        window = new JFrame("Nouvel Emprunt");
        // setSize(700, 700);
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.3);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        window.setBounds(posx, posy, realwidth, realheight);

        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        valider = new JButton("Valider");
        precedent = new JButton("Précédent");
        rechercher = new JButton("Rechercher");
        nom = new JTextField(10);
        prenom = new JTextField(10);
        labelNom = new JLabel("Nom : ");
        labelPrenom = new JLabel("Prenom : ");

        precedent.addActionListener(this);
        valider.addActionListener(this);
        rechercher.addActionListener(this);
        panneauRecherche = new JPanel();
        panneauTable = new JPanel();
        panneau = new JPanel();
        panneauBouton = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        panneauTable.setLayout(new BoxLayout(panneauTable, BoxLayout.Y_AXIS));

        String[] fields = { "Nom", "Prénom", "Service" , "Poste" };
        Object[][] tuple = { };
        model = new DefaultTableModel(tuple, fields);
        //Append users
        for(Utilisateur user : gestionUtilisateur.getListe()){
            model.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
        }
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);
        panneauRecherche.add(labelNom);
        panneauRecherche.add(nom);
        panneauRecherche.add(labelPrenom);
        panneauRecherche.add(prenom);
        panneauTable.add(table.getTableHeader());
        panneauTable.add(scrollPane);
        // window.setLayout(new GridLayout(1, 2));
        panneau.add(panneauRecherche);
        panneau.add(rechercher);
        panneau.add(panneauTable);
        panneauBouton.add(precedent);
        panneauBouton.add(valider);
        panneau.add(panneauBouton);
        window.getContentPane().add(panneau);

        window.setResizable(false);
        window.setVisible(true);
    }
    /**
     * Génère la fenêtre de choix des dates de l'emprunt
     */
    public void selectDate() {

        dateWindow = new JFrame("Nouvel Emprunt : Date");
        labelDateBegin = new JLabel("Début de la réservation :");
        labelDateEnd = new JLabel("Fin de la réservation :");

        pBegin = new Properties();
        pEnd = new Properties();
        pBegin.put("text.today", "Today");
        pBegin.put("text.month", "Month");
        pBegin.put("text.year", "Year");
        pEnd = new Properties();
        pEnd.put("text.today", "Today");
        pEnd.put("text.month", "Month");
        pEnd.put("text.year", "Year");
        UtilDateModel modelDateBegin = new UtilDateModel();
        UtilDateModel modelDateEnd = new UtilDateModel();
        datePanelBegin = new JDatePanelImpl(modelDateBegin, pBegin);
        datePanelEnd = new JDatePanelImpl(modelDateEnd, pEnd);
        datePickerBegin = new JDatePickerImpl(datePanelBegin, new DateLabelFormatter());
        datePickerEnd = new JDatePickerImpl(datePanelEnd, new DateLabelFormatter());

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.3);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        dateWindow.setBounds(posx, posy, realwidth, realheight);

        dateWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        reserver = new JButton("Réserver");
        reserver.addActionListener(this);

        panneauDate = new JPanel();

        panneauDate.add(labelDateBegin);
        panneauDate.add(datePickerBegin);
        panneauDate.add(labelDateEnd);
        panneauDate.add(datePickerEnd);
        panneauDate.add(reserver);

        dateWindow.getContentPane().add(panneauDate);

        dateWindow.setResizable(false);
        dateWindow.setVisible(true);
    }
    /**
     * Methode itemStateChanged pour gérer les actions sur la JComboBox de choix du type de matériel
     * @param e Objet de type ItemEvent
     */
    public void itemStateChanged(ItemEvent e) {
        // si un nouvel item est sélectionné
        if (e.getStateChange() == ItemEvent.SELECTED) {
            selected=1;
            Object type = choixType.getSelectedItem();
            boolean trouve = false;
            //Recherche du type de matériel sélectionné
            for(int j = 1; j<6;j++){
                if(!(type.equals(choix[j])) && !trouve){
                    selected++;
                }else{
                    trouve = true;
                }
            }
            //Si on a trouvé le type de matériel
            if(trouve){
                //Ajout des materiels correspondant au type selectionné et suppression des autres
                for (Materiel materiel : gestionMateriel.getListeMateriel()){
                    if(materiel.getClass().getSimpleName().equals(choice[selected])){
                        choixMateriel.addItem(materiel.getModele());
                    }else{
                        choixMateriel.removeItem(materiel.getModele());
                    }
                }
            }
            //Sinon c'est que "Choisissez ....." est sélectionné
            else{
                //On supprime tous les materiels
                for (Materiel materiel : gestionMateriel.getListeMateriel()){
                    choixMateriel.removeItem(materiel.getModele());
                }
            }
            
        }
    }
    /**
     * Methode actionPerformes pour gérer les actions sur les différents composants des fenêtres
     * @param evt Objet de type ActionEvent
     */
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        //En cas d'annulation on ferme la fenêtre
        if (source == annuler) {
            dispose();
        }
        //En cas d'appui sur suivant
        if (source == suivant) {
            //Si un materiel à bien été sélectionné
            if (!(choixType.getSelectedItem()).equals("Choisissez ci-dessous") && !(choixMateriel.getSelectedItem()).equals("Choisissez ci-dessous")) {
                //On récupère le matériel en tant que tel et on instancie l'emprunt
                materielSelected = gestionMateriel.getListeMateriel().get(gestionMateriel.rechercheMaterielStricte((String)choixMateriel.getSelectedItem()));
                aEmprunter = new Emprunt(materielSelected);
                //On affiche la fenêtre de choix des utilisateurs
                choixUser();
            }
        }
        if (source == precedent) {
            //Si appui sur précédent on ferme la fenêtre de choix des utilisateurs
            window.dispose();
        }
        //L'utilisateur a fait son choix d'emprunteur
        if (source == valider) {
            //Un emprunteur a bien été sélectionné dans la table
            if (table.getSelectedRow() >= 0) {
                //On ajoute l'utilisateur à l'emprunt
                aEmprunter.setUtilisateur(gestionUtilisateur.rechercheUtilisateurStricte((String) table.getValueAt(table.getSelectedRow(), 0), (String) table.getValueAt(table.getSelectedRow(), 1)));
                //On selectionne
                selectDate();
            }
        }
        //On recherche un utilisateur
        if (source == rechercher) {
            //Suppression de tous les utilisateurs présents dans la table
            for (int j = table.getRowCount() - 1; j > -1; j--) {
                model.removeRow(j);
            }
            if(nom.getText().equals("") && prenom.getText().equals("")){
                //Si le prenom et le nom sont vide alors ajout de tous les utilisateurs
                for(Utilisateur user : gestionUtilisateur.getListe()){
                    model.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
                }
            }else{
                //Ajout des utilisateurs correspondant au nom ou prénom recherché
                for(Utilisateur user : gestionUtilisateur.rechercheUtilisateur(nom.getText(), prenom.getText())){
                    model.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
                }
            }
        }
        //L'utilisateur a fait son choix de dates et souhaite finaliser sa réservation
        if (source == reserver) {
            //Les dates ont bien été sélectionnées
            if(!datePickerBegin.getJFormattedTextField().getText().equals("") && !datePickerEnd.getJFormattedTextField().getText().equals("")){
                //Instanciation des dates 
                LocalDate begin = LocalDate.parse(datePickerBegin.getJFormattedTextField().getText());
                LocalDate end = LocalDate.parse(datePickerEnd.getJFormattedTextField().getText());
                LocalDate now = LocalDate.now();
                //Date de début apres aujourd'hui
                if (begin.compareTo(now) >= 0) {
                    //Date de fin apres la date de début
                    if (begin.compareTo(end) <= 0) {
                        //Ajout des dates de début et fin ainsique la quantite à l'emprunt
                        aEmprunter.setDebut(begin);
                        aEmprunter.setFin(end);
                        aEmprunter.setQuantite(1);
                        try {
                            //Ajout de l'emprunt au gestionnaire et sauvegarde de celui-ci
                            gestionEmprunts.addEmprunt(aEmprunter);
                            gestionEmprunts.sauvegarde(pathToData);
                            JOptionPane.showMessageDialog(this, "Réservation effectuée !");
                            //Fermeture des différentes fenêtre
                            dispose();
                            window.dispose();
                            dateWindow.dispose();
                        } catch (EmpruntImpossibleException e) {
                            //Exception si des emprunts se chevauchent
                            JOptionPane.showMessageDialog(dateWindow,"Le Materiel n'est pas disponible à cette date, un autre emprunt est en cours :\n" + e.getEmpruntChevauchant().toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(dateWindow,"Date de fin avant la date de début","Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(dateWindow,"Date de début avant aujourd'hui","Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}