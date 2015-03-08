package com.poepoemyintswe.weeklyenglish.ui;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.poepoemyintswe.weeklyenglish.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by poepoe on 7/3/15.
 */
public abstract class BaseActivity extends ActionBarActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResource());
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
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      SystemBarTintManager tintManager = new SystemBarTintManager(this);
      tintManager.setStatusBarTintEnabled(true);
      tintManager.setNavigationBarTintEnabled(true);
      tintManager.setTintColor(getResources().getColor(R.color.primary_dark));
    }
  }

  protected abstract int getLayoutResource();

  protected abstract boolean getHomeUpEnabled();

  protected abstract String getCustomTitle();


}
