package frontend.user;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

import backend.Utilisateurs.*;

/**
 * Classe DelUser : gère l'interface graphique pour la suppression d'utilisateurs
 */
public class DelUser extends JFrame implements ActionListener{
    private JFrame frame;
    private JButton supprimer;
    private JButton annuler;
    private JButton rechercher;
    private JLabel labelNom;
    private JLabel labelPrenom;
    private JTextField nom;
    private JTextField prenom;
    private JPanel panneau;
    private JPanel panneaubis;
    private JPanel panneau3;
    private JPanel panneau4;
    private JTable table;
    private DefaultTableModel model;
    private String pathToData;
    private GestionnaireUtilisateur gestion;
    private Utilisateur toDel;

    /**
     * Constructeur de la classe : gère l'interface graphique pour la suppression d'utilisateur
     */
    public DelUser() {
        super("Supprimer un utilisateur");
        frame = this;
        //chargement des utilisateurs
        pathToData = System.getProperty("user.dir") + "/data";
        gestion = new GestionnaireUtilisateur(pathToData);
        //Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        int realheight = (int) (height*0.8);
        int realwidth = (int) (width*0.3);
        int posx=(int) ((width-realwidth)/2);
        int posy=(int) ((height-realheight)/2);
        setBounds(posx, posy, realwidth, realheight);

        //Creation des labels, champs et boutons
        supprimer = new JButton("Supprimer");
        annuler = new JButton("Annuler");
        rechercher = new JButton("Rechercher");
        nom = new JTextField(10);
        prenom = new JTextField(10);
        labelNom = new JLabel("Nom : ");
        labelPrenom = new JLabel("Prenom : ");
        //Ajout d'action listeners aux boutons
        annuler.addActionListener(this);
        supprimer.addActionListener(this);
        rechercher.addActionListener(this);
        //Creation des panneaux et mise en place de leur configuration
        panneau3 = new JPanel();
        panneau4 = new JPanel();
        panneau = new JPanel();
        panneaubis = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        panneau4.setLayout(new BoxLayout(panneau4, BoxLayout.Y_AXIS));

        //Création du tableau d'utilisateurs ajout des utilisateurs connus et mise en place d'ascenceurs
        String[] fields = { "Nom", "Prénom", "Service" , "Poste" };
        Object[][] tuple = { };
        model = new DefaultTableModel(tuple, fields);
        for(Utilisateur user : gestion.getListe()){
            model.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
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

        //Ajout des labels et champs de recherche
        panneau3.add(labelNom);
        panneau3.add(nom);
        panneau3.add(labelPrenom);
        panneau3.add(prenom);
        //Ajout du tableau
        panneau4.add(table.getTableHeader());
        panneau4.add(scrollPane);
        //Ajout de boutons d'actions
        panneaubis.add(annuler);
        panneaubis.add(supprimer);
        //Ajout des panneaux précédents à un panneau principal
        panneau.add(panneau3);
        panneau.add(rechercher);
        panneau.add(panneau4);
        panneau.add(panneaubis);
        //ajout de ce panneau global a la fenetre
        frame.getContentPane().add(panneau);
        //Fermeture de la fenetre si appui sur la croix
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //fenetre visible
        setVisible(true);
    }
    
   /**
    * Action à exécuter quand un des boutons est sélectionné
    * @param evt évènement déclencheur
    */
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        //La source est le bouton rechercher
        if (source == rechercher) {
            //Suppression de tous les utilisateurs présents dans la table
            for (int j = table.getRowCount() - 1; j > -1; j--) {
                model.removeRow(j);
            }
            if(nom.getText().equals("") && prenom.getText().equals("")){
                //Si le prenom et le nom sont vide alors ajout de tous les utilisateurs
                for(Utilisateur user : gestion.getListe()){
                    model.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
                }
            }else{
                //Ajout des utilisateurs correspondant au nom ou prénom recherché
                for(Utilisateur user : gestion.rechercheUtilisateur(nom.getText(), prenom.getText())){
                    model.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
                }
            }
        }
        //La source est le bouton annuler
        if(source == annuler){
            frame.dispose();
        }
        //La source est le bouton supprimer
        if(source == supprimer){
            //Un utilisateur est bien sélectionné
            if(table.getSelectedRow() >= 0){
                //Recuperation des données de l'utilisateur
                toDel = gestion.rechercheUtilisateurStricte((String) table.getValueAt(table.getSelectedRow(),0), (String) table.getValueAt(table.getSelectedRow(),1));
                //On ne supprime pas un administrateur
                if(toDel instanceof Administrateur){
                    JOptionPane.showMessageDialog(frame,"Impossible de supprimer l'utilisateur : " +toDel.fullName() + " est un administrateur","Erreur",JOptionPane.ERROR_MESSAGE);
                }else{
                    //utilisateur standard
                    toDel.supprimerSauvegarde(pathToData);
                    model.removeRow(table.getSelectedRow());
                    gestion.delUtilisateur(toDel);
                }
                
            }
        }

    }


}
