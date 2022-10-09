package com.grokthecode.tutorials.staticFactoryMethods;

public class PlayerV2 {

    int age;
    String playerName;
    String game;

    private PlayerV2(int age, String playerName, String game){
        this.age = age;
        this.playerName = playerName;
        this.game = game;
    }

    public static PlayerV2 getCricketPlayer(int age, String playerName){
        return new PlayerV2(age, playerName, "Cricket");
    }

    public static PlayerV2 getFootballPlayer(int age, String playerName){
        return new PlayerV2(age, playerName, "Football");
    }

    @Override
    public String toString() {
        return "PlayerV2{" +
                "age=" + age +
                ", playerName='" + playerName + '\'' +
                ", game='" + game + '\'' +
                '}';
    }
}
