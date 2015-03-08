package com.poepoemyintswe.weeklyenglish.ui.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.ui.MainActivity;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import mm.technomation.tmmtextutilities.mmtext;

import static com.poepoemyintswe.weeklyenglish.utils.LogUtils.makeLogTag;

/**
 * Created by poepoe on 8/3/15.
 */
public class RandomFragment extends Fragment {
  private final String TAG = makeLogTag(RandomFragment.class);

  @InjectView(R.id.swipe_to_refresh) SwipeRefreshLayout mSwipeRefreshLayout;
  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.actionbar_title) TextView title;
  @InjectView(R.id.eng) TextView eng;
  @InjectView(R.id.my) TextView my;

  private MainActivity mActivity;

  public static RandomFragment getInstance() {
    return new RandomFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    mActivity = (MainActivity) getActivity();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_random, container, false);
    ButterKnife.inject(this, rootView);

    String[] colors = mActivity.getPrimaryColor();
    toolbar.setBackgroundColor(Color.parseColor(colors[0]));
    mActivity.setSupportActionBar(toolbar);
    ActionBar actionBar = mActivity.getSupportActionBar();
    actionBar.setTitle("");
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
    title.setText("Home");
    Typeface typeface = Typeface.createFromAsset(mActivity.getAssets(), "good_times_rg.ttf");
    title.setTypeface(typeface);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
      tintManager.setStatusBarTintEnabled(true);
      tintManager.setNavigationBarTintEnabled(true);
      tintManager.setTintColor(Color.parseColor(colors[1]));
    } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
      Window window = mActivity.getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(Color.parseColor(colors[1]));
    }

    mActivity.swipeRefreshLayoutInit(mSwipeRefreshLayout);
    return rootView;
  }

  @Override public void onStart() {
    super.onStart();
    eng.setText("I wanna go to Mandalay");
    my.setText("ကျွန်တော်မန္တလေးသွားချင်တယ်။");
    mmtext.prepareView(mActivity, my, mmtext.TEXT_UNICODE, true, true);
  }
}
