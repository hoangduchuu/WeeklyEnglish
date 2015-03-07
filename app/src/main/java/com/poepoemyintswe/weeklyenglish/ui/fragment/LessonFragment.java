package com.poepoemyintswe.weeklyenglish.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.adapter.LessonAdapter;
import com.poepoemyintswe.weeklyenglish.api.LessonService;
import com.poepoemyintswe.weeklyenglish.model.Data;
import com.poepoemyintswe.weeklyenglish.model.Lesson;
import com.poepoemyintswe.weeklyenglish.ui.BaseActivity;
import com.poepoemyintswe.weeklyenglish.ui.widget.DividerItemDecoration;
import com.poepoemyintswe.weeklyenglish.utils.CustomRestAdapter;
import com.poepoemyintswe.weeklyenglish.utils.NetworkConnectivityCheck;
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

  private BaseActivity mActivity;
  private final String TAG = makeLogTag(LessonFragment.class);
  @InjectView(R.id.lesson_list) RecyclerView mRecyclerView;
  @InjectView(R.id.swipe_to_refresh) SwipeRefreshLayout mSwipeRefreshLayout;

  private LessonAdapter adapter;

  public static LessonFragment getInstance() {
    return new LessonFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    mActivity = (BaseActivity) getActivity();
    List<Lesson> lessons = new ArrayList<>();
    adapter = new LessonAdapter(lessons);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.inject(this, rootView);
    mActivity.swipeRefreshLayoutInit(mSwipeRefreshLayout);
    mActivity.recyclerViewInit(mRecyclerView);
    mRecyclerView.addItemDecoration(
        new DividerItemDecoration(mActivity.getResources().getDrawable(R.drawable.divider)));
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
