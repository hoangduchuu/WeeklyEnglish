package com.poepoemyintswe.weeklyenglish.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.ui.fragment.LessonFragment;
import com.poepoemyintswe.weeklyenglish.ui.fragment.NavigationDrawerFragment;
import com.poepoemyintswe.weeklyenglish.ui.fragment.RandomFragment;

public class MainActivity extends BaseActivity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks {

  private Fragment fragment = null;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    NavigationDrawerFragment mNavigationDrawerFragment =
        (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(
            R.id.navigation_drawer);

    mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
        (DrawerLayout) findViewById(R.id.drawer_layout));

    fragment = RandomFragment.getInstance();
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_main;
  }

  @Override protected boolean getHomeUpEnabled() {
    return false;
  }

  @Override protected String getCustomTitle() {
    return null;
  }

  @Override protected boolean needToolbar() {
    return false;
  }

  @Override
  public void onNavigationDrawerItemSelected(int position) {
    switch (position) {
      case 0:
        fragment = RandomFragment.getInstance();
        break;
      case 1:
        fragment = LessonFragment.getInstance();
        break;
    }

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
      }
    }, 300);
  }


}
