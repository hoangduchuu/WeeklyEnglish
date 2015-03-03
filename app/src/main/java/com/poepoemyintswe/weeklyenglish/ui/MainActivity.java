package com.poepoemyintswe.weeklyenglish.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.ui.fragment.RandomFragment;

public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, RandomFragment.getInstance())
          .commit();
    }
  }
}
