package edu.curtin.maze_game.lib;

import java.util.Objects;

public class Cord {
    private double xCord;
    private double yCord;

    public Cord(double xCord, double yCord){
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public double getX(){
        return xCord;
    }

    public double getY(){
        return yCord;
    }

    public void setPosition(double xCord, double yCord){
        this.xCord = xCord;
        this.yCord = yCord;
    }

    //need this to use it in dict

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cord that = (Cord) o;
        return xCord == that.xCord && yCord == that.yCord;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCord, yCord);
    }
}
