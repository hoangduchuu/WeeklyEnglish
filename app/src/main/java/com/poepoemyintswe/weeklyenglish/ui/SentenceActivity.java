package com.poepoemyintswe.weeklyenglish.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.poepoemyintswe.weeklyenglish.R;

public class SentenceActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_sentence;
  }

  @Override protected boolean getHomeUpEnabled() {
    return true;
  }

  @Override protected String getCustomTitle() {
    Intent intent = getIntent();
    return intent.getStringExtra("title");
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
