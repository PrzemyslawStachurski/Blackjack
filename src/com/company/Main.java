package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Blackjack started");

        //Creating deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();
        //System.out.println(playingDeck);

        Deck playerHand = new Deck(); //empty deck for a player
        Deck dealerHand = new Deck(); //empty deck for a dealer

        double playerMoney = 100.00;
        Scanner userInput = new Scanner(System.in);

        //loop with the game
        while (playerMoney > 0) {
            System.out.println("you have $" + playerMoney + "how much do you wanna bet?");
            double playerBet = userInput.nextDouble();//taking the players bet

            if (playerBet>playerMoney){//poor securing the game
                System.out.println("when you try to gamble with money you dont have you get thrown out of a casino ukno");
                break;
            }

            playerHand.draw(playingDeck);//drawing a players hand
            playerHand.draw(playingDeck);

            dealerHand.draw(playingDeck);//drawing a dealer hand
            dealerHand.draw(playingDeck);


            System.out.println("your hand:");
            System.out.println(playerHand);
            System.out.println("your value: "+playerHand.cardsValue());//displaying players value

            System.out.println("dealers hand: "+dealerHand.getCard(0));




        }
        System.out.println("game over");


    }
}