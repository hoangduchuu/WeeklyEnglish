package com.poepoemyintswe.weeklyenglish.ui.fragment;

import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.adapter.LessonAdapter;
import com.poepoemyintswe.weeklyenglish.db.Lesson;
import com.poepoemyintswe.weeklyenglish.ui.BaseActivity;
import com.poepoemyintswe.weeklyenglish.ui.SentenceActivity;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import io.realm.Realm;

import static com.poepoemyintswe.weeklyenglish.utils.LogUtils.makeLogTag;

/**
 * Created by poepoe on 12/1/15.
 */
public class LessonFragment extends Fragment {

  private final String TAG = makeLogTag(LessonFragment.class);

  @InjectView(R.id.lesson_list) ListView mListView;
  @InjectView(R.id.swipe_to_refresh) SwipeRefreshLayout mSwipeRefreshLayout;
  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.actionbar_title) TextView title;

  private BaseActivity mActivity;
  private LessonAdapter adapter;
  private Realm realm;

  public static LessonFragment getInstance() {
    return new LessonFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    mActivity = (BaseActivity) getActivity();
    realm = Realm.getInstance(mActivity);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_lesson, container, false);
    ButterKnife.inject(this, rootView);

    String[] colors = mActivity.getPrimaryColor();
    toolbar.setBackgroundColor(Color.parseColor(colors[0]));
    mActivity.setSupportActionBar(toolbar);
    ActionBar actionBar = mActivity.getSupportActionBar();
    actionBar.setTitle("");
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
    title.setText("Lessons");
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

    adapter = new LessonAdapter(mActivity, new LessonAdapter.OnItemClickListener() {
      @Override public void onItemClick(Lesson lesson) {
        Intent intent = new Intent(mActivity, SentenceActivity.class);
        intent.putExtra("title", lesson.getTitle());
        startActivity(intent);
      }
    });

    mListView.setAdapter(adapter);
    adapter.setResults(realm.where(Lesson.class).findAll());
    return rootView;
  }
}
