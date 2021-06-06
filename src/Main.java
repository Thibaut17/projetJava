import frontend.*;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
/**
 * Classe main : Classe lançant l'application.
 */
 public class Main{
    public static void main(String args[]) {
        //Mode sombre
        try {
           UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
           System.err.println(e);
        }
        //Lancement de l'application
        new Menu();
    }
 }
