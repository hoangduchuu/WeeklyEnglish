package com.poepoemyintswe.weeklyenglish.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.api.LessonService;
import com.poepoemyintswe.weeklyenglish.db.Data;
import com.poepoemyintswe.weeklyenglish.db.Lesson;
import com.poepoemyintswe.weeklyenglish.utils.CustomRestAdapter;
import com.poepoemyintswe.weeklyenglish.utils.NetworkConnectivityCheck;
import com.poepoemyintswe.weeklyenglish.utils.SharePref;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import io.realm.Realm;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.poepoemyintswe.weeklyenglish.utils.LogUtils.LOGE;
import static com.poepoemyintswe.weeklyenglish.utils.LogUtils.makeLogTag;

public class InitActivity extends BaseActivity {

  private final String TAG = makeLogTag(InitActivity.class);

  @InjectView(R.id.progress_bar) ProgressWheel progressWheel;
  private Realm realm;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.inject(this);

    realm = Realm.getInstance(this);

    String[] colors = getPrimaryColor();
    progressWheel.setBarColor(Color.parseColor(colors[0]));

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

    if (SharePref.getInstance(this).isFirstTime()) {
      downloadData();
      SharePref.getInstance(this).noLongerFirstTime();
    } else {
      getCount();
      Intent mainIntent = new Intent(InitActivity.this, MainActivity.class);
      InitActivity.this.startActivity(mainIntent);
      InitActivity.this.finish();
    }
  }

  private void getCount() {
    if (NetworkConnectivityCheck.getInstance(this).isConnected()) {
      LessonService lessonService =
          CustomRestAdapter.getInstance(this).normalRestAdapter().create(LessonService.class);
      lessonService.getCount(new Callback<Integer>() {
        @Override public void success(Integer integer, Response response) {
          int count = realm.where(Lesson.class).findAll().size();
          if (integer > count) {
            downloadData();
          }
        }

        @Override public void failure(RetrofitError error) {

        }
      });
    }
  }

  private void downloadData() {
    if (NetworkConnectivityCheck.getInstance(this).isConnected()) {
      LessonService lessonService =
          CustomRestAdapter.getInstance(this).GsonRestAdapter().create(LessonService.class);
      lessonService.getLessons(new Callback<Data>() {
        @Override public void success(Data data, Response response) {
          realm.beginTransaction();
          realm.copyToRealm(data);
          realm.commitTransaction();
          LOGE(TAG, "Response == " + response.getStatus());
          Intent mainIntent = new Intent(InitActivity.this, MainActivity.class);
          InitActivity.this.startActivity(mainIntent);
          InitActivity.this.finish();
        }

        @Override public void failure(RetrofitError error) {

        }
      });
    }
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_init;
  }

  @Override protected boolean getHomeUpEnabled() {
    return false;
  }

  @Override protected String getCustomTitle() {
    return null;
  }

  @Override protected boolean needToolbar() {
    return false;
  }
}
