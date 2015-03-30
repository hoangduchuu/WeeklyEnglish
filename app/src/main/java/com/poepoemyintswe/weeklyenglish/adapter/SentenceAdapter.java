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

import static com.poepoemyintswe.weeklyenglish.utils.LogUtils.LOGD;
import static com.poepoemyintswe.weeklyenglish.utils.LogUtils.LOGE;
import static com.poepoemyintswe.weeklyenglish.utils.LogUtils.makeLogTag;

/**
 * Created by poepoe on 27/3/15.
 */
public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.ViewHolder> {

  private RealmList<Sentence> sentences;
  private final String TAG = makeLogTag(SentenceAdapter.class);

  public SentenceAdapter() {
    setHasStableIds(true);
  }

  @Override public SentenceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sentence, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    LOGE(TAG, "onBindViewHolder");
    final Sentence sentence = sentences.get(position);
    LOGE(TAG, sentence.getEnglish());
    holder.eng.setText(sentence.getEnglish());
    holder.my.setText(sentence.getMyanmar());
  }

  @Override public int getItemCount() {
    return sentences.size();
  }

  public void setData(RealmList<Sentence> sentences) {
    this.sentences = sentences;
    LOGD(TAG, "Data size" + sentences.size());
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
