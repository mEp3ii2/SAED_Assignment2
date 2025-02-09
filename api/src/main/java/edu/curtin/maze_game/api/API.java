package edu.curtin.maze_game.api;

import java.util.Map;
import java.util.List;

import edu.curtin.maze_game.lib.*;
public interface API {

    Player getPlayer();
    Cord getPlayerLocation();
    void setPlayerLocation(Cord newPos);

    Map<String,List<GameItem>> getPLayerInv();
    GameItem getMostRecentItem();
    void addItemToInv(GameItem item);
    void removeItemFromInv(GameItem item);

    int[] gridSize();

    GameEntity getGridSquareContent(Cord position);
    void setGridSqaureContents(Cord position, GameEntity entity);
    void removeGridSquareContents(Cord position);
    void placeObstacle(Cord position);

    boolean isGridSqaureVisible(Cord position);
    void setGridSqaureVisibility(Cord position, boolean visible);
    void setSurrondingsVisibility();

    Map<Cord,GameEntity> getAllEntities();
    Map<Cord,Fog> getAllFog();
}