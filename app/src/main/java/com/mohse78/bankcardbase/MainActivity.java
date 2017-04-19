package com.mohse78.bankcardbase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mohse78.bankcardbase.fragments.Fragment_accounts;
import com.mohse78.bankcardbase.fragments.Fragment_add_card;
import com.mohse78.bankcardbase.fragments.Fragment_add_person;
import com.mohse78.bankcardbase.fragments.Fragment_factory;
import com.mohse78.bankcardbase.fragments.Fragment_home;
import com.mohse78.bankcardbase.fragments.Fragment_search;

import java.util.ArrayList;
import java.util.List;

import DB.DBFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    DrawerLayout drawerLayout;
    public static final String CASH_MONEY = "CASH_MONEY";
    public static final String SHAREDPREFRENCES_MAIN = "SHAREDPREFRENCES_MAIN";
    public static final String FIRST_LOG_IN = "FIRST_LOG_IN";
    public static final String YES = "YES";



    LinearLayout linear_image_addCard;
    LinearLayout linear_image_addPerson;
    LinearLayout linear_image_home;
    LinearLayout linear_image_search;
    LinearLayout linear_image_account;

    ImageView ivToolbarTopRight;
    ImageView ivToolbarTopLeft;
    ImageView ivToolbarBottom_addCard;
    ImageView ivToolbarBottom_addPerson;
    ImageView ivToolbarBottom_home;
    ImageView ivToolbarBottom_search;
    ImageView ivToolbarBottom_accounts;

    ImageView ivBtnAddPerson;
    SharedPreferences sharedPreferencesMain;
    FrameLayout frameLayout;

    DBFactory db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBFactory(this);

        sharedPreferencesMain = getSharedPreferences(SHAREDPREFRENCES_MAIN , 1);


        if(!(sharedPreferencesMain.contains(FIRST_LOG_IN) && sharedPreferencesMain.getString(FIRST_LOG_IN,"").equals(YES))) {
            //first login
            firstInsertion();

            sharedPreferencesMain.edit().putString(FIRST_LOG_IN, YES).commit();

        }



        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ivToolbarTopLeft = (ImageView) findViewById(R.id.image_toolbarTop_left);
        ivToolbarTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        ivToolbarTopRight = (ImageView) findViewById(R.id.image_toolbarTop_right);
        ivToolbarTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        linear_image_addCard = (LinearLayout) findViewById(R.id.linear_toolbarBottom_addCard);
        linear_image_addPerson = (LinearLayout) findViewById(R.id.linear_toolbarBottom_addPerson);
        linear_image_home = (LinearLayout) findViewById(R.id.linear_toolbarBottom_home);
        linear_image_search = (LinearLayout) findViewById(R.id.linear_toolbarBottom_search);
        linear_image_account = (LinearLayout) findViewById(R.id.linear_toolbarBottom_accounts);

        linear_image_addCard.setOnClickListener(this);
        linear_image_addPerson.setOnClickListener(this);
        linear_image_home.setOnClickListener(this);
        linear_image_search.setOnClickListener(this);
        linear_image_account.setOnClickListener(this);



        frameLayout = (FrameLayout) findViewById(R.id.frame_main);
        ivToolbarBottom_addCard = (ImageView) findViewById(R.id.image_toolbarBottom_addCard);
        ivToolbarBottom_addPerson = (ImageView) findViewById(R.id.image_toolbarBottom_addPerson);
        ivToolbarBottom_home = (ImageView) findViewById(R.id.image_toolbarBottom_home);
        ivToolbarBottom_search = (ImageView) findViewById(R.id.image_toolbarBottom_search);
        ivToolbarBottom_accounts = (ImageView) findViewById(R.id.image_toolbarBottom_accounts);
        ivToolbarBottom_addPerson = (ImageView) findViewById(R.id.image_toolbarBottom_addPerson);

        Fragment_factory.setFragemtnToFrame(R.id.frame_main,getSupportFragmentManager(),new Fragment_home(),false);
        ((ImageView)findViewById(R.id.image_toolbarBottom_home)).setImageResource(R.drawable.home_black);
        }


    public void resetMain(){
        ivToolbarBottom_addPerson.setImageResource(R.drawable.add_person);
        ivToolbarBottom_addCard.setImageResource(R.drawable.add_card);
        ivToolbarBottom_home.setImageResource(R.drawable.home);
        ivToolbarBottom_search.setImageResource(R.drawable.search);
        ivToolbarBottom_accounts.setImageResource(R.drawable.account);
    }




    @Override
    public void onClick(View v) {


        resetMain();

        if(v.getId() == linear_image_addCard.getId()){
            ivToolbarBottom_addCard.setImageResource(R.drawable.add_card_black);
            Fragment_factory.setFragemtnToFrame(frameLayout.getId() , getSupportFragmentManager() , new Fragment_add_card() , false);
        }
        if(v.getId() == linear_image_addPerson.getId()){
            ivToolbarBottom_addPerson.setImageResource(R.drawable.add_person_black);
            Fragment_factory.setFragemtnToFrame(frameLayout.getId() , getSupportFragmentManager() , new Fragment_add_person() , false);
        }
        if(v.getId() == linear_image_home.getId()){
            ivToolbarBottom_home.setImageResource(R.drawable.home_black);
            Fragment_factory.setFragemtnToFrame(frameLayout.getId() , getSupportFragmentManager() , new Fragment_home(), false);

        }
        if(v.getId() == linear_image_search.getId()){
            ivToolbarBottom_search.setImageResource(R.drawable.search_black);
            Fragment_factory.setFragemtnToFrame(frameLayout.getId() , getSupportFragmentManager() , new Fragment_search(), false);
        }
        if(v.getId() == linear_image_account.getId()){

            Bundle bundleCash = new Bundle();
            bundleCash.putBoolean(CASH_MONEY , true);
            Intent intent = new Intent(MainActivity.this , Activity_Transaction.class);
            intent.putExtras(bundleCash);
            startActivity(intent);
        }

    }


    private void firstInsertion(){


        db.deleteAllBanks();
        db.deleteAllPersons();
        db.deleteAllTransactionTypes();

        Person person = new Person();
        person.setName(getString(R.string.me));
        person.setDeleted(false);
        db.insertPerson(person);

        List<String> bankNames = new ArrayList<>();
        List<Integer> bankLogoIds = new ArrayList<>();
        List<Integer> bankCardImageId = new ArrayList<>();


        bankNames.add(getString(R.string.bank_ayandeh));
        bankLogoIds.add(R.drawable.bank_logo_ayandeh);
        bankCardImageId.add(R.drawable.bank_card_ayandeh);

        bankNames.add(getString(R.string.bank_gardeshgari));
        bankLogoIds.add(R.drawable.bank_logo_gardeshgari);
        bankCardImageId.add(R.drawable.bank_card_gardeshgari);

        bankNames.add(getString(R.string.bank_hekmat));
        bankLogoIds.add(R.drawable.bank_logo_hekmat);
        bankCardImageId.add(R.drawable.bank_card_hekmat);

        bankNames.add(getString(R.string.bank_iranzamin));
        bankLogoIds.add(R.drawable.bank_logo_iranzamin);
        bankCardImageId.add(R.drawable.bank_card_iranzamin);

        bankNames.add(getString(R.string.bank_keshavarzi));
        bankLogoIds.add(R.drawable.bank_logo_keshavarzi);
        bankCardImageId.add(R.drawable.bank_card_keshavarzi);

        bankNames.add(getString(R.string.bank_maskan));
        bankLogoIds.add(R.drawable.bank_logo_maskan);
        bankCardImageId.add(R.drawable.bank_card_maskan);

        bankNames.add(getString(R.string.bank_mellat));
        bankLogoIds.add(R.drawable.bank_logo_mellat);
        bankCardImageId.add(R.drawable.bank_card_mellat);

        bankNames.add(getString(R.string.bank_melli));
        bankLogoIds.add(R.drawable.bank_logo_melli);
        bankCardImageId.add(R.drawable.bank_card_melli);

        bankNames.add(getString(R.string.bank_parsian));
        bankLogoIds.add(R.drawable.bank_logo_parsian);
        bankCardImageId.add(R.drawable.bank_card_parsian);

        bankNames.add(getString(R.string.bank_pasargad));
        bankLogoIds.add(R.drawable.bank_logo_pasargad);
        bankCardImageId.add(R.drawable.bank_card_pasargad);

        bankNames.add(getString(R.string.bank_resalat));
        bankLogoIds.add(R.drawable.bank_logo_resalat);
        bankCardImageId.add(R.drawable.bank_card_resalat);

        bankNames.add(getString(R.string.bank_saderat));
        bankLogoIds.add(R.drawable.bank_logo_saderat);
        bankCardImageId.add(R.drawable.bank_card_saderat);

        bankNames.add(getString(R.string.bank_saman));
        bankLogoIds.add(R.drawable.bank_logo_saman);
        bankCardImageId.add(R.drawable.bank_card_saman);

        bankNames.add(getString(R.string.bank_sarmayeh));
        bankLogoIds.add(R.drawable.bank_logo_sarmayeh);
        bankCardImageId.add(R.drawable.bank_card_sarmayeh);

        new FirstInsertion(this).FirstInsertionBanks(bankNames, bankLogoIds , bankCardImageId);


        List<String> transTypeNames = new ArrayList<>();
        List<Integer> transTypeLogos = new ArrayList<>();
        List<Boolean> transTypeTypes = new ArrayList<>();


        TransactionType transactionType = new TransactionType();

        transTypeNames.add(getString(R.string.salary));
        transTypeLogos.add(R.drawable.salary);
        transTypeTypes.add(false);
        transTypeNames.add(getString(R.string.money_giv));
        transTypeLogos.add(R.drawable.money);
        transTypeTypes.add(false);
        transTypeNames.add(getString(R.string.other));
        transTypeLogos.add(R.drawable.credit);
        transTypeTypes.add(false);
        transTypeNames.add(getString(R.string.cost_house_buy));
        transTypeLogos.add(R.drawable.buy);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_baby));
        transTypeLogos.add(R.drawable.baby);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_car));
        transTypeLogos.add(R.drawable.car);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_clothes));
        transTypeLogos.add(R.drawable.clothe);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_education));
        transTypeLogos.add(R.drawable.education);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_resturant));
        transTypeLogos.add(R.drawable.food);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_fuel));
        transTypeLogos.add(R.drawable.fuel);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_health));
        transTypeLogos.add(R.drawable.health);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_house));
        transTypeLogos.add(R.drawable.house);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_insurance));
        transTypeLogos.add(R.drawable.insurance);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_party));
        transTypeLogos.add(R.drawable.party);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_personal));
        transTypeLogos.add(R.drawable.personal);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_pets));
        transTypeLogos.add(R.drawable.pets);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_repair));
        transTypeLogos.add(R.drawable.repair);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_sport));
        transTypeLogos.add(R.drawable.sport);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_travel));
        transTypeLogos.add(R.drawable.travel);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.cost_transport));
        transTypeLogos.add(R.drawable.transport);
        transTypeTypes.add(true);
        transTypeNames.add(getString(R.string.other));
        transTypeLogos.add(R.drawable.debit_red);
        transTypeTypes.add(true);


        Account firstAccountForCash = new Account();
        firstAccountForCash.setBalance(0.0);
        firstAccountForCash.setAccountIdOriginal("999");
        firstAccountForCash.setForTransaction(true);
        db.insertAccount(firstAccountForCash);

        new FirstInsertion(this).FirstInsertionTransactionTypes(transTypeNames,transTypeLogos,transTypeTypes);


    }

}
