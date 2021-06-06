package frontend.user;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;

import backend.Utilisateurs.Administrateur;
import utils.Sha1;
/**
 * Classe AjoutAdmin permettant de fournir une interface graphique pour ajouter des utilisateurs
 * @author Thibaut Jolive
 * @since 1.0
 */
public class AjoutAdmin extends JFrame implements ActionListener {
    JTextField nom;
    JTextField prenom;
    JTextField fonction;
    JTextField service;
    JTextField adresse;
    JTextField login;
    JPasswordField password;
    JTextField numeroBureau;
    JTextField telephone;
    JButton ajouter;
    JButton reset;
    JDialog frame;
    boolean jdialog;

    /**
     * Constructeur AjoutAdmin permettant de fournir une interface graphique pour ajouter des administrateurs
     * @author Thibaut Jolive
     * @since 1.0
     */
    public AjoutAdmin() {
        super("Ajouter un administrateur");
        jdialog = false;
        JLabel nomLabel;
        JLabel prenomLabel;
        JLabel fonctionLabel;
        JLabel serviceLabel;
        JLabel adresseLabel;
        JLabel loginLabel;
        JLabel passwordLabel;
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
        setBounds(posx, posy, realwidth, realheight);

        //Création des labels, champs et boutons
        nomLabel = new JLabel("Nom :");
        prenomLabel = new JLabel("Prénom :");
        fonctionLabel = new JLabel("Fonction :");
        serviceLabel = new JLabel("Service :");
        adresseLabel = new JLabel("Adresse :");
        loginLabel = new JLabel("Login :");
        passwordLabel = new JLabel("Mot de passe :");
        numeroBureauLabel = new JLabel("Numéro de bureau :");
        telephoneLabel = new JLabel("Numéro de téléphone :");
        nom = new JTextField();
        prenom = new JTextField();
        fonction = new JTextField();
        service = new JTextField();
        adresse = new JTextField();
        login = new JTextField();
        password = new JPasswordField();
        numeroBureau = new JTextField();
        telephone = new JTextField();
        ajouter = new JButton("Ajouter");
        reset = new JButton("Réinitialiser");

        //Ajout des action listeners
        ajouter.addActionListener(this);
        reset.addActionListener(this);

        //Choix de la disposition de la fenêtre
        setLayout(new GridLayout(10,2));
        //Disposition des labels : centré
        nomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prenomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serviceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adresseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroBureauLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adresseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        telephoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fonctionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        //Ajout des composants à la fenêtre
        getContentPane().add(nomLabel);
        getContentPane().add(nom);
        getContentPane().add(prenomLabel);
        getContentPane().add(prenom);
        getContentPane().add(fonctionLabel);
        getContentPane().add(fonction);
        getContentPane().add(serviceLabel);
        getContentPane().add(service);
        getContentPane().add(adresseLabel);
        getContentPane().add(adresse);
        getContentPane().add(numeroBureauLabel);
        getContentPane().add(numeroBureau);
        getContentPane().add(telephoneLabel);
        getContentPane().add(telephone);
        getContentPane().add(loginLabel);
        getContentPane().add(login);
        getContentPane().add(passwordLabel);
        getContentPane().add(password);
        getContentPane().add(reset);
        getContentPane().add(ajouter);

        //Fenêtre visible
        setVisible(true);
    }

    /**
     * Constructeur AjoutAdmin permettant de fournir une interface graphique pour ajouter des administrateurs
     * @param premierDemarrage Permet de savoir si l'application est démarrée pour la première fois
     * @author Thibaut Jolive
     * @since 1.0
     */
    public AjoutAdmin(boolean premierDemarrage) {
        frame = new JDialog();
        frame.setTitle("Ajouter un administrateur");
        frame.setModal(true);
        jdialog = true;
        //super("Ajouter un administrateur");
        JLabel nomLabel;
        JLabel prenomLabel;
        JLabel fonctionLabel;
        JLabel serviceLabel;
        JLabel adresseLabel;
        JLabel loginLabel;
        JLabel passwordLabel;
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

        //Création des labels, champs et boutons
        nomLabel = new JLabel("Nom :");
        prenomLabel = new JLabel("Prénom :");
        fonctionLabel = new JLabel("Fonction :");
        serviceLabel = new JLabel("Service :");
        adresseLabel = new JLabel("Adresse :");
        loginLabel = new JLabel("Login :");
        passwordLabel = new JLabel("Mot de passe :");
        numeroBureauLabel = new JLabel("Numéro de bureau :");
        telephoneLabel = new JLabel("Numéro de téléphone :");
        nom = new JTextField();
        prenom = new JTextField();
        fonction = new JTextField();
        service = new JTextField();
        adresse = new JTextField();
        login = new JTextField();
        password = new JPasswordField();
        numeroBureau = new JTextField();
        telephone = new JTextField();
        ajouter = new JButton("Ajouter");
        reset = new JButton("Réinitialiser");

        //Ajout des action listeners
        ajouter.addActionListener(this);
        reset.addActionListener(this);

        //Choix de la disposition de la fenêtre
        frame.setLayout(new GridLayout(10,2));
        //Disposition des labels : centré
        nomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prenomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serviceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adresseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroBureauLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adresseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        telephoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fonctionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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
        frame.getContentPane().add(loginLabel);
        frame.getContentPane().add(login);
        frame.getContentPane().add(passwordLabel);
        frame.getContentPane().add(password);
        frame.getContentPane().add(reset);
        frame.getContentPane().add(ajouter);

        if(premierDemarrage){
            frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
        else{
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        //Fenêtre visible
        frame.setVisible(true);
    }

   /**
    * Action à exécuter quand un des boutons est sélectionné
    * @param evt évènement déclencheur
    */
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        Administrateur user;
        Integer bureau;
        //Le bouton sélectionné est ajouter
        if(source == ajouter){
            String pathToData = System.getProperty("user.dir") + "/data";
            //Conersion du numéro de bureau en entier
            try{
                bureau = Integer.parseInt(numeroBureau.getText());
                user = new Administrateur(nom.getText(),prenom.getText(),fonction.getText(),service.getText(),adresse.getText(),bureau,telephone.getText(),login.getText(),(new Sha1(new String(password.getPassword()))).getSha1());
                user.sauvegarde(pathToData);
                if(jdialog) frame.dispose();
                else dispose();
            }
            catch(NumberFormatException e){
                numeroBureau.setText("");
            }
        }
        //Remise à zéro des champs en cas de sélection du bouton reset
        if(source == reset){
            nom.setText("");
            prenom.setText("");
            fonction.setText("");
            service.setText("");
            adresse.setText("");
            login.setText("");
            password.setText("");
            numeroBureau.setText("");
            telephone.setText("");
        }
    }
}
