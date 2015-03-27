package com.poepoemyintswe.weeklyenglish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.db.Sentence;
import io.realm.RealmList;

/**
 * Created by poepoe on 27/3/15.
 */
public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.ViewHolder> {

  private final OnItemClickListener listener;
  private RealmList<Sentence> sentences;

  public SentenceAdapter(OnItemClickListener listener) {
    setHasStableIds(true);
    this.listener = listener;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sentence, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    final Sentence sentence = sentences.get(position);
    holder.eng.setText(sentence.getEnglish());
    holder.my.setText(sentence.getMyanmar());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        listener.onItemClick(sentence);
      }
    });
  }

  @Override public int getItemCount() {
    return 0;
  }

  public void setData(RealmList<Sentence> sentences) {
    this.sentences = sentences;
  }

  public static interface OnItemClickListener {
    public void onItemClick(Sentence sentence);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.eng_sentence) TextView eng;
    @InjectView(R.id.my_sentence) TextView my;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }
}
