package com.example.thewitchercharachtersheet.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.objects.Race;
import com.example.thewitchercharachtersheet.objects.WitcherCharacter;

import java.util.concurrent.ThreadLocalRandom;

public class FragmentBackstory extends Fragment {
    EditText name;
    EditText age;
    Spinner gender;
    Spinner race;
    Spinner regionSpinner;
    Spinner homeSpinner;
    Spinner familySpinner;
    ImageView rollRandom;
    String[] northCountry;
    String[] nilfgaardCountry;
    String[] elderCountry;
    String[] names;
    WitcherCharacter playerCharacter;
    Button toOccupation;
    Button toStats;

    public FragmentBackstory() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_backstory, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        playerCharacter = (WitcherCharacter) getArguments().getSerializable("character");
        name = view.findViewById(R.id.nameField);
        rollRandom = view.findViewById(R.id.roll_random);
        age = view.findViewById(R.id.ageField);
        gender = view.findViewById(R.id.gender);
        race = view.findViewById(R.id.race);
        regionSpinner = view.findViewById(R.id.region_spinner);
        homeSpinner = view.findViewById(R.id.home_spinner);
        familySpinner = view.findViewById(R.id.family_spinner);
        toOccupation = view.findViewById(R.id.back_to_occupation);
        toStats = view.findViewById(R.id.to_stats);
        String[] genders = new String[]{"Мужчина", "Женщина"};
        String[] races;
        if(playerCharacter.getOccupation().getName() == getString(R.string.witcher) )
        {
            races = new String[]{getString(R.string.witcher)};
            race.setEnabled(false);
        }
        else if(playerCharacter.getOccupation().getName() == getString(R.string.mage) || playerCharacter.getOccupation().getName() == getString(R.string.priest))
        {
            races = new String[]{"Раса", getString(R.string.human), getString(R.string.elf)};
        }
        else {
            races = new String[]{"Раса", getString(R.string.human), getString(R.string.elf), getString(R.string.dwarf)};
        }
        String[] regions = new String[]{ getString(R.string.north_name), getString(R.string.nilfgaard_name), getString(R.string.elder_name) };
        northCountry = getResources().getStringArray(R.array.country_north);
        nilfgaardCountry = getResources().getStringArray(R.array.country_nilfgaard);
        elderCountry = getResources().getStringArray(R.array.country_elder);
        ArrayAdapter<String> adapterString = new ArrayAdapter<>(getActivity(), R.layout.my_spinner_item_one, genders);
        gender.setAdapter(adapterString);
        adapterString = new ArrayAdapter<>(getActivity(),R.layout.my_spinner_item_one, races);
        race.setAdapter(adapterString);
        adapterString = new ArrayAdapter<>(getActivity(), R.layout.my_spinner_item_one, regions);
        regionSpinner.setAdapter(adapterString);
        adapterString = new ArrayAdapter<>(getActivity(), R.layout.my_spinner_item_one, getResources().getStringArray(R.array.family_status));
        familySpinner.setAdapter(adapterString);
        names = getResources().getStringArray(R.array.human_names);
        try {
            gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    playerCharacter.setGender(parent.getItemAtPosition(position).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    homeSpinner.setEnabled(true);
                    if(parent.getItemAtPosition(position).toString() == getString(R.string.north_name)) {
                        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(getActivity(), R.layout.my_spinner_item_one, northCountry);
                        homeSpinner.setAdapter(countryAdapter);
                    }
                    else if(parent.getItemAtPosition(position).toString() == getString(R.string.nilfgaard_name)) {
                        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(getActivity(), R.layout.my_spinner_item_one, nilfgaardCountry);
                        homeSpinner.setAdapter(countryAdapter);
                    }
                    else if(parent.getItemAtPosition(position).toString() == getString(R.string.elder_name)) {
                        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(getActivity(), R.layout.my_spinner_item_one, elderCountry);
                        homeSpinner.setAdapter(countryAdapter);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            toStats.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerCharacter.setName(name.getText().toString());
                    playerCharacter.setAge(Integer.parseInt(age.getText().toString()));
                    playerCharacter.setRace(new Race(race.getSelectedItem().toString()));
                    playerCharacter.setGender(gender.getSelectedItem().toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("character", playerCharacter);
                    Navigation.findNavController(v).navigate(R.id.fragmentStats, bundle);
                }
            });
            System.out.println(playerCharacter.getStartingEquipment().size());
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        rollRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(names[ThreadLocalRandom.current().nextInt(names.length)], TextView.BufferType.EDITABLE);
            }
        });
    }
}