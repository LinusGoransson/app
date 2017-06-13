package com.example.linusgoransson.raspapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by linusgoransson on 2017-05-09.
 */
public class read{
    private Exception exception;

    protected Void doInBackground() {
        try{
            Socket socket = new Socket("192.168.220.1",5583);
        }catch (Exception e){
            this.exception = e;
            return null;
        }
        return null;
    }
}