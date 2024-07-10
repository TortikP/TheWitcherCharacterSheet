package com.example.thewitchercharachtersheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thewitchercharachtersheet.objects.Ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbilitiesActivity extends AppCompatActivity {

    LinearLayout abilitiesView;
    Map<String, ArrayList<Ability>> allAbilities = new HashMap<>();
    ArrayList<String[]> abilitiesList;
    ArrayList<String> abilitiesStats;
    ArrayList<String> difficultAbilities;
    int itemPosition;
    int keyPosition;
    TextView abilityName;
    TextView abilityValue;
    ImageView addValue;
    ImageView minusValue;
    LinearLayout headerView;
    TextView headerText;
    TextView mainAbility;
    TextView mainAbilityValue;
    EditText abilityPoints;
    RelativeLayout abilityView;
    Context context;
  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_abilities);
        context = this;
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> tasks = activityManager.getAppTasks();
        for(int i = 0;i < tasks.size();i++)
        {
            System.out.println(tasks.get(i).getTaskInfo().numActivities);
        }
        String[] placeholder = getResources().getStringArray(R.array.stats);
        abilitiesStats = new ArrayList<>();
        abilitiesStats.addAll(Arrays.asList(placeholder));
        placeholder = getResources().getStringArray(R.array.difficult_abilities);
        difficultAbilities = new ArrayList<>();
        difficultAbilities.addAll(Arrays.asList(placeholder));
        super.onCreate(savedInstanceState);
        abilitiesView = findViewById(R.id.abilities_list);
        mainAbility = findViewById(R.id.main_ability);
        mainAbilityValue = findViewById(R.id.main_ability_value);
        abilityPoints = findViewById(R.id.ability_points_text);
        LayoutInflater inflater = getLayoutInflater();
        abilitiesList = new ArrayList<>();
        abilitiesList.add(getResources().getStringArray(R.array.intelligence_abilities));
        abilitiesList.add(getResources().getStringArray(R.array.reaction_abilities));
        abilitiesList.add(getResources().getStringArray(R.array.agility_abilities));
        abilitiesList.add(getResources().getStringArray(R.array.constitution_abilities));
        abilitiesList.add(getResources().getStringArray(R.array.empathy_abilities));
        abilitiesList.add(getResources().getStringArray(R.array.craft_abilities));
        abilitiesList.add(getResources().getStringArray(R.array.will_abilities));
        abilitiesStats.remove("Скорость");
        abilitiesStats.remove("Удача");
        for(int j = 0;j < abilitiesStats.size();j++) {
            ArrayList<Ability> statAbilities = new ArrayList<>();
            for (int g = 0;g < abilitiesList.get(j).length;g++)
            {
                if(difficultAbilities.contains(abilitiesList.get(j)[g].substring(0, abilitiesList.get(j)[g].length() - 3))) {
                    statAbilities.add(new Ability(abilitiesList.get(j)[g], abilitiesStats.get(j),2));
                }
                else {
                    statAbilities.add(new Ability(abilitiesList.get(j)[g], abilitiesStats.get(j),1));
                }
                System.out.println(statAbilities.get(g).getName());
            }
            allAbilities.put(abilitiesStats.get(j), statAbilities);
            keyPosition = j;
            headerView = (LinearLayout) inflater.inflate(R.layout.ability_header, null);
            headerText = headerView.findViewById(R.id.header_text);
            headerText.setText(abilitiesStats.get(j));
            abilitiesView.addView(headerView, j + 1);
            for (int i = 0; i < allAbilities.get(abilitiesStats.get(j)).size(); i++) {

                itemPosition = i;
                abilityView = (RelativeLayout) inflater.inflate(R.layout.stats_list_element, null);
                abilityName = abilityView.findViewById(R.id.stat_name_text);
                abilityName.setText(allAbilities.get(abilitiesStats.get(j)).get(i).getName());
                abilityValue = abilityView.findViewById(R.id.stat_value_text);
                abilityValue.setText(String.valueOf(allAbilities.get(abilitiesStats.get(j)).get(i).getCurrentValue()));
                addValue = abilityView.findViewById(R.id.plus_image);
                minusValue = abilityView.findViewById(R.id.minus_image);
                addValue.setOnClickListener(new View.OnClickListener() {
                    final TextView ownedAbility = abilityValue;
                    final Ability ability = allAbilities.get(abilitiesStats.get(keyPosition)).get(itemPosition);
                    @Override
                    public void onClick(View v) {
                        if((Integer.parseInt(abilityPoints.getText().toString()) - ability.getCostModifier()) >= 0) {
                            if (Integer.parseInt(ownedAbility.getText().toString()) < 6) {
                                ability.setCurrentValue(Integer.parseInt(ownedAbility.getText().toString()) + 1);
                                ownedAbility.setText(String.valueOf(ability.getCurrentValue()));
                                abilityPoints.setText(String.valueOf(Integer.parseInt(abilityPoints.getText().toString())
                                        - ability.getCostModifier()));
                            }
                            else {
                                Toast toast = Toast.makeText(context,"Вы не можете поднять навык выше 6 при создании персонажа", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                        else {
                            Toast toast = Toast.makeText(context,"Недостаточно очков улучшения", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
                minusValue.setOnClickListener(new View.OnClickListener() {
                    final TextView ownedAbility = abilityValue;
                    final Ability ability = allAbilities.get(abilitiesStats.get(keyPosition)).get(itemPosition);
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt(ownedAbility.getText().toString()) > 0) {
                            ability.setCurrentValue(Integer.parseInt(ownedAbility.getText().toString()) - 1);
                            ownedAbility.setText(String.valueOf(ability.getCurrentValue()));
                            abilityPoints.setText(String.valueOf(Integer.parseInt(abilityPoints.getText().toString())
                                    + ability.getCostModifier()));
                        }
                        else {
                            Toast toast = Toast.makeText(context,"Навык не может быть меньше 0", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
                headerView.addView(abilityView, i + 1);
            }
        }

    }*/
}