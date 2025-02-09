package edu.curtin.maze_game.lib;

public class Fog {

    Cord posistion;
    GridAreaIcon icon;

    public Fog(Cord position, GridAreaIcon icon) {
        this.posistion = position;
        this.icon = icon;
    }

    public GridAreaIcon getIcon(){
        return icon;
    }
    

    

}
