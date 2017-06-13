package com.example.linusgoransson.raspapp;

import android.content.Context;
import android.media.Image;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by linusgoransson on 2017-03-16.
 */
public class TabSelect extends Fragment implements AdapterView.OnItemSelectedListener{
    //variabel som innehåller värdet i dropdown menyn det valda alternativet.
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_select, container, false);

        spinner = (Spinner)rootView.findViewById(R.id.spinner1);
        spinner.setAdapter((MainActivity.myAdapter));
        spinner.setOnItemSelectedListener(this);


        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MainActivity.spinnerChoice = spinner.getSelectedItem().toString();
        //gör om en string till en int för att senare i programmet kunna skicka det som antingen en int eller som en string.
        MainActivity mainActivity = new MainActivity();
        int funktion = MainActivity.funcList.get(0);
        MainActivity.valtNummer = Integer.parseInt(MainActivity.spinnerChoice);
        MainActivity.textName.setText(MainActivity.spinnerChoice);
        MainActivity.FunkNr.setText(Integer.toString(funktion));

        if (funktion == 1){
            MainActivity.textInfo.setText(getResources().getString(R.string.func1));
            MainActivity.Image.setImageResource(R.drawable.collectny);
        }else if(funktion == 2){
            MainActivity.textInfo.setText(getResources().getString(R.string.func2));
            MainActivity.Image.setImageResource(R.drawable.leaveny);
        }else if (funktion ==3){
            MainActivity.textInfo.setText(getResources().getString(R.string.func3));
            MainActivity.Image.setImageResource(R.drawable.change);
        }else if(funktion == 4){
            MainActivity.textInfo.setText(getResources().getString(R.string.func4));
            MainActivity.Image.setImageResource(R.drawable.acollect);
        }else if (funktion ==5){
            MainActivity.textInfo.setText(getResources().getString(R.string.func5));
            MainActivity.Image.setImageResource(R.drawable.pack);
        }else if(funktion == 6){
            MainActivity.textInfo.setText(getResources().getString(R.string.func6));
            MainActivity.Image.setImageResource(R.drawable.ncollect);
        }else if (funktion ==7){
            MainActivity.textInfo.setText(getResources().getString(R.string.func7));
            MainActivity.Image.setImageResource(R.drawable.aleaveny);
        }else if(funktion == 8){
            MainActivity.textInfo.setText(getResources().getString(R.string.func8));
            MainActivity.Image.setImageResource(R.drawable.manual);
        }else if (funktion ==9){
            MainActivity.textInfo.setText(getResources().getString(R.string.func9));
            MainActivity.Image.setImageResource(R.drawable.count);
        }else if (funktion ==10){
            MainActivity.textInfo.setText(getResources().getString(R.string.func10));
            MainActivity.Image.setImageResource(R.drawable.battery);
        }else if(funktion == 11){
            MainActivity.textInfo.setText(getResources().getString(R.string.func11));
            MainActivity.Image.setImageResource(R.drawable.power);
        }else if (funktion ==12){
            MainActivity.textInfo.setText(getResources().getString(R.string.func12));
            MainActivity.Image.setImageResource(R.drawable.empty);
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
