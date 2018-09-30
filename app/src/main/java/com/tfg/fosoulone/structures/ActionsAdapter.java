package com.tfg.fosoulone.structures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tfg.fosoulone.ARIAController.R;

public class ActionsAdapter extends BaseAdapter{

    private Context context;

    public ActionsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Action.ACTIONS.length;
    }

    @Override
    public Action getItem(int position) {
        return Action.ACTIONS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView actionImage = (ImageView) view.findViewById(R.id.image_action);
        TextView actionName = (TextView) view.findViewById(R.id.name_action);

        final Action item = getItem(position);
        actionImage.setImageResource(item.getIdDrawable());
        actionName.setText(item.getName());

        return view;
    }

    public String getIntent(int position){ return getItem(position).getIntent();}

    public int getCode(int position){ return getItem(position).getCode();}
}
