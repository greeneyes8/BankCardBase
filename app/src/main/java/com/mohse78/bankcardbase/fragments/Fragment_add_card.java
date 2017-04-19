package com.mohse78.bankcardbase.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mohse78.bankcardbase.Account;
import com.mohse78.bankcardbase.Activity_Banks;
import com.mohse78.bankcardbase.Activity_Persons;
import com.mohse78.bankcardbase.Bank;
import com.mohse78.bankcardbase.Card;
import com.mohse78.bankcardbase.Person;
import com.mohse78.bankcardbase.R;

import org.w3c.dom.Text;

import DB.DBFactory;
import recyclerClasses.RecyclerAdapterBanks;

/**
 * Created by ab.mohammadi on 1/30/2017.
 */
public class Fragment_add_card extends Fragment implements View.OnClickListener {


    public static final String LAST_BANK_SELECTED_ID = "LAST_BANK_SELECTED_ID";
    public static final String LAST_BANK_SELECTED_LOGO = "LAST_BANK_SELECTED_LOGO";
    public static final String LAST_BANK_SELECTED_NAME = "LAST_BANK_SELECTED_NAME";

    public static final String LAST_PERSON_SELECTED_ID = "LAST_PERSON_SELECTED_ID";
    public static final String LAST_PERSON_SELECTED_LOGO = "LAST_PERSON_SELECTED_LOGO";
    public static final String LAST_PERSON_SELECTED_NAME = "LAST_PERSON_SELECTED_NAME";

    public static final String NEW_BANK_PAGE= "NEW_BANK_PAGE";
    public static final String NEW_PERSON_PAGE= "NEW_PERSON_PAGE";



    TextView text_btn_add_card;
    TextView tv_btn_bankSelected;
    TextView tv_btn_personSelected;
    ImageView iv_btn_bankSelected;
    ImageView iv_btn_personSelected;
    Animation animationFade;
    LinearLayout linear_btn_persons;
    LinearLayout linear_btn_banks;

    EditText editCardNumber1;
    EditText editCardNumber2;
    EditText editCardNumber3;
    EditText editCardNumber4;
    EditText editExpDateYear;
    EditText editExpDateMounth;
    EditText editCvv2;
    EditText editAccountNumber;
    CheckBox checkTransactionActivate;


    String cardNumber1Str;
    String cardNumber2Str;
    String cardNumber3Str;
    String cardNumber4Str;
    String cardNumber;
    String expDateYearStr;
    String expDatemounthStr;
    String expDateStr;
    String cvv2Str;
    String accountNumberStr;
    boolean transactionActivate;
    DBFactory db;
    String errorMessage;

    SharedPreferences sharedPreferences;

    long lastBankIdSelected  = 1;
    long lastPersonIdSelected  = 1;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_card , container ,false);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)  {
        super.onActivityCreated(savedInstanceState);
        db = new DBFactory(getContext());

         tv_btn_bankSelected= (TextView) getActivity().findViewById(R.id.text_add_card_selectedBankName);
         tv_btn_personSelected= (TextView) getActivity().findViewById(R.id.text_add_card_selectedPersonName);
         iv_btn_bankSelected= (ImageView) getActivity().findViewById(R.id.image_add_card_selectedBankLogo);
         iv_btn_personSelected = (ImageView) getActivity().findViewById(R.id.image_add_card_selectedPersonLogo);

        editCardNumber1 = (EditText) getActivity().findViewById(R.id.edit_add_card_cardNumber1);
        editCardNumber2 = (EditText) getActivity().findViewById(R.id.edit_add_card_cardNumber2);
        editCardNumber3 = (EditText) getActivity().findViewById(R.id.edit_add_card_cardNumber3);
        editCardNumber4 = (EditText) getActivity().findViewById(R.id.edit_add_card_cardNumber4);

        editCvv2 = (EditText) getActivity().findViewById(R.id.edit_add_card_cvv2);
        editExpDateYear = (EditText) getActivity().findViewById(R.id.edit_add_card_expDate_year);
        editExpDateMounth = (EditText) getActivity().findViewById(R.id.edit_add_card_expDate_mounth);

        editAccountNumber = (EditText) getActivity().findViewById(R.id.edit_add_card_accountNumber);
        checkTransactionActivate = (CheckBox) getActivity().findViewById(R.id.check_add_card_transactionsActive);
        animationFade = AnimationUtils.loadAnimation(getActivity() , R.anim.btn_click_fade);
        linear_btn_banks  = (LinearLayout) getActivity().findViewById(R.id.linear_btn_banks);
        linear_btn_persons = (LinearLayout) getActivity().findViewById(R.id.linear_btn_persons);
        text_btn_add_card = (TextView) getActivity().findViewById(R.id.text_btn_add_card);

        linear_btn_banks.setOnClickListener(this);
        linear_btn_persons.setOnClickListener(this);
        text_btn_add_card.setOnClickListener(this);


        Person firstPerson= db.getPerson(1);
        Bank firstBank = db.getBank(1);

        tv_btn_bankSelected.setText(firstBank.getName());
        tv_btn_personSelected.setText(firstPerson.getName());
        iv_btn_bankSelected.setImageResource(firstBank.getImageLogoId());
        iv_btn_personSelected.setImageResource(R.drawable.person_black);



        //set focuses

        linear_btn_banks.requestFocus();

        editCardNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editCardNumber1.setTextColor(Color.BLACK);
                if(editCardNumber1.getText().length() == 4){
                    editCardNumber2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        editCardNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editCardNumber2.setTextColor(Color.BLACK);
                if(editCardNumber2.getText().length() == 4){
                    editCardNumber3.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        editCardNumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editCardNumber3.setTextColor(Color.BLACK);
                if(editCardNumber3.getText().length() == 4){
                    editCardNumber4.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        editCardNumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editCardNumber4.setTextColor(Color.BLACK);
                if(editCardNumber4.getText().length() == 4){
                    editExpDateMounth.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        editExpDateMounth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editExpDateMounth.setTextColor(Color.BLACK);
                if(editExpDateMounth.getText().length() == 2){
                    editExpDateYear.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });


        editExpDateYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editExpDateYear.setTextColor(Color.BLACK);
                if(editExpDateYear.getText().length() == 2){
                    editCvv2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        editCvv2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editCvv2.setTextColor(Color.BLACK);
                if(editCvv2.getText().length() == 4){
                    editAccountNumber.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        editAccountNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editAccountNumber.getText().length() == 4){
                    checkTransactionActivate.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == text_btn_add_card.getId()) {

            boolean isOk = true;
            text_btn_add_card.startAnimation(animationFade);

            //checking Inputs


            if(editCardNumber1.getText().length() <=3) {
                editCardNumber1.setTextColor(Color.RED);
                isOk = false;
                errorMessage = getString(R.string.error_message_cardNumber_sumCharacter);
                Snackbar.make(v,errorMessage, Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(editCardNumber2.getText().length() <=3){
                editCardNumber2.setTextColor(Color.RED);
                isOk = false;
                errorMessage = getString(R.string.error_message_cardNumber_sumCharacter);
                Snackbar.make(v,errorMessage, Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(editCardNumber3.getText().length() <=3) {
                editCardNumber3.setTextColor(Color.RED);
                isOk = false;
                errorMessage = getString(R.string.error_message_cardNumber_sumCharacter);
                Snackbar.make(v,errorMessage, Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(editCardNumber4.getText().length() <=3) {
                editCardNumber4.setTextColor(Color.RED);
                isOk = false;
                errorMessage = getString(R.string.error_message_cardNumber_sumCharacter);
                Snackbar.make(v,errorMessage, Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(editCvv2.getText().length() <3){
                editCvv2.setTextColor(Color.RED);
                isOk = false;
                errorMessage = getString(R.string.error_message_cvv2_sumCharacter);
                Snackbar.make(v,errorMessage, Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(editExpDateMounth.getText().length() <2){
                if(editExpDateMounth.getText().length() > 0){
                    if(Integer.valueOf(editExpDateMounth.getText().toString()) > 1 && Integer.valueOf(editExpDateMounth.getText().toString()) < 9){
                        editExpDateMounth.setText("0"+editExpDateMounth.getText().toString());
                    }
                }else {
                    editExpDateMounth.setTextColor(Color.RED);
                    isOk = false;
                    errorMessage = getString(R.string.error_message_expDate_sumCharacter);
                    Snackbar.make(v, errorMessage, Snackbar.LENGTH_SHORT).show();
                    return;
                }
            }
            if(editExpDateYear.getText().length() <2) {
                editExpDateYear.setTextColor(Color.RED);
                isOk = false;
                errorMessage = getString(R.string.error_message_expDate_sumCharacter);
                Snackbar.make(v,errorMessage, Snackbar.LENGTH_SHORT).show();
                return;
            }

            if(Integer.valueOf(editExpDateMounth.getText().toString())>12 || Integer.valueOf(editExpDateMounth.getText().toString())<1){
                editExpDateMounth.setTextColor(Color.RED);
                isOk = false;
                errorMessage = getString(R.string.error_message_expDate_incorrect);
                Snackbar.make(v,errorMessage, Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(Integer.valueOf(editExpDateYear.getText().toString()) < 95) {
                editExpDateYear.setTextColor(Color.RED);
                isOk = false;
                errorMessage = getString(R.string.error_message_expDate_incorrect);
                Snackbar.make(v,errorMessage, Snackbar.LENGTH_SHORT).show();
                return;
            }


            if(isOk) {

                if(checkTransactionActivate.isChecked())
                    transactionActivate = true;

                cardNumber1Str = editCardNumber1.getText().toString();
                cardNumber2Str = editCardNumber2.getText().toString();
                cardNumber3Str = editCardNumber3.getText().toString();
                cardNumber4Str = editCardNumber4.getText().toString();
                expDatemounthStr = editExpDateMounth.getText().toString();
                expDateYearStr = editExpDateYear.getText().toString();
                expDateStr = expDateYearStr+expDatemounthStr;
                cvv2Str = editCvv2.getText().toString();
                accountNumberStr = editAccountNumber.getText().toString();
                cardNumber = cardNumber1Str + cardNumber2Str +cardNumber3Str + cardNumber4Str;


                if(!(lastBankIdSelected > -1 || lastPersonIdSelected >-1))
                    return;
                Account account = new Account();
                account.setBalance(0.0);
                if(accountNumberStr != null && accountNumberStr.length() > 0)
                    account.setAccountIdOriginal(accountNumberStr);
                if(transactionActivate)
                    account.setForTransaction(true);
                else
                    account.setForTransaction(false);



                Card card = new Card();
                card.setBank_id(lastBankIdSelected);
                card.setPerson_id(lastPersonIdSelected);
                card.setCardNumber(cardNumber);
                card.setCvv2(cvv2Str);
                card.setExpDate(expDateStr);
                if (db.insertCard(card , account) > -1)
                    Snackbar.make(v,R.string.card_added , Snackbar.LENGTH_SHORT).show();
            }


        }

        if(v.getId() == linear_btn_banks.getId()){
            Intent intent = new Intent(getActivity(), Activity_Banks.class);
            startActivity(intent);

        }

        if(v.getId() == linear_btn_persons.getId()){
            Intent intent = new Intent(getActivity(), Activity_Persons.class);
            startActivity(intent);
        }



    }


    @Override
    public void onResume() {
        super.onResume();
        lastBankIdAndPersonId();
    }

    private void lastBankIdAndPersonId() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(sharedPreferences.contains(NEW_BANK_PAGE)){
            boolean newBank = sharedPreferences.getBoolean(NEW_BANK_PAGE , false);
            if(newBank){
                Fragment_factory.setFragemtnToFrame(R.id.frame_main , getActivity().getSupportFragmentManager()
                     ,new Fragment_add_bank() , true
                );
                sharedPreferences.edit().putBoolean(NEW_BANK_PAGE , false).commit();
                return;
            }
        }

        if(sharedPreferences.contains(NEW_PERSON_PAGE)){
            boolean newPerson = sharedPreferences.getBoolean(NEW_PERSON_PAGE , false);
            if(newPerson){
                Fragment_factory.setFragemtnToFrame(R.id.frame_main , getActivity().getSupportFragmentManager()
                        ,new Fragment_add_person() , true
                );
                sharedPreferences.edit().putBoolean(NEW_PERSON_PAGE , false).commit();
                return;
            }
        }


        if(sharedPreferences != null) {
            if (sharedPreferences.contains(LAST_BANK_SELECTED_ID)) {
                lastBankIdSelected = sharedPreferences.getLong(LAST_BANK_SELECTED_ID , -1l);
            }
            if (sharedPreferences.contains(LAST_PERSON_SELECTED_ID)) {
                lastPersonIdSelected = sharedPreferences.getLong(LAST_PERSON_SELECTED_ID , -1l);
            }
        }

        if(lastBankIdSelected == -1l)
            lastBankIdSelected = 1l;
        if(lastPersonIdSelected == -1l)
            lastPersonIdSelected = 1l;


        TextView tvSelectedBankName = (TextView) getActivity().findViewById(R.id.text_add_card_selectedBankName);
        TextView tvSelectedPersonName = (TextView) getActivity().findViewById(R.id.text_add_card_selectedPersonName);
        ImageView ivSelectedBankLogo = (ImageView) getActivity().findViewById(R.id.image_add_card_selectedBankLogo);
        ImageView ivSelectedPersonLogo = (ImageView) getActivity().findViewById(R.id.image_add_card_selectedPersonLogo);

        if(sharedPreferences.contains(LAST_PERSON_SELECTED_NAME) && sharedPreferences.contains(LAST_PERSON_SELECTED_LOGO)){
            String personName = sharedPreferences.getString(LAST_PERSON_SELECTED_NAME , null);
            int personLogoId = sharedPreferences.getInt(LAST_PERSON_SELECTED_LOGO , -1);

            if(personLogoId > -1 && personName != null){
                tvSelectedPersonName.setText(personName);
                ivSelectedPersonLogo.setImageResource(R.drawable.person_black);
            }
        }

        if(sharedPreferences.contains(LAST_BANK_SELECTED_NAME) && sharedPreferences.contains(LAST_BANK_SELECTED_LOGO)){
            String bankName = sharedPreferences.getString(LAST_BANK_SELECTED_NAME , null);
            int bankLogo = sharedPreferences.getInt(LAST_BANK_SELECTED_LOGO , -1);

            if(bankLogo > -1 && bankName != null){
                tvSelectedBankName.setText(bankName);
                ivSelectedBankLogo.setImageResource(bankLogo);
            }
        }



    }

}

