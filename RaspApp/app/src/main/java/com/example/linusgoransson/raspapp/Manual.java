package com.example.linusgoransson.raspapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.io.IOException;

/**
 * Created by linusgoransson on 2017-05-15.
 */
public class Manual extends Activity{
    Button buttonIn, buttonOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.manual_window);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.91),(int)(height*.64));
        buttonIn = (Button)findViewById(R.id.buttonIn);
        buttonOut = (Button)findViewById(R.id.buttonOut);
        buttonIn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Log.d("buttonIn","down");
                    new sendFunction().execute("IN");
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Log.d("buttonIn","Up");
                    new sendFunction().execute("INRELEASE");
                }
                return true;
            }
        });
        buttonOut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Log.d("buttonOut","down");
                    new sendFunction().execute("OUT");
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Log.d("buttonOut","Up");
                    new sendFunction().execute("OUTRELEASE");
                }
                return true;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Manualstate","Closed");
        new sendFunction().execute("MANCLOSE");
    }

    public void onButtonManualOut(View v) throws IOException, InterruptedException {
        new sendFunction().execute("OUT");
        }
    public void onButtonManualUp(View v) throws IOException, InterruptedException {
        new sendFunction().execute("UP");
    }
    public void onButtonManualDown(View v) throws IOException, InterruptedException {
        new sendFunction().execute("DOWN");
    }
}
