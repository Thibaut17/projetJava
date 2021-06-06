package utils;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Dimension;

import frontend.Menu;
import frontend.user.AjoutAdmin;

import java.util.*;
import backend.Utilisateurs.*;



/**
  * Classe Connexion : gestion de l'authentification de l'administrateur
  * @author Thibaut Jolive
  * @since 1.0
  */
public class Connexion extends JDialog implements ActionListener {
   // private Menu application;
   private JButton valider = new JButton("Valider");
   private JButton reset = new JButton("Reinitialiser");
   private JTextField login = new JTextField(10);
   // private JTextField passport = new JTextField(10);
   private JPasswordField mdp = new JPasswordField(10);
   private JLabel login_label;
   private JLabel mdp_label;
   private String password;
   private String log;
   private boolean tentative;
   private GestionnaireUtilisateur gestion;
   private String pathToData;
    /**
     * Procédure d'authentification
     * @param fen fenêtre recquérant la connexion 
     */
   public Connexion(Menu fen) {
      super(fen);
      //Ouverture du gestionnaire Utilisateur
      pathToData = System.getProperty("user.dir") + "/data";
      gestion = new GestionnaireUtilisateur(pathToData);
      //Initialisation du mot de passe et du login
      log = "null";
      password = "null";
      tentative = false;
      boolean premierDemarrage = false;
      //Listes des logins et mots de passe pour les administrateurs déjà enregistrés
      List<String> logins = new ArrayList<String>();
      List<String> mdps = new ArrayList<String>();
      //Chargement de ses listes
      for(Utilisateur user : gestion.getListe()){
         if(user instanceof Administrateur){
            Administrateur admin = (Administrateur) user;
            logins.add(admin.getLogin());
            mdps.add(admin.getPassword());
         }
      }
      if(logins.size()==0){
         logins.add("login");
         mdps.add((new Sha1("login").getSha1()));
         premierDemarrage = true;
      }
      //Tant que l'authentification n'est pas bonne
      while (!isValidLogin(log, password, logins, mdps)){
         //Message d'erreur si le couple identifiant/mot de passe n'est pas bon
         if (tentative) {
            JOptionPane.showMessageDialog(this, "Identifiant / mot de passe incorrect", "Erreur",
                  JOptionPane.ERROR_MESSAGE);
         }
         //Fenêtre authentification
         Login(fen);
         tentative = true;
      }
      //Connexion : affichage d'un message de bienvenue
      JOptionPane.showMessageDialog(this, "Vous êtes connectés, \n Bienvenue administrateur !");
      if(premierDemarrage) new AjoutAdmin(true);
   }
   /**
     * Crée la fenêtre de connexion
     * @param application fenêtre recquérant la connexion 
     */
   public void Login(Menu application) {
      // Détermination de la taille de la fenêtre
      Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
      int height = (int) dimension.getHeight();
      int width = (int) dimension.getWidth();
      int realheight = (int) (height * 0.1);
      int realwidth = (int) (width * 0.2);
      int posx = (int) ((width - realwidth) / 2);
      int posy = (int) ((height - realheight) / 2);
      //Creation unique de la fenêtre et de ses composants
      if (!tentative) {
         //Disposition de la fenêtre
         setLayout(new GridLayout(3, 2));
         //Impossibilité de changer la taille de la fenêtre
         setResizable(false);
         setModal(true);
         setTitle("Connexion à l'application");
         //Creation des labels et des champs
         login_label = new JLabel("Login : ");
         login_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
         mdp_label = new JLabel("Mot de passe : ");
         mdp_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
         //Ajout des composants à la fenêtre
         getContentPane().add(login_label);
         getContentPane().add(login);
         getContentPane().add(mdp_label);
         getContentPane().add(mdp);
         getContentPane().add(reset);
         getContentPane().add(valider);
         //Ajout des action listeners
         valider.addActionListener(this);
         reset.addActionListener(this);
         //Impossible de fermer la fenêtre => impossibilité de passer outre la connexion
         setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

         pack();
         //Placement centré de la fenêtre de connexion
         setBounds(posx, posy, realwidth, realheight);
         
      }
      //Bouton valider actionnable via la touche "entrée"
      super.getRootPane().setDefaultButton(valider);
      //Fenêtre visible
      setVisible(true);
   }

    /**
     * Methode verifiant si le login et le mot de passe sont présent et coherent avec ceux dans les listes
     * @param loginToVerify login à vérifier
     * @param pwdToVerify mot de passe à vérifier
     * @param logins liste de logins
     * @param pwds liste de mots de passe
     * @return renvoi vrai si le login et le mot de passe sont présent dans leur liste respective et à la même place
     */
    public boolean isValidLogin(String loginToVerify,String pwdToVerify,List<String> logins,List<String> pwds){
      boolean valid = false;
      int i=0;
      //Parcours de la liste des logins et mots de passe
      while(i<logins.size() && !valid ){
         //Verification que le login et le mot de passe correspondent et sont égaux à ceux enregistrés
         if(loginToVerify.equals(logins.get(i)) && pwdToVerify.equals(pwds.get(i))){
            valid = true;
         }
         i++;
      }
      return valid;
    }


   /**
    * Action à exécuter quand un des boutons est sélectionné
    * @param evt évènement déclencheur
    */
   public void actionPerformed(ActionEvent evt) {
      Object source = evt.getSource();
      //L'utilisateur valide sa connexion
      if (source == valider) {
         //Recuperation du login et du hash du mot de passe
         log = login.getText();
         password = (new Sha1(new String(mdp.getPassword()))).getSha1();
         //Remise à zéro des champs
         login.setText("");
         mdp.setText("");
         //Fermeture de la fenêtre
         dispose();
      }
      // L'utilisateur appui sur reset
      if (source == reset) {
         //mise à zéro des champs
         login.setText("");
         mdp.setText("");
      }
   }
}
