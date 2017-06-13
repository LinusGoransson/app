package com.example.linusgoransson.raspapp;

import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by linusgoransson on 2017-05-08.
 */
public class sendFunction extends AsyncTask<String, Void, Void> {
    private Exception exception;
    public static String Side, functionRunning, ErrorCode;
    @Override
    protected Void doInBackground(String... params) {
        try{
            try{
                Socket socket = new Socket("192.168.220.1",5583);
                PrintWriter outToServer = new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
                outToServer.print(params[0]);
                outToServer.flush();
                Log.d("READ123",params[0]);
                if (params[0].equals("READ")){
                    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (true){
                        String currentMessage = inFromServer.readLine();
                        Log.d("currentMessage",currentMessage);
                        Side = currentMessage.substring(0,1);
                        functionRunning = currentMessage.substring(1,2);
                        ErrorCode = currentMessage.substring(2,4);
                        Log.d("Side",Side);
                        Log.d("functionRunning",functionRunning);
                        Log.d("ErrorCode",ErrorCode);
                        if (ErrorCode.equals("29")){
                            functionRunning = "0";
                        }
                        return null;
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (Exception e){
            this.exception = e;
            return null;
        }
        return null;
    }
}
