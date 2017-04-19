package com.mohse78.bankcardbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import DB.DBFactory;
import recyclerClasses.RecyclerAdapterPersons;
import recyclerClasses.TextAndLogo;

public class Activity_Persons extends AppCompatActivity {

    public static final String DB_NAME = "CARDSDB";
    public static final String LAST_PERSON_SELECTED_ID = "LAST_PERSON_SELECTED_ID";


    DBFactory db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        db = new DBFactory(this);

        List<Person> persons = db.getAllPersons();
        List<TextAndLogo> tlList = new ArrayList<>();

        for(Person person : persons){
            TextAndLogo tl = new TextAndLogo();
            tl.setText(person.getName());
            tl.setImageLogoId(R.drawable.person_black);
            tlList.add(tl);
        }

        TextAndLogo tEnd = new TextAndLogo();
        tEnd.setText(getString(R.string.add_new_person));
        tEnd.setImageLogoId(R.drawable.add_person);

        tlList.add(tEnd);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_banks);
        RecyclerAdapterPersons adapterPersons = new RecyclerAdapterPersons(this,tlList);
        recyclerView.setAdapter(adapterPersons);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);


    }
}
