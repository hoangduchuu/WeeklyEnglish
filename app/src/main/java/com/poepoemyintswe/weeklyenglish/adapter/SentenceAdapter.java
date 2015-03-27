package com.poepoemyintswe.weeklyenglish.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.db.Sentence;

/**
 * Created by poepoe on 27/3/15.
 */
public class SentenceAdapter extends RealmAdapter<Sentence, SentenceAdapter.ViewHolder> {
  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {

  }

  public static class ViewHolder extends RealmAdapter.ViewHolder {

    @InjectView(R.id.id) TextView id;
    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.explanation) TextView explanation;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }
}
