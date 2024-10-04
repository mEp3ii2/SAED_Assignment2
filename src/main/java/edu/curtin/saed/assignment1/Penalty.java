package edu.curtin.saed.assignment1;

import java.util.ArrayList;
import java.util.List;

public class Penalty implements GameEventListener{
    private GameAPI gameAPI;
    private long timeOfMove;

    public Penalty(GameAPI gameAPI){
        this.gameAPI = gameAPI;
        timeOfMove = System.currentTimeMillis();
    }

    @Override
    public void onMenuOptionSelected(){}
    @Override
    public void onItemAcquired(String itemName){}

    @Override
    public void onPlayerMove(Direction direction){
        long currentTime = System.currentTimeMillis();
        if((currentTime - timeOfMove)>5000){
            int[] playerLocation = gameAPI.getPlayerLocation();
            List<int[]> possibleLocations = getAdjacentLocations(playerLocation);
            for (int[] loc : possibleLocations)
            {
                if (gameAPI.getGridContents(loc[0], loc[1]).isEmpty()) // Check for an empty location
                {
                    System.out.println("Placing penalty obstacle at: (" + loc[0] + ", " + loc[1] + ")");
                    gameAPI.setGridVisibility(loc[0], loc[1], true); // Make it visible (or use appropriate logic)
                    break;
                }
            }
        }
        timeOfMove = currentTime;
    }

    private List<int[]> getAdjacentLocations(int[] location)
    {
        List<int[]> adjacent = new ArrayList<>();
        adjacent.add(new int[] {location[0] - 1, location[1]});
        adjacent.add(new int[] {location[0] + 1, location[1]});
        adjacent.add(new int[] {location[0], location[1] - 1});
        adjacent.add(new int[] {location[0], location[1] + 1});
        return adjacent;
    }

}
