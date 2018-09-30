package com.tfg.fosoulone.structures;

import com.tfg.fosoulone.ARIAController.R;

public class Action {
    private String name;
    private int code;
    private int idDrawable;
    private String intent;

    public Action(){

    }

    public Action(String name, int code, int idDrawable){
        this.name = name;
        this.code = code;
        this.idDrawable = idDrawable;
        this.intent="";
    }

    public Action(String name, int code, int idDrawable, String intent){
        this.name = name;
        this.code = code;
        this.idDrawable = idDrawable;
        this.intent = intent;
    }

    public String getName() {
        return name;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return name.hashCode();
    }

    public String getIntent(){ return intent;}

    public int getCode(){ return code;}

    public static Action[] ACTIONS = {
            new Action("Movimiento frontal continuo", 10, R.drawable.infinite_front),
            new Action("Detención",19, R.drawable.stop),
            new Action("Giro continuo izquierda",22, R.drawable.infinite_left),
            new Action("Giro continuo derecha",16, R.drawable.infinite_right),
            new Action("Aceleración",20, R.drawable.acceleration),
            new Action("Freno",21, R.drawable.deceleration),
            new Action("Movimiento trasero continuo",12, R.drawable.infinite_back),
            new Action("Movimiento frontal con distancia",11, R.drawable.x_front, "android.intent.action.ACTION11"),
            new Action("Movimiento trasero con distancia",13, R.drawable.x_back, "android.intent.action.ACTION13"),
            new Action("Movimiento continuo con ángulo",14, R.drawable.degrees_front, "android.intent.action.ACTION14"),
            new Action("Movimiento con distancia y ángulo",15, R.drawable.x_degrees_front, "android.intent.action.ACTION15"),
            new Action("Giro con grados",17, R.drawable.degrees_turn, "android.intent.action.ACTION17"),
            new Action("Movimiento por coordenadas",18, R.drawable.coordinates, "android.intent.action.ACTION18"),
    };

    public static Action getAction(int id) {
        for (Action action : ACTIONS) {
            if (action.getId() == id) {
                return action;
            }
        }
        return null;
    }
}
