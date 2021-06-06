package frontend;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Dimension;

import frontend.user.*;
import frontend.materiel.*;
import frontend.emprunts.*;

import utils.Connexion;

/**
 * Classe Menu : fenêtre principale de l'application
 */
public class Menu extends JFrame {
   private JMenuBar menuBar;
   private JMenu fichier;
   private JMenu user;
   private JMenu things;
   private JMenu emprunt;
   private JMenuItem verouiller;
   private JMenuItem fermer;
   private JMenuItem ajout;
   private JMenuItem modif;
   private JMenuItem supp;
   private JMenuItem ajout1;
   private JMenuItem modif1;
   private JMenuItem supp1;
   private JMenuItem newEmprunt;
   private JMenuItem modifEmprunt;
   private JMenuItem retour;
   private JMenuItem admin;
   private JMenuItem retardEnCours;
   private JLabel titre;
   private String imgUrl;
   private ImageIcon icone;
   private Menu fenetre;

   /**
    * Constructeur : Crée la fenêtre de base de l'application
    */
   public Menu() {
      super("Menu");
      fenetre = this;
      /*
       * login="null"; password="null";
       */

      //Détermination de la taille de la fenêtre et placement centré de celle-ci
      Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
      int height = (int) dimension.getHeight();
      int width = (int) dimension.getWidth();
      int realheight = (int) (height * 0.7);
      int realwidth = (int) (width * 0.8);
      int posx = (int) ((width - realwidth) / 2);
      int posy = (int) ((height - realheight) / 2);
      setBounds(posx, posy, realwidth, realheight);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fermeture de l'application en cas d'appui sur la croix

      //creation du menubar
      menuBar = new JMenuBar();
      this.setJMenuBar(menuBar);

      //Création des menus
      fichier = new JMenu("Fichiers");
      user = new JMenu("Utilisateurs");
      things = new JMenu("Materiels");
      emprunt = new JMenu("Emprunts");

      //ajout des menus au menubar
      menuBar.add(fichier);
      menuBar.add(user);
      menuBar.add(things);
      menuBar.add(emprunt);

      //Creation des items de menu et ajout de leurs actions respectives

      verouiller = new JMenuItem("Verouiller");
      verouiller.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // connexion(fenetre);
            JOptionPane.showMessageDialog(fenetre, "Session verouillée", "Verouillage de l'application",
                  JOptionPane.WARNING_MESSAGE);
            new Connexion(fenetre); // authentification pour le deverouillage
         }
      });
      fermer = new JMenuItem("Fermer");
      fermer.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // connexion(fenetre);
            JOptionPane.showMessageDialog(fenetre, "Fermeture de l'application", "Fermeture",
                  JOptionPane.WARNING_MESSAGE);
            System.exit(0);
         }
      });
      admin = new JMenuItem("Ajouter un administrateur");
      admin.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new AjoutAdmin();
         }
      });
      ajout = new JMenuItem("Ajouter");
      ajout.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new AjoutUser();
         }
      });
      modif = new JMenuItem("Modifier");
      modif.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new ModifUser();
         }
      });
      supp = new JMenuItem("Supprimer");
      supp.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new DelUser();
         }
      });
      ajout1 = new JMenuItem("Ajouter");
      ajout1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new AjoutMateriel();
         }
      });
      modif1 = new JMenuItem("Modifier");
      modif1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new ModifMateriel();
         }
      });
      supp1 = new JMenuItem("Supprimer");
      supp1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new DelMateriel();
         }
      });
      newEmprunt = new JMenuItem("Nouvel Emprunt");
      newEmprunt.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new AjoutEmprunt();
         }
      });
      modifEmprunt = new JMenuItem("Modifier Emprunt");
      modifEmprunt.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new ModifEmprunt();
         }
      });
      retour = new JMenuItem("Retour Emprunt");
      retour.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new Retour();
         }
      });
      retardEnCours = new JMenuItem("Emprunts en cours / en retard");
      retardEnCours.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new Listing();
         }
      });
      
      //Ajout des items à leur menu
      fichier.add(verouiller);
      fichier.add(fermer);
      user.add(admin);
      user.add(ajout);
      user.add(modif);
      user.add(supp);
      things.add(ajout1);
      things.add(modif1);
      things.add(supp1);
      emprunt.add(newEmprunt);
      emprunt.add(modifEmprunt);
      emprunt.add(retour);
      emprunt.add(retardEnCours);

      //Creation de titre d'application a l'intérieur de l'application
      titre = new JLabel("Application de gestion de l'emprunt");
      titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      titre.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
      titre.setFont(new Font("Serif", Font.BOLD, 20));

      //creation d'une image
      // URL de l'image
      imgUrl = "data/image.jpg";
      icone = new ImageIcon(imgUrl);
      // Création de JLabel avec un alignement centré
      JLabel jlabel = new JLabel(icone, JLabel.CENTER);

      //Ajout de l'image et du titre à la fenêtre
      setLayout(new BorderLayout());
      getContentPane().add(titre, BorderLayout.NORTH);
      getContentPane().add(jlabel);

      validate();

      setVisible(true);

      new Connexion(this); // Exige l'authentification au demarrage

   }
}
