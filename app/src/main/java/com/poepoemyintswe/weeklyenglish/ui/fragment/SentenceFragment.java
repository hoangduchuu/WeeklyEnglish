package com.poepoemyintswe.weeklyenglish.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.adapter.SentenceAdapter;
import com.poepoemyintswe.weeklyenglish.db.Lesson;
import com.poepoemyintswe.weeklyenglish.db.Sentence;
import com.poepoemyintswe.weeklyenglish.ui.BaseActivity;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class SentenceFragment extends Fragment {
  @InjectView(R.id.sentence_list) RecyclerView sentenceList;
  private BaseActivity mActivity;
  private Realm realm;
  private SentenceAdapter adapter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivity = (BaseActivity) getActivity();
    realm = Realm.getInstance(mActivity);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_sentence, container, false);
    ButterKnife.inject(this, view);

    mActivity.recyclerViewInit(sentenceList);
    adapter = new SentenceAdapter(new SentenceAdapter.OnItemClickListener() {
      @Override public void onItemClick(Sentence sentence) {

      }
    });
    sentenceList.setAdapter(adapter);
    RealmResults<Lesson> results = realm.where(Lesson.class).equalTo("title", "title").findAll();
    adapter.setData(results.get(0).getSentences());
    return view;
  }
}
