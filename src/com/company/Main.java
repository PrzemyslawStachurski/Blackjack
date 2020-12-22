package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String[] ranks = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] deck = new String[52];

        for (int i = 0; i < deck.length; i++){
            for (int j = 0; j < suits.length; j++) {
                for (int k = 0; k < ranks.length; k++) {

                    deck[i] = ranks[k] + " of " + suits[j];
                    i++;
                }
            }
        }
        Collections.shuffle(Arrays.asList(deck));

        for (int i =0;i<deck.length;i++){
            System.out.println(deck[i]);
        }

    }
}