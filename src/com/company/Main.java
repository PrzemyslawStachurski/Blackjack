package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {


        System.out.println("Blackjack started");

        //Creating deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        System.out.println(playingDeck);

    }
}
