package com.mohse78.bankcardbase.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mohse78.bankcardbase.Account;
import com.mohse78.bankcardbase.AccountTransaction;
import com.mohse78.bankcardbase.R;

import java.util.Date;

import DB.DBFactory;

/**
 * Created by ab.mohammadi on 4/15/2017.
 */
public class Fragment_add_transaction extends Fragment {


    public static final String DEBIT_OR_CREDIT = "DEBIT_OR_CREDIT";
    public static final String ACCOUNT_ID = "ACCOUNT_ID";


    Boolean debitOrCredit = null;
    DBFactory db;
    TextView tvTitleDebitOrCredit;
    TextView tvBtnAdd;
    TextView tvCurrentBalance;
    EditText editAmount;
    EditText editDescription;
    Account account = null;

    double balanceBeforTransaction;
    double balanceAfterTransaction;
    double amount;
    double debit;
    double credit;
    String description;
    String snackBarMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_transaction, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        tvTitleDebitOrCredit = (TextView) getActivity().findViewById(R.id.text_add_transaction_title);
        tvCurrentBalance = (TextView) getActivity().findViewById(R.id.edit_add_transaction_currentBalance);
        tvBtnAdd = (TextView) getActivity().findViewById(R.id.text_add_transaction_apply);
        editAmount = (EditText) getActivity().findViewById(R.id.edit_add_transaction_amount);
        editDescription = (EditText) getActivity().findViewById(R.id.edit_add_transaction_description);
        db = new DBFactory(getContext());






        Bundle bundle = getArguments();
        if(bundle != null){
            debitOrCredit = bundle.getBoolean(DEBIT_OR_CREDIT);
            account = db.getAccount(bundle.getLong(ACCOUNT_ID));
            balanceBeforTransaction = account.getBalance();
            tvCurrentBalance.setText(account.getBalance()+"");
        }

        if(debitOrCredit)
            tvTitleDebitOrCredit.setText(R.string.debit);
        else
            tvTitleDebitOrCredit.setText(R.string.credit);









        tvBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //apply

                if (editAmount.getText().length() > 0)
                    amount = Double.parseDouble(editAmount.getText().toString());
                if (editDescription.getText().length() > 0)
                    description = editDescription.getText().toString();
                if (debitOrCredit){
                    if (amount > balanceBeforTransaction) {
                        snackBarMessage = getString(R.string.currentBalanceLessThanDebit);
                        Snackbar.make(v,snackBarMessage,Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    debit = amount;
                    credit = 0;
                    balanceAfterTransaction = balanceBeforTransaction - debit;
                    snackBarMessage = getString(R.string.addDebitSuccess);

                }else {
                    debit = 0;
                    credit = amount;
                    balanceAfterTransaction = balanceBeforTransaction + credit;
                    snackBarMessage = getString(R.string.addCreditSuccess);

                }

                AccountTransaction newTransaction = new AccountTransaction();

                newTransaction.setAccount_id(account.getId());
                newTransaction.setLastBalance(balanceBeforTransaction);
                newTransaction.setCurrentBalance(balanceAfterTransaction);
                newTransaction.setCreationDate(new Date());
                newTransaction.setCredit(credit);
                newTransaction.setDebit(debit);
                newTransaction.setDescription(description);
                newTransaction.setTransactionType(1000L);
                account.setBalance(balanceAfterTransaction);

                db.insertAccountTransaction(newTransaction);
                db.updateAccount(account);
                tvCurrentBalance.setText(balanceAfterTransaction+"");
                Snackbar.make(v,snackBarMessage,Snackbar.LENGTH_SHORT).show();


            }


        });

    }

}
