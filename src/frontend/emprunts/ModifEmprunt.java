package frontend.emprunts;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.*;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import backend.Emprunts.*;
import utils.*;
/**
 * Classe ModifEmprunt : classe permettant de modifier un emprunt
 * on peut modifier la date de fin de l'emprunt
 * on peut modifier la date de début de l'emprunt si elle n'est pas déja passée
 */
public class ModifEmprunt extends JFrame implements ActionListener{
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JButton modifier;
    private JButton valider;
    private JPanel panneau;
    private JPanel panneauTable;
    private JDatePickerImpl datePickerBegin;
    private JDatePickerImpl datePickerEnd;
    private Emprunt toModif;
    private GestionnaireEmprunts gestion;
    private String pathToData;
    /**
     * Constructeur permettant de modifier un emprunt
     */
    public ModifEmprunt() {
        super("Modifier un emprunt");
        frame = this;

        //Chargement des emprunts déjà en stock
        pathToData = System.getProperty("user.dir") + "/data";
        gestion = GestionnaireEmprunts.charger(pathToData);

        //Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        int realheight = (int) (height*0.8);
        int realwidth = (int) (width*0.3);
        int posx=(int) ((width-realwidth)/2);
        int posy=(int) ((height-realheight)/2);
        setBounds(posx, posy, realwidth, realheight);

        //Creation de la table listant les emprunts et ajout de tous les emprunts en cours ou non encore en cours
        String[] fields = { "Modèle", "Type", "Début", "Fin" };
        Object[][] tuple = { };
        model = new DefaultTableModel(tuple, fields);
        for(Emprunt emprunt : gestion.getEmpruntsPrevus()){
            model.addRow(new Object[] { emprunt.getMateriel().getModele(), emprunt.getMateriel().getClass().getSimpleName(), emprunt.getDebut(), emprunt.getFin() });
        }
        table = new JTable(model){
            public boolean isCellEditable(int row, int col) {
                return false; //Rends la modification des cellulles impossibke
            }
        };
        table.getTableHeader().setReorderingAllowed(false); //N'autorise pas la modification de l'ordre des colonnes
        table.setAutoCreateRowSorter(true);// Possibilité de trier les colonnes 
        //Creation du bouton d'action modifier et ajout de son action listener
        modifier = new JButton("Modifier");
        modifier.addActionListener(this);
        //Creation du panneau pour la table et du panneau principal et mise en place de leur disposition
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        panneauTable = new JPanel();
        //Ajout de la table au panneau de la table
        panneauTable.add(table.getTableHeader());
        JScrollPane scrollPane = new JScrollPane(table);
        panneauTable.add(scrollPane);
        //Ajout du panneau de la table et du bouton modifier au panneau principal
        panneau.add(panneauTable);
        panneau.add(modifier);
        //Ajout du panneau principal à la fenêtre
        getContentPane().add(panneau);
        //Fermeture de la fenêtre en cas d'appui sur la croix
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Fenêtre visible
        setVisible(true);
    }

    /**
     * Creation du formulaire de modification des dates d'emprunt
     * @param toModif Emprunt à modifier
     */
    public void modification(Emprunt toModif){
        Properties pBegin;
        JDatePanelImpl datePanelBegin;
        Properties pEnd;
        JDatePanelImpl datePanelEnd;
        JLabel dateBeginLabel;
        JLabel dateEndLabel;
        //Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        int realheight = (int) (height*0.15);
        int realwidth = (int) (width*0.3);
        int posx=(int) ((width-realwidth)/2);
        int posy=(int) ((height-realheight)/2);
        setBounds(posx, posy, realwidth, realheight);
        // Creation du calendrier pour le choix de la date de début de prêt
        pBegin = new Properties();
        pBegin.put("text.today", "Today");
        pBegin.put("text.month", "Month");
        pBegin.put("text.year", "Year");
        UtilDateModel modelDateBegin = new UtilDateModel();
        datePanelBegin = new JDatePanelImpl(modelDateBegin, pBegin);
        datePickerBegin = new JDatePickerImpl(datePanelBegin, new DateLabelFormatter());
        // Creation du calendrier pour le choix de la date de fin de prêt
        pEnd = new Properties();
        pEnd.put("text.today", "Today");
        pEnd.put("text.month", "Month");
        pEnd.put("text.year", "Year");
        UtilDateModel modelDateEnd = new UtilDateModel();
        datePanelEnd = new JDatePanelImpl(modelDateEnd, pEnd);
        datePickerEnd = new JDatePickerImpl(datePanelEnd, new DateLabelFormatter());
        //Creation des labels et boutons
        dateBeginLabel = new JLabel("Date de début : ");
        dateEndLabel = new JLabel("Date de fin : ");
        valider = new JButton("Valider");
        valider.addActionListener(this);
        //Alignement des labels : centré
        dateBeginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateEndLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //Creation du panneau principal
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        //Creation du panneau pour les choix de dates et configuration de sa disposition
        JPanel datePanneau = new JPanel();
        datePanneau.setLayout(new GridLayout(3,2));
        //Ajout des composants au panneau des dates
        datePanneau.add(dateBeginLabel);
        datePanneau.add(datePickerBegin);
        datePanneau.add(dateEndLabel);
        datePanneau.add(datePickerEnd);
        //Ajout du panneau des dates au panneau principal
        panneau.add(datePanneau);
        panneau.add(valider);

    }


    /**
     * Methode actionPerformes pour gérer les actions sur les différents composants des fenêtres
     * @param evt Objet de type ActionEvent
     */
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        if(source == modifier){
            if(table.getSelectedRow()>=0){
                toModif = gestion.getEmprunt((String)table.getValueAt(table.getSelectedRow(), 0), (LocalDate) table.getValueAt(table.getSelectedRow(), 2));
                try{
                    //On enlève le panneau précédent : tableau de choix
                    frame.remove(panneau);
                    frame.repaint();
                }catch(Exception excep){
                    System.err.println(excep.getMessage());
                }finally{
                    //On crée le panneau du Formulaire de changement et on l'ajoute à la fenêre
                    modification(toModif);
                    frame.getContentPane().add(panneau);
                    setVisible(true);
                }
            }
        }
        if(source == valider){
            //Si l'utilisateur a fait son choix de dates et valide le changement
            LocalDate now = LocalDate.now();
            LocalDate begin = toModif.getDebut();
            LocalDate end = toModif.getFin();
            boolean fini = true;
            //On ne modifie pas une date si elle est laissée vide par l'utilisateur
            if(!datePickerBegin.getJFormattedTextField().getText().equals("")){
               begin = LocalDate.parse(datePickerBegin.getJFormattedTextField().getText());
               //Si la date de debut de l'emprunt n'est pas déja dépassée
               if(toModif.getDebut().compareTo(now)>=0){
                    //Si la nouvelle date n'est pas avant le jour de modification
                    if (begin.compareTo(now) >= 0) {

                    } else {
                        JOptionPane.showMessageDialog(frame,"Date de début avant aujourd'hui","Erreur",JOptionPane.ERROR_MESSAGE);
                        fini = false;
                    }
                }
            }
            //On ne modifie pas une date si elle est laissée vide par l'utilisateur
            if(!datePickerEnd.getJFormattedTextField().getText().equals("")){
                end = LocalDate.parse(datePickerEnd.getJFormattedTextField().getText());
                //Si la date de fin est bien apres la date de debut
                if (begin.compareTo(end) <= 0 || toModif.getDebut().compareTo(end) <= 0) {

                } else {
                    JOptionPane.showMessageDialog(frame,"Date de fin avant la date de début","Erreur",JOptionPane.ERROR_MESSAGE);
                    fini = false;
                }
            }
            //Les modifications se sont bien passées
            if(fini){

                try {
                    gestion.updateEmprunt(toModif, begin, end);
                    //Sauvegarde des emprunts et fermeture de la fenêtre
                    gestion.sauvegarde(pathToData);
                    dispose();
                } catch (EmpruntImpossibleException e) {
                    JOptionPane.showMessageDialog(frame,"Impossible de modifier l'emprunt, il chevauche un autre emprunt :\n" + e.getEmpruntChevauchant().toString(),"Erreur",JOptionPane.ERROR_MESSAGE);
                    fini = false;
                }
                
            }

        }
    }
}
