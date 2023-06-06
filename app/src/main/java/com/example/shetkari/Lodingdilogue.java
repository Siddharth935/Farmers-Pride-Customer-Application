package com.example.shetkari;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class Lodingdilogue {

    private Activity activity;
    private AlertDialog dialog;

    public Lodingdilogue(Activity myactivity){
        activity = myactivity;

    }

    void startLoadingAnimation(){
        AlertDialog.Builder  builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dilogue, null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }

    void dismissDilogue(){
        dialog.dismiss();
    }
}
