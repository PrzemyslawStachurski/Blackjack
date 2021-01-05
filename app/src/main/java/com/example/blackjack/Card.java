package com.example.blackjack;

import android.widget.ImageView;

public class Card {
    private Suit suit;
    private Value value;
    private int imageView;

    public Card(Suit suit, Value value, int imageView) {
        this.suit = suit;
        this.value = value;
        this.imageView = imageView;
    }

    public String toStringCard() { return this.value.toString() + "_of_" + this.suit.toString(); }

    public Value getValue() {
        return this.value;
    }

    public int getImageView() {
        return imageView;
    }
}
