package com.poepoemyintswe.weeklyenglish.ui.fragment;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.adapter.LessonAdapter;
import com.poepoemyintswe.weeklyenglish.api.LessonService;
import com.poepoemyintswe.weeklyenglish.model.Data;
import com.poepoemyintswe.weeklyenglish.model.Lesson;
import com.poepoemyintswe.weeklyenglish.ui.MainActivity;
import com.poepoemyintswe.weeklyenglish.ui.widget.DividerItemDecoration;
import com.poepoemyintswe.weeklyenglish.utils.CustomRestAdapter;
import com.poepoemyintswe.weeklyenglish.utils.NetworkConnectivityCheck;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.poepoemyintswe.weeklyenglish.utils.LogUtils.makeLogTag;

/**
 * Created by poepoe on 12/1/15.
 */
public class LessonFragment extends Fragment {

  private final String TAG = makeLogTag(LessonFragment.class);

  @InjectView(R.id.lesson_list) RecyclerView mRecyclerView;
  @InjectView(R.id.swipe_to_refresh) SwipeRefreshLayout mSwipeRefreshLayout;
  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.actionbar_title) TextView title;

  private MainActivity mActivity;
  private LessonAdapter adapter;

  public static LessonFragment getInstance() {
    return new LessonFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    mActivity = (MainActivity) getActivity();
    List<Lesson> lessons = new ArrayList<>();
    adapter = new LessonAdapter(lessons);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.inject(this, rootView);

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
      tintManager.setTintColor(getResources().getColor(R.color.primary_dark));
    }

    mActivity.swipeRefreshLayoutInit(mSwipeRefreshLayout);
    mActivity.recyclerViewInit(mRecyclerView);
    mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, null));
    mRecyclerView.setAdapter(adapter);
    return rootView;
  }

  @Override public void onStart() {
    super.onStart();
    if (NetworkConnectivityCheck.getInstance(mActivity).isConnected()) {
      mActivity.startRefreshing(mSwipeRefreshLayout);
      LessonService lessonService =
          CustomRestAdapter.getInstance(mActivity).normalRestAdapter().create(LessonService.class);
      lessonService.getLessons(new Callback<Data>() {
        @Override public void success(Data data, Response response) {
          mActivity.stopRefreshing(mSwipeRefreshLayout);
          adapter.addAll(data.lessons);
        }

        @Override public void failure(RetrofitError error) {

        }
      });
    }
  }
}
