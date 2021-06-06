package frontend.materiel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import backend.Materiel.*;
/**
* Classe DelMateriel : gère l'interface graphique pour la suppression de matériel
*/
public class DelMateriel extends JFrame implements ActionListener {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private String pathToData;
    private GestionnaireMateriel gestion;
    private JButton annuler;
    private JButton supprimer;
    private JPanel panneau;
    /**
    * Constructeur AjoutMateriel : gère l'interface graphique pour l'ajout de nouveau matériel
    */
    public DelMateriel() {
        super("Supprimer un materiel");
        frame = this;
        pathToData = System.getProperty("user.dir") + "/data";
        gestion = GestionnaireMateriel.charger(pathToData);
        // Détermination de la taille de la fenêtre et placement centré de celle-ci
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        int realheight = (int) (height*0.8);
        int realwidth = (int) (width*0.3);
        int posx=(int) ((width-realwidth)/2);
        int posy=(int) ((height-realheight)/2);
        setBounds(posx, posy, realwidth, realheight);
        //Creation des boutons
        supprimer = new JButton("Supprimer");
        annuler = new JButton("Annuler");
        //Ajout d'action listenners
        annuler.addActionListener(this);
        supprimer.addActionListener(this);
        //Creation de la table des materiels existants et remplissage de celle-ci ainsi que un ascenceur
        String[] fields = { "Modèle", "Quantité", "Acheté le" , "Type" };
        Object[][] tuple = { };
        model = new DefaultTableModel(tuple, fields);
        for(Materiel m : gestion.getListeMateriel()){
            model.addRow(new Object[] { m.getModele(), m.getQuantite(), m.getAchat(), m.getClass().getSimpleName() });
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
        //Creation de panneaux pour la table et pour les boutons d'action et ajout de leurs composants repectifs
        JPanel bouton = new JPanel();
        JPanel content = new JPanel();
        bouton.add(annuler);
        bouton.add(supprimer);
        content.add(table.getTableHeader());
        content.add(scrollPane);
        //Creation du panneau principal et mise en place de sa disposition
        panneau = new JPanel();
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
        //Ajout des panneaux définis précédemment au panneau principal
        panneau.add(content);
        panneau.add(bouton);
        //Ajout du panneau principal à la fenêtre
        frame.getContentPane().add(panneau);
        //Fermeture de la fenêtre en cas d'appui sur la croix
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //Fenêtre visible
        setVisible(true);
    }

    /**
     * Action à exécuter quand un des boutons est sélectionné
     * 
     * @param evt évènement déclencheur
     */
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        //Fermeture de l'application en cas d'appui sur annuler
        if(source == annuler){
            frame.dispose();
        }
        //Si supprimer est sélectionné
        if(source == supprimer){
            //Vérification qu'un champs de la table est bien sélectionné
            if(table.getSelectedRow() >= 0){
                Integer idToDelTable = table.getSelectedRow();//Numero de ligne pour le JTable
                Integer idToDel = gestion.rechercheMaterielStricte((String) table.getValueAt(idToDelTable,0), (Integer) table.getValueAt(idToDelTable,1), (LocalDate) table.getValueAt(idToDelTable,2)); //Numero de ligne pour me model du Jtable et dans la liste de materiel
                gestion.delMateriel(gestion.getListeMateriel().get(idToDel));
                model.removeRow(idToDel);
                gestion.sauvegarde(pathToData);
            }
        }

    }
}
