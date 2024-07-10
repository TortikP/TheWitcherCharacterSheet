package com.example.thewitchercharachtersheet.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.adapters.StatsAdapter;
import com.example.thewitchercharachtersheet.objects.Feature;
import com.example.thewitchercharachtersheet.objects.WitcherCharacter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FragmentStats extends Fragment {

    ListView statsViewValue;
    ListView additionalView;
    TextView improvementText;
    EditText improvementField;
    ImageView rollStats;
    String[] statsNames;
    String[] additionalNames;
    String[] statsNamesShort;
    String[] additionalNamesShort;
    List<Feature> mainStats = new ArrayList<>();;
    List<Feature> additionalStats = new ArrayList<>();
    LinearLayout moveButtons;
    Button toBackstory;
    Button toAbilities;
    WitcherCharacter playerCharacter;
    View view;
    StatsAdapter statsAdapter;
    public FragmentStats() {
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_stats, container, false);
        playerCharacter = (WitcherCharacter) getArguments().getSerializable("character");
        statsViewValue = view.findViewById(R.id.stats_main_value);
        additionalView = view.findViewById(R.id.stats_additional_value);
        improvementField = view.findViewById(R.id.improvement_points);
        improvementText = view.findViewById(R.id.improvement_points_text);
        rollStats = view.findViewById(R.id.roll_stats);;
        statsNames = getResources().getStringArray(R.array.stats);
        additionalNames= getResources().getStringArray(R.array.additional_stats);
        statsNamesShort = getResources().getStringArray(R.array.stats_short);
        additionalNamesShort = getResources().getStringArray(R.array.additional_stats_short);
        moveButtons = view.findViewById(R.id.move_buttons);
        toBackstory = view.findViewById(R.id.back_to_backstory);
        toAbilities = view.findViewById(R.id.to_abilities);
        if(mainStats.size() == 0 || additionalStats.size() == 0) {
            for (int i = 0; i < statsNames.length; i++) {
                mainStats.add(new Feature(statsNames[i], 0, statsNamesShort[i]));
                additionalStats.add(new Feature(additionalNames[i], 0, additionalNamesShort[i]));
            }
        }
        statsAdapter = new StatsAdapter(getActivity(), mainStats, true);
        statsViewValue.setAdapter(statsAdapter);
        statsAdapter = new StatsAdapter(getActivity(), additionalStats, true);
        additionalView.setAdapter(statsAdapter);
        improvementField.setText("70");
        statsViewValue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View popUpView = getLayoutInflater().inflate(R.layout.stat_new_value, null);
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                boolean focusable = true;
                PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
                view.post(new Runnable(){
                    @Override
                    public void run(){
                        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                    }
                });
                EditText newValue = popUpView.findViewById(R.id.new_value);
                ImageView addButton = popUpView.findViewById(R.id.plus_value);
                ImageView minusButton = popUpView.findViewById(R.id.minus_value);
                Feature feature = (Feature) parent.getItemAtPosition(position);
                newValue.setText(String.valueOf(feature.getFeatureValue()));
                newValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus)
                        {
                            moveButtons.setVisibility(View.GONE);
                        }
                        else {
                            moveButtons.setVisibility(View.VISIBLE);
                        }
                    }
                });
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Integer.parseInt(newValue.getText().toString()) < 10)
                        {
                            newValue.setText(String.valueOf(Integer.parseInt(newValue.getText().toString()) + 1));
                        }
                        else {
                            Toast toast = Toast.makeText(getActivity(), "Значение характеристики не может превышать 10", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
                minusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Integer.parseInt(newValue.getText().toString()) > 0)
                        {
                            newValue.setText(String.valueOf(Integer.parseInt(newValue.getText().toString()) - 1));
                        }
                        else {
                            Toast toast = Toast.makeText(getActivity(), "Значение характеристики не может быть меньше 0", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                });
                ImageView closeButton = popUpView.findViewById(R.id.close_popup);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText newValue = popUpView.findViewById(R.id.new_value);
                        if(newValue.getText().toString().equals(""))
                        {
                            Toast toast = Toast.makeText(getActivity(), "Укажите значение", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else if(Integer.parseInt(newValue.getText().toString()) > 10)
                        {
                            Toast toast = Toast.makeText(getActivity(), "Значение характеристики не может превышать 10", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else if(Integer.parseInt(newValue.getText().toString()) < 0)
                        {
                            Toast toast = Toast.makeText(getActivity(), "Значение характеристики не может быть меньше 0", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else if((Integer.parseInt(improvementField.getText().toString()) -
                                Integer.parseInt(newValue.getText().toString())) < 0)
                        {
                            Toast toast = Toast.makeText(getActivity(), "Недостаточно очков улучшения", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            improvementField.setText(String.valueOf(Integer.parseInt(improvementField.getText().toString()) -
                                    Integer.parseInt(newValue.getText().toString())));
                            Feature newFeature = (Feature) parent.getItemAtPosition(position);
                            newFeature.setFeatureValue(Integer.parseInt(newValue.getText().toString()));
                            mainStats.set(position, newFeature);
                            UpdateStats(mainStats);
                            popupWindow.dismiss();
                        }
                    }
                });
            }
        });
        toAbilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                playerCharacter.setStats(mainStats);
                playerCharacter.setAdditionalStats(additionalStats);
                bundle.putSerializable("character", playerCharacter);
                Navigation.findNavController(v).navigate(R.id.fragmentAbilities, bundle);
            }
        });
        System.out.println(playerCharacter.getStartingEquipment().size());
        return view;
    }

    public void UpdateStats(List<Feature> mainStats)
    {
        Feature feature =  (Feature) additionalView.getItemAtPosition(1);
        int newStun = (mainStats.get(3).getFeatureValue() + mainStats.get(7).getFeatureValue()) / 2;
        if(newStun > 10)
        {
            feature.setFeatureValue(10);
        }
        else {
            feature.setFeatureValue(newStun);
        }
        feature =  (Feature) additionalView.getItemAtPosition(2);
        feature.setFeatureValue(mainStats.get(4).getFeatureValue() * 3);
        feature =  (Feature) additionalView.getItemAtPosition(3);
        feature.setFeatureValue((mainStats.get(4).getFeatureValue() * 3) / 5);
        feature =  (Feature) additionalView.getItemAtPosition(4);
        feature.setFeatureValue((mainStats.get(3).getFeatureValue() + mainStats.get(7).getFeatureValue()) / 2 * 5);
        feature =  (Feature) additionalView.getItemAtPosition(5);
        feature.setFeatureValue((mainStats.get(3).getFeatureValue() + mainStats.get(7).getFeatureValue()) / 2 * 5);
        feature =  (Feature) additionalView.getItemAtPosition(6);
        feature.setFeatureValue((mainStats.get(3).getFeatureValue() * 10));
        feature =  (Feature) additionalView.getItemAtPosition(7);
        feature.setFeatureValue((mainStats.get(3).getFeatureValue() + mainStats.get(7).getFeatureValue()) / 2);
        feature =  (Feature) additionalView.getItemAtPosition(8);
        feature.setFeatureValue((((mainStats.get(3).getFeatureValue() + 1) / 2) - 3) * 2);
        statsAdapter.notifyDataSetChanged();
        System.out.println("I tried");
    }

}