package com.example.thewitchercharachtersheet.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.adapters.objects.CharacterElement;

import java.util.List;

public class CharacterAdapter extends ArrayAdapter {
    private final Activity activity;
    private final List objects;
    MyView dView;
    CharacterElement currentElements;
    public CharacterAdapter(Activity activity, List objects) {
        super(activity, R.layout.character_list_element, objects);
        this.activity = activity;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rowView = convertView;
        dView = null;
        currentElements = (CharacterElement) objects.get(position);
        if (rowView == null) {
            // Get a new instance of the row layout view
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.character_list_element, null);

            // Hold the view objects in an object,
            // so they don't need to be re-fetched
            dView = new MyView();
            dView.characterName = rowView.findViewById(R.id.element1);
            dView.characterRace = rowView.findViewById(R.id.element2);
            dView.characterOccupation = rowView.findViewById(R.id.element3);
            // Cache the view objects in the tag,
            // so they can be re-accessed later
            rowView.setTag(dView);
        } else {
            dView = (MyView) rowView.getTag();
        }
        // Transfer the stock data from the data object
        // to the view objects
        dView.characterName.setText(currentElements.getCharacterName());
        dView.characterRace.setText(currentElements.getCharacterRace());
        dView.characterOccupation.setText(currentElements.getCharacterOccupation());
        return rowView;
    }

    protected static class MyView
    {
        protected TextView characterName;
        protected TextView characterOccupation;
        protected TextView characterRace;
    }

}

