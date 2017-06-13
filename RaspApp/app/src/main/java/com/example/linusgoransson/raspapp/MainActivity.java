package com.example.linusgoransson.raspapp;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity{
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    //variabel som innehåller värdet i dropdown menyn det valda alternativet.
    public static String spinnerChoice;
    //variabel som håller reda på första värdet i spinnen.
    String spinnerPos0 ="1";
    //variabler som innehåller vilken skyttel man valt och vilken funktion som man vill köra.
    int funktion = 1;
    public static int valtNummer;
    public static TextView textName;
    public static TextView textInfo;
    public static TextView FunkNr;
    public static TextView functionMessage;
    public static ImageView Image;
    public static Button buttonRun, buttonCancel;
    public static String readDm;
    public static ArrayAdapter<String> myAdapter;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String username = "test";
    String password = "test";
    public static List<Integer> funcList = new ArrayList<Integer>();
    private Exception exception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.onStart();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        new sendFunction().execute("READ");
        Log.d("fel","fungerar hit?");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int seekValue = sharedPreferences.getInt("progress_value", +1);
        String[] selectArrayString;
        int x =1;
        Log.d("fel","fungerar hit2?");
        selectArrayString = new String[seekValue];
        Log.d("fel","fungerar hit4?");
        for(int i = 0; i<seekValue;i++){
            selectArrayString[i] = String.valueOf(x);
            Log.d("selectArrayString", selectArrayString[i]);
            x++;

            if(i == seekValue -1){
                myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,selectArrayString);
            }

        }
        Log.d("fel","fungerar hit3?");
        boolean tgpref = sharedPreferences.getBoolean("tgpref", true);
        boolean tgpref2 = sharedPreferences.getBoolean("tgpref2", true);
        boolean tgpref3 = sharedPreferences.getBoolean("tgpref3", true);
        boolean tgpref4 = sharedPreferences.getBoolean("tgpref4", true);
        boolean tgpref5 = sharedPreferences.getBoolean("tgpref5", true);
        boolean tgpref6 = sharedPreferences.getBoolean("tgpref6", true);
        boolean tgpref7 = sharedPreferences.getBoolean("tgpref7", true);
        boolean tgpref8 = sharedPreferences.getBoolean("tgpref8", true);
        boolean tgpref9 = sharedPreferences.getBoolean("tgpref9", true);
        boolean tgpref10 = sharedPreferences.getBoolean("tgpref10", true);
        boolean tgpref11 = sharedPreferences.getBoolean("tgpref11", true);
        boolean tgpref12 = sharedPreferences.getBoolean("tgpref12", true);


        if(tgpref == true){
            funcList.add(1);

        }
        if(tgpref2 == true){
            funcList.add(2);

        }
        if(tgpref3 == true){
            funcList.add(3);

        }
        if(tgpref4 == true){
            funcList.add(4);

        }
        if(tgpref5 == true){
            funcList.add(5);

        }
        if(tgpref6 == true){
            funcList.add(6);

        }
        if(tgpref7 == true){
            funcList.add(7);

        }
        if(tgpref8 == true){
            funcList.add(8);

        }
        if(tgpref9 == true){
            funcList.add(9);

        }
        if(tgpref10 == true){
            funcList.add(10);

        }
        if(tgpref11 == true){
            funcList.add(11);

        }
        if(tgpref12 == true){
            funcList.add(12);

        }

        int lastvalue = funcList.get(funcList.size()-1);
        Log.d("Lista med true", String.valueOf(funcList));
        Log.d("Lista med true sista", String.valueOf(lastvalue));
        Log.d("Lista med true första", String.valueOf(funcList.get(0)));
        funktion = funcList.get(0);
        readDm = "yes";
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (readDm.equals("yes")){
                    try {
                        Thread.sleep(2000);
                        functionMessage();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new sendFunction().execute("READ");

                }
            }
        }).start();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    TabSelect tab1 = new TabSelect();
                    return tab1;
                case 1:
                    TabFunction tab2 = new TabFunction();
                    return tab2;
                case 2:
                    TabSettings tab3 = new TabSettings();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String tab1 = getResources().getString(R.string.tab1text);
            String tab2 = getResources().getString(R.string.tab2text);
            String tab3 = getResources().getString(R.string.tab3text);
            switch (position) {
                case 0:
                    return tab1;
                case 1:
                    return tab2;
                case 2:
                    return tab3;
            }
            return null;
        }
    }


    //När man klickar på nästa knappen ökar värdet på "funktion" och texten som syns i appen blir lika med det värdet.
    public void onButtonNext(View v){
        if(funktion <funcList.get(funcList.size()-1)){
            int i = funcList.indexOf(funktion);
            funktion = funcList.get(i+1);
            TextView FunkNr = (TextView)findViewById(R.id.funktion_Nummer);
            FunkNr.setText(Integer.toString(funktion));
            Log.d("Funktion: ", String.valueOf(funktion));
            functionInfo();

        }else{
            Log.d("plusfunktion", "sista " + funktion);
            funktion =funcList.get(0);
            TextView FunkNr = (TextView)findViewById(R.id.funktion_Nummer);
            FunkNr.setText(Integer.toString(funktion));
            Log.d("Funktion: ", String.valueOf(funktion));
            functionInfo();
        }
    }

    //samma som onButtonNext fast går minus i "funktion" värdet
    public void onButtonPrev(View v){
        if(funktion>funcList.get(0)){
            int i = funcList.indexOf(funktion);
            funktion = funcList.get(i-1);
            TextView FunkNr = (TextView)findViewById(R.id.funktion_Nummer);
            FunkNr.setText(Integer.toString(funktion));
            Log.d("Funktion: ", String.valueOf(funktion));
            functionInfo();
            functionMessage();
        }else{
            Log.d("minusfunktion", String.valueOf(funktion));
            funktion =funcList.get(funcList.size()-1);
            TextView FunkNr = (TextView)findViewById(R.id.funktion_Nummer);
            FunkNr.setText(Integer.toString(funktion));
            Log.d("Funktion: ", String.valueOf(funktion));
            functionInfo();
        }

    }

    public void onButtonX(View v) throws IOException{
        readDm = "no";
        new sendFunction().execute("clearError");
        readDm = "yes";
    }
    //Kör knappen på funktions sidan som skickar skyttel nummer och funktion
    public void onButtonRun(View v) throws IOException, InterruptedException {
        Log.d("Funktion som körs: ","Skyttel: "+valtNummer +" Funktion: "+funktion);
        switch (funktion){
            case 1: funktion = 1;
                new sendFunction().execute("1");
                break;
            case 2: funktion = 2;
                new sendFunction().execute("2");
                break;
            case 3: funktion = 3;
                new sendFunction().execute("3");
                break;
            case 4: funktion = 4;
                new sendFunction().execute("4");
                break;
            case 5: funktion = 5;
                new sendFunction().execute("5");
                break;
            case 6: funktion = 6;
                View view = (LayoutInflater.from(MainActivity.this)).inflate(R.layout.func6_input, null);
                final EditText func6Input = (EditText) view.findViewById(R.id.func6Input);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setView(view);

                alertBuilder.setCancelable(true).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String x = String.valueOf(func6Input.getText());
                        Log.d("func6", x);
                        Log.d("func6", "0"+x+"00");
                        new sendFunction().execute("f60"+x+"00");
                    }
                });
                Dialog dialog = alertBuilder.create();
                dialog.show();
                //new sendFunction().execute("6");
                break;
            case 7: funktion = 7;
                new sendFunction().execute("7");
                break;
            case 8: funktion = 8;
                new sendFunction().execute("8");
                break;
            case 9: funktion = 9;
                startActivity(new Intent(MainActivity.this,Manual.class));
                new sendFunction().execute("9");
                break;
            case 10: funktion = 10;
                new sendFunction().execute("10");
                break;
            case 11: funktion = 11;
                new sendFunction().execute("11");
                break;
            case 12: funktion = 12;
                new sendFunction().execute("close");
                break;
        }
    }
    //login knapp
    public void onButtonLogin(View v){
        {
            EditText inUsername =(EditText)findViewById(R.id.inUsername);
            String User = inUsername.getText().toString();
            EditText inPassword = (EditText)findViewById(R.id.inPassword);
            String Pass = inPassword.getText().toString();
            Log.d("username: ", User);
            if(User.equals(username) && Pass.equals(password)){
                Toast myToast = Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_LONG);
                myToast.show();
                startActivity(new Intent(MainActivity.this,Pop.class));
            }else{
                Toast myToast = Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG);
                myToast.show();
            }
        }
    }


    //funktion som uppdaterar text och bild på funktions sidan, så att den alltid stämmer överäns med vilken funktion man är på.
    public void functionInfo(){
        TextView textInfo = (TextView)findViewById(R.id.funktion_Info);
        final ImageView Image = (ImageView)findViewById(R.id.imageView);
        switch (funktion){
            case 1: funktion = 1;
                textInfo.setText(getResources().getString(R.string.func1));
                Image.setImageResource(R.drawable.collectny);
                break;
            case 2: funktion = 2;
                textInfo.setText(getResources().getString(R.string.func2));
                Image.setImageResource(R.drawable.leaveny);
                break;
            case 3: funktion = 3;
                textInfo.setText(getResources().getString(R.string.func3));
                Image.setImageResource(R.drawable.change);
                break;
            case 4: funktion = 4;
                textInfo.setText(getResources().getString(R.string.func4));
                Image.setImageResource(R.drawable.acollect);
                break;
            case 5: funktion = 5;
                textInfo.setText(getResources().getString(R.string.func5));
                Image.setImageResource(R.drawable.pack);
                break;
            case 6: funktion = 6;
                textInfo.setText(getResources().getString(R.string.func6));
                Image.setImageResource(R.drawable.ncollect);
                break;
            case 7: funktion = 7;
                textInfo.setText(getResources().getString(R.string.func7));
                Image.setImageResource(R.drawable.aleaveny);
                break;
            case 8: funktion = 8;
                textInfo.setText(getResources().getString(R.string.func10));
                Image.setImageResource(R.drawable.battery);
                break;
            case 9: funktion = 9;
                textInfo.setText(getResources().getString(R.string.func8));
                Image.setImageResource(R.drawable.manual);
                break;
            case 10: funktion = 10;
                textInfo.setText(getResources().getString(R.string.func9));
                Image.setImageResource(R.drawable.count);
                break;
            case 11: funktion = 11;
                textInfo.setText(getResources().getString(R.string.func11));
                Image.setImageResource(R.drawable.power);
                break;
            case 12: funktion = 12;
                textInfo.setText(getResources().getString(R.string.func12));
                Image.setImageResource(R.drawable.empty);
                break;
        }
    }

    public void functionMessage(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(sendFunction.functionRunning ==null){
                    Log.d("WifiError","Not connected to router");
                }else if(!sendFunction.ErrorCode.equals("00") && !sendFunction.ErrorCode.equals("29") && !sendFunction.ErrorCode.equals("28")){
                    functionMessage.setTextColor(Color.RED);
                    buttonRun.setVisibility(View.INVISIBLE);
                    buttonCancel.setVisibility(View.VISIBLE);
                    switch (sendFunction.ErrorCode){
                        case "01":
                            functionMessage.setText(getResources().getString(R.string.Erro1));
                            break;
                        case "02":
                            functionMessage.setText(getResources().getString(R.string.Erro2));
                            break;
                        case "03":
                            functionMessage.setText(getResources().getString(R.string.Erro3));
                            break;
                        case "04":
                            functionMessage.setText(getResources().getString(R.string.Erro4));
                            break;
                        case "05":
                            functionMessage.setText(getResources().getString(R.string.Erro5));
                            break;
                        case "06":
                            functionMessage.setText(getResources().getString(R.string.Erro6));
                            break;
                        case "07":
                            functionMessage.setText(getResources().getString(R.string.Erro7));
                            break;
                        case "08":
                            functionMessage.setText(getResources().getString(R.string.Erro8));
                            break;
                        case "09":
                            functionMessage.setText(getResources().getString(R.string.Erro9));
                            break;
                        case "0A":
                            functionMessage.setText(getResources().getString(R.string.Erro10));
                            break;
                        case "0B":
                            functionMessage.setText(getResources().getString(R.string.Erro11));
                            break;
                        case "0C":
                            functionMessage.setText(getResources().getString(R.string.Erro12));
                            break;
                        case "0D":
                            functionMessage.setText(getResources().getString(R.string.Erro13));
                            break;
                        case "0E":
                            functionMessage.setText(getResources().getString(R.string.Erro14));
                            break;
                        case "0F":
                            functionMessage.setText(getResources().getString(R.string.Erro15));
                            break;
                        case "10":
                            functionMessage.setText(getResources().getString(R.string.Erro16));
                        break;
                        case "11":
                            functionMessage.setText(getResources().getString(R.string.Erro17));
                            break;
                        case "12":
                            functionMessage.setText(getResources().getString(R.string.Erro18));
                            break;
                        case "13":
                            functionMessage.setText(getResources().getString(R.string.Erro19));
                            break;
                        case "14":
                            functionMessage.setText(getResources().getString(R.string.Erro20));
                            break;
                        case "15":
                            functionMessage.setText(getResources().getString(R.string.Erro21));
                            break;
                        case "16":
                            functionMessage.setText(getResources().getString(R.string.Erro22));
                            break;
                        case "17":
                            functionMessage.setText(getResources().getString(R.string.Erro23));
                            break;
                        case "18":
                            functionMessage.setText(getResources().getString(R.string.Erro24));
                            break;
                        case "19":
                            functionMessage.setText(getResources().getString(R.string.Erro25));
                            break;
                        case "1A":
                            functionMessage.setText(getResources().getString(R.string.Erro26));
                            break;
                        case "1B":
                            functionMessage.setText(getResources().getString(R.string.Erro27));
                            break;
                        case "1C":
                            functionMessage.setText(getResources().getString(R.string.Erro28));
                            break;
                        case "1D":
                            functionMessage.setText(getResources().getString(R.string.Erro29));
                            break;
                        case "1E":
                            functionMessage.setText(getResources().getString(R.string.Erro30));
                            break;
                        case "1F":
                            functionMessage.setText(getResources().getString(R.string.Erro31));
                            break;
                    }
                }else if (sendFunction.functionRunning.equals("0")){
                    functionMessage.setText(getResources().getString(R.string.funcStatNotRun));
                    functionMessage.setTextColor(Color.CYAN);
                    buttonRun.setVisibility(View.VISIBLE);
                    buttonCancel.setVisibility(View.INVISIBLE);
                }else{
                    functionMessage.setText(getResources().getString(R.string.funcStatRun));
                    functionMessage.setTextColor(Color.GREEN);
                    buttonRun.setVisibility(View.INVISIBLE);
                    buttonCancel.setVisibility(View.VISIBLE);
                }
    }
        });


    }



}
