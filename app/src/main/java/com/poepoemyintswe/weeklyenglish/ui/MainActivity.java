package com.poepoemyintswe.weeklyenglish.ui;

import android.os.Bundle;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.ui.fragment.LessonFragment;

public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, LessonFragment.getInstance())
          .commit();
    }
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_main;
  }

  @Override protected boolean getHomeUpEnabled() {
    return false;
  }

  @Override protected String getCustomTitle() {
    return getString(R.string.app_name);
  }
}
