package frontend.emprunts;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import backend.Emprunts.*;
import backend.Utilisateurs.GestionnaireUtilisateur;
import backend.Utilisateurs.Utilisateur;
/**
 * Classe Listing : liste selon le choix les emprunts en cours relatifs à un utilisateur / les utilisateurs ayant un emprunt en retard
 */
public class Listing extends JFrame implements ActionListener{
    private JRadioButton enCours;
    private JRadioButton retard;
    private JPanel panneauRadio;
    private JPanel panneau;
    private JPanel panneauRecherche;
    private ButtonGroup groupeRadio;
    private JTable tableEnCours;
    private DefaultTableModel modelEnCours;
    private JTable tableEnCoursUser;
    private DefaultTableModel modelEnCoursUser;
    private JTable tableRetard;
    private DefaultTableModel modelRetard;
    private JLabel labelNom;
    private JLabel labelPrenom;
    private JTextField nom;
    private JTextField prenom;
    private JButton rechercher;
    private JButton suivant;
    private JFrame frame;
    private String pathToData;
    private GestionnaireEmprunts gestionEmprunts;
    private GestionnaireUtilisateur gestionUtilisateur;
    /**
     * Constructeur Listing : liste selon le choix les emprunts en cours relatifs à un utilisateur / les utilisateurs ayant un emprunt en retard
     */
    public Listing() {
        super("Gestion des emprunts");
        frame = this;
        //Chargement des gestionnaires
        pathToData = System.getProperty("user.dir") + "/data";
        gestionEmprunts = GestionnaireEmprunts.charger(pathToData);
        gestionUtilisateur = new GestionnaireUtilisateur(pathToData);
        //Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        int realheight = (int) (height*0.8);
        int realwidth = (int) (width*0.3);
        int posx=(int) ((width-realwidth)/2);
        int posy=(int) ((height-realheight)/2);
        setBounds(posx, posy, realwidth, realheight);
        //Creation des boutons radios, de leur groupe et ajout des boutons au groupe
        enCours = new JRadioButton("En cours");
        retard = new JRadioButton("En retard");
        groupeRadio = new ButtonGroup();
        groupeRadio.add(enCours);
        groupeRadio.add(retard);
        //Creation du panneau pour les boutons radios et aout de ceux-ci au panneau
        panneauRadio = new JPanel();
        panneauRadio.add(enCours);
        panneauRadio.add(retard);
        //Ajout de actionListener aux boutons radios
        enCours.addActionListener(this);
        retard.addActionListener(this);
        //Fermeture de la fenêtre en cas d'appui sur la croix
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //mise en place de la disposition de la fenêtre
        setLayout(new BorderLayout());
        panneau = new JPanel();
        panneau.add(panneauRadio);
        //Ajout du panneau à la fenêtre
        getContentPane().add(panneau);
        //Fenêtre visible
        setVisible(true);
    }
    /**
     * Crée le panneau contenant la liste des utilisateurs ayant un emprunt en cours
     */
    public void cours(){
        JPanel panneauTable;
        //creation des labels champs et boutons
        rechercher = new JButton("Rechercher");
        nom = new JTextField(10);
        prenom = new JTextField(10);
        labelNom = new JLabel("Nom : ");
        labelPrenom = new JLabel("Prenom : ");
        suivant = new JButton("Suivant");
        //Ajout d'action listener
        rechercher.addActionListener(this);
        suivant.addActionListener(this);
        //Creation de la table pour lister les utilisateurs ayant un emprunt en cours
        String[] fields = { "Nom", "Prénom", "Service" , "Poste" };
        Object[][] tuple = { };
        modelEnCours = new DefaultTableModel(tuple, fields);
        //for(Utilisateur user : gestionEmprunts.getUtilisateursEnCours(now)){
        for(Utilisateur user : gestionEmprunts.getUtilisateurs()){
            modelEnCours.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
        }
        tableEnCours = new JTable(modelEnCours){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tableEnCours.getTableHeader().setReorderingAllowed(false);
        tableEnCours.setAutoCreateRowSorter(true);
        //Ascenceur pour la table
        JScrollPane scrollPane = new JScrollPane(tableEnCours);
        scrollPane.setVisible(true);
        //Creation des panneaux pour la recherche et la table et mise en place de leur disposition
        panneauRecherche = new JPanel();
        panneauTable = new JPanel();
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        panneauTable.setLayout(new BoxLayout(panneauTable, BoxLayout.Y_AXIS));
        //Ajout des composants à leur panneau respectif
        panneauRecherche.add(labelNom);
        panneauRecherche.add(nom);
        panneauRecherche.add(labelPrenom);
        panneauRecherche.add(prenom);
        panneauTable.add(tableEnCours.getTableHeader());
        panneauTable.add(scrollPane);
        //Ajout des panneaux / composants au panneau principal
        panneau.add(panneauRadio);
        panneau.add(panneauRecherche);
        panneau.add(rechercher);
        panneau.add(panneauTable);
        panneau.add(suivant);
    }
    /**
     * Crée le panneau contenant la liste des utilisateurs ayant un emprunt en retard
     */
    public void retardataire(){
        JPanel panneauTable;
        //Creation de la table listant les utilisateurs en retard à la date actuelle
        String[] fields = { "Nom", "Prénom", "Service", "Poste" };
        Object[][] tuple = {};
        LocalDate now = LocalDate.now();
        modelRetard = new DefaultTableModel(tuple, fields);
        for(Utilisateur user : gestionEmprunts.getUtilisateursEnRetard(now)){
            modelRetard.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
        }
        tableRetard = new JTable(modelRetard){
            public boolean isCellEditable(int row, int col) {
                return false; //Rends la modification des cellulles impossibke
            }
        };
        tableRetard.getTableHeader().setReorderingAllowed(false); //N'autorise pas la modification de l'ordre des colonnes
        tableRetard.setAutoCreateRowSorter(true);// Possibilité de trier les colonnes 
        //Creation des boutons radios pour changer de liste
        enCours = new JRadioButton("En cours");
        retard = new JRadioButton("En retard");
        //Mise des boutons dans un groupe + panneau dédié
        groupeRadio = new ButtonGroup();
        panneauRadio = new JPanel();
        groupeRadio.add(enCours);
        groupeRadio.add(retard);
        panneauRadio.add(enCours);
        panneauRadio.add(retard);
        //retard selectionne par defaut
        retard.setSelected(true);
        //Ajout action listenners
        enCours.addActionListener(this);
        retard.addActionListener(this);
        //Creation de panneau pour la table et panneau principal + mise en place de leur disposition
        panneauTable = new JPanel();
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        panneauTable.setLayout(new BoxLayout(panneauTable, BoxLayout.Y_AXIS));
        //Ascenceur pour la table
        JScrollPane scrollPane = new JScrollPane(tableRetard);
        //Ajout de la table à son panneau
        panneauTable.add(tableRetard.getTableHeader());
        panneauTable.add(scrollPane);
        //Ajout du panneau pour choisir la liste + panneau pour la table des utilisateurs en retard au panneau principal
        panneau.add(panneauRadio);
        panneau.add(panneauTable);
    }
    /**
     * Crée le panneau contenant la liste des emprunts en cours d'un utilisateur
     * @param listeEmprunts liste des emprunts d'un utilisateur
     */
    public void enCoursUser(ArrayList<Emprunt> listeEmprunts){
        /**/
        JPanel panneauTable;
        //Création de la table des utilisateurs ayant un emprunt en cours
        String[] fields = { "Matériel", "type", "Début", "Fin" };
        Object[][] tuple = {};
        modelEnCoursUser = new DefaultTableModel(tuple, fields);
        for(Emprunt emprunt : listeEmprunts){
            modelEnCoursUser.addRow(new Object[] { emprunt.getMateriel().getModele(), emprunt.getMateriel().getClass().getSimpleName(), emprunt.getDebut(), emprunt.getFin() });
        }
        tableEnCoursUser = new JTable(modelEnCoursUser){
            public boolean isCellEditable(int row, int col) {
                return false; //Rends la modification des cellulles impossibke
            }
        };
        tableEnCoursUser.getTableHeader().setReorderingAllowed(false); //N'autorise pas la modification de l'ordre des colonnes
        tableEnCoursUser.setAutoCreateRowSorter(true);// Possibilité de trier les colonnes 
        //enCours sélectionné par défaut
        enCours.setSelected(true);
        //Creation de panneau pour la table, panneau principal et mise en place de leur disposition
        panneauTable = new JPanel();
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        panneauTable.setLayout(new BoxLayout(panneauTable, BoxLayout.Y_AXIS));
        //Ajout de la table au panneau de la table
        panneauTable.add(tableEnCoursUser.getTableHeader());
        JScrollPane scrollPane = new JScrollPane(tableEnCoursUser);
        panneauTable.add(scrollPane);
        //Ajout du panneau des boutons radios et du panneau de la table au panneau principal
        panneau.add(panneauRadio);
        panneau.add(panneauTable);
        
    }
    /**
     * Methode actionPerformes pour gérer les actions sur les différents composants des fenêtres
     * @param evt Objet de type ActionEvent
     */
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        //Si le bouton selectionné est celui pour voir les emprunts en cours
        if(source == enCours){
                //On enleve tout
                //frame.remove(panneauRadio);
                frame.remove(panneau);
                //On rafraichit la fenetre et on lance la creation de l'interface pour lister les utilisateurs avec des emprunts en cours
                frame.repaint();
                cours();
                frame.getContentPane().add(panneau);
                frame.setVisible(true);
        }
        //Si le bouton selectionné est celui pour voir les emprunts en retard
        if(source == retard){
                //On enleve tout
                //frame.remove(panneau);
                frame.remove(panneau);
                //On rafraichit la fenetre et on lance la creation de l'interface pour lister les utilisateurs avec des emprunts en retard
                frame.repaint();
                retardataire();
                //Ajout du panneau principal à la fenêtre
                frame.getContentPane().add(panneau);
                //Fenêtre visible
                frame.setVisible(true);
        }
        if (source == rechercher) {
            //Suppression de tous les utilisateurs présents dans la table
            for (int j = tableEnCours.getRowCount() - 1; j > -1; j--) {
                modelEnCours.removeRow(j);
            }
            if(nom.getText().equals("") && prenom.getText().equals("")){
                //Si le prenom et le nom sont vide alors ajout de tous les utilisateurs ayant emprunté un matériel
                for(Utilisateur user : gestionEmprunts.getUtilisateurs()){
                    modelEnCours.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
                }
            }else{
                //Ajout des utilisateurs correspondant au nom ou prénom recherché
                for(Utilisateur user : gestionEmprunts.getUtilisateurs(nom.getText(), prenom.getText())){
                    modelEnCours.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
                }
            }
            
        }
        if (source == suivant){
            //Lister les emprunts si un champs est bien sélectionné
            if(tableEnCours.getSelectedRow() >= 0){
                Utilisateur emprunteur = gestionUtilisateur.rechercheUtilisateurStricte((String) tableEnCours.getValueAt(tableEnCours.getSelectedRow(),0), (String) tableEnCours.getValueAt(tableEnCours.getSelectedRow(),1));
                ArrayList<Emprunt> listeEmprunts = gestionEmprunts.getEmpruntsPrevus(emprunteur);
                //On enleve tout
                frame.remove(panneau);
                //On rafraichit la fenetre et on lance la creation de l'interface pour lister les emprunts de l'utilisateur sélectionné précédemment
                frame.repaint();
                enCoursUser(listeEmprunts);
                //Ajout du panneau principal à la fenêtre
                frame.getContentPane().add(panneau);
                //Fenêtre visible
                frame.setVisible(true);
            }
            
        }
        

    }
}
