package frontend.user;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import backend.Utilisateurs.*;

/**
 * Classe ModifUser : gère linterface graphique pour modifier des utilisateurs
 */
public class ModifUser extends JFrame implements ActionListener{
    private JButton suivant;
    private JButton annuler;
    private JButton rechercher;
    private JButton modifier;
    private JButton reset;

    private JPanel panneau;
    
    private JTable table;
    private DefaultTableModel model;

    private JLabel nom;
    private JLabel prenom;

    private JTextField nomRecherche;
    private JTextField prenomRecherche;
    private JTextField fonction;
    private JTextField service;
    private JTextField adresse;
    private JTextField numeroBureau;
    private JTextField telephone;

    private JFrame frame;

    private String pathToData;
    private GestionnaireUtilisateur gestion;
    private Utilisateur toModif;
    /**
     * Constructeur ModifUser : gère l'interface graphique pour la modification d'utilisateur
     */
    public ModifUser() {
        super("Modifier un utilisateur");
        frame = this;
        JLabel labelNomRecherche;
        JLabel labelPrenomRecherche;
        JPanel panneaubis;
        JPanel panneau3;
        JPanel panneau4;

        //Chargement des utilisateurs existants
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
        //Création des boutons, champs et labels
        suivant = new JButton("suivant");
        annuler = new JButton("Annuler");
        rechercher = new JButton("Rechercher");
        nomRecherche = new JTextField(10);
        prenomRecherche = new JTextField(10);
        labelNomRecherche = new JLabel("Nom : ");
        labelPrenomRecherche = new JLabel("Prenom : ");
        //Ajout des action listeners
        annuler.addActionListener(this);
        suivant.addActionListener(this);
        rechercher.addActionListener(this);
        //Création des panneaux et choix de leur disposition
        panneau3 = new JPanel();
        panneau4 = new JPanel();
        panneau = new JPanel();
        panneaubis = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        panneau4.setLayout(new BoxLayout(panneau4, BoxLayout.Y_AXIS));
        //Creation de la table des utilisateurs et ajouts des utilisateurs existants
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

        //Ajout des composants aux panneaux
        panneau3.add(labelNomRecherche);
        panneau3.add(nomRecherche);
        panneau3.add(labelPrenomRecherche);
        panneau3.add(prenomRecherche);
        panneau4.add(table.getTableHeader());
        panneau4.add(scrollPane);
        panneaubis.add(annuler);
        panneaubis.add(suivant);

        //Ajout des panneaux aux panneau principal
        panneau.add(panneau3);
        panneau.add(rechercher);
        panneau.add(panneau4);
        panneau.add(panneaubis);
        //Ajout du panneau principal à la fenêtre
        frame.getContentPane().add(panneau);
        //fermeture de la fenêtre en cas d'appui sur la croix
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //Fenêtre visible
        setVisible(true);
    }
   /**
    * Création du formulaire de modification
    */
    public void modification(){
        JLabel nomLabel;
        JLabel prenomLabel;
        JLabel fonctionLabel;
        JLabel serviceLabel;
        JLabel adresseLabel;
        JLabel numeroBureauLabel;
        JLabel telephoneLabel;
        //Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        int realheight = (int) (height*0.4);
        int realwidth = (int) (width*0.2);
        int posx=(int) ((width-realwidth)/2);
        int posy=(int) ((height-realheight)/2);
        frame.setBounds(posx, posy, realwidth, realheight);
        //Création des labels et des champs
        nomLabel = new JLabel("Nom :");
        prenomLabel = new JLabel("Prénom :");
        fonctionLabel = new JLabel("Fonction :");
        serviceLabel = new JLabel("Service :");
        adresseLabel = new JLabel("Adresse :");
        numeroBureauLabel = new JLabel("Numéro de bureau :");
        telephoneLabel = new JLabel("Numéro de téléphone :");
        nom = new JLabel(toModif.getNom());
        prenom = new JLabel(toModif.getPrenom());
        fonction = new JTextField(toModif.getFonction());
        service = new JTextField(toModif.getService());
        adresse = new JTextField(toModif.getAdresse());
        numeroBureau = new JTextField(String.valueOf(toModif.getNumeroBureau()));
        telephone = new JTextField(toModif.getNumeroTelephone());

        //Création des boutons et ajout des action listeners
        modifier = new JButton("Modifier");
        reset = new JButton("Réinitialiser");
        modifier.addActionListener(this);
        reset.addActionListener(this);
        //Choix de la disposition de la fentêtre
        frame.setLayout(new GridLayout(8,2));
        //Alignement des labels : Centré
        nomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prenomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serviceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adresseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroBureauLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adresseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        telephoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fonctionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        //Ajout des composants à la fenêtre
        frame.getContentPane().add(nomLabel);
        frame.getContentPane().add(nom);
        frame.getContentPane().add(prenomLabel);
        frame.getContentPane().add(prenom);
        frame.getContentPane().add(fonctionLabel);
        frame.getContentPane().add(fonction);
        frame.getContentPane().add(serviceLabel);
        frame.getContentPane().add(service);
        frame.getContentPane().add(adresseLabel);
        frame.getContentPane().add(adresse);
        frame.getContentPane().add(numeroBureauLabel);
        frame.getContentPane().add(numeroBureau);
        frame.getContentPane().add(telephoneLabel);
        frame.getContentPane().add(telephone);
        frame.getContentPane().add(reset);
        frame.getContentPane().add(modifier);

        //fenêtre visible
        setVisible(true);
    }

   /**
    * Action à exécuter quand un des boutons est sélectionné
    * @param evt évènement déclencheur
    */
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        if (source == rechercher) {
            //Suppression de tous les utilisateurs présents dans la table
            for (int j = table.getRowCount() - 1; j > -1; j--) {
                model.removeRow(j);
            }
            if(nomRecherche.getText().equals("") && prenomRecherche.getText().equals("")){
                //Si le prenom et le nom sont vide alors ajout de tous les utilisateurs
                for(Utilisateur user : gestion.getListe()){
                    model.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
                }
            }else{
                //Ajout des utilisateurs correspondant au nom ou prénom recherché
                for(Utilisateur user : gestion.rechercheUtilisateur(nomRecherche.getText(), prenomRecherche.getText())){
                    model.addRow(new Object[] { user.getNom(), user.getPrenom(), user.getService(), user.getFonction() });
                }
            }
        }
        //Fermeture de la fenêtre en cas d'annulation
        if(source == annuler){
            frame.dispose();
        }
        //Modification de l'utilisateur si le bouton modifier est sélectionné
        if(source == modifier){
            Integer bureau;
            try {
                //Conversion du numero de bureau en entier
                bureau = Integer.parseInt(numeroBureau.getText());
                //Modification d'un utilisateur en préservant le caractère administrateur de l'utilisateur
                toModif.setNom(nom.getText());
                toModif.setPrenom(prenom.getText());
                toModif.setAdresse(adresse.getText());
                toModif.setFonction(fonction.getText());
                toModif.setService(service.getText());
                toModif.setNumeroBureau(bureau);
                toModif.setNumeroTelephone(telephone.getText());
                toModif.sauvegarde(pathToData);
                frame.dispose(); //fermeture de la fenêtre
                JOptionPane.showMessageDialog(this, "Modification effectuée !");
            } catch (NumberFormatException e) {
                numeroBureau.setText("");
                JOptionPane.showMessageDialog(this, "Le numéro de bureau doit être un entier !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Ouverture du formulaire de modification
        if(source == suivant){
            //Le formulaire n'est ouvert que si un utilisateur est bien sélectionné
            if(table.getSelectedRow() >= 0){
                toModif = gestion.rechercheUtilisateurStricte((String) table.getValueAt(table.getSelectedRow(),0), (String) table.getValueAt(table.getSelectedRow(),1));
                frame.remove(panneau);
                modification();
            }
        }

    }

    
}
