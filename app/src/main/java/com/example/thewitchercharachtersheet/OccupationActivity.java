package com.example.thewitchercharachtersheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.thewitchercharachtersheet.objects.Ability;
import com.example.thewitchercharachtersheet.objects.Occupation;
import com.example.thewitchercharachtersheet.objects.WitcherCharacter;

import java.util.ArrayList;

public class OccupationActivity extends AppCompatActivity {

    Occupation[] occupations;
    ArrayList<String> mainAbilities;
    WitcherCharacter playerCharacter;
    ImageView currentImage;
    TextView currentDescription;
    ImageView prevImage;
    ImageView nextImage;
    int currentOccupation;
    ConstraintLayout layout;

    //@SuppressLint("ResourceType")
   // @Override
    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occupation);
        TypedArray occupationsResource = getResources().obtainTypedArray(R.array.occupation_array);
        TypedArray itemDef;
        TypedArray abilitiesArray;
        playerCharacter = new WitcherCharacter();
        occupations = new Occupation[occupationsResource.length()];
        for (int i = 0; i < occupationsResource.length(); i++) {
            mainAbilities = new ArrayList<>();
            int resId = occupationsResource.getResourceId(i, -1);
            if (resId < 0) {
                continue;
            }
            itemDef = getResources().obtainTypedArray(resId);
            abilitiesArray = getResources().obtainTypedArray(itemDef.getResourceId(6, -1));
            for(int j = 0;j < abilitiesArray.length();j++)
            {
                mainAbilities.add(abilitiesArray.getString(j));
            }
            occupations[i] = new Occupation(i, itemDef.getString(0), itemDef.getString(1),
                    new Ability(itemDef.getString(3), itemDef.getString(4)),
                    itemDef.getResourceId(2,-1), mainAbilities, this);
            occupations[i].getMainAbility().setDescription(itemDef.getString(5));
        }
        currentOccupation = 0;
        layout = findViewById(R.id.occupation_screen);
        currentImage = findViewById(R.id.currentProfImage);
        currentDescription = findViewById(R.id.profDescription);
        prevImage = findViewById(R.id.prevProfImage);
        nextImage = findViewById(R.id.nextProfImage);
        prevImage.setImageResource(occupations[occupations.length-1].getImageId());
        currentImage.setImageResource(occupations[currentOccupation].getImageId());
        nextImage.setImageResource(occupations[currentOccupation+1].getImageId());
        currentDescription.setText(occupations[currentOccupation].getDescription());
    }

    public void PrevProf(View view) {
        SetCurrentProfession(view);
    }

    public void NextProf(View view) {
        SetCurrentProfession(view);
    }

    private void SetCurrentProfession(View view) {
        if (view.getId() == R.id.nextButton) {
            if (currentOccupation >= occupations.length - 1) {
                currentOccupation = 0;
                prevImage.setImageResource(occupations[occupations.length-1].getImageId());
                nextImage.setImageResource(occupations[currentOccupation+1].getImageId());
            } else {
                currentOccupation++;
                if (currentOccupation >= occupations.length - 1) {
                    nextImage.setImageResource(occupations[0].getImageId());
                } else {
                    nextImage.setImageResource(occupations[currentOccupation+1].getImageId());
                }
                prevImage.setImageResource(occupations[currentOccupation-1].getImageId());
            }
        }
        else if(view.getId() == R.id.prevButton)
        {
            if(currentOccupation == 0)
            {
                currentOccupation = occupations.length-1;
                prevImage.setImageResource(occupations[currentOccupation-1].getImageId());
                nextImage.setImageResource(occupations[0].getImageId());
            }
            else {
                currentOccupation--;
                if (currentOccupation == 0)
                {
                    prevImage.setImageResource(occupations[occupations.length-1].getImageId());
                }
                else
                {
                    prevImage.setImageResource(occupations[currentOccupation-1].getImageId());
                }
                nextImage.setImageResource(occupations[currentOccupation+1].getImageId());
            }
        }
        currentImage.setImageResource(occupations[currentOccupation].getImageId());
        currentDescription.setText(occupations[currentOccupation].getDescription());
    }


    public void ShowOccupationDetails(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.occupation_details, null);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable(){
            @Override
            public void run(){
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
            }
        });
        ListView detailList = popUpView.findViewById(R.id.occupation_abilities);
        ImageView detailImage = popUpView.findViewById(R.id.occupation_image);
        ImageView detailClose = popUpView.findViewById(R.id.close_occupation_details);
        TextView detailAbilityName = popUpView.findViewById(R.id.occupation_main_ability);
        TextView detailAbilityDescription = popUpView.findViewById(R.id.occupation_main_ability_description);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, occupations[currentOccupation].getOccupationAbilities());
        detailList.setAdapter(adapter);
        detailImage.setImageResource(occupations[currentOccupation].getImageId());
        detailAbilityName.setText(occupations[currentOccupation].getMainAbility().getName());
        detailAbilityDescription.setText(occupations[currentOccupation].getMainAbility().getDescription());
        detailClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }*/

}