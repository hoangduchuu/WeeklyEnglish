package com.poepoemyintswe.weeklyenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.db.Lesson;

/**
 * Created by poepoe on 8/3/15.
 */
public class LessonAdapter extends RealmAdapter<Lesson, LessonAdapter.ViewHolder> {

  private final LayoutInflater inflater;
  private final OnItemClickListener listener;

  public LessonAdapter(Context context, OnItemClickListener listener) {
    this.inflater = LayoutInflater.from(context);
    this.listener = listener;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    return new ViewHolder(inflater.inflate(R.layout.row_lesson, viewGroup, false));
  }

  @Override public void onBindViewHolder(ViewHolder viewHolder, int i) {
    final Lesson lesson = getItem(i);
    viewHolder.id.setText(Integer.toString(lesson.getId()));
    viewHolder.title.setText(lesson.getTitle());
    viewHolder.explanation.setText(lesson.getExplanation());
    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        listener.onItemClick(lesson);
      }
    });
  }

  public static interface OnItemClickListener {
    public void onItemClick(Lesson lesson);
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
