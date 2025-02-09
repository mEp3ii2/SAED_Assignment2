package edu.curtin.maze_game.core_game;

import edu.curtin.maze_game.api.EventManager;
import edu.curtin.maze_game.lib.Cord;
import edu.curtin.maze_game.lib.Direction;
import edu.curtin.maze_game.lib.GameEntity;
import edu.curtin.maze_game.lib.GameItem;
import edu.curtin.maze_game.lib.LocaleManager;
import edu.curtin.maze_game.lib.OptionPaneLocalisation;
import edu.curtin.maze_game.lib.Tuple;


public class MoveHandler {
    
    public static boolean movePlayer(int[] gridSize, Direction move, GameContext context, EventManager eventManager){
        double pX = context.getPlayer().getPosition().getX();
        double pY = context.getPlayer().getPosition().getY();
        double pXAttempt = pX;
        double pYAttempt = pY;

        switch (move) {
            case UP:
                pYAttempt--;
                break;
            case DOWN:
                pYAttempt++;
                break;
            case LEFT:
                pXAttempt--;
                break;
            case RIGHT:
                pXAttempt++;
                break;
        }


        if(pXAttempt >=0 && pXAttempt < gridSize[0] && pYAttempt >=0 && pYAttempt <gridSize[1]){
            Cord attemptPos = new Cord(pXAttempt, pYAttempt);
            if(!context.getEntities().isEmpty() && context.getEntities().containsKey(attemptPos)){
                GameEntity entity = context.getEntities().get(attemptPos);
                Tuple<Boolean,String> result = entity.handleInteraction(context.getPlayer());
                if(!result.getFirst()){
                    String noItemTitle = LocaleManager.getBundle().getString("obstacle.interaction.noItemTitle");
                    String noItemMessage = result.getSecond();
                    OptionPaneLocalisation.showLocalizedOkDialog(noItemMessage, noItemTitle);
                    return false;
                }

                if (entity instanceof GameItem) {
                    eventManager.notifyItemAqqurired();
                }

                entity.getIcon().setShown(false);
                context.getEntities().remove(attemptPos);
            }
            
            context.getPlayer().getIcon().setPosition(pXAttempt, pYAttempt);
            context.getPlayer().setPosition(attemptPos);
        }
        
        return true;
    } 

    
}


