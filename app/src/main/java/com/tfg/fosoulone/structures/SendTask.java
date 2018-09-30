package com.tfg.fosoulone.structures;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendTask extends AsyncTask<String,Void,String> {


    Socket socket;
    int first = 1;

    /**
     * Se conecta al servidor y trata resultado
     * */
    @Override
    protected String doInBackground(String... values){

        try {
            //Se conecta al servidor
            if(first==1){
                first = 0;
                InetAddress serverAddr = InetAddress.getByName(values[1]);
                Log.i("I/TCP Client", "Connecting...");
                socket = new Socket(serverAddr, Integer.valueOf(values[2]));
            }
            else {
                if (!socket.isConnected()) {
                    InetAddress serverAddr = InetAddress.getByName(values[1]);
                    Log.i("I/TCP Client", "Connecting...");
                    socket = new Socket(serverAddr, Integer.valueOf(values[2]));
                }
            }
            Log.i("I/TCP Client", "Connected to server");


            //envia peticion de cliente
            Log.i("I/TCP Client", "Send data to server");
            PrintStream output = new PrintStream(socket.getOutputStream(),true);
            String request = values[0];
            output.println(request);
            //output.close();
            //cierra conexion
            socket.close();
            return "OK";

        }catch (UnknownHostException ex) {
            Log.e("E/TCP Client", "" + ex.getMessage());
            return "FAIL";
        } catch (IOException ex) {
            Log.e("E/TCP Client", "" + ex.getMessage());
            return "FAIL";

        }
    }

}
