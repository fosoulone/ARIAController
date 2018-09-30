package com.tfg.fosoulone.ARIAController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void manualControl(View view){
        Intent openActionsControl = new Intent("android.intent.action.MANUALCONTROL");
        startActivity(openActionsControl);

    }

    public void actionsControl(View view){
        Intent openActionsControl = new Intent("android.intent.action.ACTIONSCONTROL");
        startActivity(openActionsControl);
    }
}
