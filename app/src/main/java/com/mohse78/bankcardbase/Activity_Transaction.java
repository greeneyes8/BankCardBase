package com.mohse78.bankcardbase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mohse78.bankcardbase.fragments.Fragment_add_transaction;
import com.mohse78.bankcardbase.fragments.Fragment_factory;
import com.mohse78.bankcardbase.fragments.Fragment_show_transactionList;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import DB.DBFactory;
import recyclerClasses.CardShowing;
import recyclerClasses.RecyclerAdapterTransactions;



public class Activity_Transaction extends AppCompatActivity implements View.OnClickListener{

    public static final String CARDSHOWING_SELECTED = "CARDSHOWING_SELECTED";
    public static final String CASH_MONEY = "CASH_MONEY";
    public static final String DEBIT_OR_CREDIT = "DEBIT_OR_CREDIT";
    public static final String ACCOUNT_ID = "ACCOUNT_ID";

    DBFactory db;

    private boolean cashOrCard = false;
    long accountId = 0;

    TextView tvCardNumber;
    TextView tvPersonName;
    TextView tvCvv2;
    TextView tvExpDate;
    LinearLayout backCardImage;

    TextView tvBtnDebit;
    TextView tvBtnCredit;
    TextView tvBtnTransactionList;
    TextView tvBtnDebitUnserLineColor;
    TextView tvBtnCreditUnserLineColor;
    TextView tvBtnTransactionListUnserLineColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        db = new DBFactory(this);

        tvBtnDebit = (TextView) findViewById(R.id.textButton_debit);
        tvBtnCredit= (TextView) findViewById(R.id.textButton_credit);
        tvBtnTransactionList = (TextView) findViewById(R.id.textButton_transactionList);
        tvBtnDebitUnserLineColor= (TextView) findViewById(R.id.textButton_debit_underLineColor);
        tvBtnCreditUnserLineColor= (TextView) findViewById(R.id.textButton_credit_underLineColor);
        tvBtnTransactionListUnserLineColor= (TextView) findViewById(R.id.textButton_transactionList_underLineColor);
        tvBtnCredit.setOnClickListener(this);
        tvBtnDebit.setOnClickListener(this);
        tvBtnTransactionList.setOnClickListener(this);

        tvCardNumber = (TextView) findViewById(R.id.text_cardView2_cardShowing_cardNumber);
        tvPersonName = (TextView) findViewById(R.id.text_cardView2_cardShowing_personName);
        tvCvv2 = (TextView) findViewById(R.id.text_cardView2_cardShowing_cvv2_content);
        tvExpDate = (TextView) findViewById(R.id.text_cardView2_cardShowing_expDate_content);
        backCardImage = (LinearLayout) findViewById(R.id.linear_cardView2_cardShowing_back);

        tvBtnTransactionList.callOnClick();

        cashOrCard = getIntent().getExtras().getBoolean(CASH_MONEY);
        if(cashOrCard){
            //cash
            backCardImage.setBackgroundResource(R.drawable.money);
            accountId = 1;
        }else
        {
            CardShowing cardShowing = null;
            //card
            if(getIntent().getSerializableExtra(CARDSHOWING_SELECTED) != null)
                cardShowing = (CardShowing) getIntent().getSerializableExtra(CARDSHOWING_SELECTED);

            if(cardShowing != null){
                backCardImage.setBackgroundResource(cardShowing.getBankCardImageId());
                tvCardNumber.setText(cardShowing.getCardNumber());
                tvPersonName.setText(cardShowing.getPersonName());
                tvExpDate.setText(cardShowing.getExpDate());
                tvCvv2.setText(cardShowing.getCvv2());
                Card card = db.getCard(cardShowing.getCardId());
                accountId = card.getAccount_id();
            }
        }
    }

    private void resetTextBtn(){
        tvBtnDebit.setTypeface(null , Typeface.NORMAL);
        tvBtnCredit.setTypeface(null , Typeface.NORMAL);
        tvBtnTransactionList.setTypeface(null , Typeface.NORMAL);
        tvBtnCredit.setTextColor(Color.GRAY);
        tvBtnDebit.setTextColor(Color.GRAY);
        tvBtnTransactionList.setTextColor(Color.GRAY);
        tvBtnDebitUnserLineColor.setBackgroundColor(Color.WHITE);
        tvBtnCreditUnserLineColor.setBackgroundColor(Color.WHITE);
        tvBtnTransactionListUnserLineColor.setBackgroundColor(Color.WHITE);
    }
    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();

        if(v.getId() == tvBtnDebit.getId()){
            resetTextBtn();
            tvBtnDebit.setTypeface(null , Typeface.BOLD);
            tvBtnDebit.setTextColor(Color.BLACK);
            tvBtnDebitUnserLineColor.setBackgroundColor(Color.RED);

            //DEBIT
            bundle.putLong(ACCOUNT_ID , accountId);
            bundle.putBoolean(DEBIT_OR_CREDIT,true);
            Fragment_add_transaction transaction_debit = new Fragment_add_transaction();
            transaction_debit.setArguments(bundle);
            Fragment_factory.setFragemtnToFrame(R.id.frame_transaction_contents , getSupportFragmentManager() , transaction_debit , false);

        }
        if(v.getId() == tvBtnCredit.getId()){
            resetTextBtn();
            tvBtnCredit.setTypeface(null , Typeface.BOLD);
            tvBtnCredit.setTextColor(Color.BLACK);
            tvBtnCreditUnserLineColor.setBackgroundColor(Color.RED);

            //CREDIT
            bundle.putLong(ACCOUNT_ID , accountId);
            bundle.putBoolean(DEBIT_OR_CREDIT,false);
            Fragment_add_transaction transaction_credit = new Fragment_add_transaction();
            transaction_credit.setArguments(bundle);
            Fragment_factory.setFragemtnToFrame(R.id.frame_transaction_contents , getSupportFragmentManager() , transaction_credit , false);

        }
        if(v.getId() == tvBtnTransactionList.getId()){
            resetTextBtn();
            tvBtnTransactionList.setTypeface(null , Typeface.BOLD);
            tvBtnTransactionList.setTextColor(Color.BLACK);
            tvBtnTransactionListUnserLineColor.setBackgroundColor(Color.RED);
            Fragment_factory.setFragemtnToFrame(R.id.frame_transaction_contents , getSupportFragmentManager() , new Fragment_show_transactionList() , false);
        }

    }
}
