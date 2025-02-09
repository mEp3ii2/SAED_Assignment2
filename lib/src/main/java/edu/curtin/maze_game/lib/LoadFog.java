package edu.curtin.maze_game.lib;

import java.util.Map;

public class LoadFog {

    public static void loadFogOfWar(int[] mapSize, Map<Cord, Fog> fogOfWar,GridArea grid, Cord playerCord) {
        
        int[][] directions = {
            {0,-1},
            {0,1},
            {-1,0},
            {1,0}
        };

        for (int i = 0; i < mapSize[0]; i++) {
            for (int j = 0; j < mapSize[1]; j++) {
                Cord currPos = new Cord(i, j);
                
                if(currPos.equals(playerCord)){
                    continue;
                }

                boolean skip = false;
                for (int[] direction : directions) {
                    Cord check = new Cord(direction[0]+playerCord.getX(), direction[1]+playerCord.getY());
                    if(check.equals(currPos)){
                        skip = true;
                        break;
                    }
                }

                if(skip){
                    continue;
                }
                GridAreaIcon fogIcon = new GridAreaIcon(i, j, 0, 1, LoadFog.class.getClassLoader().getResource("fog.png"), "");
                fogIcon.setShown(true);
                Fog fog = new Fog(currPos, fogIcon);
                fogOfWar.put(currPos, fog);
                grid.getIcons().add(fogIcon);
            }
        }
    }
}

