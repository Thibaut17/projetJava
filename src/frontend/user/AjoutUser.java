package frontend.user;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;

import backend.Utilisateurs.*;

/**
 * Classe AjoutUser permettant de fournir une interface graphique pour ajouter
 * des utilisateurs
 * 
 * @author Thibaut Jolive
 * @since 1.0
 */
public class AjoutUser extends JFrame implements ActionListener {
    private JLabel nom_label;
    private JLabel prenom_label;
    private JLabel fonction_label;
    private JLabel service_label;
    private JLabel adresse_label;
    private JLabel numeroBureau_label;
    private JLabel telephone_label;
    private JTextField nom;
    private JTextField prenom;
    private JTextField fonction;
    private JTextField service;
    private JTextField adresse;
    private JTextField numeroBureau;
    private JTextField telephone;
    private JButton ajouter;
    private JButton reset;
    private Utilisateur user;

    /**
     * Constructeur AjoutUser permettant de fournir une interface graphique pour
     * ajouter des utilisateurs
     * 
     * @author Thibaut Jolive
     * @since 1.0
     */
    public AjoutUser() {
        super("Ajouter un utilisateur");
        //Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int realheight = (int) (height * 0.4);
        int realwidth = (int) (width * 0.2);
        int posx = (int) ((width - realwidth) / 2);
        int posy = (int) ((height - realheight) / 2);
        setBounds(posx, posy, realwidth, realheight);
        //Creation des labels, champs de texte et boutons
        nom_label = new JLabel("Nom :");
        prenom_label = new JLabel("Prénom :");
        fonction_label = new JLabel("Fonction :");
        service_label = new JLabel("Service :");
        adresse_label = new JLabel("Adresse :");
        numeroBureau_label = new JLabel("Numéro de bureau :");
        telephone_label = new JLabel("Numéro de téléphone :");
        nom = new JTextField();
        prenom = new JTextField();
        fonction = new JTextField();
        service = new JTextField();
        adresse = new JTextField();
        numeroBureau = new JTextField();
        telephone = new JTextField();
        ajouter = new JButton("Ajouter");
        reset = new JButton("Réinitialiser");
        //Ajout de l'ecoute d'action sur les boutons
        ajouter.addActionListener(this);
        reset.addActionListener(this);
        //Organisation de la fenêtre
        setLayout(new GridLayout(8, 2));
        //Alignement des labels : Centré
        nom_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prenom_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        service_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adresse_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroBureau_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adresse_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        telephone_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fonction_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //Ajout des composants à la fenêtre
        getContentPane().add(nom_label);
        getContentPane().add(nom);
        getContentPane().add(prenom_label);
        getContentPane().add(prenom);
        getContentPane().add(fonction_label);
        getContentPane().add(fonction);
        getContentPane().add(service_label);
        getContentPane().add(service);
        getContentPane().add(adresse_label);
        getContentPane().add(adresse);
        getContentPane().add(numeroBureau_label);
        getContentPane().add(numeroBureau);
        getContentPane().add(telephone_label);
        getContentPane().add(telephone);
        getContentPane().add(reset);
        getContentPane().add(ajouter);
        //Fermeture de la fenêtre d'ajout en cas d'appui sur la croix
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Fenêtre visible
        setVisible(true);
    }

   /**
    * Action à exécuter quand un des boutons est sélectionné
    * @param evt évènement déclencheur
    */
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        Integer bureau;
        if (source == ajouter) {
            String pathToData = System.getProperty("user.dir") + "/data";
            try {
                //Conversion du numero de bureau en entier
                bureau = Integer.parseInt(numeroBureau.getText());
                //Création et ajout d'un utilisateur
                user = new Utilisateur(nom.getText(), prenom.getText(), fonction.getText(), service.getText(),
                        adresse.getText(), bureau, telephone.getText());
                user.sauvegarde(pathToData);
                dispose();
            } catch (NumberFormatException e) {
                numeroBureau.setText("");
                JOptionPane.showMessageDialog(this, "Le numéro de bureau doit être un entier !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (source == reset) {
            //Remise à zéro des champs de texte
            nom.setText("");
            prenom.setText("");
            fonction.setText("");
            service.setText("");
            adresse.setText("");
            numeroBureau.setText("");
            telephone.setText("");
        }

    }

}
