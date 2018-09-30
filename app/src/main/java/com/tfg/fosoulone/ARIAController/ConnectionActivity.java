package com.tfg.fosoulone.ARIAController;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionActivity extends AppCompatActivity {
    private Button button;
    private Button button_no_connection;
    private EditText ip;
    private EditText port;
    private Context context = this;
    private static final String myPreferences = "com.tfg.fosoulone.prefs";
    private static int SERVERPORT = 0;
    private static String ADDRESS;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        sharedPref = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);
        button = ((Button) findViewById(R.id.login_button));
        button_no_connection = ((Button) findViewById(R.id.no_connection_button));
        ip = ((EditText) findViewById(R.id.connection_ip));
        port = ((EditText) findViewById(R.id.connection_port));
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        try {
                            ADDRESS = ip.getText().toString();
                            SERVERPORT = Integer.valueOf(port.getText().toString());
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("ip", ADDRESS);
                            editor.putInt("port", SERVERPORT);
                            editor.apply();
                            if (ip.getText().toString().length() > 0 && SERVERPORT > 0) {
                                ConnectionTask myATaskYW = new ConnectionTask();
                                myATaskYW.execute("1;");
                            } else {
                                Toast.makeText(context, "Escriba los campos correctamente", Toast.LENGTH_LONG).show();
                            }
                        }catch(Exception e){
                            Log.e("Fallo formato", "" + e.getMessage());
                            Toast.makeText(context, "Escriba los campos correctamente", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        button_no_connection.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent openMain = new Intent("android.intent.action.MAINACTIVITY");
                        startActivity(openMain);
                    }
                });

    }

    /*public void connect(View view){
        //HAY QUE AÑADIR COMPROBACIÓN DE IP Y PUERTO
        Intent openMain = new Intent("android.intent.action.MAINACTIVITY");
        startActivity(openMain);


    }*/

    class ConnectionTask extends AsyncTask<String,Void,String> {


        ProgressDialog progressDialog;
        Socket socket;
        int first = 1;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle("Conectando con el servidor");
            progressDialog.setMessage("Espere...");
            progressDialog.show();
        }

        /**
         * Se conecta al servidor y trata resultado
         * */
        @Override
        protected String doInBackground(String... values){

            try {
                //Log.d("je", "IP: " + ADDRESS + "; PUERTO: " + SERVERPORT);
                //Se conecta al servidor
                if(first==1){
                    first = 0;
                    InetAddress serverAddr = InetAddress.getByName(ADDRESS);
                    Log.i("I/TCP Client", "Connecting...");
                    socket = new Socket(serverAddr, SERVERPORT);
                }
                else {
                    if (!socket.isConnected()) {
                        InetAddress serverAddr = InetAddress.getByName(ADDRESS);
                        Log.i("I/TCP Client", "Connecting...");
                        socket = new Socket(serverAddr, SERVERPORT);
                    }
                }
                Log.i("I/TCP Client", "Connected to server");


                //envia peticion de cliente
                Log.i("I/TCP Client", "Send data to server");
                PrintStream output = new PrintStream(socket.getOutputStream(),true);
                String request = values[0];
                output.println(request);
                //output.close();
                /*
                //recibe respuesta del servidor y formatea a String
                Log.i("I/TCP Client", "Received data to server");
                InputStream stream = socket.getInputStream();
                byte[] lenBytes = new byte[256];
                stream.read(lenBytes,0,256);
                String received = new String(lenBytes,"UTF-8").trim();
                Log.i("I/TCP Client", "Received " + received);
                Log.i("I/TCP Client", "");
                //cierra conexion*/
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

        @Override
        protected void onPostExecute(String value){
            progressDialog.dismiss();
            if(value.equals("OK")) {
                Toast.makeText(context, "Conexión establecida", Toast.LENGTH_LONG).show();
                Intent openMain = new Intent("android.intent.action.MAINACTIVITY");
                startActivity(openMain);
            }
            else{
                Toast.makeText(context, "Fallo en la conexión", Toast.LENGTH_LONG).show();
            }
        }
    }
}
