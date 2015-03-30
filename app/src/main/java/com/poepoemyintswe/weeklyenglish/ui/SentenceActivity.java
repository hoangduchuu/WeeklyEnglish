package com.poepoemyintswe.weeklyenglish.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.ui.fragment.SentenceFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class SentenceActivity extends BaseActivity {
  @InjectView(R.id.toolbar) Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ButterKnife.inject(this);
    if (savedInstanceState == null) {
      SentenceFragment sentenceFragment = new SentenceFragment();
      Bundle bundle = new Bundle();
      bundle.putString("title", getIntent().getStringExtra("title"));
      sentenceFragment.setArguments(bundle);
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.container, sentenceFragment)
          .commit();
    }

    String[] colors = getPrimaryColor();
    toolbar.setBackgroundColor(Color.parseColor(colors[0]));
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      SystemBarTintManager tintManager = new SystemBarTintManager(this);
      tintManager.setStatusBarTintEnabled(true);
      tintManager.setNavigationBarTintEnabled(true);
      tintManager.setTintColor(Color.parseColor(colors[1]));
    } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(Color.parseColor(colors[1]));
    }
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_sentence;
  }

  @Override protected boolean getHomeUpEnabled() {
    return true;
  }

  @Override protected String getCustomTitle() {
    return getIntent().getStringExtra("title");
  }

  @Override protected boolean needToolbar() {
    return true;
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_sentence, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
