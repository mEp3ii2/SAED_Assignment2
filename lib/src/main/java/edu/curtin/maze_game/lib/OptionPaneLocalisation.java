package edu.curtin.maze_game.lib;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class OptionPaneLocalisation {

    private static int showConfirmDialog(String message, String title, String yesText, String noText) {
        JButton yesButton = new JButton(yesText);
        JButton noButton = new JButton(noText);
        JPanel panel = new JPanel();
        panel.add(yesButton);
        panel.add(noButton);

        
        JOptionPane optionPane = new JOptionPane(
                message,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{},  
                null);

        optionPane.add(panel, JOptionPane.OPTION_TYPE_PROPERTY);

        
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionPane.setValue(JOptionPane.YES_OPTION);
                JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(panel);
                dialog.dispose();
            }
        });

        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionPane.setValue(JOptionPane.NO_OPTION);
                JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(panel);
                dialog.dispose();
            }
        });

        
        JDialog dialog = optionPane.createDialog(null, title);
        dialog.setVisible(true);

        
        Object selectedValue = optionPane.getValue();
        return (selectedValue == null) ? JOptionPane.CLOSED_OPTION : (int) selectedValue;
    }

    public static int showLocalizedConfirmDialog(String message, String title) {
        
        ResourceBundle bundle = LocaleManager.getBundle();
        String yesText = bundle.getString("message.Yes");
        String noText = bundle.getString("message.No");

        return showConfirmDialog(message, title, yesText, noText);
    }

    private static void showOkDialog(String message, String title, String okText){
        JButton okButton = new JButton(okText);
        JPanel panel = new JPanel();
        panel.add(okButton);

        JOptionPane optionPane = new JOptionPane(
                message,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{},  
                null);

        optionPane.add(panel, JOptionPane.OPTION_TYPE_PROPERTY);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionPane.setValue(JOptionPane.OK_OPTION);
                JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(panel);
                dialog.dispose();
            }
        });

        JDialog dialog = optionPane.createDialog(null, title);
        dialog.setVisible(true);
    }

    public static void showLocalizedOkDialog(String message, String title ){
        String okMsg = LocaleManager.getBundle().getString("message.Ok");
        showOkDialog(message, title, okMsg);
    }
}
