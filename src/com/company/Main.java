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

            if (playerBet > playerMoney) {//poor securing the game
                System.out.println("when you try to gamble with money you dont have you get thrown out of a casino ukno");
                break;
            }
            boolean endRound = false;



            while (true) {
                playerHand.draw(playingDeck);//drawing a players hand
                playerHand.draw(playingDeck);

                dealerHand.draw(playingDeck);//drawing a dealer hand
                dealerHand.draw(playingDeck);
                
                System.out.println("your hand:");
                System.out.println(playerHand);
                System.out.println("your value: " + playerHand.cardsValue());//displaying players value

                System.out.println("dealers hand: " + dealerHand.getCard(0).toStringCard());//displaying dealers hand

                //player decision
                System.out.println("would you like to hit(1) or stand(2)");
                int response = userInput.nextInt();

                if (response == 1) {//if player hits
                    playerHand.draw(playingDeck);
                    System.out.println("you draw a : " + playerHand.getCard(playerHand.deckSize() - 1).toStringCard());
                    //checking for bust
                    if (playerHand.cardsValue() > 21) {
                        System.out.println("you busted, your value is :" + playerHand.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }

                } else if (response == 2) {
                    break;
                }

                //revealing dealer cards
                System.out.println("dealer cards: " + dealerHand.toString());

                //checking if dealer has more points than player
                if ((dealerHand.cardsValue() > playerHand.cardsValue()) && endRound == false) {
                    System.out.println("dealer beats you");
                    playerMoney -= playerBet;
                    endRound = true;
                }

                //dealer draws at 16 stands at 17
                while ((dealerHand.cardsValue() < 17) &&(dealerHand.cardsValue()>playerHand.cardsValue())&&(endRound == false)) {
                    dealerHand.draw(playingDeck);
                    System.out.println("dealer drew: " + dealerHand.getCard(dealerHand.deckSize() - 1).toStringCard());
                }

                System.out.println("dealer`s value is :" + dealerHand.cardsValue());

                if ((dealerHand.cardsValue() > 21) && endRound == false) {
                    System.out.println("dealer busts, you in");
                    playerMoney += playerBet;
                    endRound=true;
                }
                if (playerHand.cardsValue()==dealerHand.cardsValue()&&endRound==false){
                    System.out.println("push");
                    endRound=true;
                }
                if (playerHand.cardsValue()>dealerHand.cardsValue()&&endRound==false){
                    System.out.println("you wwin the hand");
                    playerMoney+=playerBet;
                    endRound=true;
                }else if(endRound==false){
                    System.out.println("you lose tha hand ");
                    playerMoney-=playerBet;
                    endRound=true;
                }
                playerHand.moveAllToDeck(playingDeck);
                dealerHand.moveAllToDeck(playingDeck);
                System.out.println("end of hand");

            }


        }
        System.out.println("game over");


    }
}