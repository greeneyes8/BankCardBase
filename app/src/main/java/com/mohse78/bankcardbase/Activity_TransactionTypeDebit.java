package com.mohse78.bankcardbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import DB.DBFactory;
import recyclerClasses.RecyclerAdapterCreditTypes;
import recyclerClasses.RecyclerAdapterDebitTypes;
import recyclerClasses.TextAndLogo;

public class Activity_TransactionTypeDebit extends AppCompatActivity {
    DBFactory db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_type_debit);
        db = new DBFactory(this);
        List<TransactionType> transactionTypes = db.getAllTransactionTypeDebit();
        List<TextAndLogo> tlDebits = new ArrayList<>();
        for(TransactionType tt :transactionTypes){
            TextAndLogo tl = new TextAndLogo()  ;
            tl.setText(tt.getName());
            tl.setImageLogoId(tt.getImageLogoId());
            tlDebits.add(tl);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_transactionTypeDebit);
        RecyclerAdapterDebitTypes adapterDebitTypes = new RecyclerAdapterDebitTypes(this,tlDebits);
        recyclerView.setAdapter(adapterDebitTypes);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }
}
