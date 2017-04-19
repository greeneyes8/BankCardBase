package com.mohse78.bankcardbase.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohse78.bankcardbase.Account;
import com.mohse78.bankcardbase.Bank;
import com.mohse78.bankcardbase.Card;
import com.mohse78.bankcardbase.Person;
import com.mohse78.bankcardbase.R;
import com.mohse78.bankcardbase.classes.getStringSplitedWithNumber;

import java.util.ArrayList;
import java.util.List;

import DB.DBFactory;
import recyclerClasses.CardShowing;
import recyclerClasses.RecyclerAdapterCards;

/**
 * Created by ab.mohammadi on 1/31/2017.
 */
public class Fragment_home extends Fragment {

    DBFactory db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home  ,container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        db= new DBFactory(getContext());
        List<Card> cards  = db.getAllCards();
        if(cards == null)
            return;

        List<CardShowing> cardShowingList = new ArrayList<>();
        for(Card card : cards){
            CardShowing cardShowing = new CardShowing();

            cardShowing.setCardNumber(getStringSplitedWithNumber.setCardNumber4Digits(card.getCardNumber() , 4));
            cardShowing.setExpDate(getStringSplitedWithNumber.setCardNumber4Digits(card.getExpDate() , 2));
            cardShowing.setCvv2(card.getCvv2());
            cardShowing.setCardId(card.getId());
            Bank bank = db.getBank(card.getBank_id());
            cardShowing.setBankCardImageId(bank.getImageCardId());

            Account account = db.getAccount(card.getAccount_id());
            double currentBalance = 0.0;
            if(account.getBalance() != null && account.getBalance() > 0)
                currentBalance = account.getBalance();
            cardShowing.setCurrentBalance(currentBalance);

            Person person = db.getPerson(card.getPerson_id());
            cardShowing.setPersonName(person.getName());

            cardShowingList.add(cardShowing);
        }

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_cards);
        RecyclerAdapterCards adapterCards = new RecyclerAdapterCards(getContext() , cardShowingList);
        recyclerView.setAdapter(adapterCards);

        LinearLayoutManager lmn = new LinearLayoutManager(getContext());
        lmn.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lmn);

    }

}
