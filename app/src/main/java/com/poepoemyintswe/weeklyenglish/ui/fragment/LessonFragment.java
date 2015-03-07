package com.poepoemyintswe.weeklyenglish.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.api.LessonService;
import com.poepoemyintswe.weeklyenglish.model.Data;
import com.poepoemyintswe.weeklyenglish.ui.BaseActivity;
import com.poepoemyintswe.weeklyenglish.utils.CustomRestAdapter;
import com.poepoemyintswe.weeklyenglish.utils.NetworkConnectivityCheck;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.poepoemyintswe.weeklyenglish.utils.LogUtils.LOGD;
import static com.poepoemyintswe.weeklyenglish.utils.LogUtils.makeLogTag;

/**
 * Created by poepoe on 12/1/15.
 */
public class LessonFragment extends Fragment {

  private BaseActivity mActivity;
  private final String TAG = makeLogTag(LessonFragment.class);

  public LessonFragment() {

  }

  public static LessonFragment getInstance() {
    return new LessonFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    mActivity = (BaseActivity) getActivity();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.inject(this, rootView);
    return rootView;
  }

  @Override public void onStart() {
    super.onStart();
    if (NetworkConnectivityCheck.getInstance(mActivity).isConnected()) {
      LessonService lessonService =
          CustomRestAdapter.getInstance(mActivity).normalRestAdapter().create(LessonService.class);
      lessonService.getLessons(new Callback<Data>() {
        @Override public void success(Data data, Response response) {
          LOGD(TAG, "Response = " + response.getStatus());
        }

        @Override public void failure(RetrofitError error) {

        }
      });
    }
  }
}
