package edu.curtin.maze_game.lib;

public class GameEntityFactory {

    public static GameEntity createGameEntity(String entityType, String name, String descOrReq, double x, double y, double maxX, double maxY) {
        String iconPath;
        x = Math.min(maxX - 1, Math.max(0, x));
        y = Math.min(maxY - 1, Math.max(0, y));
        
        
        switch (entityType.toLowerCase()) {
            case "obstacle":
                iconPath = "obstacleIcon.png";
                break;
            case "item":
                iconPath = "itemIcon.png";
                break;
            default:
                throw new IllegalArgumentException("Unknown entity type: " + entityType);
        }

        GridAreaIcon icon = new GridAreaIcon(x, y, 0, 1, GameEntityFactory.class.getClassLoader().getResource(iconPath),
                "");
        icon.setShown(false);
        Cord position = new Cord(x, y);

        switch (entityType.toLowerCase()) {
            case "obstacle":
                return new Obstacle(name, descOrReq, position, icon);
            case "item":
                return new GameItem(descOrReq, name, position, icon);
            default:
                throw new IllegalArgumentException("Unknown entity type: " + entityType);
        }
    }
}
