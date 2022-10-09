package com.grokthecode.tutorials.staticFactoryMethods;

public class Player {

    int age;
    String playerName;
    String game;

    public Player(int age, String playerName){
        this.age = age;
        this.playerName = playerName;
        this.game = "Cricket";
    }

    @Override
    public String toString() {
        return "Player{" +
                "age=" + age +
                ", playerName='" + playerName + '\'' +
                ", game='" + game + '\'' +
                '}';
    }
}
