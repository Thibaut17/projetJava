package frontend.emprunts;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import backend.Emprunts.*;
import backend.Utilisateurs.GestionnaireUtilisateur;
import backend.Utilisateurs.Utilisateur;
/**
 * Classe Retour : crée l'interface graphique pour effectuer le retour d'emprunts terminés
 */
public class Retour extends JFrame implements ActionListener{
    private JPanel panneau;
    private JPanel panneauRecherche;
    private JPanel panneauTable;
    private JTable table;
    private DefaultTableModel model;
    private JLabel labelNom;
    private JLabel labelPrenom;
    private JTextField nom;
    private JTextField prenom;
    private JButton rechercher;
    private JButton annuler;
    private JButton suivant;
    private JFrame frame;
    private JPanel panneauRendre;
    private JTable tableRendre;
    private DefaultTableModel modelRendre;
    private JButton rendre;
    private String pathToData;
    private GestionnaireEmprunts gestionEmprunts;
    private GestionnaireUtilisateur gestionUtilisateur;
    /**
     * Constructeur : crée l'interface graphique gérant le retour des emprunts
     */
    public Retour() {
        super("Retour emprunts");
        frame = this;
        JPanel panneauBouton;
        //ouverture du gestionnaire d'emprunts et du gestionnaire utilisateur
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
        //Création des boutons labels et champs
        suivant = new JButton("suivant");
        annuler = new JButton("Annuler");
        rechercher = new JButton("Rechercher");
        nom = new JTextField(10);
        prenom = new JTextField(10);
        labelNom = new JLabel("Nom : ");
        labelPrenom = new JLabel("Prenom : ");
        //Ajout d'action listeners
        annuler.addActionListener(this);
        suivant.addActionListener(this);
        rechercher.addActionListener(this);
        //Création de panneaux et mise en place de leur disposition
        panneauRecherche = new JPanel();
        panneauTable = new JPanel();
        panneauBouton = new JPanel();
        panneauTable.setLayout(new BoxLayout(panneauTable, BoxLayout.Y_AXIS));
        //Creation de la table des utilisateurs ayant un emprunt
        String[] fields = { "Nom", "Prénom", "Service" , "Poste" };
        Object[][] tuple = { };
        model = new DefaultTableModel(tuple, fields);
        for(Utilisateur user : gestionEmprunts.getUtilisateurs()){
            model.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
        }
        table = new JTable(model){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);
        //Ascenceurs pour a table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);
        //Ajout des champs et labels de recherche au panneau pour la recherche
        panneauRecherche.add(labelNom);
        panneauRecherche.add(nom);
        panneauRecherche.add(labelPrenom);
        panneauRecherche.add(prenom);
        panneauTable.add(table.getTableHeader());
        panneauTable.add(scrollPane);
        panneauBouton.add(annuler);
        panneauBouton.add(suivant);
        //Creation et mise en place de la disposition du panneau principal
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        //Ajout des panneaux / composants au panneau principal
        panneau.add(panneauRecherche);
        panneau.add(rechercher);
        panneau.add(panneauTable);
        panneau.add(panneauBouton);
        //Ajout à la fenêtre du panneau principal
        frame.getContentPane().add(panneau);
        //Fermeture de la fenêtre en cas d'appui sur la croix
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //Fenêtre visible
        setVisible(true);
    }
    /**
     * Liste les emprunts disponible pour un retour
     * @param listeEmprunts liste des emprunts associé à l'utilisateur
     */
    public void rendre(ArrayList<Emprunt> listeEmprunts){
        //Creation de la table des emprunts
        String[] fields = { "Modèle", "Type", "Début", "Fin" };
        Object[][] tuple = { };
        modelRendre = new DefaultTableModel(tuple, fields);
        for(Emprunt emprunt : listeEmprunts){
            modelRendre.addRow(new Object[] { emprunt.getMateriel().getModele(), emprunt.getMateriel().getClass().getSimpleName(), emprunt.getDebut(), emprunt.getFin() });
        }
        tableRendre = new JTable(modelRendre){
            public boolean isCellEditable(int row, int col) {
                return false; //Rends la modification des cellulles impossibke
            }
        };
        tableRendre.getTableHeader().setReorderingAllowed(false); //N'autorise pas la modification de l'ordre des colonnes
        tableRendre.setAutoCreateRowSorter(true);// Possibilité de trier les colonnes
        JScrollPane scrollPane = new JScrollPane(tableRendre); //Ascenceur pour la table 
        //Bouton rendre et son action listener
        rendre = new JButton("Restituer");
        rendre.addActionListener(this);
        //Creation du panneau contenant la table et mise en place de sa disposition
        panneauTable = new JPanel();
        panneauTable.setLayout(new BoxLayout(panneauTable, BoxLayout.Y_AXIS));
        //Ajout de la table à ce panneau
        panneauTable.add(tableRendre.getTableHeader());
        panneauTable.add(scrollPane);
        //Creation du panneau principal et mise en place de sa disposition
        panneauRendre = new JPanel();
        panneauRendre.setLayout(new BoxLayout(panneauRendre, BoxLayout.Y_AXIS));
        //Ajout des composants au panneau principal
        panneauRendre.add(panneauTable);
        panneauRendre.add(rendre);
        //Ajout du panneau principal à la fenêtre
        frame.getContentPane().add(panneauRendre);
        //fenêtre visible
        frame.setVisible(true);
    }
    
    /**
     * Methode actionPerformes pour gérer les actions sur les différents composants des fenêtres
     * @param evt Objet de type ActionEvent
     */
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        //Recherche d'un utilisateur dans la table
        if (source == rechercher) {
            //Suppression de tous les utilisateurs présents dans la table
            for (int j = table.getRowCount() - 1; j > -1; j--) {
                model.removeRow(j);
            }
            //Ajout des utilisateurs correspondant au nom ou prénom recherché
            for(Utilisateur user : gestionEmprunts.getUtilisateurs(nom.getText(), prenom.getText())){
                model.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
            }
        }
        //Annulation
        if(source == annuler){
            frame.dispose();
        }
        //Un utilisateur emprunteur a été sélectionné
        if(source == suivant){
            
            if(table.getSelectedRow() >= 0){
                //Recherche de l'utilisateur dans la liste des emprunts correspondant au nom et au prenom sélectionné
                Utilisateur emprunteur = gestionUtilisateur.rechercheUtilisateurStricte((String) table.getValueAt(table.getSelectedRow(),0), (String) table.getValueAt(table.getSelectedRow(),1));
                //Recuperation de la liste des emprunts associé à cet utilisateur
                ArrayList<Emprunt> listeEmprunts = gestionEmprunts.getEmpruntsPrevus(emprunteur);
                //On enlève le panneau principal précédent et on raffraichit la fenêtre
                frame.remove(panneau);
                frame.repaint();
                //On affiche le panneau contenant la liste des emprunts associé à l'utilisateur
                rendre(listeEmprunts);
            }
        }
        //Un empruntest sélectionné et à rendre
        if(source == rendre){
            if(tableRendre.getSelectedRow() >= 0){
                Emprunt empruntARendre = gestionEmprunts.getEmprunt((String)tableRendre.getValueAt(tableRendre.getSelectedRow(), 0), (LocalDate) tableRendre.getValueAt(tableRendre.getSelectedRow(), 2));
                gestionEmprunts.endEmprunt(empruntARendre);
                gestionEmprunts.sauvegarde(pathToData);
                frame.dispose();
            }
        }

    }
}
