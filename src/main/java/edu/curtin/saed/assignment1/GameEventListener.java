package edu.curtin.saed.assignment1;

public interface GameEventListener {
    void onPlayerMove(Direction direction);
    void onItemAcquired(String itemName);
    void onMenuOptionSelected();
}
