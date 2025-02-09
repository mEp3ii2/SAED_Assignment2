package edu.curtin.maze_game.lib;

import java.text.MessageFormat;
import java.util.ResourceBundle;


import javax.swing.JOptionPane;

public class Obstacle extends GameEntity{
    
    private String requires;

    public Obstacle(String name, String requires, Cord position, GridAreaIcon icon){
        super(name, requires, position,icon);
        this.requires=requires;
    }

    public String getRequirement(){
        return requires;
    }

    public boolean clearObstacle(String itemName){
        return requires.equals(itemName);
    }

    
    @Override
    public Tuple<Boolean,String> handleInteraction(Player player) {
        ResourceBundle bundle = LocaleManager.getBundle();

        if(player.hasItem(requires)){

            String prompt = MessageFormat.format(bundle.getString("obstacle.interaction.prompt"), requires);
            String title = bundle.getString("obstacle.interaction.haveItemTitle");
            int response =OptionPaneLocalisation.showLocalizedConfirmDialog(prompt, title);
           

            if(response == JOptionPane.YES_OPTION){

                player.useItem(requires);
                String clearedMsg = MessageFormat.format(bundle.getString("obstacle.cleared"), requires);
                return new Tuple<Boolean,String>(true,clearedMsg);

            }else{
                String rejectMsg = MessageFormat.format(bundle.getString("obstacle.not_cleared"), requires);
                return new Tuple<Boolean,String>(false,rejectMsg);
            }
            
        }
        String reqMsg = MessageFormat.format(bundle.getString("obstacle.required"), requires);
        return new Tuple<Boolean,String>(false,reqMsg);
    }

    
}
