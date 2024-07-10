package com.example.thewitchercharachtersheet.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;

import com.example.thewitchercharachtersheet.DatabaseHelper;
import com.example.thewitchercharachtersheet.R;
import com.example.thewitchercharachtersheet.adapters.CharacterAdapter;
import com.example.thewitchercharachtersheet.adapters.objects.CharacterElement;
import com.example.thewitchercharachtersheet.objects.WitcherCharacter;

import java.util.ArrayList;
import java.util.List;

public class FragmentMain extends Fragment {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    ListView charList;
    ArrayList<String> charNames;
    RelativeLayout newCharacter;
    public FragmentMain() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("What");
        charNames = new ArrayList<>();
        getActivity().getApplicationContext();
        databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());
        db = databaseHelper.getReadableDatabase();
        databaseHelper.create_db();
        try{
            charList = view.findViewById(R.id.charList);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), R.layout.list_element, charNames);
            charList.setAdapter(adapter);
            charList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Navigation.findNavController(view).navigate(R.id.fragmentCharacter);
                }
            });

        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        newCharacter = view.findViewById(R.id.new_character);
        newCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("character", new WitcherCharacter());
                Navigation.findNavController(v).navigate(R.id.fragmentOccupation, bundle);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        // открываем подключение
        db = databaseHelper.open();
        //получаем данные из бд в виде курсора
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] { DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_RACE, DatabaseHelper.COLUMN_OCCUPATION };
        List<CharacterElement> characterElementList = new ArrayList<>();
        while (userCursor.moveToNext())
        {
            characterElementList.add(new CharacterElement(userCursor.getString(1), userCursor.getString(2), userCursor.getString(3)));
        }

        characterElementList.add(new CharacterElement());
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(getContext(), R.layout.character_list_element, userCursor, headers, new
              int[] {R.id.element1, R.id.element2, R.id.element3}, 0);
        CharacterAdapter characterAdapter = new CharacterAdapter(getActivity(), characterElementList);
        charList.setAdapter(userAdapter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }

}