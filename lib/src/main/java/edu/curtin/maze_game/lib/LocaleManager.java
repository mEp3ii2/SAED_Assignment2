package edu.curtin.maze_game.lib;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class LocaleManager {
    private static Locale currLocal = Locale.getDefault();
    private static ResourceBundle bundle = ResourceBundle.getBundle("bundle", currLocal);
    private static final Locale[] supportedLocales ={
        new Locale("en","US"),
        new Locale("en", "AU"),
        new Locale("fil", "PH")
    };

    public static Locale getCurrentLocale(){
        return currLocal;
    }

    public static ResourceBundle getBundle(){
        return bundle;
    }

    public static void changeLocal(){
        
        Locale[] systemLocales = Locale.getAvailableLocales(); 
        
        Arrays.sort(systemLocales,Comparator.comparing(Locale::getDisplayName));
        String[] langOptions = new String[systemLocales.length];
        for(int i = 0;i<systemLocales.length;i++){
            langOptions[i] = systemLocales[i].getDisplayName();
        }
        
        JComboBox<String> languageBox = new JComboBox<>(langOptions);
        int result = JOptionPane.showConfirmDialog(null, languageBox, 
                "Select Language", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            
            int selectedIndex = languageBox.getSelectedIndex();
            Locale selLocal = systemLocales[selectedIndex];
            boolean isSupported = Arrays.stream(supportedLocales)
                                        .anyMatch(supportedLocales ->
                                        supportedLocales.equals(selLocal));
            if(isSupported){
                try{
                    ResourceBundle newBundle = ResourceBundle.getBundle("bundle", selLocal);
    
                    currLocal = selLocal;
                    bundle = newBundle;
    
                    JOptionPane.showMessageDialog(null, 
                        "Locale changed to: " + currLocal.getDisplayName(),
                        "Locale Change", JOptionPane.INFORMATION_MESSAGE);
                }catch(MissingResourceException e){
                    JOptionPane.showMessageDialog(null, 
                        "The "+selLocal.getDisplayName()+" is not available",  
                        "Locale Not Available", 
                        JOptionPane.WARNING_MESSAGE);
                        currLocal = Locale.getDefault();
                        bundle = ResourceBundle.getBundle("bundle", currLocal);
                }
            }else {
                
                JOptionPane.showMessageDialog(null, 
                    "The " + selLocal.getDisplayName() + " locale is not implemented yet.",
                    "Locale Not Supported", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
