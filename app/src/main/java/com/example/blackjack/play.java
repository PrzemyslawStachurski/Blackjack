package com.example.blackjack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Random;
import java.util.Scanner;

public class play extends AppCompatActivity {

    private Button confirm_bet;
    private Button hit_button;
    private Button stand_button;
    int playerBet;
    Deck playerHand = new Deck(); //empty deck for a player
    Deck dealerHand = new Deck();//empty deck for a dealer
    int playerMoney = 100;
    boolean betConfirmed = false;
    boolean endRound = false;
    Deck playingDeck = new Deck();

    ImageView dealersCard1;
    ImageView dealersCard2;
    ImageView dealersCard3;
    ImageView dealersCard4;
    ImageView dealersCard5;
    ImageView yourCard1;
    ImageView yourCard2;
    ImageView yourCard3;
    ImageView yourCard4;
    ImageView yourCard5;
    int hitCount = 0;
    int dealerCount = 0;
    TextView textViewDValue;
    TextView textViewYValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        final EditText betInput = findViewById(R.id.betInput);

        TextView textView = (TextView) findViewById(R.id.cash1);
        textView.setText("$" + playerMoney);

        //Creating deck
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        dealersCard1 = (ImageView) findViewById(R.id.dealersCard1);
        dealersCard1.setX(2000);
        dealersCard2 = (ImageView) findViewById(R.id.dealersCard2);
        dealersCard2.setX(2000);
        dealersCard3 = (ImageView) findViewById(R.id.dealersCard3);
        dealersCard3.setX(2000);
        dealersCard4 = (ImageView) findViewById(R.id.dealersCard4);
        dealersCard4.setX(2000);
        dealersCard5 = (ImageView) findViewById(R.id.dealersCard5);
        dealersCard5.setX(2000);

        yourCard1 = (ImageView) findViewById(R.id.yourCard1);
        yourCard1.setX(2000);
        yourCard2 = (ImageView) findViewById(R.id.yourCard2);
        yourCard2.setX(2000);
        yourCard3 = (ImageView) findViewById(R.id.yourCard3);
        yourCard3.setX(2000);
        yourCard4 = (ImageView) findViewById(R.id.yourCard4);
        yourCard4.setX(2000);
        yourCard5 = (ImageView) findViewById(R.id.yourCard5);
        yourCard5.setX(2000);


        confirm_bet = (Button) findViewById(R.id.confirm_bet);
        confirm_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temporaryBet = Integer.parseInt(betInput.getText().toString());
                endRound = false;


                if (temporaryBet > playerMoney) {
                    openDialog();
                } else if (!betConfirmed) {
                    playerBet = temporaryBet;
                    playerHand.draw(playingDeck);//drawing a players hand
                    playerHand.draw(playingDeck);

                    dealerHand.draw(playingDeck);//drawing a dealer hand
                    dealerHand.draw(playingDeck);
                    updateValues();


                    dealersCard1.animate().translationXBy(-2000).rotation(360).setDuration(1000); // animation showing dealer`s cards and displaying their images
                    dealersCard1.setImageResource(dealerHand.getCard(0).getImageView());
                    dealersCard2.setImageResource(R.drawable.card_back);
                    dealersCard2.animate().translationXBy(-2000).rotation(360).setDuration(1000);

                    yourCard1.animate().translationXBy(-2000).rotation(360).setDuration(1000);// animation showing player`s cards and displaying their images
                    yourCard1.setImageResource(playerHand.getCard(0).getImageView());
                    yourCard2.animate().translationXBy(-2000).rotation(360).setDuration(1000);
                    yourCard2.setImageResource(playerHand.getCard(1).getImageView());


                    if (playerHand.cardsValue() == 21) {
                        blackjackDialog();
                        playerMoney += playerBet * 1.5;
                    }
                    betConfirmed = true;
                }
            }
        });

        hit_button = (Button) findViewById(R.id.hit);
        hit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (hitCount) {
                    case 0:
                        playerHand.draw(playingDeck);
                        yourCard3.animate().translationXBy(-2000).rotation(360).setDuration(1000);
                        yourCard3.setImageResource(playerHand.getCard(2).getImageView());
                        hitCount++;
                        bustCheck();
                        updateValues();
                        break;

                    case 1:
                        playerHand.draw(playingDeck);
                        yourCard4.animate().translationXBy(-2000).rotation(360).setDuration(1000);
                        yourCard4.setImageResource(playerHand.getCard(3).getImageView());
                        hitCount++;
                        bustCheck();
                        updateValues();
                        break;

                    case 2:
                        playerHand.draw(playingDeck);
                        yourCard5.animate().translationXBy(-2000).rotation(360).setDuration(1000);
                        yourCard5.setImageResource(playerHand.getCard(4).getImageView());
                        hitCount++;
                        bustCheck();
                        updateValues();
                        break;
                }

            }
        });

        stand_button = (Button) findViewById(R.id.stand);
        stand_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dealersCard2.setImageResource(dealerHand.getCard(1).getImageView());

                winCheck();


            }
        });

    }

    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cannot bet!");
        builder.setMessage("The bet cannot exceed your total cash.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    public void bustDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bust!");
        builder.setMessage("You lost this round! Your hand exceeded 21. Your value was: " + playerHand.cardsValue());
        builder.setPositiveButton("Next round", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endingRound();
            }
        });
        builder.show();
    }

    public void winDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("End of round!");
        builder.setMessage("You won this hand. Your value was: " + playerHand.cardsValue() + " Dealer's value was: " + dealerHand.cardsValue());
        builder.setPositiveButton("Next round", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endingRound();
            }
        });
        builder.show();
    }

    public void pushDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("End of round!");
        builder.setMessage("It's a push. Your value was: " + playerHand.cardsValue());
        builder.setPositiveButton("Next round", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endingRound();
            }
        });
        builder.show();
    }

    public void loseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("End of round!");
        builder.setMessage("You lost this hand! Your value was: " + playerHand.cardsValue() + " Dealer's value was: " + dealerHand.cardsValue());
        builder.setPositiveButton("Next round", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endingRound();
            }
        });
        builder.show();
    }

    public void blackjackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Blackjack!");
        builder.setMessage("You won this hand! You get 1.5 times of your bet.");
        builder.setPositiveButton("Next round", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endingRound();
            }
        });
        builder.show();
    }

    public void endingRound() {
        dealersCard1.animate().translationXBy(2000).rotation(360).setDuration(1);
        dealersCard2.animate().translationXBy(2000).rotation(360).setDuration(1);
        yourCard1.animate().translationXBy(2000).rotation(360).setDuration(1);
        yourCard2.animate().translationXBy(2000).rotation(360).setDuration(1);

        switch (hitCount) {

            case 1:
                yourCard3.animate().translationXBy(2000).rotation(360).setDuration(1);
                hitCount = 0;
                break;
            case 2:
                yourCard3.animate().translationXBy(2000).rotation(360).setDuration(1);
                yourCard4.animate().translationXBy(2000).rotation(360).setDuration(1);
                hitCount = 0;
                break;
            case 3:
                yourCard3.animate().translationXBy(2000).rotation(360).setDuration(1);
                yourCard4.animate().translationXBy(2000).rotation(360).setDuration(1);
                yourCard5.animate().translationXBy(2000).rotation(360).setDuration(1);
                hitCount = 0;
                break;
        }

        switch (dealerCount) {

            case 1:
                dealersCard3.animate().translationXBy(2000).rotation(360).setDuration(1);
                dealerCount = 0;
                break;
            case 2:
                dealersCard3.animate().translationXBy(2000).rotation(360).setDuration(1);
                dealersCard4.animate().translationXBy(2000).rotation(360).setDuration(1);
                dealerCount = 0;
                break;
            case 3:
                dealersCard3.animate().translationXBy(2000).rotation(360).setDuration(1);
                dealersCard4.animate().translationXBy(2000).rotation(360).setDuration(1);
                dealersCard5.animate().translationXBy(2000).rotation(360).setDuration(1);
                dealerCount = 0;
                break;
        }

        TextView textView = (TextView) findViewById(R.id.cash1);
        textView.setText("$" + playerMoney);
        textViewDValue.setText("");
        textViewYValue.setText("");
        playerHand.moveAllToDeck(playingDeck);
        dealerHand.moveAllToDeck(playingDeck);
        playingDeck.shuffle();

        betConfirmed = false;


    }

    public void bustCheck() {
        if (playerHand.cardsValue() > 21) { //losing end
            playerMoney -= playerBet;

            bustDialog();

        }
    }

    public void dealerBustCheck() {
        if (dealerHand.cardsValue() > 21) {
            playerMoney += playerBet;

            winDialog();
        }
    }


    public void updateValues() {
        textViewDValue = (TextView) findViewById(R.id.textViewDValue);
        textViewDValue.setText(String.valueOf(dealerHand.cardsValue()));

        textViewYValue = (TextView) findViewById(R.id.textViewYValue);
        textViewYValue.setText(String.valueOf(playerHand.cardsValue()));

    }

    public void winCheck() {


        if (dealerHand.cardsValue() == 21 && !endRound) {
            playerMoney -= playerBet;
            loseDialog();
            endRound = true;
            updateValues();
        }
        //dealer draws at 16 stands at 17
        while ((dealerHand.cardsValue() < 17) && (dealerHand.cardsValue() < playerHand.cardsValue()) && !endRound) {//dealer hasnt reached cap

            switch (dealerCount) {

                case 0:
                    dealerHand.draw(playingDeck);

                    dealersCard3.animate().translationXBy(-2000).rotation(360).setDuration(1000);
                    dealersCard3.setImageResource(dealerHand.getCard(2).getImageView());

                    dealerCount++;
                    updateValues();
                    dealerBustCheck();
                    break;

                case 1:
                    dealerHand.draw(playingDeck);

                    dealersCard4.animate().translationXBy(-2000).rotation(360).setDuration(1000);
                    dealersCard4.setImageResource(dealerHand.getCard(3).getImageView());

                    dealerCount++;
                    updateValues();
                    dealerBustCheck();
                    break;

                case 2:
                    dealerHand.draw(playingDeck);

                    dealersCard5.animate().translationXBy(-2000).rotation(360).setDuration(1000);
                    dealersCard5.setImageResource(dealerHand.getCard(4).getImageView());

                    dealerCount++;
                    updateValues();
                    dealerBustCheck();
                    break;
            }
        }

        if (playerHand.cardsValue() == dealerHand.cardsValue() && !endRound) {//push
            pushDialog();

            endRound = true;
            updateValues();

        }
        if (playerHand.cardsValue() > dealerHand.cardsValue() && !endRound) {//you win

            playerMoney += playerBet;
            winDialog();

            endRound = true;
            updateValues();

        }
        if (playerHand.cardsValue() < dealerHand.cardsValue() && !endRound){
            playerMoney+= playerBet;
            loseDialog();
            endRound=true;
            updateValues();
        }
    }
}













