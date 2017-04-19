package com.mohse78.bankcardbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import DB.DBFactory;
import recyclerClasses.RecyclerAdapterCreditTypes;
import recyclerClasses.TextAndLogo;

public class Activity_TransactionTypeCredit extends AppCompatActivity {

    DBFactory db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_type_credit);
        db = new DBFactory(this);
        List<TransactionType> transactionTypes = db.getAllTransactionTypeCredit();
        List<TextAndLogo> tlCredits = new ArrayList<>();
        for(TransactionType tt :transactionTypes){
            TextAndLogo tl = new TextAndLogo()  ;
            tl.setText(tt.getName());
            tl.setImageLogoId(tt.getImageLogoId());
            tlCredits.add(tl);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_transactionTypeCredit);
        RecyclerAdapterCreditTypes adapterCreditTypes = new RecyclerAdapterCreditTypes(this,tlCredits);
        recyclerView.setAdapter(adapterCreditTypes);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }
}
