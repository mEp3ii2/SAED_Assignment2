package edu.curtin.saed.gameplugins;
import edu.curtin.saed.assignment1.*;
import java.util.Random;

import edu.curtin.saed.assignment1.Direction;

public class Teleport implements GameEventListener{
    private GameAPI gameAPI;
    private boolean teleportUsed =false;

    public Teleport(GameAPI gameAPI){
        this.gameAPI =gameAPI;
    }

    @Override
    public void onPlayerMove(Direction direction){ }
    @Override
    public void onItemAcquired(String itemName){}

    @Override
    public void onMenuOptionSelected(){
        if(!teleportUsed){
            int[] gridSize = gameAPI.gridSize();
            Random rand = new Random();
            int rRow = rand.nextInt(gridSize[0]);
            int rCol = rand.nextInt(gridSize[1]);

            gameAPI.setPlayerLocation(rRow, rCol);
            System.out.println("Teleported player");
            teleportUsed=true;
        }else{
            System.out.println("Teleport already used");
        }
    }
}
