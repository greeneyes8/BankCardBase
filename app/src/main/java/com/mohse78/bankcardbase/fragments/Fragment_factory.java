package com.mohse78.bankcardbase.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

/**
 * Created by ab.mohammadi on 1/30/2017.
 */
public class Fragment_factory {


    public static void setFragemtnToFrame(int frameLayoutId , FragmentManager manager, Fragment fragment , boolean addToBackStack){
        FragmentTransaction transaction = manager.beginTransaction();

        if(addToBackStack)
            transaction.addToBackStack(fragment.toString());
        //transaction.setCustomAnimations(android.R.anim.fade_out , android.R.anim.fade_in);
        transaction.replace(frameLayoutId , fragment).commit();

    }
}
