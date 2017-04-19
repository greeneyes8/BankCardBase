package com.mohse78.bankcardbase.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohse78.bankcardbase.R;

/**
 * Created by ab.mohammadi on 4/15/2017.
 */
public class Fragment_show_transactionList extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_transaction_list, container , false);
    }
}
