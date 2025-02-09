package edu.curtin.gameplugins;

import edu.curtin.maze_game.api.API;
import edu.curtin.maze_game.api.PluginActionListener;
import edu.curtin.maze_game.lib.Cord;
import edu.curtin.maze_game.lib.GameEntity;

public class Teleport implements PluginActionListener{
    private API api;

    public Teleport(API api){
        this.api = api;
    }

    @Override
    public void onPluginAction() {
        int[] bounds = api.gridSize();
        int maxX = bounds[0] - 1;
        int maxY = bounds[1] - 1;
        Cord newLocation;
        GameEntity entityPresent;

        
        do {
            int newX = (int) (Math.random() * (maxX + 1));  
            int newY = (int) (Math.random() * (maxY + 1));   
            newLocation = new Cord(newX, newY);

        
            entityPresent = api.getGridSquareContent(newLocation);
        } while (entityPresent != null);

        // Teleport player to the empty location
        api.setPlayerLocation(newLocation);
        api.setSurrondingsVisibility();
        
    }

}
