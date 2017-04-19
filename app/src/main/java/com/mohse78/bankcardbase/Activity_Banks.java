package com.mohse78.bankcardbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import DB.DBFactory;
import recyclerClasses.RecyclerAdapterBanks;
import recyclerClasses.TextAndLogo;

public class Activity_Banks extends AppCompatActivity {

    public static final String DB_NAME = "CARDSDB";
    public static final String LAST_BANK_SELECTED_ID = "LAST_BANK_SELECTED_ID";

    DBFactory db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);

        db = new DBFactory(this);
        List<Bank> banks = db.getAllBanks();
        List<TextAndLogo> tlBanks = new ArrayList<>();
        for(Bank bank : banks){
            TextAndLogo tl = new TextAndLogo();
            tl.setText(bank.getName());
            tl.setImageLogoId(bank.getImageLogoId());
            tlBanks.add(tl);
        }

        TextAndLogo tEnd = new TextAndLogo();
        tEnd.setText(getString(R.string.add_new_bank));
        tEnd.setImageLogoId(R.drawable.bank_logo_ansar);

        tlBanks.add(tEnd);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_banks);
        RecyclerAdapterBanks adapterBanks = new RecyclerAdapterBanks(this,tlBanks);
        recyclerView.setAdapter(adapterBanks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

    }
}
