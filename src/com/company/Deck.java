package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    public void createFullDeck() {
        for (Suit cardSuit : Suit.values()) {
            for (Value cardValue : Value.values()) {
                this.cards.add(new Card(cardSuit, cardValue));

            }
        }
    }

    public String toString() {
        String cardListOutput = "";
        for (Card aCard : this.cards) {
            cardListOutput += aCard.ToStringCard() + "\n";
        }
        return cardListOutput;
    }

    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        Random random = new Random();
        int randomCardIndex = 0;
        int originalsize = this.cards.size();
        for (int i = 0; i < originalsize; i++) {
            randomCardIndex = random.nextInt((this.cards.size() - 1 - 0) + 1); //generating rando indexform the deck
            tempDeck.add(this.cards.get(randomCardIndex)); //adding card from deck with random index to temporary deck
            this.cards.remove(randomCardIndex); //removing added card from the deck
        }
        this.cards = tempDeck;
    }

    public void removeCard(int i) {
        this.cards.remove(i);
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public void addCard(Card addCard) {
        this.cards.add(addCard);
    }

    public void draw(Deck comingFrom) {//draws from the deck
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }


    public int cardsValue() {//checks the avlue of cards in hand
        int totalValue = 0;
        int aces = 0;

        for (Card aCard : this.cards) {
            switch (aCard.getValue()) {
                case Two:
                    totalValue += 2;
                    break;
                case Three:
                    totalValue += 3;
                    break;
                case Four:
                    totalValue += 4;
                    break;
                case Five:
                    totalValue += 5;
                    break;
                case Six:
                    totalValue += 6;
                    break;
                case Seven:
                    totalValue += 7;
                    break;
                case Eight:
                    totalValue += 8;
                    break;
                case Nine:
                    totalValue += 9;
                    break;
                case Ten:
                    totalValue += 10;
                    break;
                case Jack:
                    totalValue += 10;
                    break;
                case King:
                    totalValue += 10;
                    break;
                case Queen:
                    totalValue += 10;
                    break;
                case Ace:
                    totalValue += 1;
                    break;
            }

        }
        for (int i = 0; i < aces; i++){
            if (totalValue>10){
                totalValue+=1;
            }else{
                totalValue+=11;
            }
        }
            return totalValue;
    }
}
