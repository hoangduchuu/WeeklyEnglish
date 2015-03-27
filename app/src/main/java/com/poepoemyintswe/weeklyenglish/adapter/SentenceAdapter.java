package com.poepoemyintswe.weeklyenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
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

  private final LayoutInflater inflater;
  private final OnItemClickListener listener;

  public SentenceAdapter(Context context, OnItemClickListener listener) {
    this.inflater = LayoutInflater.from(context);
    this.listener = listener;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(inflater.inflate(R.layout.row_sentence, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    final Sentence sentence = getItem(position);
    holder.eng.setText(sentence.getEnglish());
    holder.my.setText(sentence.getMyanmar());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        listener.onItemClick(sentence);
      }
    });
  }

  public static interface OnItemClickListener {
    public void onItemClick(Sentence sentence);
  }

  public static class ViewHolder extends RealmAdapter.ViewHolder {

    @InjectView(R.id.eng_sentence) TextView eng;
    @InjectView(R.id.my_sentence) TextView my;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }
}
