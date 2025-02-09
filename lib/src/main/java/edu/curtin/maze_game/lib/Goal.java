package edu.curtin.maze_game.lib;

import java.text.MessageFormat;

import javax.swing.JOptionPane;

public class Goal extends GameEntity{

    private int daysTaken;
    
    public Goal(String name, String desc, Cord position, GridAreaIcon icon) {
        super(name, desc, position, icon);
        daysTaken=0;
    }

    @Override
    public Tuple<Boolean, String> handleInteraction(Player player) {
        String gameWinTxt = MessageFormat.format(LocaleManager.getBundle().getString("game.days_elapsed"), this.getDaysTaken());

        JOptionPane.showMessageDialog(null, 
        LocaleManager.getBundle().getString("goal.reached")+" "+gameWinTxt, 
        LocaleManager.getBundle().getString("goal.title"), 
        JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);//close the game here
        return new Tuple<Boolean,String>(true, getDesc());//needed could be handled nicer but this will do
        
    }

    public void incrementDay(){
        daysTaken++;
    }

    public int getDaysTaken(){
        return daysTaken;
    }

}
