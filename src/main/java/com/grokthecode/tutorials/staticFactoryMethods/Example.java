package com.grokthecode.tutorials.staticFactoryMethods;

//http://www.sumondey.com/static-factory-methods-instead-of-public-constructors-in-java/
public class Example {

    public static void main(String[] args) {
        var firstPlayer = new Player(22, "Sam");

        System.out.println(firstPlayer);

        var cricketPlayer = PlayerV2.getCricketPlayer(22, "Sam");
        var footballPlayer = PlayerV2.getFootballPlayer(22, "Sam");

        System.out.println(cricketPlayer);
        System.out.println(footballPlayer);
    }
}
