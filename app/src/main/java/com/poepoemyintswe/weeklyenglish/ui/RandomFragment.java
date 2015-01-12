package com.poepoemyintswe.weeklyenglish.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poepoemyintswe.weeklyenglish.R;

/**
 * Created by poepoe on 12/1/15.
 */
public class RandomFragment extends Fragment {

    public RandomFragment(){

    }

    public static RandomFragment getInstance(){
        return new RandomFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }
}
