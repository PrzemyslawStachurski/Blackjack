package com.company;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }
    public void createFullDeck(){
        for (Suit cardSuit : Suit.values()){
            for (Value cardValue: Value.values()) {
                this.cards.add(new Card(cardSuit,cardValue));
                
            }
        }
    }

    public String toString(){
        String cardListOutput="";
        int i =0;
        for (Card aCard :this.cards){
            cardListOutput += "\n"+ i +"-"+aCard;
            i++;
        }
        return cardListOutput.toString();
    }
}
