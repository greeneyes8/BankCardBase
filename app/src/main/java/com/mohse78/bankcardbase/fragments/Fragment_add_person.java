package com.mohse78.bankcardbase.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mohse78.bankcardbase.MainActivity;
import com.mohse78.bankcardbase.Person;
import com.mohse78.bankcardbase.R;

import DB.DBFactory;

/**
 * Created by ab.mohammadi on 1/30/2017.
 */
public class Fragment_add_person extends Fragment {

    public static final String LAST_PERSON_SELECTED_ID = "LAST_PERSON_SELECTED_ID";
    public static final String LAST_PERSON_SELECTED_LOGO = "LAST_PERSON_SELECTED_LOGO";
    public static final String LAST_PERSON_SELECTED_NAME = "LAST_PERSON_SELECTED_NAME";

    TextView text_btn_add_person;
    Animation animationFade;
    EditText editPersonName;
    EditText editPersonMobile;
    String personName=  null;
    String personMobile = "0";
    DBFactory db;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_person , container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = new DBFactory(getActivity());

        animationFade = AnimationUtils.loadAnimation(getActivity() , R.anim.btn_click_fade);
        text_btn_add_person = (TextView) getActivity().findViewById(R.id.text_btn_add_person);
        editPersonName = (EditText) getActivity().findViewById(R.id.edit_add_person_name);
        editPersonMobile = (EditText) getActivity().findViewById(R.id.edit_add_person_mobile);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());




        text_btn_add_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animationFade);
                personMobile = editPersonMobile.getText().toString();
                personName = editPersonName.getText().toString();

                if(!(personName != null && personName.length()>0)){
                    Snackbar.make(v,R.string.error_message_personName_incorrect,Snackbar.LENGTH_SHORT).show();
                }else{
                    Person person = new Person();
                    person.setName(personName);
                    person.setMobile(personMobile);
                    long personIdInserted = db.insertPerson(person);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putLong(LAST_PERSON_SELECTED_ID , personIdInserted);
                    editor.putInt(LAST_PERSON_SELECTED_LOGO , R.drawable.add_person);
                    editor.putString(LAST_PERSON_SELECTED_NAME , personName);
                    editor.commit();

                    Snackbar.make(v,"added",Snackbar.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        ((ImageView) getActivity().findViewById(R.id.image_toolbarBottom_addPerson)).setImageResource(R.drawable.add_person_black);

        ((ImageView) getActivity().findViewById(R.id.image_toolbarBottom_addCard)).setImageResource(R.drawable.add_card);
        ((ImageView) getActivity().findViewById(R.id.image_toolbarBottom_home)).setImageResource(R.drawable.home);
        ((ImageView) getActivity().findViewById(R.id.image_toolbarBottom_search)).setImageResource(R.drawable.search);
        ((ImageView) getActivity().findViewById(R.id.image_toolbarBottom_accounts)).setImageResource(R.drawable.account);

    }
}
