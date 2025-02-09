package edu.curtin.gameplugins;

import edu.curtin.maze_game.lib.Cord;
import edu.curtin.maze_game.lib.GameEntity;
import edu.curtin.maze_game.lib.GameItem;
import java.util.Map;

import edu.curtin.maze_game.api.API;
import edu.curtin.maze_game.api.ItemAcquisitionListener;

public class Reveal implements ItemAcquisitionListener{
    private API api;

    public Reveal(API api){
        this.api = api;
    }

    @Override
    public void onItemAcquired() {
        System.out.println("Check for map");
        GameItem item = api.getMostRecentItem();
        System.out.println(item.getName());
        if(item.getName().equalsIgnoreCase("map")){
            revealMap();
        }

    }

    private void revealMap(){
        Map<Cord,GameEntity> maStuff =api.getAllEntities();

        maStuff.forEach((position, entity)->{
            entity.getIcon().setShown(true);
            api.setGridSqaureVisibility(position, false);
        });
    }

}
