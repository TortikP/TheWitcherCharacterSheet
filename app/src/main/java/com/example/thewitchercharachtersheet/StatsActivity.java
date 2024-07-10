package com.example.thewitchercharachtersheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.thewitchercharachtersheet.adapters.StatsAdapter;
import com.example.thewitchercharachtersheet.adapters.objects.StatsElement;

import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends AppCompatActivity {

   /* RelativeLayout layout;
    ListView statsView;
    ListView statsViewValue;
    ListView statsViewSubtract;
    ListView statsViewAdd;
    TextView improvementText;
    EditText improvementField;
    ImageView rollStats;
    AutoCompleteTextView gameLevel;
    String[] statsNames;
    String[] gameLevelNames;
    String[] statsValue;
    List<StatsElement> statsList;
    Activity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        context = this;
        //statsView = findViewById(R.id.stats_main_name);
        statsViewValue = findViewById(R.id.stats_main_value);
        //statsViewSubtract = findViewById(R.id.stats_main_subtract);
        //statsViewAdd = findViewById(R.id.stats_main_add);
        gameLevel = findViewById(R.id.starting_level_dropdown);
        improvementField = findViewById(R.id.improvement_points);
        improvementText = findViewById(R.id.improvement_points_text);
        rollStats = findViewById(R.id.roll_stats);
        layout = findViewById(R.id.stats_screen);
        statsNames = getResources().getStringArray(R.array.stats);
        statsValue = new String[statsNames.length];
        statsList = new ArrayList<>();
        for (int i = 0;i<statsValue.length;i++)
        {
            statsValue[i] = "0";
            statsList.add(new StatsElement(0));
        }
        gameLevelNames = new String[]{"Средний", "Опытные", "Герои", "Легенды", "Использовать кубы"};
        ArrayAdapter<String> adapterString = new ArrayAdapter<>(this, R.layout.my_spinner_item_one, gameLevelNames);
        gameLevel.setAdapter(adapterString);
        StatsAdapter statsAdapter = new StatsAdapter(this, statsList, improvementField);
        statsViewValue.setAdapter(statsAdapter);
        improvementField.setText("70");
        gameLevel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("Использовать кубы"))
                {
                    rollStats.setVisibility(View.VISIBLE);
                    improvementField.setVisibility(View.GONE);
                    improvementText.setVisibility(View.GONE);
                }
                else {
                    improvementField.setVisibility(View.VISIBLE);
                    switch((parent.getItemAtPosition(position).toString()))
                    {
                        case "Средний":
                            improvementField.setText("65");
                            break;
                        case "Опытные":
                            improvementField.setText("70");
                            break;
                        case "Герои":
                            improvementField.setText("75");
                            break;
                        case "Легенды":
                            improvementField.setText("80");
                            break;
                        default:
                            break;
                    }
                    improvementText.setVisibility(View.VISIBLE);
                    rollStats.setVisibility(View.GONE);
                }
            }
        });

    }

    public void FullStats(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.stats_summary, null);
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
        ImageView closeButton = popUpView.findViewById(R.id.close_summary);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        ListView statsSummary = popUpView.findViewById(R.id.stats_summary_name);
        ListView statsSummaryValue = popUpView.findViewById(R.id.stats_summary_value);
        ListView additionalSummary = popUpView.findViewById(R.id.additional_summary_name);
        ListView additionalSummaryValue = popUpView.findViewById(R.id.additional_summary_value);
        TextView fistDamageView = popUpView.findViewById(R.id.fist_damage);
        TextView kickDamageView = popUpView.findViewById(R.id.kick_damage);
        String[] statsNamesShort = new String[]{"Инт", "Реа", "Лвк", "Тел", "Скор", "Эмп", "Рем", "Вл", "Удч"};
        for (int i = 0; i< statsList.size(); i++)
        {
            statsList.set(i, (StatsElement) statsViewValue.getItemAtPosition(i));
            statsValue[i] = String.valueOf(statsList.get(i).getStatValue());
        }
        String[] additionalNamesShort = new String[]{"Энер", "Уст", "Бег", "Прж", "ПЗ", "Вын", "Вес", "Отдх", "Блж"};
        String tough = String.valueOf((statsList.get(3).getStatValue() + statsList.get(7).getStatValue())/2);
        if(Integer.parseInt(tough) > 10)
        {
            tough = "10";
        }
        String run = String.valueOf(statsList.get(4).getStatValue() * 3);
        String rest;
        String HP, ST;
        rest = String.valueOf((statsList.get(3).getStatValue() + statsList.get(7).getStatValue())/2);
        HP = String.valueOf(Integer.parseInt(rest) * 5);
        ST = HP;
        String maxWeight = String.valueOf(statsList.get(3).getStatValue() * 10);
        int meleeInt = (((statsList.get(3).getStatValue() + 1) / 2) - 3) * 2;
        String melee = String.valueOf(meleeInt);
        if(Integer.parseInt(melee) > 8)
        {
            melee = "8";
        }
        String kickDamage = String.valueOf(4 + Integer.parseInt(melee));
        if(Integer.parseInt(melee) > 0)
        {
            kickDamage = "+" + (4 + Integer.parseInt(melee));
            melee = "+" + melee;
        }
        String[] additionalValue = new String[]{"0", tough, run, String.valueOf(Integer.parseInt(run) / 5), HP, ST, maxWeight ,rest, melee};
        ArrayAdapter<String> adapter  = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, statsNamesShort);
        statsSummary.setAdapter(adapter);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, statsValue);
        statsSummaryValue.setAdapter(adapter);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, additionalNamesShort);
        additionalSummary.setAdapter(adapter);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, additionalValue);
        additionalSummaryValue.setAdapter(adapter);
        String unarmedDamage = "Урон ногой: 1d6 " + kickDamage;
        kickDamageView.setText(unarmedDamage);
        unarmedDamage = "Урон рукой: 1d6 " + melee;
        fistDamageView.setText(unarmedDamage);

    }*/

}