package edu.curtin.maze_game.lib;

// simple tuple class that takes any two values
public class Tuple <T,U>{
    private final T first;
    private final U second;

    public Tuple(T first, U second){
        this.first = first;
        this.second = second;
    }

    public T getFirst(){
        return first;
    }

    public U getSecond(){
        return second;
    }
}
