package com.poepoemyintswe.weeklyenglish.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poepoemyintswe.weeklyenglish.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SentenceActivityFragment extends Fragment {

  public SentenceActivityFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_sentence, container, false);
  }
}
