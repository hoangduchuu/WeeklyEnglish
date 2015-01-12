package com.poepoemyintswe.weeklyenglish;

import android.os.Bundle;

import com.poepoemyintswe.weeklyenglish.ui.RandomFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, RandomFragment.getInstance())
                    .commit();
        }
    }

    @Override
    protected boolean getHomeEnable() {
        return false;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected String getToolbarTitle() {
        return getText(R.string.app_name).toString();
    }


}
