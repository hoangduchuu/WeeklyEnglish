package com.poepoemyintswe.weeklyenglish.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.TextView;
import com.poepoemyintswe.weeklyenglish.R;
import java.util.Random;

/**
 * Created by poepoe on 7/3/15.
 */
public abstract class BaseActivity extends ActionBarActivity {

  private final int random = new Random().nextInt(15);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResource());

    if (needToolbar()) {
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      TextView title = (TextView) findViewById(R.id.actionbar_title);

      if (toolbar != null) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(getHomeUpEnabled());
        getSupportActionBar().setTitle("");
        title.setText(getCustomTitle());
        Typeface typeface = Typeface.createFromAsset(getAssets(), "good_times_rg.ttf");
        title.setTypeface(typeface);
      }
    }
  }

  protected abstract int getLayoutResource();

  protected abstract boolean getHomeUpEnabled();

  protected abstract String getCustomTitle();

  protected abstract boolean needToolbar();

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
