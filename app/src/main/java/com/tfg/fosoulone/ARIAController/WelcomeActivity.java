package com.tfg.fosoulone.ARIAController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Thread reloj = new Thread() {
            public void run(){
                try{
                    sleep(3000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent openConnection = new Intent("android.intent.action.CONNECTION");
                    startActivity(openConnection);
                    finish();
                }
            }
        };
        reloj.start();
    }
}
