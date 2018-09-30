package com.tfg.fosoulone.ARIAController;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.tfg.fosoulone.structures.SendTask;

public class ManualControlActivity extends AppCompatActivity {

    private Button forward_button;
    private Button backward_button;
    private Button left_button;
    private Button right_button;
    private Context context = this;
    private static final String myPreferences = "com.tfg.fosoulone.prefs";
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_control);
        sharedPref = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);
        forward_button = ((Button) findViewById(R.id.forward_button));
        backward_button = ((Button) findViewById(R.id.backward_button));
        left_button = ((Button) findViewById(R.id.left_button));
        right_button = ((Button) findViewById(R.id.right_button));
        final String ip = sharedPref.getString("ip", "");
        final int port = sharedPref.getInt("port",0);
        forward_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    SendTask task = new SendTask();
                    task.execute("3;1;",ip,""+port);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    SendTask task = new SendTask();
                    task.execute("3;0;",ip,""+port);
                }
                return true;
            }
        });
        backward_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    SendTask task = new SendTask();
                    task.execute("4;1;",ip,""+port);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    SendTask task = new SendTask();
                    task.execute("4;0;",ip,""+port);
                }
                return true;
            }
        });
        left_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    SendTask task = new SendTask();
                    task.execute("5;1;",ip,""+port);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    SendTask task = new SendTask();
                    task.execute("5;0;",ip,""+port);
                }
                return true;
            }
        });
        right_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    SendTask task = new SendTask();
                    task.execute("6;1;",ip,""+port);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    SendTask task = new SendTask();
                    task.execute("6;0;",ip,""+port);
                }
                return true;
            }
        });
    }
}
