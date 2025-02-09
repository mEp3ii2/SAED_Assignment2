package edu.curtin.maze_game.core_game;

import edu.curtin.maze_game.lib.*;
import java.util.HashMap;
import java.util.Map;

public class GameContext {

    private GridArea area;
    private Map<Cord, GameEntity> maStuff = new HashMap<>();
    private Map<Cord, Fog> fogOfWar = new HashMap<>();
    private Player player;
    private Goal goal;
    private int counter = 0;

    public GridArea getArea() { return area; }
    public void setArea(GridArea area) { this.area = area; }

    public Map<Cord, GameEntity> getEntities() { return maStuff; }

    public Map<Cord, Fog> getFogOfWar() { return fogOfWar; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public Goal getGoal() { return goal; }
    public void setGoal(Goal goal) { this.goal = goal; }

    public int getCounter() { return counter; }
    public void incrementDate(){
        counter++;
    }
}
