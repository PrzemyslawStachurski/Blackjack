package com.company;

public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public String ToString() {
        return this.suit.toString() + "of" + this.value.toString();
    }

    public Value getValue() {
        return this.value;
    }
}
