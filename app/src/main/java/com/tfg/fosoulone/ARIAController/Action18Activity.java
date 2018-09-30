package com.tfg.fosoulone.ARIAController;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tfg.fosoulone.structures.SendTask;

public class Action18Activity extends AppCompatActivity {
    private Button button;
    private EditText coordinateX;
    private EditText coordinateY;
    private Context context = this;
    private static final String myPreferences = "com.tfg.fosoulone.prefs";
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action18);
        sharedPref = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);
        button = ((Button) findViewById(R.id.a18_send_button));
        coordinateX = ((EditText) findViewById(R.id.a18_coordinateX));
        coordinateY = ((EditText) findViewById(R.id.a18_coordinateY));
        final String ip = sharedPref.getString("ip", "");
        final int port = sharedPref.getInt("port",0);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        try {
                            SendTask task = new SendTask();
                            task.execute("18;"+coordinateX.getText().toString()+";"+coordinateY.getText().toString()+";",ip,""+port);
                        }catch(Exception e){
                            Log.e("Fallo formato", "" + e.getMessage());
                            Toast.makeText(context, "Escriba los campos correctamente", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
