package edu.curtin.maze_game.lib;

public class GameItem extends GameEntity{
    public GameItem(String desc, String name, Cord position, GridAreaIcon icon){
        super(name, desc, position, icon);
    }

    @Override
    public Tuple<Boolean, String> handleInteraction(Player player) {
        player.addItem(this);
        return new Tuple<Boolean,String>(true, this.getName()+":"+this.getDesc());
    }
}
