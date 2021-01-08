package com.example.blackjack;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.example.blackjack.play;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class BustDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Bust!")
                .setMessage("You lost this round! Your hand exceeded 21.")
                .setPositiveButton("Next round", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });

        return builder.show();
    }
}
