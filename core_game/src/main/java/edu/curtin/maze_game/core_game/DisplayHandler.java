package edu.curtin.maze_game.core_game;

import java.util.Map;
import edu.curtin.maze_game.lib.GameEntity;
import edu.curtin.maze_game.lib.Player;
import edu.curtin.maze_game.lib.Cord;
import edu.curtin.maze_game.lib.Fog;

public class DisplayHandler {

    public static void updateSurrondings(Map<Cord,GameEntity> mapStuff,Map<Cord,Fog> fogOfWar, Player player){
        int[][] directions = {
            {-1, 0}, 
            {1, 0},  
            {0, -1}, 
            {0, 1}   
        };
        

        for (int[] dir : directions) {
            int checkX = (int)player.getPosition().getX() + dir[0];
            int checkY = (int)player.getPosition().getY() + dir[1];
            Cord checkPos = new Cord(checkX, checkY);

            
            Fog fog = fogOfWar.get(checkPos);
            if(fog != null){
                fog.getIcon().setShown(false);;
                fogOfWar.remove(checkPos);
            }

            GameEntity entity = mapStuff.get(checkPos);
            if(entity != null){
                entity.getIcon().setShown(true);     
            }
        }
    }
}
