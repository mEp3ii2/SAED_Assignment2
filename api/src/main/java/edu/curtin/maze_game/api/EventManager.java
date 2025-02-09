package edu.curtin.maze_game.api;

import java.util.ArrayList;
import java.util.List;


public class EventManager {

    private List<PlayerMovementListener> movementListeners = new ArrayList<>();
    private List<ItemAcquisitionListener> itemListeners = new ArrayList<>();
    private List<PluginActionListener> pluginActionListeners = new ArrayList<>();


    public void registerMovementListener(PlayerMovementListener listener){
        movementListeners.add(listener);
    }

    public void registerItemListener(ItemAcquisitionListener listener){
        itemListeners.add(listener);
    }

    public void registerPluginListener(PluginActionListener listener){
        pluginActionListeners.add(listener);
    }

    public void notifyPlayerMove(){
        for (PlayerMovementListener listener : movementListeners) {
            listener.onPlayerMove();
        }
    }

    public void notifyItemAqqurired(){
        for (ItemAcquisitionListener listener : itemListeners) {
            listener.onItemAcquired();
        }
    }

    public void notifyPlugin(){
        for (PluginActionListener listener : pluginActionListeners) {
            listener.onPluginAction();
        }
    }
}
