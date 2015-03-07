package com.poepoemyintswe.weeklyenglish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectViews;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.model.Lesson;
import java.util.List;

/**
 * Created by poepoe on 8/3/15.
 */
public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

  private List<Lesson> lessons;

  public LessonAdapter(List<Lesson> lessons) {
    this.lessons = lessons;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View view =
        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_lesson, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder viewHolder, int i) {
    viewHolder.bindLesson(getItem(i));
  }

  @Override public int getItemCount() {
    return lessons.size();
  }

  private Lesson getItem(int position) {
    return lessons.get(position);
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @InjectViews({ R.id.id, R.id.title, R.id.explanation }) TextView[] textView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }

    public void bindLesson(Lesson lesson) {
      textView[0].setText(lesson.id);
      textView[1].setText(lesson.title);
      textView[2].setText(lesson.explanation);
    }

    @Override public void onClick(View v) {

    }
  }
}
