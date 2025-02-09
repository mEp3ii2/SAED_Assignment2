package edu.curtin.gameplugins;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import edu.curtin.maze_game.api.API;
import edu.curtin.maze_game.api.PlayerMovementListener;
import edu.curtin.maze_game.lib.Cord;


public class Penalty implements PlayerMovementListener{
    private API api;
    private long lastMoveTime;

    public Penalty(API api){
        this.api = api;
        lastMoveTime = System.currentTimeMillis();
    }

    @Override
    public void onPlayerMove() {           
        long currTime = System.currentTimeMillis();
        long timeSinceMove = currTime - lastMoveTime;

        if(timeSinceMove >= 5000){
            placePenalty();
        }
        lastMoveTime = System.currentTimeMillis();
    }

    private void placePenalty(){
        Cord playPos = api.getPlayerLocation();
        int x = (int)playPos.getX();
        int y = (int)playPos.getY();

        
        List<Cord> adjacentPositions = Arrays.asList(
        new Cord(x, y - 1), // Above
        new Cord(x, y + 1), // Below
        new Cord(x - 1, y), // Left
        new Cord(x + 1, y)  // Right
        );

        Collections.shuffle(adjacentPositions);

        for (Cord pos : adjacentPositions) {
            if (api.getGridSquareContent(pos) == null) { 
                api.placeObstacle(pos);
                return; 
            }
        }
    }
}
