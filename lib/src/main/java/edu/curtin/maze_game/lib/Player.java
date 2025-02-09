package edu.curtin.maze_game.lib;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.text.Normalizer;

public class Player {
    private Cord position;
    private HashMap<String, List<GameItem>> inventory;  //allows for multiple of the same item, and allows for fast retrieve 
    private GridAreaIcon icon;
    private GameItem lastItem;

    public Player(Cord startingPos, GridAreaIcon icon){
        position = startingPos;
        inventory = new HashMap<>();
        this.icon = icon;
    }

    public Cord getPosition(){
        return position;
    }

    public void setPosition(double xCord, double yCord){
        position.setPosition(xCord, yCord);
    }

    public void setPosition(Cord position){
        this.position = position;
    }

    public GridAreaIcon getIcon(){
        return icon;
    }
        //checks if the key is already present
        //  if not inserts into new list 
        // else it returns the original list and inserts into the original list 
    public void addItem(GameItem item){
        inventory.computeIfAbsent(item.getName(), e-> new ArrayList<>()).add(item);
        lastItem = item;
    }

    public boolean hasItem(String name){
        return inventory.keySet().stream().anyMatch(itemName -> hasRequiredItem(name, itemName));
    }

    private boolean hasRequiredItem(String requiredItemName, String playerItemName) {
        String normalizedRequired = Normalizer.normalize(requiredItemName, Normalizer.Form.NFC);
        String normalizedPlayerItem = Normalizer.normalize(playerItemName, Normalizer.Form.NFC);
        return normalizedRequired.equalsIgnoreCase(normalizedPlayerItem);
    }


    //yes i know that i could have just gone and changed this name of the method either in the controller or moveHandle but ehh
    // i like the idea of knowing why i was removing an item even though both methods lead to the same thing
    public void useItem(String name){
        disposeItem(name);
    }

    public void removeitem(String name){
        disposeItem(name);
    }

    public void disposeItem(String name){
        List<GameItem> items = inventory.get(name);
        if(!items.isEmpty() && items != null){
            items.remove(0);
            if(items.isEmpty()){
                inventory.remove(name);
            }
        }
    }

    public String getInventory(){
        ResourceBundle bundle = LocaleManager.getBundle();
        StringBuilder invText = new StringBuilder();

        if(inventory.isEmpty()){
            return bundle.getString("inventory.empty");
        }

        for(String itemName: inventory.keySet()){
            List<GameItem> items = inventory.get(itemName);
            int count = items.size();
            String item = MessageFormat.format(bundle.getString("inventory.item_count"), itemName,count);
            invText.append(item).append("\n");

            if(!items.isEmpty()){
                String description = items.get(0).getDesc();
                invText.append("  - ").append(description).append("\n");
            }

            
        }
        return invText.toString();
    }

    public HashMap<String, List<GameItem>> getInventoryAsIs(){
        return inventory;
    }

    public GameItem getLastItem(){
        return lastItem;
    }
}