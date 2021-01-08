package com.example.blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;
    private int [] images = new int [] {R.drawable.clubs2,
            R.drawable.clubs3,
            R.drawable.clubs4,
            R.drawable.clubs5,
            R.drawable.clubs6,
            R.drawable.clubs7,
            R.drawable.clubs8,
            R.drawable.clubs9,
            R.drawable.clubs10,
            R.drawable.clubsj,
            R.drawable.clubsq,
            R.drawable.clubsk,
            R.drawable.clubsace,
            R.drawable.dia2,
            R.drawable.dia3,
            R.drawable.dia4,
            R.drawable.dia5,
            R.drawable.dia6,
            R.drawable.dia7,
            R.drawable.dia8,
            R.drawable.dia9,
            R.drawable.dia10,
            R.drawable.diaj,
            R.drawable.diaq,
            R.drawable.diak,
            R.drawable.diaace,
            R.drawable.spades2,
            R.drawable.spades3,
            R.drawable.spades4,
            R.drawable.spades5,
            R.drawable.spades6,
            R.drawable.spades7,
            R.drawable.spades8,
            R.drawable.spades9,
            R.drawable.spades10,
            R.drawable.spadesj,
            R.drawable.spadesq,
            R.drawable.spadesk,
            R.drawable.spadesace,
            R.drawable.hearts2,
            R.drawable.hearts3,
            R.drawable.hearts4,
            R.drawable.hearts5,
            R.drawable.hearts6,
            R.drawable.hearts7,
            R.drawable.hearts8,
            R.drawable.hearts9,
            R.drawable.hearts10,
            R.drawable.heartsj,
            R.drawable.heartsq,
            R.drawable.heartsk,
            R.drawable.heartsace};

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    public void createFullDeck() {
        int i = 0;
            for (Suit cardSuit : Suit.values()) {
                for (Value cardValue : Value.values()) {
                    this.cards.add(new Card(cardSuit, cardValue, images[i]));
                    i++;
                }
            }


    }

    public String toString() {
        String cardListOutput = "";
        for (Card aCard : this.cards) {
            cardListOutput += aCard.toStringCard() + "\n";
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


    public int cardsValue() {//checks the value of cards in hand
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
                    aces += 1;
                    break;
            }

        }
        for (int i = 0; i < aces; i++) {
            if (totalValue > 10) {
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }
        return totalValue;

    }

    public int deckSize() {
        return this.cards.size();
    }

    public void moveAllToDeck(Deck moveTo) {
        int thisDeckSize = this.cards.size();

        //putting cards into moveTo deck
        for (int i =0; i<thisDeckSize;i++){
            moveTo.addCard(this.getCard(i));
        }
        for (int i=0;i<thisDeckSize;i++){
            this.removeCard(0);
        }

    }
}
