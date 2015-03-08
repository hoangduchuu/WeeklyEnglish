package com.poepoemyintswe.weeklyenglish.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.ui.fragment.LessonFragment;
import com.poepoemyintswe.weeklyenglish.ui.fragment.NavigationDrawerFragment;

public class MainActivity extends ActionBarActivity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks {

  private Fragment fragment = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    NavigationDrawerFragment mNavigationDrawerFragment =
        (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(
            R.id.navigation_drawer);

    mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
        (DrawerLayout) findViewById(R.id.drawer_layout));

    fragment = LessonFragment.getInstance();
  }

  @Override
  public void onNavigationDrawerItemSelected(int position) {
    switch (position) {
      case 0:
        fragment = LessonFragment.getInstance();
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
