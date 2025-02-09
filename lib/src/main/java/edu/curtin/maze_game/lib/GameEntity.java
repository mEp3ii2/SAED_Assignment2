package edu.curtin.maze_game.lib;

public abstract class GameEntity {

    private String name;
    private String desc;
    private Cord position;
    private GridAreaIcon icon;

    public GameEntity(String name, String desc, Cord position, GridAreaIcon icon) {
        this.desc = desc;
        this.name = name;
        this.position = position;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public Cord getPosition(){
        return position;
    }
    public void setPosition(double xCord, double yCord) {
        position.setPosition(xCord, yCord);
        icon.setPosition(xCord, yCord);
    }

    public void setPosition(Cord position) {
        this.position = position;
        icon.setPosition(position.getX(), position.getY());
    }

    public GridAreaIcon getIcon(){
        return icon;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GameEntity entity = (GameEntity) obj;
        return name.equals(entity.name) && desc.equals(entity.desc);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, desc);
    }

    public abstract Tuple<Boolean,String> handleInteraction(Player player);
}
