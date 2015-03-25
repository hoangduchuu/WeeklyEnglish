package com.poepoemyintswe.weeklyenglish.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.ui.SentenceActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class SentenceFragment extends Fragment {
  @InjectView(R.id.sentence_list) ListView sentenceList;
  private SentenceActivity mActivity;

  public SentenceFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivity = (SentenceActivity) getActivity();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_sentence, container, false);
    ButterKnife.inject(this, mActivity);
    return view;
  }
}
