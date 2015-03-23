package com.poepoemyintswe.weeklyenglish.ui.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.db.Sentence;
import com.poepoemyintswe.weeklyenglish.ui.BaseActivity;
import com.poepoemyintswe.weeklyenglish.ui.widget.SecretTextView;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import java.util.Locale;
import java.util.Random;
import mm.technomation.tmmtextutilities.mmtext;

import static com.poepoemyintswe.weeklyenglish.utils.LogUtils.makeLogTag;

/**
 * Created by poepoe on 8/3/15.
 */
public class RandomFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private final String TAG = makeLogTag(RandomFragment.class);

  @InjectView(R.id.swipe_to_refresh) SwipeRefreshLayout mSwipeRefreshLayout;
  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.actionbar_title) TextView title;
  @InjectView(R.id.eng) SecretTextView eng;
  @InjectView(R.id.my) SecretTextView my;
  @InjectView(R.id.random_bg) LinearLayout bg;

  private BaseActivity mActivity;
  private RealmResults<Sentence> results;
  TextToSpeech mTextToSpeech;

  public static RandomFragment getInstance() {
    return new RandomFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    mActivity = (BaseActivity) getActivity();
    mTextToSpeech = new TextToSpeech(mActivity, new TextToSpeech.OnInitListener() {
      @Override public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
          mTextToSpeech.setLanguage(Locale.UK);
        }
      }
    });
    Realm realm = Realm.getInstance(mActivity);
    RealmQuery<Sentence> realmQuery = realm.where(Sentence.class);
    results = realmQuery.findAll();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_random, container, false);
    ButterKnife.inject(this, rootView);
    mSwipeRefreshLayout.setOnRefreshListener(this);
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

    bg.setBackgroundColor(Color.parseColor(colors[0]));
    mActivity.swipeRefreshLayoutInit(mSwipeRefreshLayout);
    return rootView;
  }

  @Override public void onPause() {
    if (mTextToSpeech != null) {
      mTextToSpeech.stop();
      mTextToSpeech.shutdown();
    }
    super.onPause();
  }

  @Override public void onStart() {
    super.onStart();
    showRandomSentence();
  }

  @OnClick(R.id.play) void speak() {
    String toSpeak = eng.getText().toString();
    mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
  }

  private void showRandomSentence() {
    int random = new Random().nextInt(results.size());
    eng.setmDuration(2000);
    my.setmDuration(2000);
    eng.show();
    my.show();
    eng.setText(results.get(random).getEnglish());
    my.setText(results.get(random).getMyanmar());
    mmtext.prepareView(mActivity, my, mmtext.TEXT_UNICODE, true, true);
    new Handler().postDelayed(new Runnable() {
      public void run() {
        if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
      }
    }, 2000);
  }

  @Override public void onRefresh() {
    showRandomSentence();
  }
}
