package com.example.linusgoransson.raspapp;

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

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by linusgoransson on 2017-03-16.
 */
public class TabFunction extends Fragment{
    TextView textInfo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_function, container, false);
        MainActivity.textName = (TextView)rootView.findViewById(R.id.textNummer);
        MainActivity.textInfo = (TextView)rootView.findViewById(R.id.funktion_Info);
        MainActivity.FunkNr = (TextView)rootView.findViewById(R.id.funktion_Nummer);
        MainActivity.Image = (ImageView)rootView.findViewById(R.id.imageView);
        MainActivity.buttonRun=(Button)rootView.findViewById(R.id.buttonRun);
        MainActivity.buttonCancel=(Button)rootView.findViewById(R.id.buttonCancel);
        MainActivity.functionMessage=(TextView)rootView.findViewById(R.id.function_Stat_Text);
        return rootView;


    }





}
