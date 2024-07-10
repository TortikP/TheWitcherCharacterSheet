package com.example.thewitchercharachtersheet.fragments;

import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.adapters.StatsAdapter;
import com.example.thewitchercharachtersheet.objects.Ability;
import com.example.thewitchercharachtersheet.objects.WitcherCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class FragmentAbilities extends Fragment {

    View view;
    WitcherCharacter playerCharacter;
    EditText improvementField;
    ListView[] abilitiesViews;
    ListView intelligenceView;
    ListView reactionView;
    ListView agilityView;
    ListView constitutionView;
    ListView empathyView;
    ListView craftView;
    ListView willView;
    String[] statNames;
    List<Ability> abilityList;
    String[][] abilities;
    String[] hardAbilities;
    StatsAdapter statsAdapter;
    HashMap<String, List<Ability>> map;
    Button toEquipment;
    public FragmentAbilities() {
        // Required empty public constructor
    }
    int namePosition;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_abilities, container, false);
        playerCharacter = (WitcherCharacter) getArguments().getSerializable("character");
        intelligenceView = view.findViewById(R.id.intelligence_list);
        reactionView = view.findViewById(R.id.reaction_list);
        agilityView = view.findViewById(R.id.agility_list);
        constitutionView = view.findViewById(R.id.constitution_list);
        empathyView = view.findViewById(R.id.empathy_list);
        craftView = view.findViewById(R.id.craft_list);
        willView = view.findViewById(R.id.will_list);
        toEquipment = view.findViewById(R.id.to_equipment);
        improvementField = view.findViewById(R.id.improvement_points);
        hardAbilities = new String[]{getString(R.string.monster_knowledge), getString(R.string.tactics), getString(R.string.language_common),
                getString(R.string.language_dwarven), getString(R.string.language_elder_speech), getString(R.string.alchemy), getString(R.string.trap_knowledge),
        getString(R.string.crafting), getString(R.string.hex_craft), getString(R.string.ritual_craft), getString(R.string.magic_resistance), getString(R.string.spellcraft)};
        abilitiesViews = new ListView[]{intelligenceView, reactionView, agilityView, constitutionView, empathyView, craftView, willView};
        abilityList = new ArrayList<>();
        map = new HashMap<>();
        statNames = new String[]{ getString(R.string.intelligence), getString(R.string.reaction),
                getString(R.string.agility),getString(R.string.constitution),getString(R.string.empathy),
                getString(R.string.craft),getString(R.string.will),};
        abilities = new String[][]{getResources().getStringArray(R.array.intelligence_abilities),
                getResources().getStringArray(R.array.reaction_abilities),
                getResources().getStringArray(R.array.agility_abilities),
                getResources().getStringArray(R.array.constitution_abilities),
                getResources().getStringArray(R.array.empathy_abilities),
                getResources().getStringArray(R.array.craft_abilities),
                getResources().getStringArray(R.array.will_abilities)};
        System.out.println(playerCharacter.getOccupation().getOccupationAbilities());
        System.out.println(playerCharacter.getStartingEquipment().size());
        for(int i = 0;i<abilitiesViews.length;i++)
        {
            namePosition = i;
            abilityList = new ArrayList<>();
            if(Objects.equals(playerCharacter.getOccupation().getMainAbility().getKeyStatName(), statNames[i]))
            {
                abilityList.add(new Ability(playerCharacter.getOccupation().getMainAbility().getFeatureName(), playerCharacter.getOccupation().getMainAbility().getKeyStatName()));
            }
            for (int j = 0;j<abilities[i].length;j++)
            {
                Ability ability = new Ability(abilities[i][j], statNames[i]);
                if(!playerCharacter.getOccupation().getOccupationAbilities().contains(ability.getFeatureName()))
                {
                    ability.setEnabled(false);
                }
                if(playerCharacter.getOccupation().getOccupationAbilities().contains(getString(R.string.any_language)))
                {
                    if((Objects.equals(ability.getFeatureName(), getString(R.string.language_common)) || Objects.equals(ability.getFeatureName(), getString(R.string.language_dwarven)) ||
                            Objects.equals(ability.getFeatureName(), getString(R.string.language_elder_speech)))) {
                        ability.setEnabled(true);
                        System.out.println("Find language");
                    }
                }
                if(Arrays.asList(hardAbilities).contains(ability.getFeatureName()))
                {
                    ability.setFeatureName(ability.getFeatureName() + " (2)");
                    ability.setCostModifier(2);
                }
                abilityList.add(ability);
            }
            map.put(statNames[i], abilityList);
            statsAdapter = new StatsAdapter(getActivity(), map.get(statNames[i]), false);
            abilitiesViews[i].setAdapter(statsAdapter);
            abilitiesViews[i].setOnItemClickListener(new AdapterView.OnItemClickListener() {
                final String statName = statNames[namePosition];
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Ability newAbility = (Ability) parent.getItemAtPosition(position);
                    if(newAbility.isEnabled()) {
                        View popUpView = getLayoutInflater().inflate(R.layout.stat_new_value, null);
                        int width = ViewGroup.LayoutParams.MATCH_PARENT;
                        int height = ViewGroup.LayoutParams.MATCH_PARENT;
                        boolean focusable = true;
                        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
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
                                int improvementPointsChange;
                                int pointChange = Integer.parseInt(newValue.getText().toString()) - newAbility.getFeatureValue();
                                System.out.println(newAbility.getFeatureName());
                                if(Arrays.asList(hardAbilities).contains(newAbility.getFeatureName().substring(0, newAbility.getFeatureName().length() - 4))) {
                                    improvementPointsChange = Integer.parseInt(improvementField.getText().toString()) - (pointChange * 2);
                                }
                                else {
                                    improvementPointsChange = Integer.parseInt(improvementField.getText().toString()) - pointChange;
                                }
                                if (newValue.getText().toString().equals("")) {
                                    Toast toast = Toast.makeText(getActivity(), "Укажите значение", Toast.LENGTH_SHORT);
                                    toast.show();
                                } else if (Integer.parseInt(newValue.getText().toString()) > 6) {
                                    Toast toast = Toast.makeText(getActivity(), "Значение навыка не может превышать 6", Toast.LENGTH_SHORT);
                                    toast.show();
                                } else if (Integer.parseInt(newValue.getText().toString()) < 0) {
                                    Toast toast = Toast.makeText(getActivity(), "Значение навыка не может быть меньше 0", Toast.LENGTH_SHORT);
                                    toast.show();
                                } else if (improvementPointsChange < 0) {
                                    Toast toast = Toast.makeText(getActivity(), "Недостаточно очков улучшения", Toast.LENGTH_SHORT);
                                    toast.show();
                                } else {
                                    if(playerCharacter.getOccupation().getOccupationAbilities().contains(getString(R.string.any_language)))
                                    {
                                        System.out.println(newAbility.getFeatureName());
                                        if(Objects.equals(newAbility.getFeatureName(), getString(R.string.language_common) + " (2)") ||
                                                Objects.equals(newAbility.getFeatureName(), getString(R.string.language_dwarven) + " (2)") ||
                                                Objects.equals(newAbility.getFeatureName(), getString(R.string.language_elder_speech) + " (2)"))
                                        {
                                            List<Ability> langs = new ArrayList<>();
                                            if (Integer.parseInt(newValue.getText().toString()) == 0) {
                                                if (Objects.equals(newAbility.getFeatureName(), getString(R.string.language_common)  + " (2)")) {
                                                    langs.add((Ability) parent.getItemAtPosition(position + 1));
                                                    langs.add((Ability) parent.getItemAtPosition(position + 2));
                                                    langs.get(0).setEnabled(true);
                                                    langs.get(1).setEnabled(true);
                                                    map.get(statName).set(position + 1, langs.get(0));
                                                    map.get(statName).set(position + 2, langs.get(1));
                                                } else if (Objects.equals(newAbility.getFeatureName(), getString(R.string.language_elder_speech)  + " (2)")) {
                                                    langs.add((Ability) parent.getItemAtPosition(position + 1));
                                                    langs.add((Ability) parent.getItemAtPosition(position - 1));
                                                    langs.get(0).setEnabled(true);
                                                    langs.get(1).setEnabled(true);
                                                    map.get(statName).set(position + 1, langs.get(0));
                                                    map.get(statName).set(position - 1, langs.get(1));
                                                } else if (Objects.equals(newAbility.getFeatureName(), getString(R.string.language_dwarven)  + " (2)")) {
                                                    langs.add((Ability) parent.getItemAtPosition(position - 1));
                                                    langs.add((Ability) parent.getItemAtPosition(position - 2));
                                                    langs.get(0).setEnabled(true);
                                                    langs.get(1).setEnabled(true);
                                                    map.get(statName).set(position - 1, langs.get(0));
                                                    map.get(statName).set(position - 2, langs.get(1));
                                                }
                                                System.out.println("Language chosen");
                                            } else {
                                                if (Objects.equals(newAbility.getFeatureName(), getString(R.string.language_common)  + " (2)")) {
                                                    langs.add((Ability) parent.getItemAtPosition(position + 1));
                                                    langs.add((Ability) parent.getItemAtPosition(position + 2));
                                                    langs.get(0).setEnabled(false);
                                                    langs.get(1).setEnabled(false);
                                                    map.get(statName).set(position + 1, langs.get(0));
                                                    map.get(statName).set(position + 2, langs.get(1));
                                                } else if (Objects.equals(newAbility.getFeatureName(), getString(R.string.language_elder_speech)  + " (2)")) {
                                                    langs.add((Ability) parent.getItemAtPosition(position + 1));
                                                    langs.add((Ability) parent.getItemAtPosition(position - 1));
                                                    langs.get(0).setEnabled(false);
                                                    langs.get(1).setEnabled(false);
                                                    map.get(statName).set(position + 1, langs.get(0));
                                                    map.get(statName).set(position - 1, langs.get(1));
                                                } else if (Objects.equals(newAbility.getFeatureName(), getString(R.string.language_dwarven)  + " (2)")) {
                                                    langs.add((Ability) parent.getItemAtPosition(position - 1));
                                                    langs.add((Ability) parent.getItemAtPosition(position - 2));
                                                    langs.get(0).setEnabled(false);
                                                    langs.get(1).setEnabled(false);
                                                    map.get(statName).set(position - 1, langs.get(0));
                                                    map.get(statName).set(position - 2, langs.get(1));
                                                }
                                                System.out.println("Language chosen");
                                            }
                                        }
                                    }
                                    improvementField.setText(String.valueOf(improvementPointsChange));
                                    newAbility.setFeatureValue(Integer.parseInt(newValue.getText().toString()));
                                    map.get(statName).set(position, newAbility);
                                    StatsAdapter adapter = (StatsAdapter) parent.getAdapter();
                                    adapter.notifyDataSetChanged();
                                    popupWindow.dismiss();
                                }
                            }
                        });
                    }
                }
            });
            setListViewHeightBasedOnChildren(abilitiesViews[i]);
        }

        toEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*boolean allChecked = true;
                for (List<Ability> currentAbilities:map.values())
                {
                    for (Ability ability:currentAbilities)
                    {
                        if(ability.getFeatureValue() == 0 &&
                                playerCharacter.getOccupation().getOccupationAbilities().contains(ability.getFeatureName()))
                        {
                            allChecked = false;
                            return;
                        }
                    }
                }
                if(Integer.parseInt(improvementField.getText().toString()) > 0) {
                    Toast toast = Toast.makeText(getActivity(), "У вас остались очки улучшения", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(!allChecked)
                {
                    Toast toast = Toast.makeText(getActivity(), "Хотя бы в одном проффесионально навыкие есть значение 0", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {*/
                    Bundle bundle = new Bundle();
                    playerCharacter.setAbilities(map);
                    bundle.putSerializable("character", playerCharacter);
                    Navigation.findNavController(v).navigate(R.id.fragmentEquipment, bundle);
                //}
            }
        });

        return view;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        StatsAdapter listAdapter = (StatsAdapter) listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}