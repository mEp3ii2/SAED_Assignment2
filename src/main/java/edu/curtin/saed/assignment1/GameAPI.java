package edu.curtin.saed.assignment1;

import java.util.List;

public interface GameAPI {
    int[] getPlayerLocation();

    void setPlayerLocation(int row, int col);
    List<String> getPlayerInv();
    String getLastAcquiredItem();
    int[] gridSize();
    String getGridContents(int row, int col);
    void setGridVisibility(int row, int col,boolean isVisible);
    boolean isGridVisible(int row, int col);

}
