package com.poepoemyintswe.weeklyenglish.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.ui.fragment.LessonFragment;
import com.poepoemyintswe.weeklyenglish.ui.fragment.NavigationDrawerFragment;
import com.poepoemyintswe.weeklyenglish.ui.fragment.RandomFragment;
import java.util.Random;

public class MainActivity extends ActionBarActivity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks {

  private Fragment fragment = null;

  private final int random = new Random().nextInt(15);

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

  public void startRefreshing(SwipeRefreshLayout swipeRefreshLayout) {
    if (!swipeRefreshLayout.isRefreshing()) {
      swipeRefreshLayout.setRefreshing(true);
    }
  }

  public void stopRefreshing(SwipeRefreshLayout swipeRefreshLayout) {
    if (swipeRefreshLayout.isRefreshing()) {
      swipeRefreshLayout.setRefreshing(false);
    }
  }

  public void swipeRefreshLayoutInit(SwipeRefreshLayout mSwipeRefreshLayout) {
    mSwipeRefreshLayout.setColorSchemeResources(R.color.green, R.color.red, R.color.blue,
        R.color.yellow);
    TypedValue typed_value = new TypedValue();
    this.getTheme()
        .resolveAttribute(android.support.v7.appcompat.R.attr.actionBarSize, typed_value, true);
    mSwipeRefreshLayout.setProgressViewOffset(false, 0,
        getResources().getDimensionPixelSize(typed_value.resourceId));
  }

  public void recyclerViewInit(RecyclerView recyclerView) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    layoutManager.scrollToPosition(0);
    layoutManager.setSmoothScrollbarEnabled(true);
    recyclerView.setLayoutManager(layoutManager);
  }

  public String[] getPrimaryColor() {
    String[] myColors = getResources().getStringArray(R.array.primary_colors);
    String[] myColorDark = getResources().getStringArray(R.array.primary_dark_colors);
    String[] colors = new String[2];
    colors[0] = "#" + myColors[random];
    colors[1] = "#" + myColorDark[random];
    return colors;
  }
}
