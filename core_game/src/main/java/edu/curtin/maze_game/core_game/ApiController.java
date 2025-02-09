package edu.curtin.maze_game.core_game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.curtin.maze_game.api.API;
import edu.curtin.maze_game.lib.*;

public class ApiController implements API{

    private GameContext context;

    public ApiController(GameContext context){
        this.context=context;
    }

    @Override
    public Player getPlayer(){
        return context.getPlayer();
    }
    @Override
    public Cord getPlayerLocation() {
        return context.getPlayer().getPosition();
    }

    @Override
    public void setPlayerLocation(Cord newPos) {
        context.getPlayer().setPosition(newPos);
        context.getPlayer().getIcon().setPosition(newPos.getX(),newPos.getY());
    }

    
    

    @Override
    public HashMap<String,List<GameItem>> getPLayerInv() {
        return context.getPlayer().getInventoryAsIs();
    }

    @Override
    public GameItem getMostRecentItem() {
        return context.getPlayer().getLastItem();
    }

    @Override
    public void addItemToInv(GameItem item) {
        context.getPlayer().addItem(item);
    }

    @Override
    public void removeItemFromInv(GameItem item) {
        context.getPlayer().removeitem(item.getName());
    }
    @Override
    public int[] gridSize() {
        return new int[]{(int)context.getArea().getGridWidth(), (int)context.getArea().getGridHeight()};
    }

    @Override
    public GameEntity getGridSquareContent(Cord position) {
        return context.getEntities().get(position);
    }

    @Override
    public void setGridSqaureContents(Cord position, GameEntity entity) {
        context.getEntities().put(position, entity);
    }

    @Override
    public void removeGridSquareContents(Cord position) {
        context.getEntities().remove(position);
    
    }

    @Override
    public boolean isGridSqaureVisible(Cord position) {
        Fog fog = context.getFogOfWar().get(position);
        return fog !=null && fog.getIcon().isShown();
    }

    @Override
    public void setGridSqaureVisibility(Cord position, boolean visible) {
        Fog fog = context.getFogOfWar().get(position);
        if(fog != null){
            fog.getIcon().setShown(visible);
        } 
    }

    @Override
    public Map<Cord, GameEntity> getAllEntities() {
        return context.getEntities();
    }

    @Override
    public Map<Cord, Fog> getAllFog() {
        return context.getFogOfWar();
    }

    @Override
    public void setSurrondingsVisibility() {
        DisplayHandler.updateSurrondings(context.getEntities(), context.getFogOfWar(),context.getPlayer());
        setGridSqaureVisibility(getPlayerLocation(), false);
        context.getArea().repaint();
    }

	@Override
	public void placeObstacle(Cord position) {
		Obstacle obs = (Obstacle)GameEntityFactory.createGameEntity("obstacle",
             "Penalty Obstacle",
             "Live with it", 
             position.getX(), 
             position.getY(),
              context.getArea().getGridWidth(),
               context.getArea().getGridHeight());
        context.getEntities().put(position, obs);
        context.getArea().getIcons().add(obs.getIcon());
        obs.getIcon().setShown(true);
        context.getArea().repaint();
	}
}
