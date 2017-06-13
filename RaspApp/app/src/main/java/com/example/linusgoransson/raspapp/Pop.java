package com.example.linusgoransson.raspapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by linusgoransson on 2017-03-20.
 */
public class Pop extends Activity{

    ToggleButton switchfunc1, switchfunc2,switchfunc3,switchfunc4,switchfunc5,switchfunc6,switchfunc7,switchfunc8,switchfunc9,switchfunc10,switchfunc11,switchfunc12;
    public static SeekBar seek_bar;
    public static TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);
        super.onStart();

        setContentView(R.layout.popupwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.91),(int)(height*.74));

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();


        switchfunc1 = (ToggleButton) findViewById(R.id.switchfunc1);
        boolean tgpref = sharedPreferences.getBoolean("tgpref", true);
        Log.d("TGPREFXD", String.valueOf(tgpref));
        switchfunc1.setChecked(tgpref);

        switchfunc2 = (ToggleButton)findViewById(R.id.switchfunc2);
        boolean tgpref2 = sharedPreferences.getBoolean("tgpref2", true);
        switchfunc2.setChecked(tgpref2);

        switchfunc3 = (ToggleButton)findViewById(R.id.switchfunc3);
        boolean tgpref3 = sharedPreferences.getBoolean("tgpref3", true);
        switchfunc3.setChecked(tgpref3);

        switchfunc4 = (ToggleButton)findViewById(R.id.switchfunc4);
        boolean tgpref4 = sharedPreferences.getBoolean("tgpref4", true);
        switchfunc4.setChecked(tgpref4);

        switchfunc5 = (ToggleButton)findViewById(R.id.switchfunc5);
        boolean tgpref5 = sharedPreferences.getBoolean("tgpref5", true);
        switchfunc5.setChecked(tgpref5);

        switchfunc6 = (ToggleButton)findViewById(R.id.switchfunc6);
        boolean tgpref6 = sharedPreferences.getBoolean("tgpref6", true);
        switchfunc6.setChecked(tgpref6);

        switchfunc7 = (ToggleButton)findViewById(R.id.switchfunc7);
        boolean tgpref7 = sharedPreferences.getBoolean("tgpref7", true);
        switchfunc7.setChecked(tgpref7);

        switchfunc8 = (ToggleButton)findViewById(R.id.switchfunc8);
        boolean tgpref8 = sharedPreferences.getBoolean("tgpref8", true);
        switchfunc8.setChecked(tgpref8);

        switchfunc9 = (ToggleButton)findViewById(R.id.switchfunc9);
        boolean tgpref9 = sharedPreferences.getBoolean("tgpref9", true);
        switchfunc9.setChecked(tgpref9);

        switchfunc10 = (ToggleButton)findViewById(R.id.switchfunc10);
        boolean tgpref10 = sharedPreferences.getBoolean("tgpref10", true);
        switchfunc10.setChecked(tgpref10);

        switchfunc11 = (ToggleButton)findViewById(R.id.switchfunc11);
        boolean tgpref11 = sharedPreferences.getBoolean("tgpref11", true);
        switchfunc11.setChecked(tgpref11);

        switchfunc12 = (ToggleButton)findViewById(R.id.switchfunc12);
        boolean tgpref12 = sharedPreferences.getBoolean("tgpref12", true);
        switchfunc12.setChecked(tgpref12);
        seekBarFunction();

    }
    public void seekBarFunction(){
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        int seekValue = sharedPreferences.getInt("progress_value", -1);
        String seekValueString = String.valueOf(seekValue);
        seek_bar = (SeekBar)findViewById(R.id.seekBar);
        textview = (TextView)findViewById(R.id.textView6);
        textview.setText(seekValueString +" / " +seek_bar.getMax());
        seek_bar.setProgress(seekValue);
        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener(){

                    int progressValue;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = progress;
                        textview.setText(+ progress +" / " +seek_bar.getMax());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        textview.setText(+ progressValue +" / " +seek_bar.getMax());
                        editor.putInt("progress_value",progressValue);
                        editor.commit();
                    }
                }
        );
    }

    public void onSwitchClick(View v) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch(v.getId()){
            case R.id.switchfunc1:
                editor.putBoolean("tgpref", switchfunc1.isChecked());
                editor.commit();
                break;
            case R.id.switchfunc2:
                editor.putBoolean("tgpref2", switchfunc2.isChecked());
                editor.commit();
                break;
            case R.id.switchfunc3:
                editor.putBoolean("tgpref3", switchfunc3.isChecked());
                editor.commit();
                break;
            case R.id.switchfunc4:
                editor.putBoolean("tgpref4", switchfunc4.isChecked());
                editor.commit();
                break;
            case R.id.switchfunc5:
                editor.putBoolean("tgpref5", switchfunc5.isChecked());
                editor.commit();
                break;
            case R.id.switchfunc6:
                editor.putBoolean("tgpref6", switchfunc6.isChecked());
                editor.commit();
                break;
            case R.id.switchfunc7:
                editor.putBoolean("tgpref7", switchfunc7.isChecked());
                editor.commit();
                break;
            case R.id.switchfunc8:
                editor.putBoolean("tgpref8", switchfunc8.isChecked());
                editor.commit();
                break;
            case R.id.switchfunc9:
                editor.putBoolean("tgpref9", switchfunc9.isChecked());
                editor.commit();
                break;
            case R.id.switchfunc10:
                editor.putBoolean("tgpref10", switchfunc10.isChecked());
                editor.commit();
                break;
            case R.id.switchfunc11:
                editor.putBoolean("tgpref11", switchfunc11.isChecked());
                editor.commit();
                break;
            case R.id.switchfunc12:
                editor.putBoolean("tgpref12", switchfunc12.isChecked());
                editor.commit();
                break;
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


}
