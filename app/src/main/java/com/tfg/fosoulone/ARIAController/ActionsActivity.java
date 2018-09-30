package com.tfg.fosoulone.ARIAController;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.tfg.fosoulone.structures.Action;
import com.tfg.fosoulone.structures.ActionsAdapter;
import com.tfg.fosoulone.structures.SendTask;


public class ActionsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private ActionsAdapter adapter;
    private Context context = this;
    private static final String myPreferences = "com.tfg.fosoulone.prefs";
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        gridView = (GridView) findViewById(R.id.gridview);
        adapter = new ActionsAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        sharedPref = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Action item = (Action) parent.getItemAtPosition(position);
        if(!item.getIntent().isEmpty()){
            Intent openAction = new Intent(item.getIntent());
            startActivity(openAction);
        }
        else{
            String ip = sharedPref.getString("ip", "");
            int port = sharedPref.getInt("port",0);

            SendTask task = new SendTask();
            task.execute(""+item.getCode(),ip,""+port);
        }
        /*
        Intent intent = new Intent(this, ActividadDetalle.class);
        intent.putExtra(ActividadDetalle.EXTRA_PARAM_ID, item.getId());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptionsCompat activityOptions =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this,
                            new Pair<View, String>(view.findViewById(R.id.imagen_coche),
                                    ActividadDetalle.VIEW_NAME_HEADER_IMAGE)
                    );

            ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
        } else
            startActivity(intent);*/
    }
}
